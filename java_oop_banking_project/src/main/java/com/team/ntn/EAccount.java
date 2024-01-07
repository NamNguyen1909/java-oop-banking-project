/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team.ntn;

/**
 *
 * @author THANH_NAM
 */
public class EAccount extends Account{

    public EAccount(Employee e, String pass) {
        super(e);
        this.username=e.getEmployeeID();
        this.password=pass;
        e.addAcc(this);
    }


    

    
    @Override
    public void display() {
        System.out.printf("\nUsername: %s \t\tPassword: %s",this.username, this.password);
        System.out.println("\n----------------------------------------------------------------------------");
    }
    
}
