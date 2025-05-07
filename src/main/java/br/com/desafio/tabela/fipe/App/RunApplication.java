package br.com.desafio.tabela.fipe.App;

import br.com.desafio.tabela.fipe.Service.ConsoleMenuService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RunApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(RunApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		FipeApplication fipeApplication = new FipeApplication();
		ConsoleMenuService menu = new ConsoleMenuService(fipeApplication);
		menu.exibirMenu();
	}
}
