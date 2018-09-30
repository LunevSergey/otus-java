package ru.otus;

import java.util.HashMap;
import java.util.Map;

public class ATM {

    private Map<Bill, Integer> cells = new HashMap<>();

    public void deposit(Bill denominaton, Integer sum) {
        int currentValue;
        if (cells.containsKey(denominaton)) {
            currentValue = cells.get(denominaton);
        } else {
            currentValue = 0;
        }
        cells.put(denominaton, sum + currentValue);
    }

    public int getBalance() {
        int balance = 0;
        for (Map.Entry<Bill, Integer> entry : cells.entrySet()) {
            balance += entry.getKey().getValue() * entry.getValue();
        }
        return balance;
    }

    public Map<Bill, Integer> withdraw(final int sum) {
        Map<Bill, Integer> withdraw = new HashMap<>();
        int remain = sum;
        for (Bill bill : Bill.values()) {
            int bills = cells.getOrDefault(bill, 0);
            if (bills > 0) {
                int notesNeeded = remain / bill.getValue();
                int notesToDispence = Math.min(notesNeeded, bills);
                withdraw.put(bill, notesToDispence);
                remain -= notesToDispence * bill.getValue();
            }
        }
        if (remain < 0 || sum != compute(withdraw)) {
            throw new IllegalStateException(String.format("Can`t withdraw sum %d, state: ", sum) + cells);
        }
        withdraw.forEach((bill, notesDispensed) -> cells.compute(bill, (key, notes) -> notes - notesDispensed));
        return withdraw;
    }

    private int compute(Map<Bill, Integer> pack) {
        int result = 0;
        for (Map.Entry<Bill, Integer> entry : pack.entrySet()) {
            result += entry.getKey().getValue() * entry.getValue();
        }
        return result;
    }

    public String report() {
        return cells.toString();
    }
}
