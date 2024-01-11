/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team.ntn;

/**
 *
 * @author THANH_NAM
 */
public class EAccount extends Account {

    private static int count;

    public EAccount(Employee e) {
        super(e);
        e.addAcc(this);
        setAccountID(generateAccountId());
    }

    public EAccount(Employee e, String accountID) {
        super(e);
        e.addAcc(this);
        setAccountID(accountID);
    }

}
