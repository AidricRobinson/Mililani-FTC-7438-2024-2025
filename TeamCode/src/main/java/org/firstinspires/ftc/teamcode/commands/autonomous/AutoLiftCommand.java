package org.firstinspires.ftc.teamcode.commands.autonomous;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.PIDController;
import org.firstinspires.ftc.teamcode.subsystems.CascadeLiftSubsystem;


public class AutoLiftCommand {
    CascadeLiftSubsystem m_cascadeLiftSubsystem;
    Telemetry m_telemetry;
    PIDController m_PIDController;

    public AutoLiftCommand (CascadeLiftSubsystem cascadeLiftSubsystem,Telemetry telemetry){
        m_cascadeLiftSubsystem = cascadeLiftSubsystem;
        m_telemetry = telemetry;
        m_PIDController = new PIDController(0.0005, 0, 0, 0.8, 999999999);
    }
    public void operate(int counts, String direction) {
        m_cascadeLiftSubsystem.setCascadeLiftEncoder(counts, direction);



        while (m_cascadeLiftSubsystem.isBusyCheck() == true) {
            double powerOutput = -0.8;
            m_cascadeLiftSubsystem.setCascadePower(powerOutput);
            m_telemetry.addData("IsBusyCheck: ", m_cascadeLiftSubsystem.isBusyCheck());
            m_telemetry.update();


        }
        m_cascadeLiftSubsystem.shutdown();
    }

    public void shutdown () {
        m_cascadeLiftSubsystem.shutdown();
    }
}
