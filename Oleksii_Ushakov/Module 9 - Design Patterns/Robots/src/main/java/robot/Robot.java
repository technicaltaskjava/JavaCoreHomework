package robot;

import robot.motion.MotionType;
import robot.weapon.WeaponType;

/**
 * @author Alexey Ushakov
 */


public class Robot {
    private String name;
    private MotionType motion;
    private WeaponType leftWeaponType;
    private WeaponType rightWeaponType;

    public Robot(String name, MotionType motion, WeaponType leftWeaponType, WeaponType rightWeaponType) {
        this.motion = motion;
        this.name = name;
        this.leftWeaponType = leftWeaponType;
        this.rightWeaponType = rightWeaponType;
    }

    public String getName() {
        return name;
    }

    public MotionType getMotion() {
        return motion;
    }

    public WeaponType getLeftWeaponType() {
        return leftWeaponType;
    }

    public WeaponType getRightWeaponType() {
        return rightWeaponType;
    }
}
