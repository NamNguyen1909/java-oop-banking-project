/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team.ntn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
        System.out.println("\nDanh sach khach hang : ");

        this.customerList.forEach(u -> u.display());
    }

    public void displayAccountList() {
        System.out.println("\nDanh sach tai khoan: ");

        this.accountList.forEach(u -> u.display());
    }

    //Tra cứu khách hàng theo họ tên và mã số khách hàng.
    public List<Customer> searchCustomer(String kw) {
        return this.customerList.stream()
                .filter(c -> c.getFullName().contains(kw) || c.getCustomerID().equals(kw))
                .collect(Collectors.toList());
    }

    //Tra cứu danh sách tài khoản của một khách hàng theo mã số khách hàng.
    public List<Account> searchCusAcc(String kw) {
        return this.customerList.stream()
                .filter(c -> c.getCustomerID().equals(kw))
                .findFirst()
                .map(Customer::getAccList)
                .orElse(Collections.emptyList());
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
