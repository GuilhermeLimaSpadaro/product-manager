package application;

import java.util.Locale;
import java.util.Scanner;

import model.entities.Product;
import model.services.ProductService;

public class Main {

    //*********//
	public static void main(String[] args) throws Exception {
		Locale.setDefault(Locale.US);
		ProductService pService = new ProductService();
		try (Scanner input = new Scanner(System.in)) {
			boolean running = true;
			while (running) {
				printMenu();
				String option = input.nextLine();
				int op;
				try {
					op = Integer.parseInt(option);
				} catch (Exception e) {
					System.out.println("Opcao invalida! Digite um numero.");
					continue;
				}

				switch (op) {
				case 1:
					addProducts(input, pService);
					break;
				case 2:
					listProducts(pService);
					break;
				case 3:
					findProduct(input, pService);
					break;
				case 4:
					showTotal(pService);
					break;
				case 0:
					System.out.println("Saindo..");
					running = false;
					break;
				default:
					System.out.println("Opção invalida!");
				}

			}
		} catch (Exception e) {
			System.err.println("Erro: " + e.getMessage());
		}
	}

	// ************//

	public static void printMenu() {
		System.out.println(
				"=========================\r\n" + "       MENU PRINCIPAL\r\n" + "=========================\r\n" + "");
		System.out.println("1. Adicionar produto.");
		System.out.println("2. Listar produtos.");
		System.out.println("3. Encontrado produto.");
		System.out.println("4. Somar valor total.");
		System.out.println("0. Sair");
		System.out.print("\nEscolha uma das opções a cima: ");
	}

	private static char askContinue(Scanner input) {
		while (true) {
			String line = input.nextLine().trim().toUpperCase();
			if (!line.isEmpty() && (line.charAt(0) == 'S' || line.charAt(0) == 'N')) {
				return line.charAt(0);
			}
			System.out.print("Entrada inválida! Digite S ou N: ");
		}
	}

	private static void addProducts(Scanner input, ProductService service) throws Exception {
		char optionChar;
		do {
			System.out.println("Insira os dados do produto.");
			System.out.print("Id: ");
			int id = intValidation(input);
			System.out.print("Nome: ");
			String name = input.nextLine();
			System.out.print("Preco: ");
			double price = doubleValidation(input);
			Product product = new Product(id, name, price);
			service.add(product);
			System.out.println("\nDeseja adicionar outro produto: S/N");
			optionChar = askContinue(input);
		} while (optionChar == 'S');
	}

	private static double doubleValidation(Scanner input) {
		while (true) {
			try {
				String option = input.nextLine().trim();
				return Double.parseDouble(option);
			} catch (NumberFormatException e) {
				System.out.println("Opcao invalida! Digite um numero.");
			}
		}
	}

	private static int intValidation(Scanner input) {
		while (true) {
			try {
				String option = input.nextLine().trim();
				return Integer.parseInt(option);
			} catch (NumberFormatException e) {
				System.out.println("Opcao invalida! Digite um numero.");
			}
		}
	}

	private static void listProducts(ProductService service) {
		try {
			System.out.println(service.listProducts());
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	private static void findProduct(Scanner input, ProductService service) {
		System.out.print("\nInsira o nome do produto desejado: ");
		String searchName = input.nextLine();
		try {
			Product p = service.findProduct(searchName);
			System.out.println(p);
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	private static void showTotal(ProductService service) {
		System.out.printf("O total dos produtos é: $%.2f\n", service.totalSum());
	}
}
