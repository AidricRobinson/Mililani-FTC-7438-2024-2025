package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

public class ClawSubsystem {
    private Servo claw;
    private boolean toggle;
    private CRServo servo;
    public ClawSubsystem(OpMode opMode) {
         claw = opMode.hardwareMap.get(Servo.class, "claw");
         toggle = true;
    }
    public void setClawPosition(double Position) {
        claw.setPosition(Position);
    }

    public void forwardClawDirection() {
        claw.setDirection(Servo.Direction.FORWARD);
    }
    public void reverseClawDirection() {
        claw.setDirection(Servo.Direction.REVERSE);
    }
}