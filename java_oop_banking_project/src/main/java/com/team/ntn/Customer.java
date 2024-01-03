/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team.ntn;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author THANH_NAM
 */
public class Customer {

    private static int count;
    private String fullName;
    private String gender;
    private LocalDate dateOfBirth;
    private String hometown;
    private String IDCard;
    private String customerID;
    private List<Account> accList = new ArrayList<>();

    public Customer() {
    }

    public Customer(String fullName, String gender, String dateOfBirth, String hometown, String IDCard) {
        this.fullName = fullName;
        this.gender = gender;
        this.dateOfBirth = LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern(Configuration.DATE_FORMAT));
        this.hometown = hometown;
        setIDCard(IDCard);
        this.customerID = generateCustomerID();
    }

    public void add(Account... a) {
        this.accList.addAll(Arrays.asList(a));
    }

    public void inputCustomer() throws Exception {
        System.out.println("~~~~~Nhap khach hang");
        this.customerID = generateCustomerID();
        System.out.println("Ma khach hang: " + this.customerID);

        System.out.print("Ho ten: ");
        this.fullName = Configuration.sc.nextLine();

        System.out.print("Ngay sinh: ");
        String ngaySinhInput = Configuration.sc.nextLine();
        // Them so 0 vao truoc ngay va thang neu chung chi co 1 chu so
        ngaySinhInput = ngaySinhInput.replaceAll("(?<!\\d)(\\d)(?!\\d)", "0$1");
        this.dateOfBirth = LocalDate.parse(ngaySinhInput, DateTimeFormatter.ofPattern(Configuration.DATE_FORMAT));

        System.out.print("Gioi tinh(Nam/Nu): ");
        this.setGender(Configuration.sc.nextLine());

        System.out.print("Que quan: ");
        this.hometown = Configuration.sc.nextLine();

        System.out.print("CCCD: ");
        setIDCard(Configuration.sc.nextLine());
    }

    public static String generateCustomerID() {
        // Lấy ngày tháng năm hiện tại
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        String soThuTu = String.format("%04d", count++);
        String maKhachHang = dateFormat.format(currentDate) + soThuTu;
        return maKhachHang;
    }

    public void displayAccList() {
        this.accList.forEach(tk -> tk.display());
    }

    public void display() {
        System.out.println("\n=============================================================================");
        System.out.printf("\n\tHo ten: %s\tNgay sinh: %s\tGioi tinh: %s\n\tQue quan: %s\tCCCD: %s\n\tMa khach hang: %s\n",
                this.fullName, this.dateOfBirth.format(DateTimeFormatter.ofPattern(Configuration.DATE_FORMAT)),
                this.gender, this.hometown, this.IDCard, this.customerID);
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
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) throws Exception {
        if (gender == null) {
            this.gender = "Khong ro";
        } else {
            switch (gender.toLowerCase()) {
                case "nam":
                    this.gender = "Nam";
                    break;
                case "nu":
                    this.gender = "Nu";
                    break;
                default:
                    throw new Exception("Gioi tinh khong hop le");
            }
        }
    }

    /**
     * @return the dateOfBirth
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return the hometown
     */
    public String getHometown() {
        return hometown;
    }

    /**
     * @param hometown the hometown to set
     */
    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    /**
     * @return the IDCard
     */
    public String getIDCard() {
        return IDCard;
    }

    /**
     * @param IDCard the IDCard to set
     */
    public void setIDCard(String IDCard) {
        // Kiểm tra xem IDCard chỉ chứa các ký tự số hay không
        if (IDCard.matches("\\d+")) {
            this.IDCard = IDCard;
        } else {
            System.out.println("CCCD khong hop le");
        }
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
