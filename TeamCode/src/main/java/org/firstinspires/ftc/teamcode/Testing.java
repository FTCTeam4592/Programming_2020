package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.SkyStone4592;


@TeleOp(name = "BEEP BOOP")

public class Testing extends SkyStone4592 {

    @Override
    public void runOpMode() throws InterruptedException {

        tele();
        waitForStart();


        leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        liftSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (opModeIsActive()) {

            double leftyPower;
            double rightyPower;

            double liftPower;

            int flipOutDist = 75;

            double flipPos = -0.5;
            double rotPos = 0;
            double clawPos = 0.8;

            double leftFront_Power = Range.clip(gamepad1.left_stick_x, -1, 1);
            double rightFront_Power = Range.clip(leftFront_Power, -1, 1);
            double leftRear_Power = Range.clip(-leftFront_Power, -1, 1);
            double rightRear_Power = Range.clip(-leftFront_Power, -1, 1);

            leftFront_Power = (float) scaleInput(leftFront_Power);
            rightFront_Power = (float) scaleInput(rightFront_Power);
            leftRear_Power = (float) scaleInput(leftRear_Power);
            rightRear_Power = (float) scaleInput(rightRear_Power);

            //triggerForward();
            //triggerBackward();


            //drive
            double frontRight = Range.clip(-gamepad1.right_stick_y, -1, 1);
            double rearRight = Range.clip(-gamepad1.right_stick_y, -1, 1);
            double frontLeft = Range.clip(gamepad1.left_stick_y, -1, 1);
            double rearLeft = Range.clip(gamepad1.left_stick_y, -1, 1);

            frontRight = (float) scaleInput(frontRight);
            rearRight = (float) scaleInput(rearRight);
            frontLeft = (float) scaleInput(frontLeft);
            rearLeft = (float) scaleInput(rearLeft);

            double distance_extend = fDS.getDistance(DistanceUnit.INCH);
            telemetry.addData("distance extend", distance_extend);


            int current;

            if (gamepad1.right_stick_x == 0 && gamepad1.right_stick_y == 0) {
                float movex = gamepad1.left_stick_y;
                float movey = -1 * gamepad1.left_stick_x;


                // roll: right_stick_x ranges from -1 to 1, where -1 is full counterclockwise, and
                // 1 is full clockwise

                float roll = gamepad1.left_trigger;
                float rolr = gamepad1.right_trigger;

                // clip the right/left values so that the values never exceed +/- 1
                movex = Range.clip(movex, -1, 1);
                movey = Range.clip(movey, -1, 1);
                roll = Range.clip((roll * 2), -1, 1);
                rolr = Range.clip((rolr * 2), -1, 1);

                // scale the joystick value to make it easier to control
                // the robot more precisely at slower speeds.
                movex = (float) scaleInput(movex);
                movey = (float) scaleInput(movey);
                roll = (float) scaleInput(roll);
                rolr = (float) scaleInput(rolr);

                // write the values to the motors
                leftFront.setPower(Range.clip(-movex + movey + roll - rolr, -1, 1));
                leftRear.setPower(Range.clip(movex - movey + roll - rolr, -1, 1));
                rightFront.setPower(Range.clip(-movex - movey - roll + rolr, -1, 1));
                rightRear.setPower(Range.clip(movex + movey - roll + rolr, -1, 1));
            } else {
                tele();
                leftyPower = gamepad1.right_stick_y;
                rightyPower = gamepad1.right_stick_y;
                //arcade(leftyPower, rightyPower);
            }


            if (gamepad1.y) {
                liftSlide.setPower(0.5);
            } else if (gamepad1.b) {
                liftSlide.setPower(-0.5);
            } else {
                liftSlide.setPower(0);
            }

            if (gamepad1.a && flipArm.getCurrentPosition() < 4) {
                flipArm.setTargetPosition(10);
                //flipPos = 0.7;
            } else if (gamepad1.a && flipArm.getCurrentPosition() > 4) {

                if (gamepad1.a && flipArm.getTargetPosition() < 0.4) { //AAAAAAAAAAAAAAAAAAAAAAAAAAAAA
                    flipArm.setTargetPosition(1);
                    //flipPos = 0.7;
                } else if (gamepad1.a && flipArm.getTargetPosition() > 0.4) {
                    flipArm.setTargetPosition(0);
                    //flipPos = 0;
                }

                //flipArm.setPosition(flipPos);


                if (gamepad1.dpad_right) {
                    rotateClaw.setPosition(0.5);
                    //rotPos = 0.5;
                } else {
                    rotateClaw.setPosition(0);
                }

                //rotateClaw.setPosition(rotPos);

                if (gamepad1.x) {
                    clampClaw.setPosition(1);
                    //clawPos = 1;
                } else {
                    clampClaw.setPosition(0.2);
                    //clawPos = 0.2;
                }

                //clampClaw.setPosition(clawPos);

                if (gamepad1.right_bumper) {
                    platformClampLeft.setPosition(0.8);
                } else {
                    platformClampLeft.setPosition(0.2);
                }

                telemetry.addData("flipArm", flipArm.getCurrentPosition());
                telemetry.addData("flipArm", flipArm.getTargetPosition());

                telemetry.addData("clamp", clampClaw.getPosition());


                telemetry.update();
            }


        }

    }
}
