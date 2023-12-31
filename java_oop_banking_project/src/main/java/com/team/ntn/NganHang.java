/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team.ntn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author THANH_NAM
 */
public class NganHang {

    private List<KhachHang> dskh = new ArrayList<>();

    public NganHang() {
    }

    public void themKhachHang(KhachHang... a) {
        this.dskh.addAll(Arrays.asList(a));
    }
    
    public void hienThi() {
        this.dskh.forEach(u->u.hienThi());
    }

    /**
     * @return the dskh
     */
    public List<KhachHang> getDskh() {
        return dskh;
    }

    /**
     * @param dskh the dskh to set
     */
    public void setDskh(List<KhachHang> dskh) {
        this.dskh = dskh;
    }

}
