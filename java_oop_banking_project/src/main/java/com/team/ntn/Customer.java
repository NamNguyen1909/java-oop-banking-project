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

/**
 *
 * @author THANH_NAM
 */
public class Customer extends Person {

    private static int count;
    private String customerID;
    private List<Account> accList = new ArrayList<>();

    public Customer()  {
    }
        
    public Customer(String fullName, String gender, String dateOfBirth, String hometown, String IDCard) throws Exception {
        super(fullName, gender, dateOfBirth, hometown, IDCard);
        this.customerID = generateCustomerID();
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
        this.customerID = generateCustomerID();
        System.out.println("Ma khach hang: " + this.customerID);
        super.input();

        System.out.println("=>Dang ky khach hang moi thanh cong!!!");
        Thread.sleep(1500);
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
        System.out.printf("\tMa khach hang: %s\n", this.customerID);
    }

    public void displayAll() {
        this.display();
        this.displayAccList();
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
        return Objects.equals(customerID, customer.customerID);
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
    public String getCustomerID() {
        return customerID;
    }

    /**
     * @param customerID the customerID to set
     */
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

}
