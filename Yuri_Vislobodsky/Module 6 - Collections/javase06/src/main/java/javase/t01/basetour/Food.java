package javase.t01.basetour;

/**
 * Food Types Class
 * Created by Yury Vislobodsky on 30.03.2016.
 */
public enum Food {
    AI {
        @Override
        public double getCost(int daysNumber) {
            return 50.0 * daysNumber;
        }
    },
    HB {
        @Override
        public double getCost(int daysNumber) {
            return 20.0 * daysNumber;
        }
    },
    BB {
        @Override
        public double getCost(int daysNumber) {
            return 10.0 * daysNumber;
        }
    };
    public abstract double getCost(int daysNumber);
}
