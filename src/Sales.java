class Sales {
	private String modelNo, modelName, brand, os, customerName;
	int total, discount, quantity;
	Sales(String modelNo, String modelName, String brand, String os, String customerName, int discount, int quantity, int total) {
		this.modelNo = modelNo;
		this.modelName = modelName;
		this.brand = brand;
		this.os = os;
		this.customerName = customerName;
		this.total = total;
		this.discount = discount;
		this.quantity = quantity;
	}
	public String getModelNo() {
		return this.modelNo;
	}
	public String getModelName() {
		return this.modelName;
	}
	public String getBrand() {
		return this.brand;
	}
	public String getOs() {
		return this.os;
	}
	public String getCustomerName() {
		return this.customerName;
	}
	public int getTotal() {
		return this.total;
	}
	public int getDiscount() {
		return this.discount;
	}
	public int getQuantity() {
		return this.quantity;
	}
	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}