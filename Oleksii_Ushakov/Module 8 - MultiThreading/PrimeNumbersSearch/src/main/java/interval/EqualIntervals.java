package interval;

/**
 * @author Alexey Ushakov
 */
class EqualIntervals {
    private EqualIntervals() {
    }

    public static Interval[] getEqualIntervals(final Interval commonInterval, int count) {
        if (commonInterval.size()<count){
            throw new IllegalArgumentException("The size of the initial interval is less than the expected number of intervals");
        }

        Interval[] resultIntervals = new Interval[count];
        int delta = commonInterval.size() / count;

        resultIntervals[0] = new Interval(commonInterval.getLeftBorder(), commonInterval.getLeftBorder() + delta - 1);

        for (int i = 1; i < count; i++) {
            resultIntervals[i] = new Interval();
            resultIntervals[i].setLeftBorder(resultIntervals[i - 1].getRightBorder() + 1);
            resultIntervals[i].setRightBorder(resultIntervals[i - 1].getRightBorder() + delta);
        }

        int rest = commonInterval.getRightBorder() - resultIntervals[count - 1].getRightBorder();

        for (int i = 1; i <= rest; i++) {
            resultIntervals[count - rest + i - 1].increaseFrom(i - 1);
            resultIntervals[count - rest + i - 1].increaseTo(i);
        }

        return resultIntervals;
    }
}
