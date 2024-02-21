package vn.devpro.homework.update.category;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class CategoryManagement {
	public static int autoId = 1;
	private static ArrayList<Category> categories = new ArrayList<Category>();
	
	public static void init() {
		categories.add(new Category(autoId++, "QA", "Quan ao"));
		categories.add(new Category(autoId++, "GD", "Giay dep"));
		categories.add(new Category(autoId++, "PK", "Phu kien"));
	}
	
	static Scanner sc = new Scanner(System.in);
	
	public static void execute() {
		do {
			System.out.println("\n=====================CAP NHAT THONG TIN CUA HANG=====================");
			System.out.println("Chon mot chuc nang de cap nhat");
			System.out.println("\t1. Hien thi danh sach chung loai");
			System.out.println("\t2. Them chung loai vao danh sach.");
			System.out.println("\t3. Sua chung loai trong danh sach.");
			System.out.println("\t4. Xoa chung loai trong danh sach.");
			System.out.println("\t5. Sap xep chung loai theo ten.");
			System.out.println("\t0. Quay lai.");
			
			System.out.print("Lua chon cua ban la: ");
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
				sort();
				break;
			case 0:
				return;
			default:
				System.out.println("Lua chon khong hop le, vui long chon lai!");
			}
		} while(true);
	}

	private static void display() {
		System.out.println("\n-------------------DANH SACH CHUNG LOAI-----------------------");
		System.out.printf("%-3s %-5s %-25s%n", "ID", "Code", "Name");
		for(Category category : categories) {
			category.display();
		}
	}

	private static void add() {
		System.out.println("\n-------------------Them chung loai moi vao danh sach---------------------");
		System.out.print("\tNhap code moi: ");
		String newCode = sc.nextLine();
		System.out.print("\tNhap name moi: ");
		String newName = sc.nextLine();
		
		// Kiem tra tinh hop le
		if(newName.isEmpty()) {
			System.out.println("Ten khong duoc de trong");
			return;
		}
		
		// Kiem tra ten co ton tai trong danh sach hay ko
		if(findByName(newName) != -1) {
			System.out.println("\tTen '" + newName + "' da co trong danh sach");
			return;
		}
		
		// Them chung loai vao danh sach
		// Tao doi tuong chung loai
		Category newCategory = new Category(autoId++, newCode, newName);
		categories.add(newCategory); // Them doi tuong vao danh sach
		System.out.println("\tThem moi chung loai thanh cong!");
	}

	private static void edit() {
		System.out.println("\n-------------------Sua thong tin cua chung loai trong danh sach---------------------");
		System.out.print("\tNhap ID chung loai can sua: ");
		int id = Integer.parseInt(sc.nextLine());
		// Tim vi tri cua chung loai trong danh sach
		int index = findById(id);
		if(index == -1) {
			System.out.println("\tChung loai khong co trong danh sach");
			return;
		}
		
		// Nhap thong tin moi
		System.out.println("\nNhap thong tin moi cho chung loai:");
		System.out.print("\tNhap code moi: ");
		String newCode = sc.nextLine();
		System.out.print("\tNhap name moi: ");
		String newName = sc.nextLine();
		
		// Kiem tra tinh hop le
		if(newName.isEmpty()) {
			System.out.println("Ten khong duoc de trong");
			return;
		}
		
		// Kiem tra ten moi co ton tai trong danh sach hay khong
		if(findById(id) != -1 && findById(id) != index) {
			System.out.println("\tTen '" + newName + "' da co trong danh sach");
			return;
		}
		
		// Cap nhat thong tin cua chung loai
		categories.get(index).setCode(newCode);
		categories.get(index).setName(newName);
		System.out.println("\tCap nhat chung loai thanh cong!");
	}

	private static void remove() {
		System.out.println("\n-------------------Xoa thong tin chung loai hang---------------------");
		System.out.print("\tNhap ID chung loai can xoa: ");
		int id = Integer.parseInt(sc.nextLine());
		// Tim kiem chung loai co trong danh sach hay ko
		int index = findById(id);
		if(index == -1) {
			System.out.println("\tChung loai khong co trong danh sach");
		}
		categories.remove(index);
		System.out.println("\tXoa chung loai thanh cong!");
	}

	private static void sort() {
		Collections.sort(categories, new Comparator<Category>() {
			@Override
			public int compare(Category o1, Category o2) {
				return o1.getName().compareToIgnoreCase(o2.getName());
			}
			
		});
	}
	
	public static int findByName(String name) {
		for(int i = 0; i < categories.size(); i++) {
			if(categories.get(i).getName().equalsIgnoreCase(name)) {
				return i;
			}
		}
		return -1;
	}
	
	public static int findById(int id) {
		for(int i = 0; i < categories.size(); i++) {
			if(categories.get(i).getId() == id) {
				return i;
			}
		}
		return -1;
	}
	
	// Ham tim theo id va tra ve doi tuong
	public static Category getCategory(int id) {
		for(Category category : categories) {
			if(category.getId() == id) {
				return category;
			}
		}
		return new Category();
	}
}
