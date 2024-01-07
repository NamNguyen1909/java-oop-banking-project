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
    private List<Employee> employeeList = new ArrayList<>();

    private List<Account> accountList = new ArrayList<>();

    public Bank() {
    }

    public void addCustomer(Customer... customers) {
        this.customerList.addAll(Arrays.asList(customers));
    }

    public void removeCustomer(Customer customer) {
        this.customerList.remove(customer);
        //so sanh bang equals nen phai override equals
    }

    public void addEmployee(Employee... employees) {
        this.employeeList.addAll(Arrays.asList(employees));
    }

    public void removeEmployee(Employee employees) {
        this.employeeList.remove(employees);
    }

    public void addAccount(Account... accounts) {
        this.accountList.addAll(Arrays.asList(accounts));
    }

    public void removeAccount(Account account) {
        this.accountList.remove(account);
        //so sanh bang equals nen phai override equals
    }

    public void displayCustomerList() {
        System.out.println("\nDanh sach khach hang : ");

        this.customerList.forEach(u -> u.display());
    }

    public void displayEmployeeList() {
        System.out.println("\nDanh sach nhan vien : ");

        this.employeeList.forEach(e -> e.display());
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

    public static void menu() throws InterruptedException {
        Thread.sleep(1000);

        System.out.println("\n\t\t===== MENU =====");
        System.out.println("1. Mo tai khoan");
        System.out.println("2. Dang nhap");
        System.out.println("3. Gui/Rut tien");
        System.out.println("4. Tinh tien lai");
        System.out.println("5. Thoat");
    }

    public static int getUserSelection(int min, int max) {
        String input = Configuration.sc.nextLine();

        if (input.matches("\\d*")) {
            int x = Integer.parseInt(input);
            if (min <= x && x <= max) {
                return x;
            }
        }

        System.out.println("Vui long chon lai!");
        return -1;
    }

    public void signIn() {
        System.out.print("Ten dang nhap: ");
        String username = Configuration.sc.nextLine();
        System.out.print("Mat khau: ");
        String password = Configuration.sc.nextLine();
        if (this.checkSignIn(username, password)) {
            System.out.println("Dang nhap thanh cong, Chao mung " + this.getCustomerList().stream().filter(c -> c.getCustomerID().equals(username)).findFirst().get().getFullName());
        } else {
            System.out.println("Ten dang nhap hoac mat khau khong dung. Vui long thu lai!");
        }

    }

    public boolean checkSignIn(String username, String password) {
        // Sử dụng anyMatch để kiểm tra xem có tài khoản nào khớp với username và password không
        return this.getAccountList().stream()
                .anyMatch(a -> a.getUsername().equals(username) && a.getPassword().equals(password));
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
