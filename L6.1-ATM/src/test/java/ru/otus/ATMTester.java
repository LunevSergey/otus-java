package ru.otus;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class ATMTester {

    @Test
    public void testGetBalance() {
        ATM atm = new ATM();
        atm.deposit(Bill.ONE_THOUSAND, 2);
        atm.deposit(Bill.FIVE_THOUSANDS, 1);
        Assert.assertEquals(7000, atm.getBalance());
    }

    @Test
    public void testDeposit() {
        ATM atm = new ATM();
        atm.deposit(Bill.ONE_THOUSAND, 3);
        atm.deposit(Bill.FIVE_THOUSANDS, 2);
        atm.deposit(Bill.FIVE_HUNDREDS, 8);
        atm.deposit(Bill.ONE_HUNDRED, 3);
        Assert.assertEquals(17300, atm.getBalance());
    }

    @Test
    public void testWithdraw() {
        ATM atm = new ATM();
        atm.deposit(Bill.ONE_THOUSAND, 3);
        atm.deposit(Bill.FIVE_THOUSANDS, 2);
        atm.deposit(Bill.FIVE_HUNDREDS, 8);
        atm.deposit(Bill.ONE_HUNDRED, 3);
        int balance = atm.getBalance();
        Map<Bill, Integer> withdraw = atm.withdraw(12000);
        Assert.assertEquals(2, withdraw.get(Bill.FIVE_THOUSANDS).intValue());
        Assert.assertEquals(2, withdraw.get(Bill.ONE_THOUSAND).intValue());
        Assert.assertEquals(0, withdraw.get(Bill.FIVE_HUNDREDS).intValue());
        Assert.assertEquals(0, withdraw.get(Bill.ONE_HUNDRED).intValue());
        Assert.assertEquals(balance - 12000, atm.getBalance());
    }

    @Test()
    public void testWithdrawError() {
        ATM atm = new ATM();
        atm.deposit(Bill.ONE_THOUSAND, 3);
        atm.deposit(Bill.FIVE_THOUSANDS, 2);
        atm.deposit(Bill.FIVE_HUNDREDS, 8);
        atm.deposit(Bill.ONE_HUNDRED, 3);
        int balance = atm.getBalance();
        Map<Bill, Integer> withdraw = atm.withdraw(12400);
        System.out.println("Before: " + balance + " , after: " + atm.getBalance());
        System.out.println(atm.report());
        System.out.println("Withdraw: " + withdraw);
    }
}
