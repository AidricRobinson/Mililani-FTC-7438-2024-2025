package org.firstinspires.ftc.teamcode.commands.TeleOp;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.RollerSubsystem;

public class RollerOperateCommand {
    RollerSubsystem m_rollerSubsystem;
    Gamepad m_gamepad;
    Telemetry m_telemetry;

    public RollerOperateCommand (Gamepad gamepad,RollerSubsystem rollerSubsystem,Telemetry telemetry) {
        m_rollerSubsystem = rollerSubsystem;
        m_gamepad = gamepad;
        m_telemetry = telemetry;
    }

    public void operate (){
        if (m_gamepad.right_trigger > 0.2){
            m_rollerSubsystem.setPower(1);
        }
        else if (m_gamepad.left_trigger > 0.2){
            m_rollerSubsystem.setPower(-1);
        }
        else {
            m_rollerSubsystem.setPower(0);
        }


    }
    public void shutdown(){
        m_rollerSubsystem.shutdown();
    }
}
