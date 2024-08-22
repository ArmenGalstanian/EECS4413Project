package model;

public class Brand {
	private Long id;
	private String brandDescription;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBrandDescription() {
		return brandDescription;
	}

	public void setBrandDescription(String brandDescription) {
		this.brandDescription = brandDescription;
	}

	public String toString() {
		return "Brand - Id: " + id + ", Brand Description: " + brandDescription;
	}
}
