package br.com.desafio.tabela.fipe.App;

import br.com.desafio.tabela.fipe.Model.DataTableModel;
import br.com.desafio.tabela.fipe.Model.ValueFipeModel;
import br.com.desafio.tabela.fipe.Service.ConsoleMenuService;
import br.com.desafio.tabela.fipe.Service.ConsumptionApiService;
import br.com.desafio.tabela.fipe.Service.DataConvertService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Scanner;

public class FipeApplication {

    Scanner scanner = new Scanner(System.in);
    private ConsoleMenuService menu = new ConsoleMenuService(this);
    private ConsumptionApiService consumir = new ConsumptionApiService();
    private DataConvertService conversor = new DataConvertService();

    public void run() throws JsonProcessingException {
        menu.exibirMenu();
    }

    public void consultarFipe(String tipoVeiculo) throws JsonProcessingException {
        String enderecoBase = "https://parallelum.com.br/fipe/api/v1/";
        String endereco;

        switch (tipoVeiculo.toLowerCase()) {
            case "carros":
                endereco = enderecoBase + "carros/marcas";
                break;
            case "motos":
                endereco = enderecoBase + "motos/marcas";
                break;
            case "caminhoes":
                endereco = enderecoBase + "caminhoes/marcas";
                break;
            default:
                System.out.println("Tipo de veículo inválido!");
                return;
        }

        System.out.println("Consultando FIPE para: " + tipoVeiculo);

        String json = consumir.obterDados(endereco);
        DataTableModel[] marcas = conversor.getData(json, DataTableModel[].class);

        System.out.println("+--------+------------------+");
        for (DataTableModel marca : marcas) {
            System.out.printf("Código -> %s | Marca: %s\n", marca.codigo(), marca.nome());
        }
        System.out.println("+--------+------------------+");

        System.out.println("Digite o código da marca para consulta dos modelos:");
        int codigoMarca = scanner.nextInt();
        scanner.nextLine();

        String enderecoModelos = enderecoBase + tipoVeiculo.toLowerCase() + "/marcas/" + codigoMarca + "/modelos";

        System.out.println("Consultando modelos...");
        String jsonModelos = consumir.obterDados(enderecoModelos);

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode raiz = mapper.readTree(jsonModelos);
            JsonNode modelosArray = raiz.get("modelos");

            DataTableModel[] modelos = mapper.treeToValue(modelosArray, DataTableModel[].class);

            System.out.println("+--------+------------------+");
            for (DataTableModel modelo : modelos) {
                System.out.printf("Código -> %s | Modelo -> %s\n", modelo.codigo(), modelo.nome());
            }
            System.out.println("+--------+------------------+");

            System.out.println("Digite o código do modelo que deseja consultar os anos:");
            int codigoModelo = scanner.nextInt();
            scanner.nextLine();

            String enderecoAnos = enderecoBase + tipoVeiculo.toLowerCase() + "/marcas/" + codigoMarca + "/modelos/" + codigoModelo + "/anos";

            System.out.println("Consultando anos disponíveis...");
            String jsonAnos = consumir.obterDados(enderecoAnos);

            DataTableModel[] anos = mapper.readValue(jsonAnos, DataTableModel[].class);

            System.out.println("+--------+------------------+");
            for (DataTableModel ano : anos) {
                System.out.printf("Código -> %s | Ano -> %s\n", ano.codigo(), ano.nome());
            }
            System.out.println("+--------+------------------+");

            System.out.println("Digite o código do ano para consultar o valor FIPE:");
            String codigoAno = scanner.nextLine();

            String enderecoValor = enderecoBase + tipoVeiculo.toLowerCase() + "/marcas/" + codigoMarca + "/modelos/" + codigoModelo + "/anos/" + codigoAno;

            System.out.println("Consultando valor FIPE...");
            String jsonValor = consumir.obterDados(enderecoValor);

            ValueFipeModel veiculo = mapper.readValue(jsonValor, ValueFipeModel.class);

            System.out.println("+---------------------------+");
            System.out.println("Modelo: " + veiculo.Modelo());
            System.out.println("Valor: " + veiculo.Valor());
            System.out.println("Ano: " + veiculo.AnoModelo());
            System.out.println("Combustível: " + veiculo.Combustivel());
            System.out.println("+---------------------------+");

        } catch (Exception e) {
            throw new RuntimeException("Erro ao processar JSON", e);
        } finally {
            scanner.close();
            System.out.println("Api precessada corretamente, Finalizando Aplicação...");
        }
    }
}
