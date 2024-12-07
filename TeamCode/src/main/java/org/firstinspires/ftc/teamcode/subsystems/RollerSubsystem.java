package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;

import com.qualcomm.robotcore.hardware.CRServo;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants.PayloadConstants;

public class RollerSubsystem {

    CRServo roller;
    public RollerSubsystem (OpMode opMode, Telemetry telemetry){
        roller = opMode.hardwareMap.get(CRServo.class, "roller");

        roller.setDirection(PayloadConstants.kroller);

    }
   public void setPower   (double power){
        roller.setPower(power);
   }
   public void shutdown (){
        roller.setPower(0);
   }
}