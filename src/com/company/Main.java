package com.company;

import com.company.Models.ETF;
import com.company.Models.EtfPortfolio;
import com.company.Services.ETFBalancer;

import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        String path = "/home/sbusiso/Documents/personal_finance/tfsa.csv";
        String line = "";
        int lineNumber = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            ArrayList<ETF> etfs = new java.util.ArrayList<>();


            while ((line = br.readLine()) != null) {
                if (lineNumber == 0) {
                    lineNumber++;
                    continue;
                }
                String[] values = line.split(",");

                ETF etf = new ETF(values);
                etfs.add(etf);
                lineNumber++;
            }

            EtfPortfolio portfolio = new EtfPortfolio(etfs);

            float totalValue = 0;
            System.out.println("Number of Holdings: " + portfolio.getNumberOfHoldings());
            System.out.println("Total Invested:" + portfolio.getPurchaseValue());
            System.out.println("Total Value:" + totalValue);
            System.out.println("=========================================================");
            System.out.println("=========================================================");

            ETFBalancer balancer = new ETFBalancer(etfs);
            etfs = balancer.balance(portfolio.getPurchaseValue(), 803.30);

            for (ETF etf : etfs) {
                System.out.println(
                        etf.getName() + "\n" +
                                "Ideal Allocation: " + etf.getIdealAllocation() + "%" + "\n" +
                                "Current Allocation: " + etf.calculateCurrentAllocation(portfolio.getPurchaseValue()) + "%" + "\n" +
                                "Purchased Value: R" + etf.getPurchaseValue() + "\n" +
                                "Current Value: R" + etf.getCurrentValue() + "\n" +
                                "Balanced value: R" + etf.calculateBalancedValue(portfolio.getPurchaseValue()) + "\n" +
                                "Max Amount To Allocate: R" + Math.round(etf.getAmountToAllocate() * 100.0) / 100.0 + "\n" +
                                "Amount To Allocate To Be Even: R" + Math.round((etf.calculateBalancedValue(portfolio.getPurchaseValue()- etf.getPurchaseValue()))*100.0 )/100.0 + "\n" +
                                "Date Updated: " + etf.getDate() + "\n\n");
                System.out.println("=========================================================");
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void view(int row) {

    }

    public static void saveRecord(String path) {
        try {
            FileWriter fw = new FileWriter(path, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println("a,b,c,d");
            pw.flush();
            pw.close();
            bw.close();
            fw.close();
        } catch (Exception E) {

        }
    }
//    create table builder\
//    AsciiTable at = new AsciiTable();
//
//at.addRule();
//at.addRow("row 1 col 1", "row 1 col 2");
//at.addRule();
//at.addRow("row 2 col 1", "row 2 col 2");
//at.addRule();
// at will adjust the width
}
