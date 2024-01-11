/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team.ntn;

import java.util.Objects;

/**
 *
 * @author THANH_NAM
 */
public class UnlimitedAccount extends Account {

    private double balance;

    public UnlimitedAccount(Customer customer) {
        super(customer);
    }

    public UnlimitedAccount(Customer customer, double balance) throws Exception {
        super(customer);
        if (balance >= 100000) {
            setBalance(balance);
        } else {
            throw new Exception("So tien toi thieu de mo tai khoan la 100.000 dong!\n");
        }

        this.password = initPassword();
    }

    @Override
    public void display()  {
        super.display();
        System.out.printf("\nSo du: %.1f",this.balance);
        System.out.println("\n----------------------------------------------------------------------------");
    }
    
    

    @Override
    public void input() throws InterruptedException {
        System.out.println("\n\n~~~~~Nhap tai khoan ");

        setUsername(this.user.getUserId());
        this.password = initPassword();
        System.out.printf("Username: %s\t\tPassword: %s\n", this.username, this.password);

        //doi mat khau sau
        //vong lap nhap+check mat khau
//        String pass;
//        do {
//            System.out.print("Password: ");
//            pass = Configuration.sc.nextLine();
//            setPassword(pass);
//        } while (!isStrongPassword(pass));
        //vong lap check so tien 
        float soTien;
        do {
            try {
                System.out.print("So tien gui: ");
                soTien = Float.parseFloat(Configuration.sc.nextLine());
                if (soTien >= 100000) {
                    setBalance(soTien);
                } else {
                    throw new Exception("So tien toi thieu de mo tai khoan la 100.000 dong!\n");
                }
            } catch (NumberFormatException e) {
                System.err.println("Loi dinh dang so. Hay nhap so hop le.");
                continue; // Nhập lại nếu có lỗi định dạng số
            } catch (Exception e) {
                System.err.println(e.getMessage());
                continue; // Nhập lại nếu có lỗi khác
            }

            // Nếu chương trình đã đi đến đây, nghĩa là giá trị soTien hợp lệ
            break; // Thoát khỏi vòng lặp
        } while (true); // Lặp lại nếu giá trị soTien không hợp lệ
        System.out.println("=>Tao tai khoan thanh cong!!!");
        Thread.sleep(2000);
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            setBalance(this.balance + amount);
            System.out.printf("=>Nap tien thanh cong!\n==>So du moi: %.1f\n", this.getBalance());

        } else {
            System.out.println("So tien nap vao phai lon hon 0!\n");
        }
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && this.balance - amount >= 50000) {
            setBalance(this.balance - amount);
            System.out.println("Rut tien thanh cong!");
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

        UnlimitedAccount otherAccount = (UnlimitedAccount) obj;

        // So sánh các thuộc tính quan trọng
        return Double.compare(otherAccount.balance, balance) == 0
                && Objects.equals(username, otherAccount.username)
                && Objects.equals(password, otherAccount.password);
    }

    @Override
    public int hashCode() {
        // Tạo mã băm dựa trên các thuộc tính quan trọng
        return Objects.hash(balance, username, password);
    }

    /**
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

}