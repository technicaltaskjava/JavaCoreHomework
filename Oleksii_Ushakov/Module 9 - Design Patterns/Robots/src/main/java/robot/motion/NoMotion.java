package robot.motion;

/**
 * @author Alexey Ushakov
 */
public class NoMotion implements MotionType{
    @Override
    public String move() {
        return "I can`t move";
    }
}
