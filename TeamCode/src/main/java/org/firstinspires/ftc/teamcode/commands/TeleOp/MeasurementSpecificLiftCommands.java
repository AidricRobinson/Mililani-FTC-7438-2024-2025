package org.firstinspires.ftc.teamcode.commands.TeleOp;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.subsystems.CascadeLiftSubsystem;
import org.firstinspires.ftc.teamcode.commands.autonomous.AutoLiftCommand;

public class MeasurementSpecificLiftCommands {
    CascadeLiftSubsystem m_cascadeLiftSubsystem;
    Gamepad m_gamepad;
    Telemetry m_telemetry;
    AutoLiftCommand m_autoLiftCommand;

    public MeasurementSpecificLiftCommands(Gamepad gamepad, CascadeLiftSubsystem cascadeLiftSubsystem,Telemetry telemetry,AutoLiftCommand autoLiftCommand){
        m_cascadeLiftSubsystem = cascadeLiftSubsystem;
        m_gamepad = gamepad;
        m_telemetry = telemetry;
        m_autoLiftCommand = autoLiftCommand;
    }
    public void operate(Gamepad gamepad) {
        //DON'T RELY ON THE NUMBERS. THEY ARE PLACE HOLDERS >:(
        //High Specimen

        if (gamepad.y){
            m_autoLiftCommand.operate(1800, "UP");
        }
        else if (gamepad.b) {
            m_autoLiftCommand.operate(-1400, "DOWN");
        }


    }
    public void shutdown() {
        m_cascadeLiftSubsystem.shutdown();
    }
}