package org.firstinspires.ftc.teamcode.commands.TeleOp;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.CascadeLiftSubsystem;

public class CascadeLiftCommand {
    CascadeLiftSubsystem m_cascadeLiftSubsystem;
    Gamepad m_gamepad;
    Telemetry m_telemetry;
    public CascadeLiftCommand (Gamepad gamepad, CascadeLiftSubsystem cascadeLiftSubsystem, Telemetry telemetry) {
        m_gamepad = gamepad;
        m_cascadeLiftSubsystem = cascadeLiftSubsystem;
        m_telemetry = telemetry;
    }
    public void operate(Gamepad gamepad) {
        //the vertical lift
        if (gamepad.right_trigger > 0.2){
            m_cascadeLiftSubsystem.setCascadePower(0.8);
        }
        else if (gamepad.right_bumper) {
            m_cascadeLiftSubsystem.setCascadePower(-0.8);
        }
        else {
            m_cascadeLiftSubsystem.setCascadePower(0);
        }
 
    }

    public void shutdown () {
        m_cascadeLiftSubsystem.shutdown();
    }
}
