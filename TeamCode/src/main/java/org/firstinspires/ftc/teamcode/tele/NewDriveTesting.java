package org.firstinspires.ftc.teamcode.tele;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.SkyStone4592;


@TeleOp(name = "New Drive Test", group = "test")

public class NewDriveTesting extends SkyStone4592 {

    @Override
    public void runOpMode() throws InterruptedException {

        tele();
        waitForStart();

        leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        liftSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        flipArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (opModeIsActive()) {

            double leftyPower;
            double rightyPower;

            double liftPower;

            int flipOutDist = 75;

            double flipPos = -0.5;
            double rotPos = 0;
            double clawPos = 0.8;

            double drive = Range.clip(gamepad1.left_stick_y, -1, 1);
            double strafe = Range.clip(gamepad1.left_stick_x, -1, 1);
            double rotate = Range.clip(gamepad1.right_stick_x, -1, 1);
//            double drive = Range.clip(-gamepad1.left_stick_y, -1, 1); //bruh
//            double strafe = Range.clip(gamepad1.right_stick_x, -1, 1);
//            double rotate = Range.clip(gamepad1.left_stick_x, -1, 1);


            double d = (float) scaleInput(drive);
            double s = (float) scaleInput(strafe);
            double r = (float) scaleInput(rotate);

            leftFront.setPower(Range.clip((-0.75) * (d + s + r), -1, 1));
            leftRear.setPower(Range.clip((0.75) * (d - s + r), -1, 1));
            rightFront.setPower(Range.clip((-0.75) * (d - s - r), -1, 1));
            rightRear.setPower(Range.clip((0.75) * (d + s - r), -1, 1));


            if (gamepad2.a && flipArm.getCurrentPosition() < 400) {
                flipArm.setTargetPosition(850);
                flipArm.setPower(0.5);
                flipArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            } else if (gamepad2.a && flipArm.getCurrentPosition() > 4) {
                flipArm.setTargetPosition(0);
                flipArm.setPower(0.5);
                flipArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                if (gamepad2.a && flipArm.getTargetPosition() < 0.4) { //AAAAAAAAAAAAAAAAAAAA
                    flipArm.setTargetPosition(5000); //anson don't set this to 5000 ever again please i beg you
                    //flipArm.setPower(0.25);
                    //flipPos = 0.7;
                } else if (gamepad2.a && flipArm.getTargetPosition() > 0.4) {
                    flipArm.setTargetPosition(0);
                    //telemetry.addData("current position: ",  flipArm.getTargetPosition());
                    //flipArm.setPower(-0.5);
                    //flipPos = 0;
                }

                //flipArm.setPosition(flipPos);


                if (gamepad2.dpad_right) {
                    rotateClaw.setPosition(0.5);
                    //rotPos = 0.5;
                } else {
                    rotateClaw.setPosition(0);
                }

                double lift = Range.clip(gamepad2.left_stick_y, -1, 1);
                double l = (float) scaleInput(lift);
                liftSlide.setPower(Range.clip((0.75) * l, -1, 1));

                if (gamepad2.b && capFlip.getPosition() < 0.3) {
                    capFlip.setPosition(0.6);
                } else if (gamepad2.b && capFlip.getPosition() > 0.3) {
                    capFlip.setPosition(0);
                }


                //rotateClaw.setPosition(rotPos);

                if (gamepad2.x) {
                    clampClaw.setPosition(0.3);
                    //clawPos = 1;
                } else {
                    clampClaw.setPosition(1);
                    //clawPos = 0.2;
                }

                //clampClaw.setPosition(clawPos);

                if (gamepad2.right_bumper) {
                    platformClampLeft.setPosition(0.2);
                } else {
                    platformClampLeft.setPosition(0.9);
                }

                telemetry.addData("flipArm", flipArm.getCurrentPosition());
                telemetry.addData("flipArm", flipArm.getTargetPosition());

                telemetry.addData("clamp", clampClaw.getPosition());


                telemetry.update();
            }


        }

    }
}
