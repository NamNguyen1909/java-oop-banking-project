/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.team.java_oop_banking_project;

import com.team.ntn.*;

/**
 *
 * @author THANH_NAM
 */
public class Java_oop_banking_project {

    public static void main(String[] args) throws Exception {
        
        //Sau khi tao tai khoan nhap thong tin,
        //se xuat tat ca thong tin lai 1 lan va co option sua chua /xac nhan
        // them menu sua chua cac thuoc tinh cua Account... thong tin cu->thong tin moi
        
        
        //test
        
        //con thieu kiem tra tuoi tao tai khoan
        Customer  u1= new Customer("Thanh Nam", "Nam", "19/09/2004", "Ben Tre", "123456789");
        Customer u2= new Customer();
        
        u2.inputCustomer();
        
        Account tk1= new Account(u1, 100000, "asdasdasdad08312**");
        Account tk2= new Account(u2);
        Account tk3= new Account(u1, 1503565000, "tienhoilo@@223");
        u1.add(tk1,tk3);
        u2.add(tk2);
        //tai khoan phai thuoc customer truoc thi username moi dc gan ma tao tai khoan tiep
        tk2.inputAccount();
        

        
        Bank dskh= new Bank();
        
        dskh.addCustomer(u1,u2);
        dskh.addAccount();
        dskh.displayCustomerList();
        dskh.displayAccountList();
        
        
        
        
        
        
        
    }
}
