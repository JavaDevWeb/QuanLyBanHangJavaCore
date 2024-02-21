package vn.devpro.homework.update.customer;

import java.util.Scanner;

public class Customer {
	private int id;
	private String code;
	private String name;
	private String mobile;
	Scanner sc = new Scanner(System.in);
	
	public void edit() {
		do {
			System.out.println("\n==================CAP NHAT THONG TIN HANG HOA===================");
			System.out.println("Chon mot chuc nang quan ly");
			System.out.println("\t1. Sua Code khach hang.");
			System.out.println("\t2. Sua Name khach hang");
			System.out.println("\t3. Sua Mobile khach hang");
			System.out.println("\t0. Quay lai");
			
			System.out.print("Lua chon cua ban: ");
			int choice = Integer.parseInt(sc.nextLine());
			
			switch(choice) {
			case 1:
				editCode();
				break;
			case 2:
				editName();
				break;
			case 3:
				editMobile();
				break;
			case 0:
				return;
			default:
				System.out.println("Lua chon ko hop le");
			}
		} while (true);
	}
	
	private void editCode() {
		System.out.print("\tNhap Code moi: ");
		String newCode = sc.nextLine();
		// Kiem tra tinh hop le
		if(newCode.isEmpty()) {
			System.out.println("\tCode khong duoc de trong.");
	        return;
		}
		
		// Kiem tra Code moi co ton tai trong he thong hay ko
		if(CustomerManagement.findByCode(newCode) != -1) {
			System.out.println("\tCode '" + newCode + "' da ton tai trong he thong.");
	        return;
		}
		this.setCode(newCode);
		System.out.println("\tCap nhat code moi thanh cong!");
	}

	private void editName() {
		System.out.print("\tNhap name moi: ");
		String newName = sc.nextLine();
		// Kiem tra tinh hop le
		if(newName.isEmpty()) {
			System.out.println("\tName khong duoc de trong.");
	        return;
		}
		
		// Kiem tra Code moi co ton tai trong he thong hay ko
		if(CustomerManagement.findByName(newName) != -1) {
			System.out.println("\tName '" + newName + "' da ton tai trong he thong.");
	        return;
		}
		this.setName(newName);
		System.out.println("\tCap nhat name moi thanh cong!");
	}

	private void editMobile() {
		System.out.print("\tNhap name moi: ");
		String newMobile = sc.nextLine();
		// Kiem tra tinh hop le
		if(newMobile.isEmpty()) {
			System.out.println("\tName khong duoc de trong.");
	        return;
		}
		
		// Kiem tra Code moi co ton tai trong he thong hay ko
		if(CustomerManagement.findByMobile(newMobile) != -1) {
			System.out.println("\tMobile '" + newMobile + "' da ton tai trong he thong.");
	        return;
		}
		this.setMobile(newMobile);
		System.out.println("\tCap nhat mobile thanh cong!");
	}

	public void display() {
		System.out.printf("%3d %-10s %-25s %-12s%n", this.id, this.code, this.name, this.mobile);
	}
	
	public Customer() {
		super();
	}
	
	public Customer(int id, String name, String mobile) {
		super();
		this.id = id;
		this.name = name;
		this.mobile = mobile;
	}
	
	public Customer(int id, String code, String name, String mobile) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.mobile = mobile;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
