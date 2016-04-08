package com.epam.task1;

import com.epam.task1.candy.Candy;
import com.epam.task1.candy.Chocolate;
import com.epam.task1.candy.Jelly;
import com.epam.task1.candy.Lollipop;
import com.epam.task1.gift.Gift;
import com.epam.task1.gift.IGift;
import com.epam.task1.util.SortCandiesByName;
import com.epam.task1.util.SortCandiesByWeight;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by Olga Kramska on 03-Apr-16.
 */
public class GiftTest {
    private IGift gift;

    @Before
    public void init() {
        final List<Candy> candies = new ArrayList() {{
            add(new Chocolate(100));
            add(new Lollipop(200));
            add(new Jelly(150));
        }};
        gift = new Gift(candies);
    }

    @Test
    public void testGetTotalWeight() {
        assertEquals(450, gift.getTotalWeight(), 0.01);
    }

    @Test
    public void testAddCandy() {
        gift.addCandy(new Lollipop(55));
        assertArrayEquals(new Candy[]{new Chocolate(100), new Lollipop(200), new Jelly(150), new Lollipop(55)},
                gift.getCandies().toArray());
    }

    @Test
    public void testSortCandies(){
        assertArrayEquals(new Candy[]{new Chocolate(100), new Jelly(150), new Lollipop(200)},
                gift.sortCandies(new SortCandiesByName()).toArray());
        assertArrayEquals(new Candy[]{new Chocolate(100), new Jelly(150), new Lollipop(200)},
                gift.sortCandies(new SortCandiesByWeight()).toArray());
    }

    @Test
    public void testFindCandyOfMaxWeight(){
        assertEquals(new Lollipop(200), gift.findCandyOfMaxWeight());
    }
}
