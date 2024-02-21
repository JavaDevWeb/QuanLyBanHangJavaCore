package vn.devpro.homework.sale;

import java.util.ArrayList;
import java.util.List;

import vn.devpro.homework.sale.ProductInOrder;
import vn.devpro.homework.update.customer.Customer;
import vn.devpro.homework.update.customer.CustomerManagement;

public class Order {
	private int id;
	private int customerId;
	private String code;
	private double total;
	private List<ProductInOrder> productInOrders = new ArrayList<ProductInOrder>();
	
	public void display() {
		System.out.println("\tMa gio hang: " + this.code);
	    String customerName = "";
	    String customerMobile = "";
	    Customer customer = CustomerManagement.getCustomerById(customerId);
	    if (customer != null) {
	        customerName = customer.getName();
	        customerMobile = customer.getMobile();
	    }
	    System.out.println("\tTen khach hang: " + customerName);
	    System.out.println("\tSo dien thoai: " + customerMobile);
	    System.out.println("Danh sach san pham");
	    System.out.printf("%-3s %-35s %-8s %-15s %-15s%n", "ID", "Ten san pham", "So luong", "Don Gia",
	            "Thanh tien");
	    for (ProductInOrder productInOrder : productInOrders) {
	        productInOrder.display();
	        System.out.println();
	    }
	    System.out.println();
	    System.out.printf("\tCong thanh tien: %,.2f", totalCartMoney());
	}
	
	public double totalCartMoney() {
		double total = 0;
	    for (ProductInOrder productInOrder : productInOrders) {
	        total += productInOrder.total();
	    }
	    return total;
	}
	
	public void addProductInOrder(ProductInOrder productInOrder) {
        productInOrders.add(productInOrder);
    }
	
	public Order() {
		super();
	}
	
	public Order(int id, int customerId, String code, double total, List<ProductInOrder> productInOrders) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.code = code;
		this.total = total;
		this.productInOrders = productInOrders;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public List<ProductInOrder> getProductInOrders() {
		return productInOrders;
	}

	public void setProductInOrders(List<ProductInOrder> productInOrders) {
		this.productInOrders = productInOrders;
	}
}
