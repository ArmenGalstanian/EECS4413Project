package model;

public class Product {
	private Long id;
	private String model;
	private String description;
	private String categoryDescription;
	private Long categoryID;
	private String brandDescription;
	private Long brandID;
	private Double price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	public void setCategoryID(Long categoryID) {
		this.categoryID = categoryID;
	}
	
	public Long getCategoryID() {
		return categoryID;
	}

	public void setBrandID(Long brandID) {
		this.brandID = brandID;
	}
	
	public Long getBrandID() {
		return brandID;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Double getPrice() {
		return price;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	public String getBrandDescription() {
		return brandDescription;
	}

	public void setBrandDescription(String brandDescription) {
		this.brandDescription = brandDescription;
	}
	
	public String toString() {
		return "Category - Id: " + id + ", Category Description: " + categoryDescription;
	}
}
