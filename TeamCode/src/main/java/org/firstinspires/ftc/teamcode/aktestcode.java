package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "aktestmotors", group = "Test")



public class aktestcode extends LinearOpMode {


    DcMotor leftFront, rightFront, leftRear, rightRear;

    public void runOpMode() {


        leftFront = hardwareMap.dcMotor.get("leftFront");

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        rightFront = hardwareMap.dcMotor.get("rightFront");

        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftRear = hardwareMap.dcMotor.get("leftRear");

        leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        rightRear = hardwareMap.dcMotor.get("rightRear");

        rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);



        waitForStart();

        while(opModeIsActive()) {

            leftFront.setTargetPosition(5000);
            rightFront.setTargetPosition(5000);
            leftRear.setTargetPosition(5000);
            rightRear.setTargetPosition(5000);

            leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            leftRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            leftFront.setPower(0.5);
            rightFront.setPower(0.5);
            leftRear.setPower(0.5);
            rightRear.setPower(0.5);

            leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        }
    }
}