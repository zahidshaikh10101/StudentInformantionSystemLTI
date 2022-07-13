package model;

public class Customer {
	int customerId;
	String customerName;
	
	public Customer(String customerName) {
		super();
		this.customerName = customerName;
	}

	public Customer(int customerId) {
		super();
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	
	
	
	
	

}
