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
public class Employee  extends Person{
    private static int count;
    private String employeeID;
    private EAccount employeeAccount;

    public Employee() {
    }

    public Employee( String fullName, String gender, String dateOfBirth, String hometown, String IDCard ) throws Exception {
        super(fullName, gender, dateOfBirth, hometown, IDCard);
        this.employeeID = String.format("%04d", ++count);
    }
    
    public void addAcc(EAccount a) {
        setEmployeeAccount(a);
    }

    @Override
    public void display() {
        super.display(); 
        System.out.printf("\nMa nhan vien: %s",this.employeeID );
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
        return Objects.equals(employeeID, employee.employeeID);
    }
    

    
    

    /**
     * @return the eployeeID
     */
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
    
    
}
