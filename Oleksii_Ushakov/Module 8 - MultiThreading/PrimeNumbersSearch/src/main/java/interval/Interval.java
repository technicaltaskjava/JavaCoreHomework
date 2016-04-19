package interval;

/**
 * @author Alexey Ushakov
 */
public class Interval {
    private int leftBorder;
    private int rightBorder;

    public Interval() {
        this.leftBorder = 0;
        this.rightBorder = 0;
    }

    public Interval(int leftBorder, int rightBorder) {
        if (rightBorder < leftBorder) {
            throw new IntervalBoundariesException(String.format("Right border can`t be less than the left [%1$d:%2$d]", leftBorder, rightBorder));
        }
        this.leftBorder = leftBorder;
        this.rightBorder = rightBorder;
    }

    public int getLeftBorder() {
        return leftBorder;
    }

    public void setLeftBorder(int leftBorder) {
        this.leftBorder = leftBorder;
    }

    public int getRightBorder() {
        return rightBorder;
    }

    public void setRightBorder(int rightBorder) {
        this.rightBorder = rightBorder;
    }

    public int size() {
        return rightBorder - leftBorder + 1;
    }

    @Override
    public String toString() {
        return String.format("[%1$d:%2$d]", leftBorder, rightBorder);
    }

    public void increaseFrom(int value) {
        leftBorder += value;
    }

    public void increaseTo(int value) {
        rightBorder += value;
    }

    public Interval[] getEqualIntervals(int count) {
        return EqualIntervals.getEqualIntervals(this, count);
    }

}
