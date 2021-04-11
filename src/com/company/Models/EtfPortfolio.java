package com.company.Models;

import java.util.ArrayList;

public class EtfPortfolio {
    private final int numberOfHoldings;
    private double idealAllocationPercent;
    private double purchaseValue;
    private double freeCash;
    private double currentValue;

//    how much money do I have in here
//    how much of it is allocated in shares
//    how much of it is free

    public EtfPortfolio(ArrayList<ETF> etfs) {

        for (ETF etf : etfs) {
            purchaseValue += etf.getPurchaseValue();
            idealAllocationPercent += etf.getIdealAllocation();
        }
        numberOfHoldings = etfs.size();
    }

    public double getIdealAllocationPercent() {
        return idealAllocationPercent;
    }

    public double getPurchaseValue() {
        return purchaseValue;
    }

    public int getNumberOfHoldings() {
        return numberOfHoldings;
    }
}
