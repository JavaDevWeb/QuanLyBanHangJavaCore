package vn.devpro.homework.sale;

import vn.devpro.homework.update.product.Product;
import vn.devpro.homework.update.product.ProductManagement;

public class ProductInOrder { 
	private int id;
	private int productId;
	private int orderId;
	private int quantity;
	
	public void display() {
		Product product = ProductManagement.getProductById(productId);
		System.out.printf("%3d %-35s %,8d %,15.2f %,15.2f", product.getId(), product.getName()
				, this.quantity, product.getPrice(), this.total());
	}
	
	public double total() {
		Product product = ProductManagement.getProductById(this.productId);
		return this.quantity * product.getPrice();
	}
	
	public ProductInOrder() {
		super();
	}
	
	public ProductInOrder(int id, int productId, int orderId, int quantity) {
		super();
		this.id = id;
		this.productId = productId;
		this.orderId = orderId;
		this.quantity = quantity;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getProductId() {
		return productId;
	}
	
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public int getOrderId() {
		return orderId;
	}
	
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
