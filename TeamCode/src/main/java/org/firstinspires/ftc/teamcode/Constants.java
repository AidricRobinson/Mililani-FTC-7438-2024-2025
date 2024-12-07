package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;



public class Constants {
    public static class MecanumConstants {
        public static final DcMotorEx.Direction kLeftFrontDirection = DcMotorEx.Direction.REVERSE;
        public static final DcMotorEx.Direction kLeftBackDirection = DcMotorEx.Direction.REVERSE;
        public static final DcMotorEx.Direction kRightFrontDirection = DcMotorEx.Direction.FORWARD;
        public static final DcMotorEx.Direction kRightBackDirection = DcMotorEx.Direction.FORWARD;
        public static final double slowModeDenominator = 0.5;
    }
    public static class EncoderDriveConstants {
        public static final double kPULSES_PER_ROTATION = 384.5;
        public static final double kCOUNTS_PER_ROTATION = kPULSES_PER_ROTATION;
        public static final double kDRIVE_GEAR_REDUCTION = 1;
        public static final double kWHEEL_DIAMETER = 3.77953;
        public static final double kWHEEL_CIRCUMFERENCE = Math.PI * kWHEEL_DIAMETER;
        public static final double kCOUNTS_PER_INCH = (kCOUNTS_PER_ROTATION * kDRIVE_GEAR_REDUCTION) / kWHEEL_CIRCUMFERENCE;
        public static final int kTURN_ANGLE_TOLERANCE = 1;

        public static final double kEncoderDriveP = 0.2;
        public static final double kEncoderDriveMin = 0.2;
        public static final double kEncoderDriveMax = 0.5;
    }
    public static class PayloadConstants {
        public static final DcMotorEx.Direction kHorizontalExtension = DcMotorEx.Direction.FORWARD;
        public static final DcMotorEx.Direction kleftCascadeMotor = DcMotorEx.Direction.REVERSE;
        public static final DcMotorEx.Direction krightCascadeMotor = DcMotorEx.Direction.FORWARD;
        public static final DcMotorEx.Direction kroller = DcMotorEx.Direction.FORWARD;
        public static final int highChamberCounts = 1600;
        public static final int slamDownCounts = 250;
    }
    public static class AutoConstants {
        public static final int kliftCommandCount = 20;
        public static final int khorizontalCommandCount = 20;

    }

    public static class PIDConstants {
        public static final double minLimit = -1;
        public static final double maxLimit = 1;
    }

}
