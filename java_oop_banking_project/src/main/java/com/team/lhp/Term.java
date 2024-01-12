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
public enum Term {
    ONE_WEEK(7, 2) {
        @Override
        public LocalDate calculateMaturityDate(LocalDate d) {
            return d.plusDays(this.time);
        }

        @Override
        public double calculateInterest(double d) {
            return (d * this.rate) / 100 / 12 / 4;
        }
    },
    ONE_MONTH(1, 5.5) {
        @Override
        public LocalDate calculateMaturityDate(LocalDate d) {
            return d.plusMonths(this.time);
        }

        @Override
        public double calculateInterest(double d) {
            return (d * this.rate) / 100 / 12;
        }

    },
    SIX_MONTHS(6, 7.5) {
        @Override
        public LocalDate calculateMaturityDate(LocalDate d) {
            return d.plusMonths(this.time);
        }

        @Override
        public double calculateInterest(double d) {
            return (d * this.rate) / 100 / 12;
        }

    },
    TWELVE_MONTHS(12, 7.9) {
        @Override
        public LocalDate calculateMaturityDate(LocalDate d) {
            return d.plusMonths(this.time);
        }

        @Override
        public double calculateInterest(double d) {
            return (d * this.rate) / 100 / 12;
        }

    },;

    protected int time;
    protected double rate;

    private Term(int khoangThoiGian, double laiSuat) {
        this.time = khoangThoiGian;
        this.rate = laiSuat;
    }

    public abstract LocalDate calculateMaturityDate(LocalDate d);

    public abstract double calculateInterest(double d);
}
