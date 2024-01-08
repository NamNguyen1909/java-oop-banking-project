/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.team.java_oop_banking_project;

import com.team.ntn.*;
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
        Employee p1 = new Employee("Thanh Nam", "Nam", "19/09/2004", "Ben Tre", "1234567890");
        Employee p2 = new Employee("Hoang Phuc", "nam", "23/02/2001", "Ben Tre", "312312331");
        EAccount a1 = new EAccount(p1, "admin");
        EAccount a2 = new EAccount(p2, "admin");

        bank.addEmployee(p1, p2);
        bank.addEmAcc(a1, a2);
//        bank.displayEmployeeList();

//khac hang va tai khoan tao san de test chuc nang admin
        Customer ct1 = new Customer("Customer 1", "Nam", "12/01/2001", "HCM", "123");
        Customer ct2 = new Customer("Customer 2", "nam", "19/11/2001", "HCM", "123213");

        Account at3 = new Account(ct2, 111111);
        Account at4 = new Account(ct2, 111001);
        Account at5 = new Account(ct1, 2222222);
        Account at6 = new Account(ct1, 2200222);
        bank.addCustomer(ct1, ct2);
        bank.addAccount(at3, at4, at5, at6);
        bank.displayAccountList();

        int option;
        float amount;
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

                    Account ac1 = new Account(c1);
                    ac1.inputAccount();
                    c1.add(ac1);
                    ac1.display();
                    bank.addAccount(ac1);

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
                                    bank.getAccountList().stream().filter(a -> a.equals(bank.getSignedInAcc())).findFirst().get().setPassword(Configuration.sc.nextLine());
                                    break;
                                case 4:
                                    //tao tai khoan co ki han

                                    break;
                                case 5:
                                    System.out.print("So tien muon gui: ");
                                    amount = Float.parseFloat(Configuration.sc.nextLine());
//                                    bank.getCustomerList().stream().filter(c -> c.equals(bank.getSignedInCustomer())).findFirst().get()
//                                            .getAccList().stream().filter(a -> a.equals(bank.getSignedInAcc())).findFirst().get().deposit(amount);
//                                    bank.getAccountList().stream().filter(a -> a.equals(bank.getSignedInAcc())).findFirst().get().deposit(amount);
                                    bank.getSignedInAcc().deposit(amount);
                                    break;
                                case 6:
                                    //rut tien

                                    break;
                                case 7:
                                    //tinh tien lai

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

                                    Account ac2 = new Account(c2);
                                    ac2.inputAccount();
                                    c2.add(ac2);
                                    ac2.display();
                                    bank.addAccount(ac2);
                                    break;
                                case 4:
                                    System.out.print("Nhap ma khach hang can xoa: ");
                                    String ss = Configuration.sc.nextLine();
                                    // Tìm tài khoản của khách hàng cần xóa trong accountList
                                    List<Account> accountsToDelete = bank.getAccountList().stream()
                                            .filter(account -> account.getUsername().equals(ss))
                                            .collect(Collectors.toList());

                                    // Xóa tài khoản khỏi accountList
                                    bank.getAccountList().removeAll(accountsToDelete);

                                    // Tìm khách hàng cần xóa trong customerList
                                    Optional<Customer> customerToDelete = bank.getCustomerList().stream()
                                            .filter(customer -> customer.getCustomerID().equals(ss))
                                            .findFirst();

                                    // Xóa khách hàng khỏi customerList
                                    customerToDelete.ifPresent(customer -> bank.getCustomerList().remove(customer));
                                    break;
                                case 5:
                                    System.out.print("Them tai khoan cho khach hang co ma: ");
                                    String ma = Configuration.sc.nextLine();
                                    for (Customer c : bank.getCustomerList()) {
                                        if (c.getCustomerID().equals(ma)) {
                                            //xu ly them tai khoan co ki han

                                        } else {
                                            System.out.println("=>Khong tim thay khach hang!");
                                        }
                                    }
                                    break;
                                case 6:
                                    System.out.print("Nhap ten dang nhap cua tai khoan can xoa: ");
                                    String tentk = Configuration.sc.nextLine();
                                    System.out.print("Nhap mat khau cua tai khoan can xoa: ");
                                    String matkhautk = Configuration.sc.nextLine();

                                    boolean found = false;  // Biến cờ để kiểm tra xem có tìm thấy tài khoản hay không

                                    for (Account a : bank.getAccountList()) {
                                        if (a.getUsername().equals(tentk) && a.getPassword().equals(matkhautk)) {
                                            bank.removeAccount(a);
                                            found = true;  // Đặt biến cờ thành true khi tìm thấy tài khoản
                                            System.out.println("=>Xoa thanh cong");
                                            break;
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
                                        System.out.println("Danh sach tai khoan cua " + sc8);
                                        foundCusAcc.forEach(a -> a.display());
                                    }
                                    break;
                                case 9:
                                    bank.sortCustomersByTotalDepositDescending();
                                    bank.getCustomerList().forEach(c -> c.displayAll());
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
                                    System.out.print("So tien muon gui: ");
                                    amount = Float.parseFloat(Configuration.sc.nextLine());
                                    bank.getSignedInAcc().deposit(amount);
                                    break;
                                case 2:
                                    //rut tien 

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
                        
                        
                        
                        
                        
                    }
                    break;
                case 5:
                    bank.signOut();
                    System.out.println("/n/t=====Goodbye=====/n");
                    Thread.sleep(2500);
                    break;
            }
        } while (option != 5);

    }
}
