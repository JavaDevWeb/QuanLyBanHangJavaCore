package vn.devpro.homework.update.customer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class CustomerManagement {
	public static int autoId = 1;
	private static ArrayList<Customer> customers = new ArrayList<Customer>();
	
	public static void init() {
		customers.add(new Customer(autoId++, "Premium", "Nguyen Khac Huan", "0332693457"));
		customers.add(new Customer(autoId++, "Average", "Le Duc Anh", "0356423419"));
		customers.add(new Customer(autoId++, "Regular", "Nguyen Thi My Linh", "0322836482"));
	}
	
	static Scanner sc = new Scanner(System.in);
	public static void execute() {
		do {
			System.out.println("\n==================CAP NHAT THONG TIN KHACH HANG===================");
			System.out.println("Chon mot chuc nang quan ly");
			System.out.println("\t1. Hien thi danh sach khach hang");
			System.out.println("\t2. Them khach hang moi vao danh sach");
			System.out.println("\t3. Sua thong tin khach hang");
			System.out.println("\t4. Xoa thong tin khach hang");
			System.out.println("\t5. Sap xep danh sach khach hang theo ten");
			System.out.println("\t0. Quay lai");
			
			System.out.print("Lua chon cua ban: ");
			int choice = Integer.parseInt(sc.nextLine());
			
			switch(choice) {
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
				System.out.println("Lua chon ko hop le");
			}
		} while(true);
	}
	
	private static void display() {
		System.out.println("\n-------------------DANH SACH SAN PHAM-----------------------");
		System.out.printf("%-3s %-10s %-25s %-12s%n", "ID", "Code", "Name", "Mobile");
		for(Customer customer : customers) {
			customer.display();
		}
	}
	
	private static void add() {
		System.out.println("\n-------------------Them khach hang vao danh sach-----------------------");
		System.out.print("\tNhap code moi: ");
		String newCode = sc.nextLine();
		System.out.print("\tNhap name moi: ");
		String newName = sc.nextLine();
		System.out.print("\tNhap mobile moi: ");
		String newMobile = sc.nextLine();
		
		// Kiem tra tinh hop le
		if(newName.isEmpty()) {
			System.out.println("\tTen khong duoc de trong");
			return;
		}
		// Kiem tra ten da ton tai trong danh sach
		if(findByName(newName) != -1) { // Ten da ton tai
			System.out.println("\tTen '" + newName + "' da co trong danh sach");
			return;
		}
		// Them loai hang vao danh sach
		// + Tao doi tuong chung loai 
		Customer newCustomer = new Customer(autoId++, newCode, newName, newMobile);
		// + Add doi tuong vao danh sach
		customers.add(newCustomer);
		System.out.println("\tThem moi khach hang thanh cong!");
	}
	
	private static void edit() {
		System.out.println("\n-------------------Sua thong tin khach hang---------------------");
		System.out.print("\tNhap ID can sua: ");
		int id = Integer.parseInt(sc.nextLine());
		
		// Tim xem san pham co trong danh sach ko
		int index = findById(id);
		if(index == -1) {
			System.out.println("\tKhach hang khong co trong danh sach");
			return;
		}
		
		customers.get(index).edit();
	}
	
	private static void remove() {
		System.out.println("\n-------------------Sua thong tin khach hang---------------------");
		System.out.print("\tNhap ID can sua: ");
		int id = Integer.parseInt(sc.nextLine());
		
		// Kiem tra khach hang co trong danh sach ko
		int index = findById(id);
		if(index == -1) {
			System.out.println("\tKhach hang khong co trong danh sach");
			return;
		}
		customers.remove(index);
		System.out.println("\tXoa hang hoa thanh cong!");
	}
	
	private static void sort() {
		Collections.sort(customers, new Comparator<Customer>() {
			@Override
			public int compare(Customer o1, Customer o2) {
				return o1.getName().compareToIgnoreCase(o2.getName());
			}
		});
	}
	
	// Ham tim kiem tin loai hang da ton tai hay chua
	public static int findByName(String name) {
		for(int i = 0; i < customers.size(); i++) {
			if(customers.get(i).getName().equalsIgnoreCase(name)) {
				return i;
			}
		}
		return -1;
	}

	// Ham tim kiem loai hang theo id
	public static int findById(int id) {
		for(int i = 0; i < customers.size(); i++) {
			if(customers.get(i).getId() == id) {
				return i;
			}
		}
		return -1;
	}

	public static int findByCode(String code) {
		for(int i = 0; i< customers.size(); i++) {
			if(customers.get(i).getCode().equalsIgnoreCase(code)) {
				return i;
			}
		}
		return -1;
	}
	
	public static int findByMobile(String mobile) {
		for(int i = 0; i < customers.size(); i++) {
			if(customers.get(i).getCode().equals(mobile)) {
				return i;
			}
		}
		return -1;
	}

	// Lay 1 khach hang theo id
	public static Customer getCustomerById(int id) {
		for (Customer customer : customers) {
			if(customer.getId() == id) {
				return customer;
			}
		}
		return null;
	}
	
	public static ArrayList<Customer> getCustomers() {
		return customers;
	}

	public static void setCustomers(ArrayList<Customer> customers) {
		CustomerManagement.customers = customers;
	}
}
