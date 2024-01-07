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
        
        bank.addEmployee(p1,p2);
        bank.displayEmployeeList();

        int option;
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
                    account.display();
                    bank.addAccount(account);

                    break;
                case 2:
                    bank.signIn();
                    break;
                case 3:
                    break;
            }
        } while (option != 5);

    }
}
