package org.firstinspires.ftc.teamcode.commands.TeleOp;

import com.qualcomm.robotcore.hardware.Gamepad;
import org.firstinspires.ftc.teamcode.subsystems.CascadeLiftSubsystem;

public class AscensionCommand {
    CascadeLiftSubsystem m_cascadeLiftSubsystem ;
    public AscensionCommand (CascadeLiftSubsystem cascadeLiftSubsystem){
        m_cascadeLiftSubsystem = cascadeLiftSubsystem;
    }
    public void operate (Gamepad gamepad){
        if (gamepad.dpad_down) {
            //measurement not correct. Im not 100% sure how this works. Worry about this later :c
            m_cascadeLiftSubsystem.setCascadeLiftEncoder(20, "UP");
            m_cascadeLiftSubsystem.setCascadeLiftEncoder(20,"DOWN");
            m_cascadeLiftSubsystem.setCascadeLiftEncoder(20, "UP");
            m_cascadeLiftSubsystem.setCascadeLiftEncoder(20,"DOWN");
        }
        else {
            m_cascadeLiftSubsystem.setCascadePower(0);
        }
    }
    public void shutdown (){
        m_cascadeLiftSubsystem.shutdown();
    }
}
