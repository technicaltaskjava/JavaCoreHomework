package robot.motion;

/**
 * @author Alexey Ushakov
 */
public class Crawling implements MotionType {
    @Override
    public String move() {
        return "I move on caterpillars";
    }
}
