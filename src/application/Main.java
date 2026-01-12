package application;

import java.util.Locale;
import java.util.Scanner;

import model.entities.Product;
import model.services.ProductService;

public class Main {
	public static void main(String[] args) throws Exception {
		Locale.setDefault(Locale.US);
		ProductService pService = new ProductService();

		try (Scanner input = new Scanner(System.in)) {

			int option = 5;
			while (option != 0) {
				System.out.println();
				System.out.println("1. Adicionar produto.");
				System.out.println("2. Listar produtos.");
				System.out.println("3. Encontrado produto.");
				System.out.println("4. Somar valor total.");
				System.out.println("0. Sair");
				System.out.print("\nEscolha uma das opções a cima: ");
				option = input.nextInt();
				System.out.println();
				switch (option) {
				case 1:
					char optionChar = 0;
					do {
						System.out.println("Insira os dados do produto.");
						System.out.print("Id: ");
						Integer id = input.nextInt();
						System.out.print("Nome: ");
						String name = input.next();
						System.out.print("Preco: ");
						Double price = input.nextDouble();
						input.nextLine();
						Product product = new Product(id, name, price);
						pService.add(product);
						System.out.println("\nDeseja adicionar outro produto: S/N");
						optionChar = input.next().charAt(0);
						optionChar = Character.toUpperCase(optionChar);
					} while (optionChar == 'S');
					break;
				case 2:
					System.out.println(pService.listProcucts());
					break;
				case 3:
					System.out.print("\nInsira o nome do produto desejado: ");
					String searchName = input.next();
					Product p = pService.findProduct(searchName);
					System.out.println(p.toString());
					break;
				case 4:
					System.out.printf("O total dos produtos é: $%.2f\n", pService.TotalSum());
					break;
				case 0:
					System.out.println("Saindo..");
				default:
				}

			}
		}
	}
}
