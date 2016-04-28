package com.epam.strategy.strategyos;

        import com.epam.strategy.os.OS;

public class StrategyClient {
    SettingOS strategy;

    public void setStrategy(SettingOS strategy) {
        this.strategy = strategy;
    }

    public void executeStrategy(OS os) {
        strategy.setOS(os);
    }
}
