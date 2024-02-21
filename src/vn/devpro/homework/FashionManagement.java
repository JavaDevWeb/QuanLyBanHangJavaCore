package vn.devpro.homework;

import java.util.Scanner;

import vn.devpro.homework.sale.CartManagement;
import vn.devpro.homework.statistics.OrderManagement;
import vn.devpro.homework.update.UpdateManagement;
import vn.devpro.homework.update.category.CategoryManagement;
import vn.devpro.homework.update.customer.CustomerManagement;
import vn.devpro.homework.update.product.ProductManagement;

public class FashionManagement {
	public static void main(String[] args) {
		CategoryManagement.init();
		ProductManagement.init();
		CustomerManagement.init();
		
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("\n======================CHUONG TRINH QUAN LY SHOP THOI TRANG======================");
			System.out.println("Chon mot chuc nang de quan ly.");
			System.out.println("\t1. Cap nhat thong tin shop thoi trang.");
			System.out.println("\t2. Quan ly phien giao dich cua khach hang.");
			System.out.println("\t3. Quan ly don hang va doanh thu.");
			System.out.println("\t0. Thoat chuong trinh.");
			
			System.out.print("Lua chon cua ban: ");
			int choice = Integer.parseInt(sc.nextLine());
			
			switch (choice) {
			case 1:
				UpdateManagement.execute();
				break;
			case 2:
				CartManagement.execute();
				break;
			case 3: 
				OrderManagement.execute();
				break;
			case 0:
				System.exit(0);
			default:
				System.out.println("Lua chon khong hop le, ban vui long chon lai!");
			}
		} while(true);
	}
}
