/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team.lhp;

import java.util.Scanner;
//import static com.team.lhp.Scanner.sc;
import java.time.LocalDate;
import com.team.ntn.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

/**
 *
 * @author lehoangphuc
 */
public class TaiKhoanCoKyHan extends Account {

    private KyHan kyHan;
    private LocalDate ngayDaoHan;
    private double soTien;

    public TaiKhoanCoKyHan(Customer customer) {
        super(customer);
        setAccountID(generateAccountId());
    }

    public TaiKhoanCoKyHan(Customer customer, double balance, KyHan kyhan) throws Exception {
        super(customer);
        if (balance >= 100000) {
            setBalance(balance);
        } else {
            throw new Exception("So tien toi thieu de mo tai khoan la 100.000 dong!\n");
        }
        setAccountID(generateAccountId());

        this.kyHan = kyHan;
        this.ngayDaoHan = kyHan.tinhDaoHan(LocalDate.now());
    }

    public TaiKhoanCoKyHan(Customer customer, double balance, String accountId, KyHan kyHan,LocalDate ngayDaoHan) throws Exception {
        super(customer);
        if (balance >= 100000) {
            setBalance(balance);
        } else {
            throw new Exception("So tien toi thieu de mo tai khoan la 100.000 dong!\n");
        }
        setAccountID(accountId);

        this.kyHan = kyHan;
        this.ngayDaoHan = ngayDaoHan;
    }

    @Override
    public void display() {
        super.display();
        System.out.printf("\tSo du: %.1f\tKy Han: %s\tNgay Dao Han: %s\n",
                this.soTien, this.kyHan, this.ngayDaoHan.format(DateTimeFormatter.ofPattern(Configuration.DATE_FORMAT)));
        System.out.println("\n--------------------------------------------------------------------------------------");
    }

    @Override
    public void input() throws InterruptedException {
        do {
            try {
                System.out.print("\nSo tien gui: ");
                double soTien = Float.parseFloat(Configuration.sc.nextLine());
                if (soTien >= 100000) {
                    setSoTien(soTien);
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
                this.kyHan = KyHan.MOT_TUAN;
                break;
            case 2:
                this.kyHan = KyHan.MOT_THANG;
                break;
            case 3:
                this.kyHan = KyHan.SAU_THANG;
                break;
            case 4:
                this.kyHan = KyHan.MUOI_HAI_THANG;
            default:
                System.out.println("Khong hop le, ky han mac dinh se la MOT TUAN!");
                kyHan = KyHan.MOT_TUAN;
                break;
        }

        this.ngayDaoHan = kyHan.tinhDaoHan(LocalDate.now());

        System.out.println("--> Tao tai khoan thanh cong!!!");
        Thread.sleep(2000);
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            setSoTien(this.soTien + amount);
            System.out.printf("=> Nap tien thanh cong!\n==> So du moi: %.1f\n", this.getSoTien());

        } else {
            System.out.println("So tien nap vao phai lon hon 0!\n");
        }
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && this.soTien - amount >= 50000) {
            setSoTien(this.soTien - amount);
            System.out.println("Rut tien thanh cong!");
            System.out.printf("So du moi: %.1f\n", this.getSoTien());

        } else {
            System.out.println("So tien toi thieu can de tai khoan la 50000. Rut tien khong thanh cong!");
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

        TaiKhoanCoKyHan otherAccount = (TaiKhoanCoKyHan) obj;

        // So sánh các thuộc tính quan trọng
        return Double.compare(otherAccount.soTien, soTien) == 0
                && Objects.equals(accountID, otherAccount.accountID);
    }

    @Override
    public int hashCode() {
        // Tạo mã băm dựa trên các thuộc tính quan trọng
        return Objects.hash(soTien, accountID);
    }

    @Override
    public void tinhTienLai() {
        System.out.printf("So tien hien tai: %.1f\n", this.soTien);
        System.out.printf("Tien lai: %.1f\n", this.kyHan.tinhLai(this.soTien));
    }

    public boolean isDaoHan() {
        return this.ngayDaoHan.equals(LocalDate.now());
    }

    public void capNhatDaoHan() {
        this.ngayDaoHan = this.kyHan.tinhDaoHan(this.ngayDaoHan);
    }

//------------------------------//------------------------------//------------------------------
    /**
     * @return the soTien
     */
    public double getSoTien() {
        return soTien;
    }

    /**
     * @param soTien the soTien to set
     */
    public void setSoTien(double soTien) {
        this.soTien = soTien;
    }

    /**
     * @return the kyHan
     */
    public KyHan getKyHan() {
        return kyHan;
    }

    /**
     * @param kyHan the kyHan to set
     */
    public void setKyHan(KyHan kyHan) {
        this.kyHan = kyHan;
    }

    /**
     * @return the ngayDaoHan
     */
    public LocalDate getNgayDaoHan() {
        return ngayDaoHan;
    }

    /**
     * @param ngayDaoHan the ngayDaoHan to set
     */
    public void setNgayDaoHan(LocalDate ngayDaoHan) {
        this.ngayDaoHan = ngayDaoHan;
    }
}
