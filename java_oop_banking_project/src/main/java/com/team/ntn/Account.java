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

    protected Person user;
    protected String username;
    protected String password;

    public Account(Customer customer) {
        this.user = customer;
        this.username = customer.getCustomerID();
        customer.getAccList().add(this);
    }

    public Account(Employee employee) {
        this.user = employee;
    }

    public void display() {
        System.out.printf("\nUsername: %s \t\tPassword: %s\n",
                this.username, this.password);
    }

    public void input() throws InterruptedException {

    }

    public String initPassword() {
        Random rand = new Random();
        int ranNum = rand.nextInt(1000000); // Số ngẫu nhiên từ 0 đến 999999
        return String.format("%06d", ranNum); // Định dạng thành chuỗi 6 chữ số
    }

    private boolean isStrongPassword(String password) {
        // Kiểm tra độ dài
        if (password.length() < 8) {
            return false;
        }

        // Kiểm tra chữ cái
        boolean hasLetter = false;
        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) {
                hasLetter = true;
                break;
            }
        }

        // Kiểm tra chữ số
        boolean hasDigit = false;
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                hasDigit = true;
                break;
            }
        }

        // Kiểm tra ký tự đặc biệt
        boolean hasSpecialChar = false;
        for (char c : password.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                hasSpecialChar = true;
                break;
            }
        }

        // Tổng hợp các điều kiện
        return hasLetter && hasDigit && hasSpecialChar;
    }

    public void deposit(double amount) {
    }

    public void withdraw(double amount) {
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
        return Objects.equals(username, otherAccount.username)
                && Objects.equals(password, otherAccount.password);
    }

    @Override
    public int hashCode() {
        // Tạo mã băm dựa trên các thuộc tính quan trọng
        return Objects.hash(username, password);
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
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
//        if (isStrongPassword(password)) {
//            this.password = password;
//        } else {
//            System.out.println("Mat khau khong du manh. Yeu cau it nhat 8 ky tu, bao gom chu cai, chu so và ky tu dac biet.\n");
//        }
    }

}
