/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team.ntn;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 *
 * @author THANH_NAM
 */
public class Customer extends Person {

    private static int count;
    private String customerID;
    private List<Account> accList = new ArrayList<>();
    private String username;
    private String password;

    public Customer() {
        this.customerID = generateCustomerID();
        setUsername(getCustomerID());
        setPassword(initPassword());
    }

    public Customer(String fullName, String gender, String dateOfBirth, String hometown, String IDCard) throws Exception {
        super(fullName, gender, dateOfBirth, hometown, IDCard);
        this.customerID = generateCustomerID();
        setUsername(getCustomerID());
        setPassword(initPassword());
    }

    // Constructor chỉ sử dụng khi đọc từ file
    public Customer(String fullName, String gender, String dateOfBirth, String hometown, String IDCard, String customerID, String username, String password) throws Exception {
        super(fullName, gender, dateOfBirth, hometown, IDCard);
        this.customerID = customerID;
        this.username = username;
        this.password = password;
    }

    public void add(Account... a) {
        this.accList.addAll(Arrays.asList(a));
    }

    /**
     *
     * @throws InterruptedException
     */
    @Override
    public void input() throws InterruptedException {
        System.out.println("\n~~~~~Nhap khach hang");
        super.input();
        System.out.printf("\nMa khach hang: %s\nUsername: %s\tPassword: %s" + this.customerID, this.username, this.password);

        System.out.println("=>Dang ky khach hang moi thanh cong!!!");
        Thread.sleep(1000);
    }

    public static String generateCustomerID() {
        // Lấy ngày tháng năm hiện tại
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        String soThuTu = String.format("%04d", ++count);
        String maKhachHang = dateFormat.format(currentDate) + soThuTu;
        return maKhachHang;
    }

    public void displayAccList() {
        this.accList.forEach(tk -> tk.display());
    }

    @Override
    public void display() {
        super.display();
        System.out.printf("\tMa khach hang: %s", this.customerID);
        System.out.printf("\nUsername: %s \t\tPassword: %s\n",
                this.username, this.password);
    }

    public void displayAll() {
        this.display();
        this.displayAccList();
    }

    public float getTotalDeposite() {
        return (float) this.getAccList().stream().mapToDouble(Account::getBalance).sum();
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        //kiem tra this và obj co phai cung 1 doi tuong 
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        // kiem tra co khac class hoac la null khong
        //tranh loi NullPointerException
        Customer customer = (Customer) obj;
        return Objects.equals(customerID, customer.customerID)
                && Objects.equals(username, customer.username)
                && Objects.equals(password, customer.password);
    }

    /**
     * @return the accList
     */
    public List<Account> getAccList() {
        return accList;
    }

    /**
     * @param accList the accList to set
     */
    public void setAccList(List<Account> accList) {
        this.accList = accList;
    }

    /**
     * @return the customerID
     */
    @Override
    public String getCustomerID() {
        return customerID;
    }

    /**
     * @param customerID the customerID to set
     */
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
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
