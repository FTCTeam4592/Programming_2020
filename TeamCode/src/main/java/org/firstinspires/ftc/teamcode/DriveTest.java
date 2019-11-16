package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;


/**
 * Created by user on 12/9/17.
 */

@Disabled
@TeleOp(name = "DriveTest")

public class DriveTest extends Robot4592 {

    @Override
    public void runOpMode() throws InterruptedException {
        tele();

        waitForStart();


        leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        liftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        final double originalFlipPos = flipOut.getCurrentPosition();


        while (opModeIsActive()) {
            // update

            final int drop_position = 0;
            final int original_position = 3000;
            final int extendUp_Raised = 4000;
            final int extendUp_Lowered = 0;
            final int original_out = extendOut.getCurrentPosition();
            final int original_up = extendUp.getCurrentPosition();
            final double originial_flip_up = flipUp.getPosition();

            //strafe
            double leftFront_Power = Range.clip(-1* gamepad1.left_stick_x,-0.99,0.99);
            double rightFront_Power = Range.clip(leftFront_Power,-0.99,0.99);
            double leftRear_Power = Range.clip(-leftFront_Power,-0.99,0.99);
            double rightRear_Power = Range.clip(-leftFront_Power,-0.99,0.99);

            leftFront_Power = (float) scaleInput(leftFront_Power);
            rightFront_Power = (float) scaleInput(rightFront_Power);
            leftRear_Power = (float) scaleInput(leftRear_Power);
            rightRear_Power = (float) scaleInput(rightRear_Power);

            //drive
            double frontRight = Range.clip(-gamepad1.right_stick_y, -0.99, 0.99);
            double rearRight = Range.clip(-gamepad1.right_stick_y,-0.99,0.99);
            double frontLeft = Range.clip(gamepad1.left_stick_y,-0.99,0.99);
            double rearLeft = Range.clip(gamepad1.left_stick_y,-0.99,0.99);

            frontRight = (float) scaleInput(frontRight);
            rearRight = (float) scaleInput(rearRight);
            frontLeft = (float) scaleInput(frontLeft);
            rearLeft = (float) scaleInput(rearLeft);


            if(Math.abs(gamepad1.left_stick_x*gamepad1.left_stick_y)>0 && ((gamepad1.right_stick_x+gamepad1.right_stick_y) == 0)) {
                drive(leftFront_Power, rightFront_Power, leftRear_Power, rightRear_Power);
            }
            else {
                drive(-frontLeft, frontRight, -rearLeft, rearRight);
            }

            if (gamepad2.x && liftArm.getCurrentPosition()>= 500) {
                drop(0); //when x is pressed, the lift arm will go down
            }
            if (gamepad2.x && liftArm.getCurrentPosition() < 500) {
                climb(3200); //when b is pressed, teh lift arm will go up
            }

            if (gamepad2.b && extendUp.getCurrentPosition() <= 1500) {
                extendUp(extendUp_Raised);
            } else if (gamepad2.b && extendUp.getCurrentPosition() >= 1500) {
                extendUp(extendUp_Lowered);
            }

            double ext = 0.5;
            extendOut.setPower(0);
            while(gamepad2.right_bumper) {
                extendOut.setPower(ext);
            }

            while(gamepad2.left_bumper){
                extendOut.setPower(-ext);
            }




            if (gamepad2.b && extendUp.getCurrentPosition()<250) {


                extendUp.setTargetPosition(4500);
                extendUp.setPower(1);

            } else if (gamepad2.b && extendUp.getCurrentPosition()>250) {

                extendUp.setTargetPosition(10);
                extendUp.setPower(1);
            }

            while(gamepad2.dpad_up){

                flipUp(0.6);
            }
            while(gamepad2.dpad_down){
                flipUp(0.1);
            }


            telemetry.addData("leftfrontpower: ", leftFront_Power);
            telemetry.addData("leftrearpower: ", leftRear_Power);
            telemetry.addData("rightfrontpower: ", rightFront_Power);
            telemetry.addData("rightrearpower: ", rightRear_Power);
            telemetry.addData("lift position", liftArm.getCurrentPosition());
            telemetry.addData("extension position: ", extendOut.getCurrentPosition());
            telemetry.addData("extend up: ", extendUp.getCurrentPosition());
            telemetry.addData("flipup: ", flipUp.getPosition());
            telemetry.addData("flip out: ", flipOut.getCurrentPosition());
            /*
            if (isGold) {
                telemetry.addLine("IT WORKS BABYŸŸŸŸŸŸ;;;;;;;;;;;;YY");
            } else {
                telemetry.addLine("THIS AIN'T IT");
            }
*/
            telemetry.update();
            idle();


            telemetry.update();
            idle();
        }

    }


    private void drop( int p){

        liftArm.setTargetPosition(p);
        liftArm.setPower(-0.7);

    }

    private void climb( int o){

        liftArm.setTargetPosition(o);
        liftArm.setPower(1.0);

    }

    private void extendUp(int u){

        extendUp.setTargetPosition(u);
        extendUp.setPower(0.5);

    }

    private void extendDown(int d){
        extendUp.setTargetPosition(d);
        extendUp.setPower(-0.5);
    }

    private void flipUp(double s){

        flipUp.setPosition(s);

    }

    private void flipDown(){

        flipUp.setPosition(0);

    }

}