package javase05.t01.testclass;

/**
 * Additional test class with enum
 * Created by Yury Vislobodsky on 23.03.2016.
 */
public enum Operation {
    PLUS {
        public double calculate(double numberA, double numberB) {
            return numberA + numberB;
        }

        public String toString() {
            return "+";
        }
    },
    MINUS {
        public double calculate(double numberA, double numberB) {
            return numberA - numberB;
        }

        public String toString() {
            return "-";
        }
    },
    MULTIPLY {
        public double calculate(double numberA, double numberB) {
            return numberA * numberB;
        }

        public String toString() {
            return "*";
        }
    },
    DIVIDE {
        public double calculate(double numberA, double numberB) {
            return numberA / numberB;
        }

        public String toString() {
            return "/";
        }
    };

    public abstract double calculate(double numberA, double numberB);
}
