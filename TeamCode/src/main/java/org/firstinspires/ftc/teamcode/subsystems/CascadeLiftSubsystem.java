package org.firstinspires.ftc.teamcode.subsystems;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import org.firstinspires.ftc.teamcode.Constants.PayloadConstants;

public class CascadeLiftSubsystem {
    DcMotorEx horizontalExtension;
    DcMotorEx leftCascadeMotor;
    DcMotorEx rightCascadeMotor;

    public CascadeLiftSubsystem(OpMode opMode, Telemetry telemetry) {
        horizontalExtension = opMode.hardwareMap.get(DcMotorEx.class, "horizontalExtension");
        leftCascadeMotor = opMode.hardwareMap.get(DcMotorEx.class, "leftCascadeMotor");
        rightCascadeMotor = opMode.hardwareMap.get(DcMotorEx.class, "rightCascadeMotor");

        horizontalExtension.setDirection(PayloadConstants.kHorizontalExtension);
        leftCascadeMotor.setDirection(PayloadConstants.kleftCascadeMotor);
        rightCascadeMotor.setDirection(PayloadConstants.krightCascadeMotor);

        horizontalExtension.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        leftCascadeMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        rightCascadeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        horizontalExtension.setPower(0);
        leftCascadeMotor.setPower(0);
        rightCascadeMotor.setPower(0);

        horizontalExtension.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftCascadeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightCascadeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public double[] encoderReading() {
        double[] encoderReading = new double[4];

        encoderReading[0] = leftCascadeMotor.getCurrentPosition();
        encoderReading[1] = rightCascadeMotor.getCurrentPosition();

        return encoderReading;
    }

    public void encoderReporting(Telemetry telemetry) {
        telemetry.addData("Horizontal Power: ", horizontalExtension.getPower());
        telemetry.addData("Cascade Power: ", leftCascadeMotor);
        telemetry.addData("Cascade Power: ", rightCascadeMotor);

        telemetry.addData("Left Cascade Counts: ", leftCascadeMotor.getCurrentPosition());
        telemetry.addData("Right Cascade Counts: ", rightCascadeMotor.getCurrentPosition());
        telemetry.addData("Left Horizontal Counts: ", horizontalExtension.getCurrentPosition());
        telemetry.update();
    }

    public void setCascadeLiftEncoder(int targetCounts, String Direction) {
        leftCascadeMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightCascadeMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);


        if (Direction == "UP") {
            leftCascadeMotor.setTargetPosition(targetCounts);
            rightCascadeMotor.setTargetPosition(targetCounts);
        } else if (Direction == "DOWN") {
            leftCascadeMotor.setTargetPosition(-targetCounts);
            rightCascadeMotor.setTargetPosition(-targetCounts);
        }
        leftCascadeMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightCascadeMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }

    public void setHorizontalExtensionEncoder(int targetCounts, String Direction) {
        horizontalExtension.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        if (Direction == "UP") {
            horizontalExtension.setTargetPosition(targetCounts);
       }
        else if (Direction == "DOWN") {
            horizontalExtension.setTargetPosition(-targetCounts);
        }
        horizontalExtension.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void setCascadePower(double power) {
        leftCascadeMotor.setPower(power);
        rightCascadeMotor.setPower(power);
    }

    public void setHorizontalPower(double power) {
        horizontalExtension.setPower(power);
    }

    public void shutdown() {
        horizontalExtension.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftCascadeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightCascadeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public boolean isBusyCheck() {
        boolean isBusy = true;
        if (horizontalExtension.isBusy() == true || leftCascadeMotor.isBusy() == true || rightCascadeMotor.isBusy() == true) {

        } else {
            isBusy = false;
        }
        return isBusy;

    }
}