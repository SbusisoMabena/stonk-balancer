import com.company.Models.ETF;
import com.company.Models.EtfPortfolio;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ETFPortfolioTest {
    @Test
    public void EtfPortfolioReturnsTheCorrectTotalPurchaseValue()
    {
        ArrayList<ETF> etfs = new ArrayList<ETF>();
        ETF etf1 = new ETF();
        etf1.setIdealAllocation(50);
        etf1.setPurchaseValue(20);

        ETF etf2 = new ETF();
        etf2.setIdealAllocation(50);
        etf2.setPurchaseValue(20);

        etfs.add(etf1);
        etfs.add(etf2);

        EtfPortfolio etfPortfolio = new EtfPortfolio(etfs);
        double purchaseValue = etfPortfolio.getPurchaseValue();

        assertEquals(40, purchaseValue);
    }

    @Test
    public void EtfPortfolioReturnsTheCorrectNumberOfHoldings()
    {
        ArrayList<ETF> etfs = new ArrayList<ETF>();
        ETF etf1 = new ETF();
        etf1.setIdealAllocation(50);
        etf1.setPurchaseValue(20);

        ETF etf2 = new ETF();
        etf2.setIdealAllocation(50);
        etf2.setPurchaseValue(20);

        etfs.add(etf1);
        etfs.add(etf2);

        EtfPortfolio etfPortfolio = new EtfPortfolio(etfs);
        int numberOfHoldings = etfPortfolio.getNumberOfHoldings();

        assertEquals(2, numberOfHoldings);
    }

    @Test
    public void EtfPortfolioReturnsTheCorrectIdealAllocation()
    {
        ArrayList<ETF> etfs = new ArrayList<ETF>();
        ETF etf1 = new ETF();
        etf1.setIdealAllocation(50);
        etf1.setPurchaseValue(20);

        ETF etf2 = new ETF();
        etf2.setIdealAllocation(50);
        etf2.setPurchaseValue(20);

        etfs.add(etf1);
        etfs.add(etf2);

        EtfPortfolio etfPortfolio = new EtfPortfolio(etfs);

        assertEquals(100, etfPortfolio.getIdealAllocationPercent());
    }
}
