import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by user on 12/17/17.
 */
//@Autonomous(name="IMU Test")
public class IMUTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        ExpansionHubIMU imu = new ExpansionHubIMU(hardwareMap);
        imu.initialize();

        waitForStart();

        while(opModeIsActive())
        {
            imu.update(telemetry);

            telemetry.update();
            idle();
        }
    }
}
