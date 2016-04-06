package javase.t01.basetour;

/**
 * Transport types Class
 * Created by Yury Vislobodsky on 30.03.2016.
 */
public enum Transport {
    BUS {
        @Override
        public double getCost() {
            return 100;
        }
    },
    TRAIN {
        @Override
        public double getCost() {
            return 200;
        }
    },
    AIRLINES {
        @Override
        public double getCost() {
            return 300;
        }
    };
    public abstract double getCost();
}
