package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Cart {
	
	private List<Product> products;
	private float bill;
	
	private final float tax = 0.13f;
	private final float shipping = 0.0f;
	
	public Cart() {
		products = new ArrayList<Product>();
		bill = 0;
	}
	
	public Cart(List<Product> products) {
		this.products = products;
		updateBill();
	}
	
	private void updateBill() {
		bill = 0;
		for (Product product: products) {
			bill += product.getPrice() * product.getQty();
		}
		bill = (float) (Math.round(bill * 100.0) / 100.0);

		
	}

	public List<Product> getProducts() {return this.products;}
	
	public float getBill() {return (float) (Math.round(this.bill*100.0)/100.0);}
	
	public void clear() {
		products.clear();
		bill = 0;
	}
	
	public void add(Product product) {
		Product itemInCart = contains (product);
		
		if (itemInCart != null)
			update(product.getId(), product.getQty());
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
				updateBill();
				return;
			}
		}
	}
	
	
	public void remove(Product product) {remove(product.getId());}
	
	public void update(Long id, int qty) {
		for (Product product: products)
			if (product.getId() == id) {
				bill -= product.getPrice()*product.getQty();
				product.setQty(qty);
				bill += product.getPrice()*product.getQty();
				return;
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
	
	public float calculateTax() {
		return (float) (Math.round((bill * tax)*100.0)/100.0);
	}
	
	public float calculateTotal() {
		return  (float) (Math.round((bill + calculateTax() + shipping)*100.0)/100.0);
	}
	public String toString() {return "Size of Cart: " + products.size() + ", Total Bill: $" + bill;}

	public boolean isEmpty() {
		return products.isEmpty();
	}
}
