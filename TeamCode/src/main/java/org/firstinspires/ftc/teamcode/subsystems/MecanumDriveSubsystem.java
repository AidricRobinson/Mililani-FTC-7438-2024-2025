package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.hardware.bosch.BNO055IMU;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.Constants.MecanumConstants;

public class MecanumDriveSubsystem {
    private BNO055IMU imu;
    private final BNO055IMU.Parameters parameters;
    DcMotorEx leftFront;
    DcMotorEx leftBack;
    DcMotorEx rightFront;
    DcMotorEx rightBack;
    private boolean slowModeOn;

    // initialize motor stuff
    public MecanumDriveSubsystem(OpMode opMode) {
        imu = opMode.hardwareMap.get(BNO055IMU.class, "imu");
        parameters = new BNO055IMU.Parameters();
        imu.initialize(parameters);

        leftFront = opMode.hardwareMap.get(DcMotorEx.class, "leftFront");
        leftBack = opMode.hardwareMap.get(DcMotorEx.class, "leftBack");
        rightFront = opMode.hardwareMap.get(DcMotorEx.class, "rightFront");
        rightBack = opMode.hardwareMap.get(DcMotorEx.class, "rightBack");
        // TeleOp
        leftFront.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        leftBack.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        rightFront.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        rightBack.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        // Motor spin sides
        leftFront.setDirection(MecanumConstants.kLeftFrontDirection);
        leftBack.setDirection(MecanumConstants.kLeftBackDirection);
        rightFront.setDirection(MecanumConstants.kRightFrontDirection);
        rightBack.setDirection(MecanumConstants.kRightBackDirection);

        slowModeOn = false;
    }
    //Tele Op code
    public void operate(Gamepad gamepad, Telemetry telemetry) {
        //mecanum drive variables
        double y = gamepad.left_stick_y;
        double x = gamepad.left_stick_x;
        double rx = -gamepad.right_stick_x;


        //field orientated variables
        double botHeading = getRollRadians();
        double rotX = x * Math.cos(botHeading) - y * Math.sin(botHeading);
        double rotY = x * Math.sin(botHeading) + y * Math.cos(botHeading);
        double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);

        if (gamepad.dpad_up && !slowModeOn) {
            slowModeOn = true;
        }
        else if (gamepad.dpad_down && slowModeOn){
            slowModeOn = false;
        }
        if (slowModeOn) {
            leftFront.setPower((rotY-rotX+rx) / denominator * MecanumConstants.slowModeDenominator);
            leftBack.setPower((rotY+rotX+rx) / denominator * MecanumConstants.slowModeDenominator);
            rightFront.setPower((rotY+rotX-rx) / denominator * MecanumConstants.slowModeDenominator);
            rightBack.setPower((rotY-rotX-rx) / denominator * MecanumConstants.slowModeDenominator);
        }
        else {
            leftFront.setPower((rotY-rotX+rx) / denominator);
            leftBack.setPower((rotY+rotX+rx) / denominator);
            rightFront.setPower((rotY+rotX-rx) / denominator);
            rightBack.setPower((rotY-rotX-rx)/ denominator);
        }
        telemetry.addData("Slow mode: ", slowModeOn);
        telemetry.addData("Angle Heading: ", botHeading);
        telemetry.update();
    }
    // Method to gather encoder counts
    public double[] encoderReading () {
        double[] encoderReading = new double[4];
        encoderReading[0] = leftFront.getCurrentPosition();
        encoderReading[1] = leftBack.getCurrentPosition();
        encoderReading[2] = rightFront.getCurrentPosition();
        encoderReading[3] = rightBack.getCurrentPosition();

        return encoderReading;
    }
    // method that is used to reset counts
    public void setEncoderTarget(int targetCounts, String Direction) {
        leftFront.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        // if statement for setting targets
        if(Direction == "FORWARD") {
            leftFront.setTargetPosition(targetCounts);
            leftBack.setTargetPosition(targetCounts);
            rightBack.setTargetPosition(targetCounts);
            rightFront.setTargetPosition(targetCounts);
        }
        else if (Direction == "BACKWARD") {
            leftFront.setTargetPosition(-targetCounts);
            leftBack.setTargetPosition(-targetCounts);
            rightFront.setTargetPosition(-targetCounts);
            rightBack.setTargetPosition(-targetCounts);
        }
        else if (Direction == "LEFT"){
            leftFront.setTargetPosition(-targetCounts);
            leftBack.setTargetPosition(targetCounts);
            rightFront.setTargetPosition(targetCounts);
            rightBack.setTargetPosition(-targetCounts);
        }
        else if (Direction == "RIGHT"){
            leftFront.setTargetPosition(targetCounts);
            leftBack.setTargetPosition(-targetCounts);
            rightFront.setTargetPosition(-targetCounts);
            rightBack.setTargetPosition(targetCounts);
        }
        leftFront.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        leftBack.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
    }
    public void resetEncoder(){
        leftFront.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
    }
    public void shutdown(){
        leftFront.setPower(0);
        leftBack.setPower(0);
        rightFront.setPower(0);
        rightBack.setPower(0);
    }
    public void autoLeft(double power){
        leftFront.setPower(power);
        leftBack.setPower(power);
    }
    public void autoRight(double power) {
        rightFront.setPower(power);
        rightBack.setPower(power);
    }
    public void autoStrafe(double power){
        rightFront.setPower(-power);
        leftBack.setPower(-power);
        leftFront.setPower(power);
        rightBack.setPower(power);
    }

    //Gyro position
    public double getYaw(){
        return imu.getAngularOrientation(AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES).thirdAngle;
    }
    public double getPitch(){
        return imu.getAngularOrientation(AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES).secondAngle;
    }
    public double getRollRadians(){
        return imu.getAngularOrientation(AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.RADIANS).firstAngle;
    }
    public double getRollDegrees(){
        return imu.getAngularOrientation(AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES).firstAngle;
    }

    public boolean isBusyCheck() {
        boolean isBusy = true;
        if (leftFront.isBusy() == true || rightFront.isBusy() == true || leftBack.isBusy() == true || rightBack.isBusy() == true) {

        }
        else {
            isBusy = false;
        }
        return isBusy;
    }
}