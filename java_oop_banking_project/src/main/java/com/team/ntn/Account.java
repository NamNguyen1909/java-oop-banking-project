/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team.ntn;

import java.util.Objects;

/**
 *
 * @author THANH_NAM
 */
public class Account {

    protected Customer customer;
    protected float balance;
    protected String username;
    protected String password;

    public Account(Customer customer) {
        this.customer = customer;
        this.username = this.customer.getCustomerID();

    }

    public Account(Customer customer, float balance, String password) throws Exception {
        this.customer = customer;
        if (balance >= 100000) {
            setBalance(balance);
        } else {
            throw new Exception("So tien toi thieu de mo tai khoan la 100.000 dong!\n");
        }
        this.username = this.customer.getCustomerID();
        setPassword(password);
    }

    public void display() {
        System.out.println("\n----------------------------------------------------------------------------");
        System.out.printf("\nUsername: %s \t\tPassword: %s\nSo du: %.1f",
                this.username, this.password, this.balance);
    }

    public void inputAccount() {
        setUsername(this.customer.getCustomerID());
        System.out.println("~~~~~Nhap tai khoan ");
        //hien thi ten tai khoan
        System.out.printf("Username: %s\n", this.username);

        //vong lap nhap+check mat khau
        String pass;
        do {
            System.out.print("Password: ");
            pass = Configuration.sc.nextLine();
            setPassword(pass);
        } while (!isStrongPassword(pass));

        //vong lap check so tien 
        float soTien;
        do {
            try {
                System.out.print("So tien gui: ");
                soTien = Float.parseFloat(Configuration.sc.nextLine());
                if (soTien >= 100000) {
                    setBalance(soTien);
                } else {
                    throw new Exception("So tien toi thieu de mo tai khoan la 100.000 dong!\n");
                }
            } catch (NumberFormatException e) {
                System.err.println("Loi dinh dang so. Hay nhap so hop le.\n");
                continue; // Nhập lại nếu có lỗi định dạng số
            } catch (Exception e) {
                System.err.println(e.getMessage());
                continue; // Nhập lại nếu có lỗi khác
            }

            // Nếu chương trình đã đi đến đây, nghĩa là giá trị soTien hợp lệ
            break; // Thoát khỏi vòng lặp
        } while (true); // Lặp lại nếu giá trị soTien không hợp lệ

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

    public void deposit(float amount) {
        setBalance(this.balance + amount);
        System.out.println("Nap tien thanh cong!");
    }

    public void withdraw(float amount) throws Exception {
        if (this.balance - amount >= 50000) {
            setBalance(this.balance - amount);
            System.out.println("Rut tien thanh cong!");
        } else {
            throw new Exception("So tien toi thieu can de tai khoan la 50000. Rut tien khong thanh cong!");
        }
    }

    public double calculateInterest() {
        return 0;
    }

    public boolean checkTerm() {
        return true;
    }

    public void renewTerm() {

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
        return Float.compare(otherAccount.balance, balance) == 0
                && Objects.equals(username, otherAccount.username)
                && Objects.equals(password, otherAccount.password);
    }

    @Override
    public int hashCode() {
        // Tạo mã băm dựa trên các thuộc tính quan trọng
        return Objects.hash(balance, username, password);
    }

    /**
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * @return the balance
     */
    public float getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(float balance) {
        this.balance = balance;
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
        if (isStrongPassword(password)) {
            this.password = password;
        } else {
            System.out.println("Mat khau khong du manh. Yeu cau it nhat 8 ky tu, bao gom chu cai, chu so và ky tu dac biet.\n");
        }
    }

}
