package com.company.Services;

import com.company.Models.ETF;

import java.util.ArrayList;

public class ETFBalancer {

    private ArrayList<ETF> etfs;

    public ETFBalancer(ArrayList<ETF> etfs) {
        this.etfs = etfs;
    }

    public ArrayList<ETF> balance(double currentPortfolioAmount, double availableAmount) {
        double sumOfDeficit = 0;
        if(currentPortfolioAmount==0){
            currentPortfolioAmount = availableAmount;
        }

        for (ETF etf : etfs) {
            double idealAllocationAmount = (currentPortfolioAmount * (etf.getIdealAllocation()/100));
            double deficit = etf.getPurchaseValue() - idealAllocationAmount;
            if (deficit < 0) {
                etf.setDeficit(deficit);
                sumOfDeficit += deficit;
            }
        }

        for (ETF etf : etfs) {
            if (etf.getDeficit() < 0) {
                double deficitProportion = ((etf.getDeficit() / sumOfDeficit) * 100);
                etf.setDeficitProportion(deficitProportion);
                double amountToAllocate =  (availableAmount * (deficitProportion / 100));
                etf.setAmountToAllocate(amountToAllocate);
            }
        }

        return etfs;
    }

    private double splitAmountEvenly(double availableAmount, int numberOfEtfs) {
        return availableAmount / numberOfEtfs;
    }

//    rebalance the portfolio

//        count all etfs that haven't reached their limit
//        look at what's left (The amount) and add it up for each of them and divide it by the total amount
//        allocate the amounts (subtracting from the availableAmount)... if the ETF has reached it's limit take what's left and put it back on what's left on the available amount.
//        perform the dividing calculations again and repeat
//        if all is allocated and some funds are left, start afresh and divide what's left to all the ETFS


//    take the available amount
//    split it accross the ETFs that haven't reached their limit yet
//    foreach ETF show how much should come from the share
}
