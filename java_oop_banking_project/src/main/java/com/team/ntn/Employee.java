/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team.ntn;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author THANH_NAM
 */
public class Employee extends Person {

    private static int count;
    private String employeeID;
    private EAccount employeeAccount;
    private String username;
    private String password;

    public Employee() {
    }

    public Employee(String fullName, String gender, String dateOfBirth, String hometown, String IDCard, String password) throws Exception {
        super(fullName, gender, dateOfBirth, hometown, IDCard);
        this.employeeID = String.format("%04d", ++count);
        setUsername(getEmployeeID());
        setPassword(password);
    }

    public Employee(String fullName, String gender, String dateOfBirth, String hometown, String IDCard, String employeeID, String username, String password) throws Exception {
        super(fullName, gender, dateOfBirth, hometown, IDCard);
        this.employeeID = employeeID;
        this.username = username;
        this.password = password;
    }

    public void addAcc(EAccount a) {
        setEmployeeAccount(a);
    }

    @Override
    public void display() {
        super.display();
        System.out.printf("\nMa nhan vien: %s \nUsername: %s\tPassword: %s", this.employeeID, this.username, this.password);
        this.employeeAccount.display();
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
        Employee employee = (Employee) obj;
        return Objects.equals(employeeID, employee.employeeID)
                && Objects.equals(username, employee.username)
                && Objects.equals(password, employee.password);
    }

    /**
     * @return the eployeeID
     */
    @Override
    public String getEmployeeID() {
        return employeeID;
    }

    /**
     * @param eployeeID the eployeeID to set
     */
    public void setEmployeeID(String eployeeID) {
        this.employeeID = eployeeID;
    }

    /**
     * @return the employeeAccount
     */
    public EAccount getEmployeeAccount() {
        return employeeAccount;
    }

    /**
     * @param employeeAccount the employeeAccount to set
     */
    public void setEmployeeAccount(EAccount employeeAccount) {
        this.employeeAccount = employeeAccount;
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
