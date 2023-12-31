/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team.ntn;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author THANH_NAM
 */
public class KhachHang {

    private String hoTen;
    private String gioiTinh;
    private LocalDate ngaySinh;
    private String queQuan;
    private String CCCD;
    private List<TaiKhoan> dstk = new ArrayList<>();

    public KhachHang() {
    }

    public KhachHang(String hoTen, String gioiTinh, String ngaySinh, String queQuan, String CCCD) {
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = LocalDate.parse(ngaySinh, DateTimeFormatter.ofPattern(CauHinh.DATE_FORMAT));
        this.queQuan = queQuan;
        this.CCCD = CCCD;
    }

    public void hienThi() {
        System.out.printf("\n\tHo ten: %s\tNgay sinh: %s\tGioi tinh: %s\n\tQue quan: %s\tCCCD: %s\n",
                this.hoTen, this.ngaySinh.format(DateTimeFormatter.ofPattern(CauHinh.DATE_FORMAT)),
                this.gioiTinh, this.queQuan, this.CCCD);
        
        //con thieu xuat ds tai khoan
    }

    public void nhap1KhachHang() throws Exception {
        System.out.print("Ho ten: ");
        this.hoTen = CauHinh.sc.nextLine();

        System.out.print("Ngay sinh: ");
        this.ngaySinh = LocalDate.parse(CauHinh.sc.nextLine(), DateTimeFormatter.ofPattern(CauHinh.DATE_FORMAT));

        System.out.print("Gioi tinh(Nam/Nu): ");
        this.setGioiTinh(CauHinh.sc.nextLine());
        
        System.out.print("Que quan: ");
        this.queQuan= CauHinh.sc.nextLine();
        
        System.out.print("CCCD: ");
        this.CCCD=CauHinh.sc.nextLine();
    }

    public void themTaiKhoan(TaiKhoan... a) {
        this.dstk.addAll(Arrays.asList(a));
    }

    /**
     * @return the hoTen
     */
    public String getHoTen() {
        return hoTen;
    }

    /**
     * @param hoTen the hoTen to set
     */
    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    /**
     * @return the gioiTinh
     */
    public String getGioiTinh() {
        return gioiTinh;
    }

    /**
     * @param gioiTinh the gioiTinh to set
     */
    public void setGioiTinh(String gioiTinh) throws Exception {
        if (null == gioiTinh) {
            this.gioiTinh = "Khong ro";
        } else {
            switch (gioiTinh) {
                case "Nam":
                    this.gioiTinh = "Nam";
                    break;
                case "Nu":
                    this.gioiTinh = "Nu";
                    break;
                default:
                    throw new Exception("Gioi tinh khong hop le");
            }
        }
    }

    /**
     * @return the ngaySinh
     */
    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    /**
     * @param ngaySinh the ngaySinh to set
     */
    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    /**
     * @return the queQuan
     */
    public String getQueQuan() {
        return queQuan;
    }

    /**
     * @param queQuan the queQuan to set
     */
    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    /**
     * @return the CCCD
     */
    public String getCCCD() {
        return CCCD;
    }

    /**
     * @param CCCD the CCCD to set
     */
    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

}
