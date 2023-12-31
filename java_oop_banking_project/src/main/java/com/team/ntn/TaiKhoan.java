/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team.ntn;

/**
 *
 * @author THANH_NAM
 */
public class TaiKhoan {

    private KhachHang khachHang;
    private float soDu;
    private String username;
    private String password;

    public TaiKhoan(KhachHang khachHang) {
        this.khachHang = khachHang;
        this.username = this.khachHang.getMaKhachHang();

    }

    public TaiKhoan(KhachHang khachHang, float soDu, String password) throws Exception {
        this.khachHang = khachHang;
        setSoDu(soDu);
        this.username = this.khachHang.getMaKhachHang();
        this.password = password;
    }

    public void hienThi() {
        System.out.println("\n----------------------------------------------------------------------------");
        System.out.printf("\nUsername: %s \t\tPassword: %s\nSo du: %.1f",
                this.username, this.password, this.soDu);
    }

    public void tao1TaiKhoan() {
        
        System.out.println("~~~~~Nhap tai khoan ");
        System.out.printf("Username: %s\n", this.username);

        System.out.print("Password: ");
        this.password = CauHinh.sc.nextLine();

        try {
            System.out.print("So tien gui: ");
            float soTien = Float.parseFloat(CauHinh.sc.nextLine());
            setSoDu(soTien);
        } catch (NumberFormatException e) {
            System.err.println("Lỗi định dạng số. Hãy nhập số hợp lệ.");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
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

    /**
     * @return the khachHang
     */
    public KhachHang getKhachHang() {
        return khachHang;
    }

    /**
     * @param khachHang the khachHang to set
     */
    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    /**
     * @return the soDu
     */
    public float getSoDu() {
        return soDu;
    }

    /**
     * @param soDu the soDu to set
     * @throws Exception if soDu không đạt điều kiện
     */
    public void setSoDu(float soDu) throws Exception {
        if (soDu >= 50000) {
            this.soDu = soDu;
        } else {
            throw new Exception("So tien khong dat muc toi thieu!");
        }
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
        if (isStrongPassword(password)) {
            this.password = password;
        } else {
            System.out.println("Mật khẩu không đủ mạnh. Yêu cầu ít nhất 8 ký tự, bao gồm chữ cái, chữ số và ký tự đặc biệt.");
        }
    }

}
