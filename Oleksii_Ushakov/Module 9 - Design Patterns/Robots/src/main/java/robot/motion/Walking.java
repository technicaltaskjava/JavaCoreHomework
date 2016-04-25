package robot.motion;

/**
 * @author Alexey Ushakov
 */
public class Walking implements MotionType {
    @Override
    public String move() {
        return "I walk";
    }
}
