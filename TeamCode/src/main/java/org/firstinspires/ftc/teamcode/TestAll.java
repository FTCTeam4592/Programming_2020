package org.firstinspires.ftc.teamcode;

public class TestAll extends SkyStone4592 {
    public void runOpMode() throws InterruptedException {
        testDrive(5, 5);
        stopRobot();
        testArms();
    }

    public void testDrive(double speed, double dist) {
        driveForward(speed, dist);
        driveReverse(speed, dist);

        turnLeft(speed, dist);
        turnRight(speed, dist);

        strafeLeft(speed, 90);
        strafeRight(speed, 90);
    }

    public void testArms() {
        flipArm.setPosition(0.725);
        sleep(2000);
        flipArm.setPosition(0);

        rotateClaw.setPosition(20);
    }
}
