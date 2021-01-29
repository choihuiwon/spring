package dto;

public class ProductDTO {
	private String product_no;
	private String product_name;
	private int price;
	private int ea;
	private String maker;
	
	public ProductDTO(String product_no, String product_name, int price, int ea, String maker) {
		super();
		this.product_no = product_no;
		this.product_name = product_name;
		this.price = price;
		this.ea = ea;
		this.maker = maker;
	}
	
	public String getProduct_no() {
		return product_no;
	}
	public void setProduct_no(String product_no) {
		this.product_no = product_no;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getEa() {
		return ea;
	}
	public void setEa(int ea) {
		this.ea = ea;
	}
	public String getMaker() {
		return maker;
	}
	public void setMaker(String maker) {
		this.maker = maker;
	}
	
	
}
