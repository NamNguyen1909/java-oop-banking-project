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

    private String maKhachHang;
    private KhachHang khachHang;
    private float soDu;
    private String username;
    private String password;

    public TaiKhoan() {
    }

    public TaiKhoan(String maKhachHang, KhachHang khachHang, float soDu, String username, String password) {
        this.maKhachHang = maKhachHang;
        //maKhachHang do ngang hang cap: dang thieu
        this.khachHang = khachHang;
        this.soDu = soDu;
        this.username = username;
        this.password = password;
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
     */
    public void setSoDu(float soDu) {
        this.soDu = soDu;
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
