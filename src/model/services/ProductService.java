package model.services;

import java.util.ArrayList;
import java.util.List;

import model.entities.Product;
import model.exceptions.ProductServiceException;

public class ProductService {

	List<Product> listP = new ArrayList<>();

	public void add(Product product) throws ProductServiceException {
		for (Product p : listP) {
			if (p.getId().equals(product.getId())) {
				throw new ProductServiceException("Produto ja cadastrado.");
			}
		}
		listP.add(product);
	}

	public void remove(Product product) throws ProductServiceException {
		Product findProduct = null;
		for (Product p : listP) {
			if (p.getId().equals(product.getId())) {
				findProduct = p;
			}
		}
		if (findProduct == null) {
			throw new ProductServiceException("Produto nao existe na lista.");
		}
		listP.remove(findProduct);
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
			throw new ProductServiceException("Lista não pode ser vazia");
		}
		for (Product p : listP) {
			if (name.trim().equalsIgnoreCase(p.getName())) {
				return p;
			}
		}
		throw new ProductServiceException("Produto nao encontrado.");
	}

	public void updateProduct(Product product, String name, double price) throws ProductServiceException {
		Product findProduct = null;
		if (name == null || name.isEmpty()) {
			throw new ProductServiceException("Nome invalido.");
		}
		if (price < 0) {
			throw new ProductServiceException("Preco invalido.");
		}
		for (Product p : listP) {
			if (p.getId().equals(product.getId())) {
				findProduct = p;
				findProduct.setName(name);
				findProduct.setPrice(price);
				return;
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
