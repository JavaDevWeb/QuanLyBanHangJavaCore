package vn.devpro.homework.sale;

import java.util.ArrayList;
import java.util.List;

import vn.devpro.homework.update.customer.Customer;
import vn.devpro.homework.update.customer.CustomerManagement;

public class Cart {
	private int id;
	private int customerId;
	private String code;
	List<CartProduct> cartProducts = new ArrayList<CartProduct>();
	
	public void display() {
		System.out.println("\tMa gio hang: " + this.code);
		String customerName = "";
		String customerMobile = "";
		Customer customer = CustomerManagement.getCustomerById(customerId);
		if(customer != null) {
			customerName = customer.getName();
			customerMobile = customer.getMobile();
		}
		System.out.println("\tTen khach hang: " + customerName);
		System.out.println("\tSo dien thoai: " + customerMobile);
		System.out.println("Danh sach san pham");
		System.out.printf("%-3s %-30s %-8s %-15s %-15s%n", "ID", "Ten san pham", "So luong", "Don gia", "Thanh tien");
		for(CartProduct cartProduct : cartProducts) {
			cartProduct.display();
		}
		System.out.printf("\tCong thanh tien: %,.2f", this.totalCartMoney());
	}
	
	public double totalCartMoney() {
		double total = 0;
		for(CartProduct cartProduct : cartProducts) {
			total += cartProduct.total();
		}
		return total;
	}
	
	// Tim san pham trong gio hang
	public int findCartProductById(int productId) {
		for(int i = 0; i < cartProducts.size(); i++) {
			if(cartProducts.get(i).getProductId() == productId) {
				return i;
			}
		}
		return -1;
	}
	
	public Cart() {
		super();
	}

	public Cart(int id, int customerId, String code, List<CartProduct> cartProducts) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.code = code;
		this.cartProducts = cartProducts;
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

	public List<CartProduct> getCartProducts() {
		return cartProducts;
	}

	public void setCartProducts(List<CartProduct> cartProducts) {
		this.cartProducts = cartProducts;
	}
}
