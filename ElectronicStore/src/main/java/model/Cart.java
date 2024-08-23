package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Cart {
	
	private List<Product> products;
	private float bill;
	
	public Cart() {
		products = new ArrayList<Product>();
		bill = 0;
	}
	
	public Cart(List<Product> products) {
		this.products = products;
	}
	
	public List<Product> getItems() {return this.products;}
	
	public float getBill() {return this.bill;}
	
	public void clear() {
		products.clear();
		bill = 0;
	}
	
	public void add(Product product) {
		if (contains(product) != null)
			return;
		else {
			products.add(product);
			bill += product.getPrice()*product.getQty();
		}
	}
	
	public void add(List<Product> products) {
		for (Product product: products)
			add(product);
	}
	
	public void remove(long id) {
		Iterator<Product> iter = products.iterator();
		while (iter.hasNext()) {
			Product product = (Product) iter.next();
			if (product.getId() == id) {
				iter.remove();
				bill -= product.getPrice()*product.getQty();
				return;
			}
		}
	}
	
	
	public void remove(Product product) {remove(product.getId());}
	
	public void update(int id, int qty) {
		for (Product product: products)
			if (product.getId() == id) {
				bill -= product.getPrice()*product.getQty();
				product.setQty(qty);
				bill += product.getPrice()*product.getQty();
			}
	}
	
	public Product contains(long id) {
		for (Product i: products)
			if (i.getId() == id)
				return i;
		return null;
	}
	
	public Product contains(Product product) {
		for (Product i: products)
			if (i.equals(product))
				return i;
		return null;
	}
	
	public String toString() {return "Size of Cart: " + products.size() + ", Total Bill: $" + bill;}
}
