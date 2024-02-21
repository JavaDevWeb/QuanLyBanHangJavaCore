package vn.devpro.homework.statistics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

import vn.devpro.homework.sale.Order;
import vn.devpro.homework.sale.ProductInOrder;
import vn.devpro.homework.update.customer.Customer;
import vn.devpro.homework.update.customer.CustomerManagement;
import vn.devpro.homework.update.product.ProductManagement;

public class OrderManagement {
	private static List<Order> orders = new ArrayList<Order>();
	
	static Scanner sc = new Scanner(System.in);
	public static void execute() {
		do {
			System.out.println("\n==================QUAN LY ĐON HANG===================");
			System.out.println("Chon mot chuc nang quan ly");
			System.out.println("\t1. Hien thi danh sach hoa don");
			System.out.println("\t2. Xoa 1 don hang khoi danh sach");
			System.out.println("\t3. Hien thi tong doanh thu co duoc tu tat ca cac hoa don");
			System.out.println("\t4. Hien thi tong so tien thu duoc theo khach hang");
			System.out.println("\t5. Hien thi tong so tien thu duoc theo san pham da ban");
			System.out.println("\t0. Quay lai");
			
			System.out.print("Lua chon cua ban: ");
			int choice = Integer.parseInt(sc.nextLine());
			
			switch(choice) {
			case 1:
				display();
				break;
			case 2:
				remove();
				break;
			case 3:
				displayTotalRevenue();
				break;
			case 4:
				displayTotalRevenueByCustomer();
				break;
			case 5:
				displayTotalRevenueByProduct();
				break;
			case 0:
				return;
			default:
				System.out.println("Lua chon ko hop le");
			}
		} while (true);
	}
	
	// hien thi danh sach hoa don	
	public static void display() {
		System.out.println("\n---------------------------DANH SACH HOA DON---------------------------");
		for(Order order : orders) {
			order.display();
			System.out.println("\n-----------------------------------------------------------------------");
		}
	}
	
	// Xoa thong tin cua 1 don hang
	public static void remove() {
		System.out.println("\n---------------------------Xoa don hang ra khoi danh sach---------------------------");
		System.out.print("\tNhap Code can xoa: ");
		String code = sc.nextLine();
		// Tim kiem chung loai co trong danh sach hay ko
		int index = findOrderByCode(code);
		if(index == -1) {
			System.out.println("\tDon hang khong trong danh sach");
		}
		orders.remove(index);
		System.out.println("\tXoa don hang thanh cong!");
	}
	
	private static int findOrderByCode(String orderId) {
		for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getCode().equals(orderId)) {
                return i;
            }
        }
        return -1;
	}
	
	// Hien thi tong doanh thu co duoc tu tat ca cac hoa don
	public static void displayTotalRevenue() {
		double totalRevenue = calculateTotalRevenue();
		System.out.printf("\nTong doanh thu tu tat ca cac hoa don la: %,.2f", totalRevenue);
	}
	
	public static double calculateTotalRevenue() {
		double totalRevenue = 0;
		for(Order order : orders) {
			totalRevenue += order.totalCartMoney();
		}
		return totalRevenue;
	}
	
	// Hien thi tong so tien thu duoc theo khach hang
	public static void displayTotalRevenueByCustomer() {
		System.out.println("\n--------------------Tong so tien theo khach hang----------------------");
        for (Customer customer : CustomerManagement.getCustomers()) {
            double totalRevenueByCustomer = calculateTotalRevenueByCustomer(customer.getId());
            System.out.printf("Khach hang: %s - Tong so tien: %,.2f\n", customer.getName(), totalRevenueByCustomer);
        }
	}
	
	private static double calculateTotalRevenueByCustomer(int customerId) {
        double totalRevenueByCustomer = 0;
        for (Order order : orders) {
            if (order.getCustomerId() == customerId) {
                totalRevenueByCustomer += order.getTotal();
            }
        }
        return totalRevenueByCustomer;
    }
	
	// Hien thi tong tien thu duoc theo san pham da ban
	public static void displayTotalRevenueByProduct() {
        System.out.println("\n------------------Tong so tien theo san pham da ban---------------------");
        
        // Sử dụng Map để lưu thông tin theo sản phẩm
        Map<Integer, ProductInfo> productInfoMap = new HashMap<>();

        // Duyệt qua danh sách hóa đơn
        for (Order order : orders) {
            // Duyệt qua danh sách sản phẩm trong hóa đơn
            for (ProductInOrder productInOrder : order.getProductInOrders()) {
                int productId = productInOrder.getProductId();
                int quantity = productInOrder.getQuantity();
                double revenue = productInOrder.total();

                // Nếu sản phẩm đã có trong Map, cập nhật thông tin
                if (productInfoMap.containsKey(productId)) {
                    ProductInfo productInfo = productInfoMap.get(productId);
                    productInfo.addQuantity(quantity);
                    productInfo.addRevenue(revenue);
                } else { // Nếu sản phẩm chưa có trong Map, thêm mới
                    ProductInfo productInfo = new ProductInfo(productId, quantity, revenue);
                    productInfoMap.put(productId, productInfo);
                }
            }
        }

        // Hiển thị thông tin
        System.out.printf("%-5s %-35s %-20s %-15s%n", "STT", "Ten san pham", "Tong so luong", "Doanh thu");
        int stt = 1;
        for (ProductInfo productInfo : productInfoMap.values()) {
            System.out.printf("%-5s %-35s %-20s %,-15.2f%n", stt++, 
                    ProductManagement.getProductById(productInfo.productId).getName(),
                    productInfo.quantity, productInfo.revenue);
        }
    }

    // Class để lưu thông tin sản phẩm
    private static class ProductInfo {
        private int productId;
        private int quantity;
        private double revenue;

        public ProductInfo(int productId, int quantity, double revenue) {
            this.productId = productId;
            this.quantity = quantity;
            this.revenue = revenue;
        }

        public void addQuantity(int quantity) {
            this.quantity += quantity;
        }

        public void addRevenue(double revenue) {
            this.revenue += revenue;
        }
    }
	
	public static List<Order> getOrders() {
		return orders;
	}

	public static void setOrders(List<Order> orders) {
		OrderManagement.orders = orders;
	}
}
