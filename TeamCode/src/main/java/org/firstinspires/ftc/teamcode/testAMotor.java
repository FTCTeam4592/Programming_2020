package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="testAMotor", group="Testing")


public class testAMotor extends LinearOpMode {

    public DcMotor leftFront, rightFront, leftRear, rightRear;

    public void runOpMode() throws InterruptedException {


        leftFront = hardwareMap.dcMotor.get("leftFront");

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        rightFront = hardwareMap.dcMotor.get("rightFront");

        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftRear = hardwareMap.dcMotor.get("leftRear");

        leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        rightRear = hardwareMap.dcMotor.get("rightRear");

        rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        waitForStart();

        while(opModeIsActive()) {

            leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            leftFront.setTargetPosition(500);

            rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            rightFront.setTargetPosition(500);

            leftRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            leftRear.setTargetPosition(500);

            rightRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            rightRear.setTargetPosition(500);

            telemetry.addData("Pos", leftFront.getCurrentPosition());

            leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            leftRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            rightRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            leftFront.setPower(0.5);

            rightFront.setPower(0.5);

            leftRear.setPower(0.5);

            rightRear.setPower(0.5);


            telemetry.update();

        }

    }

}
