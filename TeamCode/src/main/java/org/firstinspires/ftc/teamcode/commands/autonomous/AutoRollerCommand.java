package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.RollerSubsystem;


public class AutoRollerCommand {
    Telemetry m_telemetry;
    RollerSubsystem m_rollerSubsystem;
    ElapsedTime m_ElapsedTime;
    double startTime;


    public AutoRollerCommand(ElapsedTime elapsedTime, RollerSubsystem rollerSubsystem, Telemetry telemetry) {
        m_ElapsedTime = elapsedTime;
        m_rollerSubsystem = rollerSubsystem;
        m_telemetry = telemetry;
    }


    public void operate(String direction) {
        startTime = m_ElapsedTime.milliseconds();
        if (direction == "FORWARD") {
            while (m_ElapsedTime.milliseconds() < startTime + 2000) {
                m_rollerSubsystem.setPower(0.3);
            }
        }


        else if (direction == "BACKWARD"){
            while (m_ElapsedTime.milliseconds() < startTime + 2000) {
                m_rollerSubsystem.setPower(-0.3);
            }
        }


        else {
           m_rollerSubsystem.setPower(0);
       }
        m_rollerSubsystem.shutdown();
    }

    public void shutdown() {
        m_rollerSubsystem.shutdown();
    }
}