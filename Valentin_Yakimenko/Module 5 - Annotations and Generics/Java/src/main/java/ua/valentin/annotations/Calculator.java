package ua.valentin.annotations;

/**
 * Implements calculator.
 * Created by yakimenko.valentin on 29.03.16.
 */
public class Calculator {
    /**
     The result of the calculation.
     */
    private int result;
    /**
     To summarize the arguments .
     */
    public void add(int ... params) {
        for (int param : params) {
            this.result += param;
        }
    }
    /**
     Calculated by dividing.
     If no of arguments , throws an exception.
     */
    public void div(int ... args) throws UserException {
        if (args.length > 0) {
            this.result = args[0];
            for (int params : args) {
                if (params == 0) {
                    throw new IllegalArgumentException("You try to div by 0. Please change arg!");
                }
                this.result /= params;
            }
        } else {
            throw new UserException("You should enter args!");
        }
    }
    /**
     The result of calculation.
     */
    public int getResult() {
        return this.result;
    }
    /**
     Clear the calculation result .
     */
    public void cleanResult() {
        this.result = 0;
    }
 }
