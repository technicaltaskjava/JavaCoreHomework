package epam.com.task1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * Created by O.Gondar on 06.04.2016.
 */
public class TestAirCompany {


    AirCompany company = new AirCompany();

    @Before
    public void init() {

        company.addAirCraft(Distances.LONG, 4, 4);
        company.addAirCraft(Distances.MIDDLE, 2, 2);
        company.addAirCraft(Distances.SHORT, 1, 1);
    }

    @Test
    public void testAircompanyFillingCollection() {

        Assert.assertTrue(2 == company.getAircraft(1).getBearingCapacity());

    }

    @Test
    public void testAircompanySortCollection() {
        company.sortAirCrafts();
        Assert.assertTrue(1 == company.getAircraft(0).getBearingCapacity());
        Assert.assertTrue(4 == company.getAircraft(2).getBearingCapacity());

    }

}
