class InventoryManagement {
	private String modelNo, modelName, brand, os, range;
	int cost, sellingPrice, quantity;
	InventoryManagement(String modelNo, String modelName, String brand, String os, String range, int cost, int sellingPrice, int quantity) {
		this.modelNo = modelNo;
		this.modelName = modelName;
		this.brand = brand;
		this.os = os;
		this.range = range;
		this.cost = cost;
		this.sellingPrice = sellingPrice;
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
	public String getRange() {
		return this.range;
	}
	public int getCost() {
		return this.cost;
	}
	public int getSellingPrice() {
		return this.sellingPrice;
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
	public void setRange(String range) {
		this.range = range;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public void setSellingPrice(int sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}