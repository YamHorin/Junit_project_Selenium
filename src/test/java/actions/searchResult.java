package actions;


public class searchResult {

	private String name;
	private String productDescription;
	@Override
	public String toString() {
		return "[name=" + name + ", productDescription=" + productDescription + "]\n";
	}
	public String getName() {
		return name;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public searchResult(String name, String productDescription) {
		this.name = name;
		this.productDescription = productDescription;
	}
	public boolean contain(String str)
	{
		return (name.toLowerCase().contains(str.toLowerCase()))||(productDescription.toLowerCase().contains(str.toLowerCase()));
	}

	
	
	
	
}
