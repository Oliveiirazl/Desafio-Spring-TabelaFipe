package br.com.desafio.tabela.fipe.Service;

import br.com.desafio.tabela.fipe.App.FipeApplication;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Scanner;

public class ConsoleMenuService {

    private final FipeApplication fipeApplication;

    public ConsoleMenuService(FipeApplication fipeApplication) {
        this.fipeApplication = fipeApplication;
    }

    public void exibirMenu() throws JsonProcessingException {
        Scanner scanner = new Scanner(System.in);
        int menu;

        do {
            System.out.println("=== MENU INICIAL ===");
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Carro");
            System.out.println("2 - Moto");
            System.out.println("3 - Caminhão");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida. Digite um número.");
                scanner.next();
                System.out.print("Opção: ");
            }

            menu = scanner.nextInt();

            switch (menu) {
                case 1:
                    fipeApplication.consultarFipe("carros");
                    menu = 0;
                    break;
                case 2:
                    fipeApplication.consultarFipe("motos");
                    menu = 0;
                    break;
                case 3:
                    fipeApplication.consultarFipe("caminhao");
                    menu = 0;
                    break;
                case 0:
                    System.out.println("Encerrando aplicação...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

            System.out.println();

        } while (menu != 0);

        scanner.close();
    }
}


