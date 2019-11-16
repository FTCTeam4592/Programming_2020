package org.firstinspires.ftc.teamcode.tele;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.RoverRuckus4592;

public class DriveTrainTest extends RoverRuckus4592 {

    @Override
    public void runOpMode() throws InterruptedException {

        tele();
        waitForStart();


        leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (opModeIsActive()) {

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

            if(Math.abs(gamepad1.left_stick_x * gamepad1.left_stick_y)>0 && ((gamepad1.right_stick_x+gamepad1.right_stick_y)==0)) {
                //strafe
                drive(leftFront_Power, rightFront_Power, leftRear_Power, rightRear_Power);
            }
            else {
                //drive
                drive(-frontLeft, frontRight, -rearLeft, rearRight);
            }

            if(gamepad1.right_bumper){
                strafeRight(1, 15);
            }
            if(gamepad1.left_bumper) {
                driveReverse(1, 10);
            }

            telemetry.update();
        }

    }

}
