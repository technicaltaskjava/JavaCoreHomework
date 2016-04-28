package robot.weapon;

/**
 * @author Alexey Ushakov
 */
public class NoWeapon implements WeaponType {
    @Override
    public String shooting() {
        return "I haven't weapon";
    }
}
