package model.services.productservice;

import java.util.ArrayList;
import java.util.List;

import model.entities.Product;
import model.services.exceptionservice.ProductServiceException;

public class ProductService {

	List<Product> listP = new ArrayList<>();

	public void add(Product product) {
		listP.add(product);
	}

	public String listProducts() throws ProductServiceException {
		if (listP.isEmpty()) {
			throw new ProductServiceException("Lista nao pode ser vazia!");
		}
		StringBuilder sb = new StringBuilder();
		for (Product p : listP) {
			sb.append(p.toString()).append("\n");
		}
		return sb.toString();
	}

	public Product findProduct(String name) throws ProductServiceException {
		if (listP.isEmpty()) {
			throw new ProductServiceException("Produto nao encontrado!");
		}
		for (Product p : listP) {
			if (name.trim().equalsIgnoreCase(p.getName())) {
				return p;
			}
		}
		throw new ProductServiceException("Produto nao encontrado.");
	}

	public Double totalSum() {
		double sum = 0.0;
		for (Product p : listP) {
			sum += p.getPrice();
		}
		return sum;
	}
}
