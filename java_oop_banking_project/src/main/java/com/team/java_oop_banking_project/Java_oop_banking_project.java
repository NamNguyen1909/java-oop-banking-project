/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.team.java_oop_banking_project;

import com.team.lhp.TaiKhoanCoKyHan;
import com.team.ntn.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author THANH_NAM
 */
public class Java_oop_banking_project {

    public static void main(String[] args) throws Exception {
        Bank bank = new Bank();

        bank.readEmployeeListFromFile(bank.getEmployeeList(), "src/main/resources/employeeList.txt");

        Employee p1 = new Employee("Thanh Nam", "Nam", "19/09/2004", "Ben Tre", "1234567890", "admin");
        Employee p2 = new Employee("Hoang Phuc", "nam", "23/02/2001", "Ben Tre", "312312331", "admin");

        bank.addEmployee(p1, p2);

        bank.writeEmployeeListToFile(bank.getEmployeeList(), "src/main/resources/employeeList.txt");

        bank.readCustomerListFromFile(bank.getCustomerList(), "src/main/resources/customerList.txt");
        bank.readUnlimitedAccountListFromFile(bank.getUnlimitedAccountList(), "src/main/resources/unlimitedAccountList.txt");

        Customer ct1 = new Customer("Customer 1", "Nam", "12/01/2001", "HCM", "123");
        Customer ct2 = new Customer("Customer 2", "nam", "19/11/2001", "HCM", "123213");

        UnlimitedAccount at3 = new UnlimitedAccount(ct2, 111111);
        UnlimitedAccount at4 = new UnlimitedAccount(ct2, 111001);
        bank.addCustomer(ct1, ct2);
        bank.addUnlimitedAccount(at3, at4);

        bank.displayCustomerList();
        bank.displayEmployeeList();
        bank.displayAccountList();

        bank.writeCustomerListToFile(bank.getCustomerList(), "src/main/resources/customerList.txt");
        bank.writeUnlimitedAccountListToFile(bank.getUnlimitedAccountList(), "src/main/resources/unlimitedAccountList.txt");
        int option;
        double amount;
        do {
            Bank.menu();
            do {
                System.out.print("Nhap lua chon cua ban: ");
                option = Bank.getUserSelection(1, 8);
            } while (option == -1);

            switch (option) {
                case 1:
                    Customer c1 = new Customer();
                    c1.input();
                    c1.display();
                    bank.addCustomer(c1);

                    UnlimitedAccount ac1 = new UnlimitedAccount(c1);
                    ac1.input();
                    ac1.display();
                    bank.addUnlimitedAccount(ac1);

                    break;

                case 2:
                    bank.signIn();

                    if (bank.getSignedInCustomer() != null) {

                        // Hiển thị menu cho khách hàng
                        do {

                            Bank.cusMenu2();

                            do {
                                System.out.print("Nhap lua chon cua ban: ");
                                option = Bank.getUserSelection(1, 8);
                            } while (option == -1);

                            switch (option) {
                                case 1:
                                    bank.getSignedInCustomer().display();
                                    break;

                                case 2:
                                    bank.getSignedInCustomer().displayAccList();
                                    break;
                                case 3:
                                    System.out.print("Nhap mat khau moi: ");
                                    bank.getCustomerList().stream().filter(a -> a.equals(bank.getSignedInPer())).findFirst().get().setPassword(Configuration.sc.nextLine());
                                    System.out.println("=>Doi mat khau thanh cong!");
                                    break;
                                case 4:
                                    //tao tai khoan co ky han

                                    TaiKhoanCoKyHan taiKhoan = new TaiKhoanCoKyHan(bank.getSignedInCustomer());
                                    taiKhoan.input();
                                    taiKhoan.display();
                                    bank.addTaiKhoanCoKyHan(taiKhoan);
                                    break;
                                case 5:
                                    System.out.print("Nhap ma tai khoan muon gui: ");
                                    String ma = Configuration.sc.nextLine();
                                    // Sử dụng Optional<Account> để xử lý kết quả có hoặc không
                                    Optional<Account> accFound = bank.getSignedInCustomer().getAccList().stream()
                                            .filter(a -> a.getAccountID().equals(ma))
                                            .findFirst();

                                    if (accFound.isPresent()) {
                                        System.out.print("So tien muon gui: ");
                                        amount = Double.parseDouble(Configuration.sc.nextLine());
                                        accFound.get().deposit(amount);
                                    } else {
                                        System.out.println("Khong tim thay tai khoan co ma: " + ma);
                                    }

                                    break;

                                case 6:
                                    //rut tien
                                    System.out.print("Nhap ma tai khoan muon rut: ");
                                    String maTaiKhoanRutTien = Configuration.sc.nextLine();
                                    // Sử dụng Optional<Account> để xử lý kết quả có hoặc không
                                    Optional<Account> taiKhoanRutTienTimThay = bank.getSignedInCustomer().getAccList().stream()
                                            .filter(a -> a.getAccountID().equals(maTaiKhoanRutTien))
                                            .findFirst();

                                    if (taiKhoanRutTienTimThay.isPresent()) {
                                        System.out.print("So tien muon rut: ");
                                        amount = Double.parseDouble(Configuration.sc.nextLine());
                                        taiKhoanRutTienTimThay.get().withdraw(amount);
                                    } else {
                                        System.out.println("Khong tim thay tai khoan co ma: " + maTaiKhoanRutTien);
                                    }
                                    break;
                                case 7:
                                    //tinh tien lai
                                    System.out.print("Nhap ma tai khoan can tinh lai: ");
                                    String maTaiKhoanTinhLai = Configuration.sc.nextLine();
                                    // Sử dụng Optional<Account> để xử lý kết quả có hoặc không
                                    Optional<Account> taiKhoanTinhLaiTimThay = bank.getSignedInCustomer().getAccList().stream()
                                            .filter(a -> a.getAccountID().equals(maTaiKhoanTinhLai))
                                            .findFirst();

                                    if (taiKhoanTinhLaiTimThay.isPresent()) {
                                        taiKhoanTinhLaiTimThay.get().tinhTienLai();
                                    } else {
                                        System.out.println("Khong tim thay tai khoan co ma: " + maTaiKhoanTinhLai);
                                    }
                                    break;
                                case 8:
                                    bank.signOut();
                                    break;
                            }

                        } while (option != 8);

                    } else if (bank.getSignedInEmployee() != null) {

                        // Hiển thị menu cho nhân viên
                        do {

                            Bank.emMenu2();

                            do {
                                System.out.print("Nhap lua chon cua ban: ");
                                option = Bank.getUserSelection(1, 10);
                            } while (option == -1);

                            switch (option) {
                                case 1:
                                    bank.displayCustomerList();
                                    break;
                                case 2:
                                    bank.displayAccountList();
                                    break;
                                case 3:
                                    Customer c2 = new Customer();
                                    c2.input();
                                    c2.display();
                                    bank.addCustomer(c2);

                                    UnlimitedAccount ac2 = new UnlimitedAccount(c2);
                                    ac2.input();
                                    ac2.display();
                                    bank.addUnlimitedAccount(ac2);
                                    break;
                                case 4:
                                    System.out.print("Nhap ma khach hang can xoa: ");
                                    String ss = Configuration.sc.nextLine();

                                    // Tìm khách hàng cần xóa trong danh sách khách hàng
                                    Customer customerToDelete = bank.getCustomerList().stream()
                                            .filter(customer -> customer.getCustomerID().equals(ss))
                                            .findFirst().orElse(null);

                                    if (customerToDelete != null) {
                                        // Tạo một danh sách mới để chứa các tài khoản cần xóa
                                        List<Account> accountsToDelete = new ArrayList<>(customerToDelete.getAccList());

                                        // Xóa tất cả các tài khoản của khách hàng khỏi danh sách chung
                                        bank.getUnlimitedAccountList().removeAll(accountsToDelete);

                                        // Xóa khách hàng khỏi danh sách khách hàng
                                        bank.getCustomerList().remove(customerToDelete);

                                        System.out.println("Xoa khach hang thanh cong.");
                                    } else {
                                        System.out.println("Khong tim thay khach hang co ma: " + ss);
                                        // Thêm các thao tác bạn muốn thực hiện khi không tìm thấy khách hàng
                                    }
                                    break;

                                case 5:
                                    System.out.print("Them tai khoan cho khach hang co ma: ");
                                    String ma = Configuration.sc.nextLine();
                                    boolean foundCustomer = false;  // Biến cờ để kiểm tra xem có khách hàng nào được tìm thấy hay không

                                    for (Customer c : bank.getCustomerList()) {
                                        if (c.getCustomerID().equals(ma)) {
                                            // Xử lý khi tìm thấy khách hàng
                                            foundCustomer = true;
                                            TaiKhoanCoKyHan taiKhoan = new TaiKhoanCoKyHan(bank.getSignedInCustomer());
                                            taiKhoan.input();
                                            taiKhoan.display();
                                            bank.addTaiKhoanCoKyHan(taiKhoan);
                                            break;
                                        }
                                    }

                                    if (!foundCustomer) {
                                        System.out.println("=>Khong tim thay khach hang!");
                                    }
                                    break;
                                case 6:
                                    bank.getUnlimitedAccountList().forEach(a -> a.display());
                                    bank.getTaiKhoanCoKyHanList().forEach(a -> a.display());
                                    System.out.print("Nhap ten ma cua tai khoan can xoa: ");
                                    String matk = Configuration.sc.nextLine();

                                    boolean found = false;  // Biến cờ để kiểm tra xem có tìm thấy tài khoản hay không

                                    for (Account a : bank.getUnlimitedAccountList()) {
                                        if (a.getAccountID().equals(matk)) {
                                            bank.getUnlimitedAccountList().remove(a);
                                            found = true;  // Đặt biến cờ thành true khi tìm thấy tài khoản
                                            System.out.println("=>Xoa thanh cong");
                                            break;
                                        }
                                    }

                                    if (!found) {
                                        for (Account a : bank.getTaiKhoanCoKyHanList()) {
                                            if (a.getAccountID().equals(matk)) {
                                                bank.getTaiKhoanCoKyHanList().remove(a);
                                                found = true;  // Đặt biến cờ thành true khi tìm thấy tài khoản
                                                System.out.println("=>Xoa thanh cong");
                                                break;
                                            }
                                        }
                                    }

                                    // Kiểm tra biến cờ để xuất thông báo phù hợp
                                    if (!found) {
                                        System.out.println("=>Khong tim thay tai khoan");
                                    }

                                    break;
                                case 7:
                                    System.out.print("Nhap ten/ma khach hang can tim: ");
                                    String sc7 = Configuration.sc.nextLine();
                                    List<Customer> foundCus = bank.searchCustomer(sc7);
                                    if (foundCus != null) {
                                        System.out.println("=>Tim thay: ");
                                        foundCus.forEach(a -> a.display());
                                    } else {
                                        System.out.println("=>Khong tim thay!");
                                    }
                                    break;
                                case 8:
                                    System.out.print("Nhap ma khach hang: ");
                                    String sc8 = Configuration.sc.nextLine();
                                    List<Account> foundCusAcc = bank.searchCusAcc(sc8);
                                    if (!foundCusAcc.isEmpty()) {
                                        System.out.println("=>Danh sach tai khoan cua " + sc8);
                                        foundCusAcc.forEach(a -> a.display());
                                    }
                                    break;
                                case 9:
                                    bank.sortCustomersByTotalDepositDescending();
                                    bank.getCustomerList().forEach(c
                                            -> {
                                        c.display();
                                        System.out.println("\nTong tien: " + c.getTotalDeposite());

                                    }
                                    );
                                    break;

                                case 10:
                                    bank.signOut();
                                    break;
                            }
                        } while (option != 10);

                    }

                    break;
                case 3:
                    System.out.println("~~~~~Dang nhap");
                    bank.signIn();
                    if (bank.getSignedInCustomer() != null) {
                        do {
                            System.out.println("\n1. Gui tien");
                            System.out.println("2. Rut tien");
                            System.out.println("3. Dang xuat");

                            do {
                                System.out.print("Nhap lua chon cua ban: ");
                                option = Bank.getUserSelection(1, 3);
                            } while (option == -1);

                            switch (option) {
                                case 1:
                                    System.out.print("Nhap ma tai khoan muon gui: ");
                                    String ma = Configuration.sc.nextLine();
                                    // Sử dụng Optional<Account> để xử lý kết quả có hoặc không
                                    Optional<Account> accFound = bank.getSignedInCustomer().getAccList().stream()
                                            .filter(a -> a.getAccountID().equals(ma))
                                            .findFirst();

                                    if (accFound.isPresent()) {
                                        System.out.print("So tien muon gui: ");
                                        amount = Double.parseDouble(Configuration.sc.nextLine());
                                        accFound.get().deposit(amount);
                                    } else {
                                        System.out.println("Khong tim thay tai khoan co ma: " + ma);
                                    }

                                    break;
                                case 2:
                                    //rut tien 
                                    System.out.print("Nhap ma tai khoan muon rut: ");
                                    String maTaiKhoanRutTien = Configuration.sc.nextLine();
                                    // Sử dụng Optional<Account> để xử lý kết quả có hoặc không
                                    Optional<Account> taiKhoanRutTienTimThay = bank.getSignedInCustomer().getAccList().stream()
                                            .filter(a -> a.getAccountID().equals(maTaiKhoanRutTien))
                                            .findFirst();

                                    if (taiKhoanRutTienTimThay.isPresent()) {
                                        System.out.print("So tien muon rut: ");
                                        amount = Double.parseDouble(Configuration.sc.nextLine());
                                        taiKhoanRutTienTimThay.get().withdraw(amount);
                                    } else {
                                        System.out.println("Khong tim thay tai khoan co ma: " + maTaiKhoanRutTien);
                                    }
                                    break;
                                case 3:
                                    bank.signOut();
                                    break;

                            }
                        } while (option != 3);
                    }
                    break;
                case 4:
                    System.out.println("~~~~~Dang nhap");
                    bank.signIn();
                    if (bank.getSignedInCustomer() != null) {
                        //tinh tien lai
                        System.out.print("Nhap ma tai khoan can tinh lai: ");
                        String maTaiKhoanTinhLai = Configuration.sc.nextLine();
                        // Sử dụng Optional<Account> để xử lý kết quả có hoặc không
                        Optional<Account> taiKhoanTinhLaiTimThay = bank.getSignedInCustomer().getAccList().stream()
                                .filter(a -> a.getAccountID().equals(maTaiKhoanTinhLai))
                                .findFirst();

                        if (taiKhoanTinhLaiTimThay.isPresent()) {
                            taiKhoanTinhLaiTimThay.get().tinhTienLai();
                        } else {
                            System.out.println("Khong tim thay tai khoan co ma: " + maTaiKhoanTinhLai);
                        }
                    }
                    break;
                case 5:
                    bank.signOut();
                    bank.writeCustomerListToFile(bank.getCustomerList(), "src/main/resources/customerList.txt");
                    bank.writeUnlimitedAccountListToFile(bank.getUnlimitedAccountList(), "src/main/resources/unlimitedAccountList.txt");
                    System.out.println("\n\t=====Goodbye=====\n");
                    Thread.sleep(2500);
                    break;
            }
        } while (option != 5);
    }

}
