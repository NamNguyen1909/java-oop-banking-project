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



        Bank dskh = new Bank();

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
                    customer.inputCustomer();
                    customer.display();
                    dskh.addCustomer(customer);

                    Account account = new Account(customer);
                    account.inputAccount();
                    account.display();
                    dskh.addAccount(account);
                    
                    break;
            }
        } while (option != 7);

    }
}
