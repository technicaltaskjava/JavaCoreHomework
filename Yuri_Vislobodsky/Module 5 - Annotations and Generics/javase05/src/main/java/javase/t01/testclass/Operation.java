package javase.t01.testclass;

/**
 * Additional test class with enum
 * Created by Yury Vislobodsky on 23.03.2016.
 */
public enum Operation {
    PLUS {
        @Override
        public double calculate(double numberA, double numberB) {
            return numberA + numberB;
        }

        @Override
        public String toString() {
            return "+";
        }
    },
    MINUS {
        @Override
        public double calculate(double numberA, double numberB) {
            return numberA - numberB;
        }

        @Override
        public String toString() {
            return "-";
        }
    },
    MULTIPLY {
        @Override
        public double calculate(double numberA, double numberB) {
            return numberA * numberB;
        }

        @Override
        public String toString() {
            return "*";
        }
    },
    DIVIDE {
        @Override
        public double calculate(double numberA, double numberB) {
            return numberA / numberB;
        }

        @Override
        public String toString() {
            return "/";
        }
    };

    public abstract double calculate(double numberA, double numberB);
}
