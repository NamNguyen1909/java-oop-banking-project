/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team.ntn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author THANH_NAM
 */
public class Bank {

    private List<Customer> customerList = new ArrayList<>();
    private List<Account> accountList = new ArrayList<>();

    public Bank() {
    }

    public void addCustomer(Customer... a) {
        this.customerList.addAll(Arrays.asList(a));
    }

    public void removeCustomer(Customer customer) {
        this.customerList.remove(customer);
        //so sanh bang equals nen phai override equals
    }

    public void addAccount() {
        for (Customer customer : this.customerList) {
            this.accountList.addAll(customer.getAccList());
        }
    }

    public void removeAccount(Account account) {
        this.accountList.remove(account);
        //so sanh bang equals nen phai override equals
    }

    public void displayCustomerList() {
        this.customerList.forEach(u -> u.display());
    }

    public void displayAccountList() {
        this.accountList.forEach(u -> u.display());
    }

    /**
     * @return the customerList
     */
    public List<Customer> getCustomerList() {
        return customerList;
    }

    /**
     * @param customerList the customerList to set
     */
    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    /**
     * @return the accountList
     */
    public List<Account> getAccountList() {
        return accountList;
    }

    /**
     * @param accountList the accountList to set
     */
    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

}
