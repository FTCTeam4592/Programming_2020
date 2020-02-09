package org.firstinspires.ftc.teamcode;

public class TestAll extends SkyStone4592 {

    public void runOpMode() throws InterruptedException {
        testDrive(5, 5);
        stopRobot();
        testArm();
        stopRobot();
    }

    public void testDrive(double speed, double dist) {
        driveForward(speed, dist);
        driveReverse(speed, dist);
        sleep(500);

        turnLeft(speed, dist);
        turnRight(speed, dist);
        sleep(500);

        strafeLeft(speed, 90);
        strafeRight(speed, 90);
    }

    public void testArm() {
        flipArm.setPower(5); //hopefully nothing breaks
        sleep(500);
        flipArm.setPower(-5); //hopefully flips back
        sleep(500);

        rotateClaw.setPosition(1); //spin the claw a bit
        sleep(500);
        rotateClaw.setPosition(0); //set it back
        sleep(500);

        clampClaw.setPosition(0.3); //closes claw
        sleep(500);
        clampClaw.setPosition(0); //should open the claw?
        sleep(500);

        platformClampLeft.setPosition(0.9); //test the platform grabber
        sleep(500);
        platformClampLeft.setPosition(0); //put it back, hopefully
        sleep(500);
    }
}
