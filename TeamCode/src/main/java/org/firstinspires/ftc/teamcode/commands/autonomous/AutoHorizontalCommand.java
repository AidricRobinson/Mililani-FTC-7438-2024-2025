package org.firstinspires.ftc.teamcode.commands.autonomous;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.CascadeLiftSubsystem;

public class AutoHorizontalCommand {
    CascadeLiftSubsystem m_cascadeLiftSubsystem;
    Telemetry m_telemetry;
    public AutoHorizontalCommand(CascadeLiftSubsystem cascadeLiftSubsystem, Telemetry telemetry) {
        m_cascadeLiftSubsystem = cascadeLiftSubsystem;
        m_telemetry = telemetry;
    }
    public void operate(int counts, String direction) {
        m_cascadeLiftSubsystem.setHorizontalExtensionEncoder(counts, direction);

        while (m_cascadeLiftSubsystem.isBusyCheck() == true) {
            double powerOutput = 0.3;
            m_cascadeLiftSubsystem.setCascadePower(powerOutput);
        }

        m_cascadeLiftSubsystem.shutdown();
    }

    public void shutdown () {
        m_cascadeLiftSubsystem.shutdown();
    }
}
