package robot.weapon;

/**
 * @author Alexey Ushakov
 */
public class Firearm implements WeaponType {
    @Override
    public String shooting() {
        return "I shoot by bullets";
    }
}
