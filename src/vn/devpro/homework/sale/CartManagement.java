package vn.devpro.homework.sale;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import vn.devpro.homework.update.customer.CustomerManagement;
import vn.devpro.homework.update.product.ProductManagement;
import vn.devpro.homework.statistics.OrderManagement;
import vn.devpro.homework.update.customer.Customer;

public class CartManagement {
	public static int autoId = 1;
	static Scanner sc = new Scanner(System.in);
	private static Cart cart = new Cart();
	public static void execute() {
		do {
			System.out.println("\n==================QUAN LY GIO HANG===================");
			System.out.println("Chon mot chuc nang quan ly");
			System.out.println("\t1. Hien thi gio hang");
			System.out.println("\t2. Them san pham vao gio hang");
			System.out.println("\t3. Thay doi so luong san pham trong gio hang");
			System.out.println("\t4. Xoa hang trong gio hang");
			System.out.println("\t5. Huy gio hang");
			System.out.println("\t6. Thanh toan gio hang");
			System.out.println("\t0. Quay lai");
			
			System.out.print("Lua chon cua ban: ");
			int choice = Integer.parseInt(sc.nextLine());
			
			switch(choice) {
			case 1:
				display();
				break;
			case 2:
				addToCart();
				break;
			case 3:
				changeProductQuantity();
				break;
			case 4:
				deleteCartProduct();
				break;
			case 5:
				cart = new Cart();
				break;
			case 6: 
				payment();
				break;
			case 0:
				return;
			default:
				System.out.println("Lua chon ko hop le");
			}
		} while (true);
	}
	
	public static void display() {
		System.out.println("\n\t\tGIO HANG CUA BAN");
		if(cart.getCartProducts().size() <= 0) {
			System.out.println("\tKhong co san pham nao trong gio hang");
		}
		else {
			cart.display();
		}
	}
	
	// Them san pham vao gio hang
	private static void addToCart() {
		System.out.println("\n-------------------THEM SAN PHAM VAO GIO HANG-------------------");
		System.out.print("\tNhap id san pham can mua: ");
		int productId = Integer.parseInt(sc.nextLine());
		// Kiem tra san pham nay co trong ds san pham ko
		int productIndex = ProductManagement.findById(productId);
		if(productIndex == -1) {
			System.out.println("\tSan pham khong co trong danh sach san pham");
			return;
		}
		// Co thi nhap so luong
		System.out.print("\tNhap so luong can mua: ");
		int quantity = Integer.parseInt(sc.nextLine());
		if(quantity <= 0) {
			System.out.println("\tSo luong ko hop le");
			return;
		}
		// Cap nhat san pham vao gio hang: Co 2 truong hop
		// + TH1: San pham chua co trong gio hang -> them moi
		// + TH2: San pham da co trong gio hang -> Tang so luong
		
		// Tim san pham xem có trong gio hang chua
		int cartProductIndex = cart.findCartProductById(productId);
		// Tinh tong so luong hang du kien mua
		if(cartProductIndex != -1) { // San pham co trong gio
			// Tong cua so luong moi nhap va so luong da co trong gio
			quantity += cart.getCartProducts().get(cartProductIndex).getQuantity();
		}
		
		// Cap nhat gio hang
		if(cartProductIndex == -1) { //TH1
			cart.getCartProducts().add(new CartProduct(productId, quantity));
		}
		else { //TH2
			cart.getCartProducts().get(cartProductIndex).setQuantity(quantity);
		}
		System.out.println("\tThem san pham moi thanh cong!");
	}
	
