package com.epam.strategy;

import com.epam.strategy.guns.gun.Gun;
import com.epam.strategy.guns.gun.MachineGun;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class StrategyTest {
    @Test
    public void singletonTest() {
        Gun m60 = new MachineGun();
        assertEquals("It is a machine gun.", m60.show());
        assertEquals("I'm shooting.", m60.shoot());
        assertEquals("I'm burst shooting!", m60.performShootSpeed());
        assertEquals("My gun is on board.", m60.performCarry());

    }
}
