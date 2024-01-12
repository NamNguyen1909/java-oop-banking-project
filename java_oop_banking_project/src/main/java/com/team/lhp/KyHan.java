/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.team.lhp;

import java.time.LocalDate;

/**
 *
 * @author lehoangphuc
 */
public enum KyHan {
    MOT_TUAN(7, 2) {
        @Override
        public LocalDate tinhDaoHan(LocalDate d) {
            return d.plusDays(this.khoangThoiGian);
        }

        @Override
        public double tinhLai(double d) {
            return (d * this.laiSuat) / 100 / 12 / 4;
        }
    },
    MOT_THANG(1, 5.5) {
        @Override
        public LocalDate tinhDaoHan(LocalDate d) {
            return d.plusMonths(this.khoangThoiGian);
        }

        @Override
        public double tinhLai(double d) {
            return (d * this.laiSuat) / 100 / 12;
        }

    },
    SAU_THANG(6, 7.5) {
        @Override
        public LocalDate tinhDaoHan(LocalDate d) {
            return d.plusMonths(this.khoangThoiGian);
        }

        @Override
        public double tinhLai(double d) {
            return (d * this.laiSuat) / 100 / 12;
        }

    },
    MUOI_HAI_THANG(12, 7.9) {
        @Override
        public LocalDate tinhDaoHan(LocalDate d) {
            return d.plusMonths(this.khoangThoiGian);
        }

        @Override
        public double tinhLai(double d) {
            return (d * this.laiSuat) / 100 / 12;
        }

    },;

    protected int khoangThoiGian;
    protected double laiSuat;

    private KyHan(int khoangThoiGian, double laiSuat) {
        this.khoangThoiGian = khoangThoiGian;
        this.laiSuat = laiSuat;
    }

    public abstract LocalDate tinhDaoHan(LocalDate d);

    public abstract double tinhLai(double d);
}