	// Sua thong tin san pham trong gio hang
	public static void changeProductQuantity() {
		System.out.println("\n-------------------THAY DOI SO LUONG SAN PHAM TRONG GIO HANG-------------------");
		System.out.print("\tNhap id san pham can thay doi: ");
		int productId = Integer.parseInt(sc.nextLine());
		// Kiem tra san pham nay co trong gio hang ko
		int cartProductIndex = cart.findCartProductById(productId);
		if(cartProductIndex == -1) {
			System.out.println("\tSan pham khong co trong gio hang");
			return;
		}
		// Co thi nhap so luong
		System.out.print("\tNhap so luong can them(+)/bot(-): ");
		int quantity = Integer.parseInt(sc.nextLine());
		// Tinh so luong sau khi them/bot
		quantity += cart.getCartProducts().get(cartProductIndex).getQuantity();
		// so luong sau khi them/bot khong duoc nho hon 1 va khong duoc vuot qua so luong co the ban
		cart.getCartProducts().get(cartProductIndex).setQuantity(quantity);
		System.out.println("\tThay doi so luong thanh cong!");
	}
	
	// Xoa san pham trong gio hang
	public static void deleteCartProduct() {
		System.out.println("\n-------------------XOA SAN PHAM TRONG GIO HANG-------------------");
		System.out.print("\tNhap id san pham can xoa: ");
		int productId = Integer.parseInt(sc.nextLine());
		int index = cart.findCartProductById(productId);
		if(index == -1) {
			System.out.println("\tSan pham khong co trong gio hang");
			return;
		}
		cart.getCartProducts().remove(index);
		System.out.println("\tXoa san pham thanh cong!");
	}
	
	// Thanh toan
	public static void payment() {
		System.out.println("\n-------------------THANH TOAN GIO HANG-------------------");
	    // Cap nhat thong tin khach hang
	    System.out.print("\tNhap id khach hang: ");
	    int customerId = Integer.parseInt(sc.nextLine());
	    int customerIndex = CustomerManagement.findById(customerId);
	    String customerName;
	    String customerMobile;
	    if (customerIndex == -1) { // Khach moi
	        do {

	            System.out.print("\tNhap Name: ");
	            customerName = sc.nextLine();
	            System.out.print("\tNhap Mobile: ");
	            customerMobile = sc.nextLine();
	        } while (customerName.isEmpty());
	        // Them khach hang vao danh sach khach hang
	        customerId = CustomerManagement.autoId++;
	        Customer customer = new Customer(customerId, customerName, customerMobile);
	        CustomerManagement.getCustomers().add(customer);
	    } else { // Khach hang da co trong danh sach
	        customerName = CustomerManagement.getCustomers().get(customerIndex).getName();
	    }

	    // Cap nhat thong tin hoa don
	    int orderId = autoId++;
	    String orderCode = orderId + "_" + customerId;

	    // Luu hoa don vao danh sach hoa don (orders)
	    Order order = new Order();
	    order.setId(orderId);
	    order.setCode(orderCode);
	    order.setCustomerId(customerId);

	    // Them san pham trong gio hang vao danh sach san pham cua hoa don
	    List<ProductInOrder> productsInOrder = new ArrayList<>();
        for (CartProduct cartProduct : cart.getCartProducts()) {
            ProductInOrder productInOrder = new ProductInOrder();
            productInOrder.setProductId(cartProduct.getProductId());
            productInOrder.setOrderId(orderId);
            productInOrder.setQuantity(cartProduct.getQuantity());
            order.addProductInOrder(productInOrder); // Thêm vào danh sách sản phẩm của đơn hàng
            productsInOrder.add(productInOrder);
        }

	    // Tinh tong tien cho hoa don
	    double total = order.totalCartMoney();
	    order.setTotal(total);

	    // Tao mot danh sach san pham moi cho hoa don
	    order.setProductInOrders(new ArrayList<>(productsInOrder));

	    // Luu hoa don vao danh sach hoa don
	    OrderManagement.getOrders().add(order);

	    // Hien thi hoa don
	    System.out.println("\t\tHOA DON BAN HANG");
	    order.display();
	    System.out.println("\tCam on ban da ung ho cua hang!");
	    // Xoa gio hang sau khi thanh toan
	    cart = new Cart();
	}
}
