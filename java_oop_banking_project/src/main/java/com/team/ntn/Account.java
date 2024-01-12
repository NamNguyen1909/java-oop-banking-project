/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team.ntn;

import java.util.Objects;
import java.util.Random;

/**
 *
 * @author THANH_NAM
 */
public class Account {

    private static int count;
    protected Person user;
    protected String accountID;

    public Account(Customer customer) {
        this.user = customer;
        customer.getAccList().add(this);
    }

    public Account(Employee employee) {
        this.user = employee;
    }

    public String generateAccountId() {
        return String.format("%06d", ++count);
    }

    public void display() {
        System.out.println("\n\tMa tai khoan: " + getAccountID());
    }

    public void input() throws InterruptedException {

    }

    public void deposit(double amount) {
    }

    public void withdraw(double amount) {
    }

    public void tinhTienLai() {
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Account otherAccount = (Account) obj;

        // So sánh các thuộc tính quan trọng
        return Objects.equals(accountID, otherAccount.accountID);
    }

    @Override
    public int hashCode() {
        // Tạo mã băm dựa trên các thuộc tính quan trọng
        return Objects.hash(accountID);
    }

    public double getBalance() {
        return 0;
    }

    public void setBalance(double balance) {
    }

    /**
     * @return the user
     */
    public Person getUser() {
        return user;
    }

    /**
     * @param customer the user to set
     */
    public void setUser(Person user) {
        this.user = user;
    }

    /**
     * @return the accountID
     */
    public String getAccountID() {
        return accountID;
    }

    /**
     * @param accountID the accountID to set
     */
    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

}
