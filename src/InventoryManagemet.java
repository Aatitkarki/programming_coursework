class InventoryManagement {
	private String modelNo, modelName, brand, category, recommendation;
	private int cost, sellingPrice, quantity;
	InventoryManagement(String modelNo, String modelName, String brand,String category, String recommendation, int cost, int sellingPrice, int quantity) {
		this.modelNo = modelNo;
		this.modelName = modelName;
		this.category = category;
                this.brand = brand;
		this.recommendation = recommendation;
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
	public String getCategory() {
		return this.category;
	}
	public String getRecommendation() {
		return this.recommendation;
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
	public void setCategory(String category) {
		this.category = category;
	}
	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
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