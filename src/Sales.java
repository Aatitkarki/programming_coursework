class Sales {
	private String firstName, lastName, date, modelNumber, brand;
	private int total, discount, quantity;
	Sales(String firstName, String lastName, String date, String modelNumber, String brand, int quantity, int discount, int total) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.date = date;
		this.modelNumber = modelNumber;
		this.brand = brand;
		this.quantity = quantity;
		this.discount = discount;
		this.total = total;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	public String getLastName() {
		return this.lastName;
	}
	public String getDate() {
		return this.date;
	}
	public String getModelNumber() {
		return this.modelNumber;
	}
	public String getBrand() {
		return this.brand;
	}
	public int getQuantity() {
		return this.quantity;
	}
	public int getDiscount() {
		return this.discount;
	}
	public int getTotal() {
		return this.total;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public void setTotal(int total) {
		this.total = total;
	}
}