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

/**
 *
 * @author THANH_NAM
 */
public class KhachHang {

    private static int dem;
    private String hoTen;
    private String gioiTinh;
    private LocalDate ngaySinh;
    private String queQuan;
    private String CCCD;
    private String maKhachHang;
    private List<TaiKhoan> dstk = new ArrayList<>();

    public KhachHang() {
    }

    public KhachHang(String hoTen, String gioiTinh, String ngaySinh, String queQuan, String CCCD) {
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = LocalDate.parse(ngaySinh, DateTimeFormatter.ofPattern(CauHinh.DATE_FORMAT));
        this.queQuan = queQuan;
        setCCCD(CCCD);
        this.maKhachHang = taoMaKhachHang();
    }

    public void hienThi() {
        System.out.println("\n=============================================================================");
        System.out.printf("\n\tHo ten: %s\tNgay sinh: %s\tGioi tinh: %s\n\tQue quan: %s\tCCCD: %s\n\tMa khach hang: %s\n",
                this.hoTen, this.ngaySinh.format(DateTimeFormatter.ofPattern(CauHinh.DATE_FORMAT)),
                this.gioiTinh, this.queQuan, this.CCCD, this.maKhachHang);

        this.dstk.forEach(tk -> tk.hienThi());
    }

    public static String taoMaKhachHang() {
        // Lấy ngày tháng năm hiện tại
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        String soThuTu = String.format("%04d", dem++);
        String maKhachHang = dateFormat.format(currentDate) + soThuTu;
        return maKhachHang;
    }

    public void nhap1KhachHang() throws Exception {
        System.out.println("~~~~~Nhap khach hang");
        this.maKhachHang = taoMaKhachHang();
        System.out.println("Ma khach hang: " + this.maKhachHang);

        System.out.print("Ho ten: ");
        this.hoTen = CauHinh.sc.nextLine();

        System.out.print("Ngay sinh: ");
        String ngaySinhInput = CauHinh.sc.nextLine();
        // Them so 0 vao truoc ngay va thang neu chung chi co 1 chu so
        ngaySinhInput = ngaySinhInput.replaceAll("(?<!\\d)(\\d)(?!\\d)", "0$1");
        this.ngaySinh = LocalDate.parse(ngaySinhInput, DateTimeFormatter.ofPattern(CauHinh.DATE_FORMAT));

        System.out.print("Gioi tinh(Nam/Nu): ");
        this.setGioiTinh(CauHinh.sc.nextLine());

        System.out.print("Que quan: ");
        this.queQuan = CauHinh.sc.nextLine();

        System.out.print("CCCD: ");
        setCCCD(CauHinh.sc.nextLine());
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
    if (gioiTinh == null) {
        this.gioiTinh = "Khong ro";
    } else {
        switch (gioiTinh.toLowerCase()) {
            case "nam":
                this.gioiTinh = "Nam";
                break;
            case "nu":
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
        // Kiểm tra xem CCCD chỉ chứa các ký tự số hay không
        if (CCCD.matches("\\d+")) {
            this.CCCD = CCCD;
        } else {
            System.out.println("CCCD khong hop le");
        }
    }

    /**
     * @return the dstk
     */
    public List<TaiKhoan> getDstk() {
        return dstk;
    }

    /**
     * @param dstk the dstk to set
     */
    public void setDstk(List<TaiKhoan> dstk) {
        this.dstk = dstk;
    }

    /**
     * @return the maKhachHang
     */
    public String getMaKhachHang() {
        return maKhachHang;
    }

    /**
     * @param maKhachHang the maKhachHang to set
     */
    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

}
