package view;

import robot.Robot;
import robot.motion.Crawling;
import robot.motion.Flying;
import robot.motion.Walking;
import robot.weapon.Firearm;
import robot.weapon.Laser;
import robot.weapon.Plasma;
import robotbuilder.RobotBuilder;

/**
 * @author Alexey Ushakov
 *         Pattern strategy: Used to select the components of the robot
 *         Pattern builder: Used to create a robot
 */
public class RobotsView {
    private RobotsView() {
    }

    private static void viewRobot(Robot robot) {
        StringBuilder describe = new StringBuilder("Name: ");
        describe.append(robot.getName()).append('\n');
        describe.append("Move: ").append(robot.getMotion().move()).append('\n');
        describe.append("Left weapon: ").append(robot.getLeftWeaponType().shooting()).append('\n');
        describe.append("Right weapon: ").append(robot.getRightWeaponType().shooting()).append('\n');
        System.out.println(describe);
    }

    public static void main(String[] args) {
        RobotBuilder robotBuilder = new RobotBuilder();

        Robot flyingRobot = robotBuilder
                .setName("FA-5")
                .setMotion(new Flying())
                .createRobot();

        viewRobot(flyingRobot);

        Robot massiveRobot = robotBuilder
                .setMotion(new Crawling())
                .setLeftWeaponType(new Firearm())
                .setRightWeaponType(new Plasma())
                .setName("Big R")
                .createRobot();

        viewRobot(massiveRobot);

        Robot droid = robotBuilder
                .setMotion(new Walking())
                .setLeftWeaponType(new Laser())
                .setRightWeaponType(new Laser())
                .setName("C-3PO")
                .createRobot();

        viewRobot(droid);
    }
}
