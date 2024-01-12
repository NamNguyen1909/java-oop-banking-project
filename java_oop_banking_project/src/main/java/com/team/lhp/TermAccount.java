/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team.lhp;

import com.team.ntn.*;
//import static com.team.ntn.Configuration.sc;
import java.util.Scanner;
//import static com.team.lhp.Scanner.sc;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
//import java.util.Locale;
import java.util.Objects;

/**
 *
 * @author lehoangphuc
 */
public class TermAccount extends Account {

    private Term term;
    private LocalDate dueDate;
    private double balance;

    public TermAccount(Customer customer) {
        super(customer);
        setAccountID(generateAccountId());
    }

    public TermAccount(Customer customer, double balance, Term kyhan) throws Exception {
        super(customer);
        if (balance >= 100000) {
            setBalance(balance);
        } else {
            throw new Exception("So tien toi thieu de mo tai khoan la 100.000 dong!\n");
        }
        setAccountID(generateAccountId());

        this.term = term;
        this.dueDate = term.calculateMaturityDate(LocalDate.now());
    }

    public TermAccount(Customer customer, double balance, String accountId, Term kyHan, LocalDate ngayDaoHan) throws Exception {
        super(customer);
        if (balance >= 100000) {
           setBalance(balance);
        } else {
            throw new Exception("So tien toi thieu de mo tai khoan la 100.000 dong!\n");
        }
        setAccountID(accountId);

        this.term = term;
        this.dueDate = dueDate;
    }

    @Override
    public void display() {
        System.out.printf("\tMa tai khoan: %s\tSo du: %.1f\n\tKy Han: %s\tNgay dao han: %s\n",
                this.accountID, this.balance, this.term, this.dueDate.format(DateTimeFormatter.ofPattern(Configuration.DATE_FORMAT)));
        System.out.println("\n\n======================================================================================");
    }

    @Override
    public void input() throws InterruptedException {
        do {
            try {
                System.out.print("\nSo tien gui: ");
                double soTien = Float.parseFloat(Configuration.sc.nextLine());
                if (soTien >= 100000) {
                    setBalance(soTien);
                } else {
                    throw new Exception("So tien toi thieu de mo tai khoan la 100.000 dong!\n");
                }
            } catch (NumberFormatException e) { // Loi dinh dang so
                System.err.println("Loi dinh dang so. Hay nhap so hop le.");
                continue;
            } catch (Exception e) { // Loi khac
                System.err.println(e.getMessage());
                continue;
            }
            break;

        } while (true);

        System.out.print("Chon Ky Han [1: MOT TUAN, 2: MOT THANG, 3: SAU THANG, 4: MUOI HAI THANG]: ");

        Scanner sc = new Scanner(System.in);
        int luaChonKyHan = sc.nextInt();

        switch (luaChonKyHan) {
            case 1:
                this.term = Term.ONE_WEEK;
                break;
            case 2:
                this.term = Term.ONE_MONTH;
                break;
            case 3:
                this.term = Term.SIX_MONTHS;
                break;
            case 4:
                this.term = Term.TWELVE_MONTHS;
                break;
            default:
                System.out.println("Khong hop le, ky han mac dinh se la MOT TUAN!");
                term = Term.ONE_WEEK;
                break;
        }

        this.dueDate = term.calculateMaturityDate(LocalDate.now());

        System.out.println("\n--> Tao tai khoan thanh cong!!!");
        Thread.sleep(2000);
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            setBalance(this.balance + amount);
            System.out.printf("--> Nap tien thanh cong!\n--> So du moi: %.1f\n", this.getBalance());

        } else {
            System.out.println("--> So tien nap vao phai lon hon 0!\n");
        }
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && this.balance - amount >= 0) {
            this.setBalance(this.balance - amount);
            System.out.printf("\n--> Rut tien tai khoan ky han thanh cong! So du cuoi: %.1f\n", this.getBalance());

        } else {
            System.out.println("--> So tien rut vuot qua so tien tai khoan co");
        }

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        TermAccount otherAccount = (TermAccount) obj;

        // So sánh các thuộc tính quan trọng
        return Double.compare(otherAccount.balance, balance) == 0
                && Objects.equals(accountID, otherAccount.accountID);
    }

    @Override
    public int hashCode() {
        // Tạo mã băm dựa trên các thuộc tính quan trọng
        return Objects.hash(balance, accountID);
    }

    @Override
    public void tinhTienLai() {
        LocalDate currentDate = LocalDate.now();
        double tienLaiDenNgayHienTai = this.getBalance() * 0.002;
        LocalDate maturityDate = this.dueDate;
        double tienLaiVaoNgayDaoHan = this.term.calculateInterest(this.balance);

        System.out.printf("\n- So tien hien tai: %.1f\n", this.balance);
        System.out.printf("- Ngay hom nay: %s\n", currentDate.format(DateTimeFormatter.ofPattern(Configuration.DATE_FORMAT)));
        System.out.printf("\t+ Tien lai tinh den hien tai: %.1f/nam\n", tienLaiDenNgayHienTai);
        System.out.printf("- Ngay dao han: %s\n", maturityDate.format(DateTimeFormatter.ofPattern(Configuration.DATE_FORMAT)));
        System.out.printf("\t+ Tien lai vao ngay dao han: %.1f\n", tienLaiVaoNgayDaoHan);
    }

    public boolean isDaoHan() {
        return this.dueDate.equals(LocalDate.now());
    }

    public void capNhatDaoHan() {
        this.dueDate = this.term.calculateMaturityDate(this.dueDate);
    }

//------------------------------//------------------------------//------------------------------
    /**
     * @return the balance
     */
    @Override
    public double getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    @Override
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * @return the term
     */
    public Term getTerm() {
        return term;
    }

    /**
     * @param term the term to set
     */
    public void setTerm(Term term) {
        this.term = term;
    }

    /**
     * @return the dueDate
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * @param dueDate the dueDate to set
     */
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
