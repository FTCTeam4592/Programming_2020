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

        while (opModeIsActive()) {

            double leftyPower;
            double rightyPower;

            double liftPower;

            int flipOutDist = 75;

            double flipPos = -0.5;
            double rotPos = 0;
            double clawPos = 0.8;

            double drive = Range.clip(gamepad1.left_stick_y, -1, 1);
            double strafe = Range.clip(-gamepad1.right_stick_x, -1, 1);
            double rotate = Range.clip(-gamepad1.left_stick_x, -1, 1);


            double d = (float) scaleInput(drive);
            double s = (float) scaleInput(strafe);
            double r = (float) scaleInput(rotate);

            leftFront.setPower(Range.clip((0.75)* (d + s + r), -1, 1));
            leftRear.setPower(Range.clip((0.75)* (d - s + r), -1, 1));
            rightFront.setPower(Range.clip((0.75)* (d - s - r), -1, 1));
            rightRear.setPower(Range.clip((0.75)* (d + s - r), -1, 1));


            if(gamepad2.a && flipArm.getPosition()<0.4){
                flipArm.setPosition(0.67);
                //flipPos = 0.7;
            }
            else if (gamepad2.a && flipArm.getPosition()>0.4){
                flipArm.setPosition(0);
                //flipPos = 0;
            }

            //flipArm.setPosition(flipPos);


            if (gamepad2.dpad_right) {
                rotateClaw.setPosition(0.5);
                //rotPos = 0.5;
            }
            else {
                rotateClaw.setPosition(0);
            }

            double lift = Range.clip(gamepad2.left_stick_y, -1, 1);
            double l = (float) scaleInput(lift);
            liftSlide.setPower(Range.clip((0.75) * l, -1, 1));





            //rotateClaw.setPosition(rotPos);

            if(gamepad2.x){
                clampClaw.setPosition(1);
                //clawPos = 1;
            }
            else{
                clampClaw.setPosition(0.3);
                //clawPos = 0.2;
            }

            //clampClaw.setPosition(clawPos);

            if(gamepad2.right_bumper){
                platformClamp.setPosition(0.9);
            }
            else{
                platformClamp.setPosition(0.2);
            }

            telemetry.addData("flipArm", flipArm.getPosition());

            telemetry.addData("clamp", clampClaw.getPosition());


            telemetry.update();
        }



    }

    private void arcade(double left, double right){

        leftFront.setPower(left);
        leftRear.setPower(left);
        rightFront.setPower(right);
        rightRear.setPower(right);

    }

}
