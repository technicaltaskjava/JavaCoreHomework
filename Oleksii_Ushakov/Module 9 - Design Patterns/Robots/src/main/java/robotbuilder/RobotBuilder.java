package robotbuilder;

import robot.Robot;
import robot.motion.MotionType;
import robot.motion.NoMotion;
import robot.weapon.NoWeapon;
import robot.weapon.WeaponType;

/**
 * @author Alexey Ushakov
 */
public class RobotBuilder {
    private String name;
    private MotionType motion;
    private WeaponType leftWeaponType;
    private WeaponType rightWeaponType;

    public RobotBuilder() {
        this.name = "";
        this.motion = new NoMotion();
        this.leftWeaponType = new NoWeapon();
        this.rightWeaponType = new NoWeapon();
    }

    public Robot createRobot() {
        return new Robot(name, motion, leftWeaponType, rightWeaponType);
    }

    public RobotBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public RobotBuilder setMotion(MotionType motion) {
        this.motion = motion;
        return this;
    }

    public RobotBuilder setLeftWeaponType(WeaponType leftWeaponType) {
        this.leftWeaponType = leftWeaponType;
        return this;
    }

    public RobotBuilder setRightWeaponType(WeaponType rightWeaponType) {
        this.rightWeaponType = rightWeaponType;
        return this;
    }
}
