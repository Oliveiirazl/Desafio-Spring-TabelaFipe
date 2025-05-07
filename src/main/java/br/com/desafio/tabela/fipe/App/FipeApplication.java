package br.com.desafio.tabela.fipe.App;

import br.com.desafio.tabela.fipe.Model.DataTableModel;
import br.com.desafio.tabela.fipe.Service.ConsoleMenuService;
import br.com.desafio.tabela.fipe.Service.ConsumptionApiService;
import br.com.desafio.tabela.fipe.Service.DataConvertService;

import java.util.List;

public class FipeApplication {

    private ConsoleMenuService menu = new ConsoleMenuService(this);
    private ConsumptionApiService consumir = new ConsumptionApiService();
    private DataConvertService conversor = new DataConvertService();

    public void run() {
        menu.exibirMenu();
    }

    public void consultarFipe(String tipoVeiculo) {
        String enderecoBase = "https://parallelum.com.br/fipe/api/v1/";
        String endereco;

        switch (tipoVeiculo.toLowerCase()) {
            case "carro":
                endereco = enderecoBase + "carros/marcas";
                break;
            case "moto":
                endereco = enderecoBase + "motos/marcas";
                break;
            case "caminhão":
                endereco = enderecoBase + "caminhoes/marcas";
                break;
            default:
                System.out.println("Tipo de veículo inválido!");
                return;
        }

        System.out.println("Consultando FIPE para: " + tipoVeiculo);


        String json = consumir.obterDados(endereco);


        DataTableModel[] dados = conversor.getData(json, DataTableModel[].class);


        System.out.println("Digite um dos codigos para acessar os modelos");
        for (DataTableModel marca : dados) {
            System.out.printf("Código -> %s | Modelo: %s\n", marca.codigo(), marca.nome());
        }
        System.out.println("+--------+------------------+");
    }
}
