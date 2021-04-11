import com.company.Models.ETF;
import com.company.Services.ETFBalancer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ETFBalancerTest {
    @Test
    public void balanceWithOneETF()
    {
        ETF etf= new ETF();
        etf.setName("test etf");
        etf.setPurchaseValue(0);
        etf.setIdealAllocation(100);

        ArrayList<ETF> etfs = new ArrayList<ETF>();
        etfs.add(etf);

        ETFBalancer balancer = new ETFBalancer(etfs);

        ArrayList<ETF> balancedEtfs = balancer.balance(0,1000);
        assertEquals(1,balancedEtfs.size());
        assertEquals(1000, balancedEtfs.get(0).getAmountToAllocate(),0);
    }

    @Test
    public void balanceWithTwoEtfsThatHaveEqualIdealAllocations()
    {
        ETF etf1 = new ETF();
        etf1.setName("test etf 1");
        etf1.setIdealAllocation(50);

        ETF etf2 = new ETF();
        etf2.setName("test etf 2");
        etf2.setIdealAllocation(50);

        ArrayList<ETF> etfs = new ArrayList<ETF>();
        etfs.add(etf1);
        etfs.add(etf2);

        ETFBalancer balancer = new ETFBalancer(etfs);

        ArrayList<ETF> balancedEtfs = balancer.balance(0,1000);
        assertEquals(2,balancedEtfs.size());
        assertEquals(500, balancedEtfs.get(0).getAmountToAllocate(),0);
        assertEquals(500, balancedEtfs.get(1).getAmountToAllocate(),0);
    }

    @Test
    public void balanceWithTwoEtfsThatHaveDifferentAllocations()
    {
        ETF etf1 = new ETF();
        etf1.setName("test etf 1");
        etf1.setIdealAllocation(85);

        ETF etf2 = new ETF();
        etf2.setName("test etf 2");
        etf2.setIdealAllocation(15);

        ArrayList<ETF> etfs = new ArrayList<ETF>();
        etfs.add(etf1);
        etfs.add(etf2);

        ETFBalancer balancer = new ETFBalancer(etfs);

        ArrayList<ETF> balancedEtfs = balancer.balance(0,1000);
        assertEquals(2,balancedEtfs.size());
        assertEquals(850, balancedEtfs.get(0).getAmountToAllocate(),0);
        assertEquals(150, balancedEtfs.get(1).getAmountToAllocate(),0);
    }

    @Test
    public void balanceWithThreeEtfsThatHaveDifferentAllocations(){
        ETF etf1 = new ETF();
        etf1.setName("test etf 1");
        etf1.setIdealAllocation(72.6);

        ETF etf2 = new ETF();
        etf2.setName("test etf 2");
        etf2.setIdealAllocation(15);

        ETF etf3 = new ETF();
        etf3.setName("test etf 3");
        etf3.setIdealAllocation(12.4);

        ArrayList<ETF> etfs = new ArrayList<ETF>();
        etfs.add(etf1);
        etfs.add(etf2);
        etfs.add(etf3);

        ETFBalancer balancer = new ETFBalancer(etfs);

        ArrayList<ETF> balancedEtfs = balancer.balance(0,1000);
        assertEquals(3,balancedEtfs.size());
        assertEquals(726, balancedEtfs.get(0).getAmountToAllocate(),0);
        assertEquals(150, balancedEtfs.get(1).getAmountToAllocate(),0);
        assertEquals(124, balancedEtfs.get(2).getAmountToAllocate(),0);
    }

    @Test
    public void balanceWithThreeEtfsWithDifferentAllocationsAndoneOfThemHasReachedTheLimit()
    {

        ETF etf1 = new ETF();
        etf1.setName("test etf 1");
        etf1.setIdealAllocation(72.6);

        ETF etf2 = new ETF();
        etf2.setName("test etf 2");
        etf2.setIdealAllocation(15);
        etf2.setPurchaseValue(300);

        ETF etf3 = new ETF();
        etf3.setName("test etf 3");
        etf3.setIdealAllocation(12.4);

        ArrayList<ETF> etfs = new ArrayList<ETF>();
        etfs.add(etf1);
        etfs.add(etf2);
        etfs.add(etf3);

        ETFBalancer balancer = new ETFBalancer(etfs);

        ArrayList<ETF> balancedEtfs = balancer.balance(1000,250);
        assertEquals(3,balancedEtfs.size());
        assertEquals(213.529, balancedEtfs.get(0).getAmountToAllocate(),0.01);
        assertEquals(0, balancedEtfs.get(1).getAmountToAllocate(),0.01);
        assertEquals(36.470, balancedEtfs.get(2).getAmountToAllocate(),0.01);
    }
}
