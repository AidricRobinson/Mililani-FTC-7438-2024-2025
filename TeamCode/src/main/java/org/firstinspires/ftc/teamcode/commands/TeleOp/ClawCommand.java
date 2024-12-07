package org.firstinspires.ftc.teamcode.commands.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.ClawSubsystem;
import com.qualcomm.robotcore.hardware.Gamepad;

public class ClawCommand {
    ClawSubsystem m_clawSubsystem;
    public ClawCommand(ClawSubsystem clawSubsystem) {
        m_clawSubsystem = clawSubsystem;
    }
    public void operate(Gamepad gamepad) {
        if (gamepad.b) {
            m_clawSubsystem.forwardClawDirection();
            m_clawSubsystem.setClawPosition(0.65);
        }
        if (gamepad.a) {
            m_clawSubsystem.reverseClawDirection();
            m_clawSubsystem.setClawPosition(0.3);
        }
    }
}
