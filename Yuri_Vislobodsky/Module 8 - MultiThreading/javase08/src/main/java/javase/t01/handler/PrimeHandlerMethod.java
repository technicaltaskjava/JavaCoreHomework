package javase.t01.handler;

/**
 * Enum class to describe two methods of prime numbers handling
 * Created by Yury Vislobodsky on 17.04.2016.
 */
public enum PrimeHandlerMethod {
    METHOD1 {
        @Override
        public String toString() {
            return "Method 1: Thread adds prime numbers directly to common set";
        }
    },
    METHOD2 {
        @Override
        public String toString() {
            return "Method 2: Thread adds prime numbers to its local set, then adds local set to common set";
        }
    };

    @Override
    public abstract String toString();
}
