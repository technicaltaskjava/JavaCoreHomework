package com.epam.strategy.strategyOS;

        import com.epam.strategy.OS.OS;

public class StrategyClient {
    SettingOS strategy;

    public void setStrategy(SettingOS strategy) {
        this.strategy = strategy;
    }

    public void executeStrategy(OS os) {
        strategy.setOS(os);
    }
}
