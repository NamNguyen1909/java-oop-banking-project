/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.team.java_oop_banking_project;

import com.team.ntn.*;

/**
 *
 * @author THANH_NAM
 */
public class Java_oop_banking_project {

    public static void main(String[] args) throws Exception {
        Bank bank = new Bank();
        Employee p1 = new Employee("Thanh Nam", "Nam", "19/09/2004", "Ben Tre", "1234567890");
        Employee p2 = new Employee("Hoang Phuc", "nam", "23/02/2001", "Ben Tre", "312312331");
        Account a1 = new EAccount(p1, "admin");
        Account a2 = new EAccount(p2, "admin");

        bank.addEmployee(p1, p2);
        bank.addAccount(a1, a2);
        bank.displayEmployeeList();

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
                    Customer customer = new Customer();
                    customer.input();
                    customer.display();
                    bank.addCustomer(customer);

                    Account account = new Account(customer);
                    account.inputAccount();
                    customer.add(account);
                    account.display();
                    bank.addAccount(account);

                    break;
                case 2:
                    bank.signIn();

                    if (bank.getSignedInCustomer() != null) {

                        // Hiển thị menu cho khách hàng
                        do {

                            Bank.cusMenu2();

                            do {
                                System.out.print("Nhap lua chon cua ban: ");
                                option = Bank.getUserSelection(1, 6);
                            } while (option == -1);

                            switch (option) {
                                case 1:
                                    bank.getSignedInCustomer().display();
                                    break;

                                case 2:
                                    bank.getSignedInCustomer().displayAccList();
                                    break;
                                case 3:
                                    System.out.print("So tien muon gui: ");
                                    amount=Float.parseFloat(Configuration.sc.nextLine());
                                    bank.getCustomerList().stream().filter(c -> c.equals(bank.getSignedInCustomer())).findFirst().get().getAccList().stream().filter(a -> a.equals(bank.getSignedInAcc())).findFirst().get().deposit(amount);
                                    break;
                                default:
                                    throw new AssertionError();
                            }

                        } while (option != 6);

                    } else if (bank.getSignedInEmployee() !=null) {
  
                        // Hiển thị menu cho nhân viên
                        do {

                            Bank.emMenu2();

                            do {
                                System.out.print("Nhap lua chon cua ban: ");
                                option = Bank.getUserSelection(1, 8);
                            } while (option == -1);

                        } while (option != 6);

                    }

                    break;
                case 3:
                    break;
            }
        } while (option != 5);

    }
}
