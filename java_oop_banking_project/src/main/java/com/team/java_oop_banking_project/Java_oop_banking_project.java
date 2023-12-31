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
        // them menu sua chua cac thuoc tinh cua TaiKhoan... thong tin cu->thong tin moi
        
        
        //test
        
        //con thieu kiem tra tuoi tao tai khoan
        KhachHang  u1= new KhachHang("Thanh Nam", "Nam", "19/09/2004", "Ben Tre", "123456789");
        KhachHang u2= new KhachHang();
        
        u2.nhap1KhachHang();
        
        TaiKhoan tk1= new TaiKhoan(u1, 100000, "asdasdasdad");
        TaiKhoan tk2= new TaiKhoan(u2);
        TaiKhoan tk3= new TaiKhoan(u1, 1503565000, "tienhoilo");
        
        tk2.tao1TaiKhoan();
        
        u1.themTaiKhoan(tk1,tk3);
        u2.themTaiKhoan(tk2);
        
        NganHang dskh= new NganHang();
        
        dskh.themKhachHang(u1,u2);
        dskh.hienThi();
        
        
        
        
        
        
        
    }
}
