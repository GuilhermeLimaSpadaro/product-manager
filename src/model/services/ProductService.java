package model.services;

import java.util.ArrayList;
import java.util.List;

import model.entities.Product;

public class ProductService {

	List<Product> listP = new ArrayList<>();

	public void add(Product product) {
		listP.add(product);
	}

	public String listProcucts() {
		if (listP.isEmpty()) {
			throw new NullPointerException("Lista não pode ser vazia!");
		}
		String str = "";
		for (Product p : listP) {
			str += p.toString();
		}
		return str;
	}

	public Product findProduct(String name) throws Exception {
		if (listP.isEmpty()) {
			throw new NullPointerException("Lista nao pode ser vazia");
		}
		for (Product p : listP) {
			if (name.trim().equalsIgnoreCase(p.getName())) {
				return p;
			}
		}
		return null;
	}

	public Double TotalSum() {
		Double sum = 0.0;
		for (Product p : listP) {
			sum += p.getPrice();
		}
		return sum;
	}
}
