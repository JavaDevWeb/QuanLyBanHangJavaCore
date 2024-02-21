package vn.devpro.homework.update.product;

import java.util.Scanner;

import vn.devpro.homework.update.category.Category;
import vn.devpro.homework.update.category.CategoryManagement;

public class Product {
	private	int id;
	private int categoryId;
	private String code;
	private String name;
	private float price;
	
	Scanner sc = new Scanner(System.in);
	
	public void edit() {
		do {
			System.out.println("\n---------------------CAP NHAT THONG TIN SAN PHAM---------------------");
			System.out.println("Chon mot chuc nang de cap nhat thong tin san pham");
			System.out.println("\t1. Sua CategoryId chung loai.");
			System.out.println("\t2. Sua code san pham.");
			System.out.println("\t3. Sua name san pham.");
			System.out.println("\t4. Sua price san pham.");
			System.out.println("\t0. Quay lai.");
			
			System.out.print("Lua chon cua ban la: ");
			int choice = Integer.parseInt(sc.nextLine());
			
			switch (choice) {
			case 1:
				editCategoryId();
				break;
			case 2:
				editCode();
				break;
			case 3:
				editName();
				break;
			case 4:
				editPrice();
				break;
			case 0:
				return;
			default:
				System.out.println("Lua chon khong hop le, vui long chon lai!");
			}
		} while(true);
	}
	
	private void editCategoryId() {
		System.out.print("\tNhap categoryId moi: ");
		int newCategoryId = Integer.parseInt(sc.nextLine());
		
		// Kiem tra categoryId co trong danh sach hay ko
		if(CategoryManagement.findById(newCategoryId) == -1) {
			System.out.println("\tSan pham khong ton tai");
			return;
		}
		this.setCategoryId(newCategoryId);
		System.out.println("\tSua CategoryId moi thanh cong!");
	}

	private void editCode() {
		System.out.print("\tNhap code moi: ");
		String newCode = sc.nextLine();
		
		// Kiem tra tinh hop le
		if(newCode.isEmpty()) {
			System.out.println("\tCode khong dc de trong");
			return;
		}
		
		// Kiem tra code co trong danh sach hay ko
		if(ProductManagement.findByName(newCode) != -1) {
			System.out.println("\tCode '" + newCode + "' da ton tai trong he thong.");
			return;
		}
		this.setCode(newCode);
		System.out.println("\tSua code moi thanh cong!");
	}

	private void editName() {
		System.out.print("\tNhap name moi: ");
		String newName = sc.nextLine();
		
		// Kiem tra tinh hop le
		if(newName.isEmpty()) {
			System.out.println("\tName khong dc de trong");
			return;
		}
		if(ProductManagement.findByName(newName) != -1) {
			System.out.println("\tName '" + newName + "' da co trong he thong.");
			return;
		}
		this.setName(newName);
		System.out.println("\tSua name moi thanh cong!");
	}

	private void editPrice() {
		System.out.print("\tNhap price moi: ");
		float newPrice = Float.parseFloat(sc.nextLine());
		
		// Kiem tra tinh hop le
		if(newPrice < 0) {
			System.out.println("\tPrice khong duoc la so am");
			return;
		}
		this.setPrice(newPrice);
		System.out.println("\tSua price moi thanh cong!");
	}

	public void display() {
		Category category = CategoryManagement.getCategory(categoryId);
		System.out.printf("%3d %5d %-5s %-25s %,15.2f%n", this.id, this.categoryId,
				this.code, this.name, this.price);
	}
	
	public Product() {
		super();
	}

	public Product(int id, int categoryId, String code, String name, float price) {
		super();
		this.id = id;
		this.categoryId = categoryId;
		this.code = code;
		this.name = name;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
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

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
}
