package robot.motion;

/**
 * @author Alexey Ushakov
 */
public class Flying implements MotionType {
    @Override
    public String move() {
        return "I fly";
    }
}
