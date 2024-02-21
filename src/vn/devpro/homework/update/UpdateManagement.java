package vn.devpro.homework.update;

import java.util.Scanner;

import vn.devpro.homework.update.category.CategoryManagement;
import vn.devpro.homework.update.customer.CustomerManagement;
import vn.devpro.homework.update.product.ProductManagement;

public class UpdateManagement {
	public static void execute() {
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("\n==================CHUC NANG QUAN LY CAP NHAT THONG TIN HE THONG===================");
			System.out.println("Chon mot chuc nang de cap nhat");
			System.out.println("\t1. Cap nhat chung loai.");
			System.out.println("\t2. Cap nhat san pham.");
			System.out.println("\t3. Cap nhat khach hang.");
			System.out.println("\t0. Quay lai.");
			
			System.out.print("Lua chon cua ban la: ");
			int choice = Integer.parseInt(sc.nextLine());
			
			switch (choice) {
			case 1:
				CategoryManagement.execute();
				break;
			case 2:
				ProductManagement.execute();
				break;
			case 3:
				CustomerManagement.execute(); 
				break;
			case 0:
				return;
			default:
				System.out.println("Lua chon khong hop le, vui long chon lai!");
			}
		} while(true);
	}
}
