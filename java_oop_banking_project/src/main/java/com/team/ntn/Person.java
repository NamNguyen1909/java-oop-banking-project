/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team.ntn;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author THANH_NAM
 */
public class Person {

    protected String fullName;
    protected String gender;
    protected LocalDate dateOfBirth;
    protected String hometown;
    protected String IDCard;

    public Person() {
    }

    public Person(String fullName, String gender, String dateOfBirth, String hometown, String IDCard) throws Exception {
        this.fullName = fullName;
        setGender(gender);
        this.dateOfBirth = LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern(Configuration.DATE_FORMAT));
        this.hometown = hometown;
        setIDCard(IDCard);
    }

    public void input() throws InterruptedException {
        try {
            System.out.print("Ho ten: ");
            this.fullName = Configuration.sc.nextLine();

            LocalDate inputDate = null;
            boolean validDate = false;
            do {
                System.out.print("Ngay sinh: ");
                String ngaySinhInput = Configuration.sc.nextLine();

                // Them so 0 vao truoc ngay va thang neu chung chi co 1 chu so
                ngaySinhInput = ngaySinhInput.replaceAll("(?<!\\d)(\\d)(?!\\d)", "0$1");

                try {
                    inputDate = LocalDate.parse(ngaySinhInput, DateTimeFormatter.ofPattern(Configuration.DATE_FORMAT));
                    validDate = isAgeValid(inputDate, 18);
                    setDateOfBirth(inputDate);
                } catch (Exception e) {
                    System.out.println("Ngay sinh khong hop le. Vui long nhap lai.");
                }
            } while (!validDate);

            // Vòng lặp để kiểm tra và yêu cầu nhập lại giới tính đến khi hợp lệ
            String inputGender = null;
            do {
                try {
                    System.out.print("Gioi tinh(Nam/Nu): ");
                    //thrm trim de loai bo khoang trang truoc va sau 
                    inputGender = Configuration.sc.nextLine().trim();
                    this.setGender(inputGender);
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            } while (!isValidGender(inputGender));

            System.out.print("Que quan: ");
            this.hometown = Configuration.sc.nextLine();

            do {
                try {
                    System.out.print("CCCD: ");
                    setIDCard(Configuration.sc.nextLine());
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                    continue; // Nhập lại nếu có lỗi
                }
            } while (IDCard == null || IDCard.isEmpty());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    //kiem tra tuoi nhap vao co hop le khong 
    protected boolean isAgeValid(LocalDate inputDate, int minAge) {
        LocalDate currentDate = LocalDate.now();

        if (inputDate.isBefore(currentDate)) {
            int age = Period.between(inputDate, currentDate).getYears();
            return age >= minAge;
        } else {
            return false; // Ngày sinh không thể là trong tương lai
        }
    }

    // Phương thức kiểm tra giới tính có hợp lệ hay không
    protected boolean isValidGender(String gender) {
        switch (gender.toLowerCase()) {
            case "nam" -> {
                return true;
            }
            case "nu" -> {
                return true;
            }
            default -> {
                return false;
            }
        }
    }

    public void display() {
        System.out.println("\n=============================================================================");

        System.out.printf("\n\tHo ten: %s\tNgay sinh: %s\tGioi tinh: %s\n\tQue quan: %s\t\t\tCCCD: %s\n",
                this.fullName, this.dateOfBirth.format(DateTimeFormatter.ofPattern(Configuration.DATE_FORMAT)),
                this.gender, this.hometown, this.IDCard);
    }

    public String getUserId() {
        if (this instanceof Customer) {
            return ((Customer) this).getCustomerID();
        } else if (this instanceof Employee) {
            // Assume Employee also has a method getEmployeeID
            return ((Employee) this).getEmployeeID();
        } else {
            // Trường hợp khác (nếu có)
            return null;
        }
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
            if (isValidGender(gender)) {
                switch (gender.toLowerCase()) {
                    case "nam" ->
                        this.gender = "Nam";
                    case "nu" ->
                        this.gender = "Nu";
                }
            } else {
                throw new Exception("Gioi tinh khong hop le!\n");
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
     * @throws Exception nếu tuổi không đủ 18
     */
    public void setDateOfBirth(LocalDate dateOfBirth) throws Exception {
        if (isAgeValid(dateOfBirth, 18)) {
            this.dateOfBirth = dateOfBirth;
        } else {
            throw new Exception("Tuoi phai lon hon hoac bang 18!\n");
        }
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
     * @throws Exception nếu CCCD không hợp lệ
     */
    public void setIDCard(String IDCard) throws Exception {
        // Kiểm tra xem IDCard chỉ chứa các ký tự số hay không
        if (IDCard.matches("\\d+")) {
            this.IDCard = IDCard;
        } else {
            throw new Exception("CCCD khong hop le!\n");
        }
    }

    public String getEmployeeID() {
        return null;
    }
    
    public String getCustomerID() {
        return null;
    }
}
