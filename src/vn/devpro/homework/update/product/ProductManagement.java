package vn.devpro.homework.update.product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import vn.devpro.homework.sale.CartProduct;

public class ProductManagement {
	public static int autoId;
	
	static Scanner sc = new Scanner(System.in);
	
	private static ArrayList<Product> products = new ArrayList<Product>();
	
	public static void init() {
		products.add(new Product(autoId++, 1, "QA01", "Quan ao thu dong", 300000));
		products.add(new Product(autoId++, 2, "GD01", "Giay the thao", 500000));
		products.add(new Product(autoId++, 1, "QA02", "Quan ao he", 200000));
		products.add(new Product(autoId++, 3, "PK01", "Tui sach", 3500000));
		products.add(new Product(autoId++, 2, "GD02", "Giay cao got", 1800000));
		products.add(new Product(autoId++, 3, "Pk02", "Mu luoi chai", 990000));
	}
	
	public static void execute() {
		do {
			System.out.println("\n==================CAP NHAT DANH SACH SAN PHAM===================");
			System.out.println("Chon mot chuc nang quan ly");
			System.out.println("\t1. Hien thi danh sach san pham");
			System.out.println("\t2. Them moi san pham vao danh sach");
			System.out.println("\t3. Sua thong tin san pham");
			System.out.println("\t4. Xoa thong tin san pham");
			System.out.println("\t5. Sap xep theo ten san pham");
			System.out.println("\t6. Tim kiem san pham theo chung loai");
			System.out.println("\t7. Tim kiem san pham (1 phan) theo san pham");
			System.out.println("\t0. Quay lai");
			
			System.out.print("Lua chon cua ban: ");
			int choice = Integer.parseInt(sc.nextLine());
			
			switch (choice) {
			case 1:
				display();
				break;
			case 2:
				add();
				break;
			case 3:
				edit();
				break;
			case 4:
				remove();
				break;
			case 5:
				sortByName();
				break;
			case 6: 
				searchByCategory();
				break;
			case 7:
				searchByNamePart();
				break;
			case 0:
				return;
			default:
				System.out.println("Lua chon khong hop le, vui long chon lai!");
			}
		} while(true);
	}

	private static void display() {
		System.out.println("\n-------------------DANH SACH SAN PHAM-----------------------");
		System.out.printf("%-3s %-5s %-5s %-25s %-15s%n", "ID", "Ca.Id", "Code", "Name", "Price");
		for(Product product : products) {
			product.display();
		}
	}

	private static void add() {
		System.out.println("\n-------------------Them san pham vao danh sach-----------------------");
		System.out.print("\tNhap categoryId moi: ");
		int categoryId = Integer.parseInt(sc.nextLine());
		System.out.print("\tNhap code moi: ");
		String code = sc.nextLine();
		System.out.print("\tNhap name moi: ");
		String name = sc.nextLine();
		System.out.print("\tNhap gia moi: ");
		float price = Float.parseFloat(sc.nextLine());
		
		// Kiem tra tinh hop le
		if(name.isEmpty()) {
			System.out.println("\tTen khong duoc de trong");
			return;
		}
		
		// Kiem tra ten co ton tai trong danh sach khong
		if(findByName(name) != -1) { // ten da ton tai
			System.out.println("\tTen '" + name + "' da co trong danh sach");
			return;
		}
		
		// Them san pham vao danh sach
		// Tao doi tuong san pham moi
		Product newProduct = new Product(autoId++, categoryId, code, name, price);
		products.add(newProduct);
		System.out.println("\tThem moi san pham thanh cong!");
	}

	private static void edit() {
		System.out.println("\n-------------------Sua san pham trong danh sach-----------------------");
		System.out.print("\tNhap ID can sua: ");
		int id = Integer.parseInt(sc.nextLine());
		// Kiem tra xem san pham co trong danh sach hay ko
		int index = findById(id);
		if(index == -1) {
			System.out.println("\tSan pham khong co trong danh sach");
			return;
		}
		
		products.get(index).edit();
	}

	private static void remove() {
		System.out.println("\n-------------------Xoa thong tin hang hoa---------------------");
		System.out.print("\tNhap ID can xoa: ");
		int id = Integer.parseInt(sc.nextLine());
		// Tim xem san pham co trong danh sach ko
		int index = findById(id);
		if(index == -1) {
			System.out.println("\tSan pham khong co trong danh sach");
			return;
		}
		products.remove(index);
		System.out.println("Xoa san pham thanh cong");
	}

	private static void sortByName() {
		Collections.sort(products, new Comparator<Product>() {
			@Override
			public int compare(Product o1, Product o2) {
				return o1.getName().compareToIgnoreCase(o2.getName());
			}
		});
	}

	private static void searchByCategory() {
		System.out.print("\tNhap categoryId can tim: ");
	    int categoryId = Integer.parseInt(sc.nextLine());
	    System.out.println("\n-------------------DANH SACH SAN PHAM-----------------------");
		System.out.printf("%-3s %-5s %-5s %-25s %-15s%n", "ID", "Ca.Id", "Code", "Name", "Price");
		for(Product product : products) {
	    	if(product.getCategoryId() == categoryId) {
	    		product.display();
	    	}
	    }
	}

	private static void searchByNamePart() {
		System.out.print("\tNhap Name san pham: ");
	    String keyword = sc.nextLine().toLowerCase();
	    System.out.println("\n-------------------DANH SACH SAN PHAM-----------------------");
		System.out.printf("%-3s %-5s %-5s %-25s %-15s%n", "ID", "Ca.Id", "Code", "Name", "Price");
	    for(Product product : products) {
	    	if(product.getName().toLowerCase().contains(keyword)) {
	    		product.display();
	    	}
	    }
	}
	
	// Ham tim kiem tin loai hang da ton tai hay chua
	public static int findByName(String name) {
		for(int i = 0; i < products.size(); i++) {
			if(products.get(i).getName().equalsIgnoreCase(name)) {
				return i;
			}
		}
		return -1;
	}

	// Ham tim kiem loai hang theo id
	public static int findById(int id) {
		for(int i = 0; i < products.size(); i++) {
			if(products.get(i).getId() == id) {
				return i;
			}
		}
		return -1;
	}
	
	// Lay mot san pham theo id
	public static Product getProductById(int id) {
		for(Product product : products) {
			if(product.getId() == id) {
				return product;
			}
		}
		return null;
	}
}
