package com.company;

import com.company.Models.ETF;
import com.company.Models.EtfPortfolio;
import com.company.Services.ETFBalancer;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the path of the portfolio CSV: ");

        String path = scanner.nextLine();
        String line = "";
        int lineNumber = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            final ArrayList<ETF> etfs = new java.util.ArrayList<>();


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

            printPortfolioSummary(portfolio);

            showMenu(scanner, etfs, portfolio);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void showMenu(Scanner scanner, ArrayList<ETF> etfs, EtfPortfolio portfolio) {
        String prompt = "Enter an action you would like to perform: \n" +
                "1, List Holdings. \n" +
                "2, Balance Portfolio.\n" +
                "0, Exit.";
        System.out.println(prompt);

        int action = Integer.parseInt(scanner.nextLine());

        switch (action) {
            case 0 -> System.exit(0);
            case 1 -> {
                printHoldings(etfs, portfolio);
                showMenu(scanner, etfs, portfolio);
            }
            case 2 -> {
                System.out.println("Please enter the available amount");
                double availableAmount = Double.parseDouble(scanner.nextLine());
                ArrayList<ETF> balancedEtfs = balancePortfolio(etfs, portfolio, availableAmount);
                printBalancedEtfAllocations(balancedEtfs, portfolio);
                showMenu(scanner, etfs, portfolio);
            }
            default -> {
                System.out.println("Unknown action");
                showMenu(scanner, etfs, portfolio);
            }
        }
    }

    private static ArrayList<ETF> balancePortfolio(ArrayList<ETF> etfs, EtfPortfolio portfolio, double availableAmount) {
        ETFBalancer balancer = new ETFBalancer(etfs);
        return balancer.balance(portfolio.getPurchaseValue(), availableAmount);
    }

    private static void printBalancedEtfAllocations(ArrayList<ETF> etfs, EtfPortfolio portfolio) {
        List<List<String>> rows = new ArrayList<>();
        List<String> headerRow = Arrays.asList("Name", "Ideal Allocation", "Current Allocation",
                "Purchased Value", "Current Value", "Balanced Value", "Max Amount to allocate", "Amount to be even");
        rows.add(headerRow);
        for (ETF etf : etfs) {
            rows.add(Arrays.asList(
                    etf.getName(),
                    Double.toString(etf.getIdealAllocation()) + "%",
                    Double.toString(etf.calculateCurrentAllocation(portfolio.getPurchaseValue())) + "%",
                    "R"+etf.getPurchaseValue(),
                    "R"+etf.getCurrentValue(),
                    "R"+roundAmount(etf.calculateBalancedValue(portfolio.getPurchaseValue())),
                    "R"+roundAmount(etf.getAmountToAllocate()),
                   "R"+roundAmount((etf.calculateBalancedValue(portfolio.getPurchaseValue() - etf.getPurchaseValue())))
            )
            );
        }

        String table = formatAsTable(rows);
        System.out.println(table);
    }

    private static void printHoldings(ArrayList<ETF> etfs, EtfPortfolio portfolio) {
        for (ETF etf : etfs) {
            System.out.println(
                    "---------------------------------------------------------\n" +
                            etf.getName() + "\n" +
                            "---------------------------------------------------------\n" +
                            "Ideal Allocation: " + etf.getIdealAllocation() + "%" + "\n" +
                            "Current Allocation: " + etf.calculateCurrentAllocation(portfolio.getPurchaseValue()) + "%" + "\n" +
                            "Purchased Value: R" + etf.getPurchaseValue() + "\n" +
                            "Current Value: R" + etf.getCurrentValue() + "\n" +
                            "Date Updated: " + etf.getDate() + "\n\n");
        }
    }

    private static void printPortfolioSummary(EtfPortfolio portfolio) {
        float totalValue = 0;
        System.out.println("Number of Holdings: " + portfolio.getNumberOfHoldings());
        System.out.println("Total Invested:" + roundAmount(portfolio.getPurchaseValue()));
        System.out.println("Total Value:" + totalValue);
        System.out.println("=========================================================");
        System.out.println("=========================================================");
    }

    public static void view(int row) {

    }

    private static double roundAmount(double amount) {
        return Math.round(amount * 100.0) / 100.0;
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

    private static String formatAsTable(List<List<String>> rows) {
        int[] maxLengths = new int[rows.get(0).size()];
        for (List<String> row : rows) {
            for (int i = 0; i < row.size(); i++) {
                maxLengths[i] = Math.max(maxLengths[i], row.get(i).length());
            }
        }

        StringBuilder formatBuilder = new StringBuilder();
        for (int maxLength : maxLengths) {
            formatBuilder.append("%-").append(maxLength + 2).append("s");
        }
        String format = formatBuilder.toString();

        StringBuilder result = new StringBuilder();
        for (List<String> row : rows) {
            result.append(String.format(format, row.toArray(new String[0]))).append("\n");
        }
        return result.toString();
    }
}
