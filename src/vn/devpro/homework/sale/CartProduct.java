package vn.devpro.homework.sale;

import vn.devpro.homework.update.product.Product;
import vn.devpro.homework.update.product.ProductManagement;

public class CartProduct { 
	private int productId;
	private int quantity; // So luong khach mua
	
	public void display() {
		// Lay 1 san pham trong danh sach san pham
		Product product = ProductManagement.getProductById(this.productId);
		System.out.printf("%3d %-30s %,8d %,15.2f %,15.2f%n", product.getId(), product.getName(),
				this.quantity, product.getPrice(), this.total());
	}
	
	public double total() {
		// Lay 1 san pham trong danh sach san pham
		Product product = ProductManagement.getProductById(this.productId);
		return this.quantity * product.getPrice();
	}
	
	public CartProduct() {
		super();
	}
	
	public CartProduct(int productId, int quantity) {
		super();
		this.productId = productId;
		this.quantity = quantity;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
