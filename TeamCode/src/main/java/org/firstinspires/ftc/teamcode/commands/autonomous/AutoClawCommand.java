package org.firstinspires.ftc.teamcode.commands.autonomous;

import org.firstinspires.ftc.teamcode.subsystems.ClawSubsystem;

public class AutoClawCommand {
    ClawSubsystem m_clawSubsystem;
    public AutoClawCommand(ClawSubsystem clawSubsystem){
        m_clawSubsystem = clawSubsystem;
    }
    public void open(){
        m_clawSubsystem.setClawPosition(0.75);
    }
    public void close(){
        m_clawSubsystem.setClawPosition(0);
    }
}
