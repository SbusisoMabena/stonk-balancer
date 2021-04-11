package com.company.Models;

public class ETF {
    private String name = ""; //safe
    private double idealAllocation = 0; // safe
    private double purchaseValue = 0; //safe
    private double currentValue = 0; //safe
    private double deficit = 0;
    private double deficitProportion = 0;
    private double amountToAllocate = 0;
    private int shares = 0; // safe
    private double fractionalShares = 0; //safe
    private String date = ""; // think about where the date bought should go

    private String ticker;

    public ETF() {

    }

    public ETF(String[] values) {
        try {
            this.name = values[0] != null ? values[0] : "";
            this.idealAllocation = !values[1].equals("") ? Double.parseDouble(values[1]) : 0;
            this.purchaseValue = !values[3].equals("") ? Double.parseDouble(values[3]) : 0;
            this.date = !values[6].equals("") ? values[6] : "";
        } catch (IndexOutOfBoundsException E) {
//pass
        }
    }
    public double getDeficit() {
        return deficit;
    }

    public void setDeficit(double deficit) {
        this.deficit = deficit;
    }

    public void setDeficitProportion(double deficitProportion) {
        this.deficitProportion = deficitProportion;
    }

    public double getAmountToAllocate() {
        return amountToAllocate;
    }

    public void setAmountToAllocate(double amountToAllocate) {
        this.amountToAllocate = amountToAllocate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getIdealAllocation() {
        return idealAllocation;
    }

    public void setIdealAllocation(double idealAllocation) {
        this.idealAllocation = idealAllocation;
    }

    public double calculateCurrentAllocation(double totalValue) {
        if(totalValue == 0)
        {
            return 0;
        }
        return Math.round((purchaseValue / totalValue)*100);
    }

    public double getPurchaseValue() {
        return purchaseValue;
    }

    public void setPurchaseValue(double purchaseValue) {
        this.purchaseValue = purchaseValue;
    }

    public double getCurrentValue() {
        return currentValue;
    }

    public double calculateBalancedValue(double portfolioValue) {
        return (idealAllocation * portfolioValue)/100;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
