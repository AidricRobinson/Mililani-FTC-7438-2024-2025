package org.firstinspires.ftc.teamcode.commands.autonomous;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.Constants.EncoderDriveConstants;
import org.firstinspires.ftc.teamcode.PIDController;

public class TurnDriveCommand {
    MecanumDriveSubsystem m_mecanumDriveSubsystem;
    Telemetry m_Telemetry;
    LinearOpMode m_LinearOpMode;
    PIDController m_pidController;
    public TurnDriveCommand(MecanumDriveSubsystem mecanumDriveSubsystem, LinearOpMode linearOpMode, Telemetry telemetry) {
        m_mecanumDriveSubsystem = mecanumDriveSubsystem;
        m_LinearOpMode = linearOpMode;
        m_Telemetry = telemetry;
        m_pidController = new PIDController(0.01, 0.00001, 0,0,999999999.9999);
    }
    public void operate(double targetAngle) {
        double currentAngle = m_mecanumDriveSubsystem.getRollDegrees();
        double m_TargetAngle = currentAngle + targetAngle;
        m_pidController.createSetPoint(m_TargetAngle);
        m_pidController.setProcessVariable(currentAngle);
        double error = m_pidController.getError();

        while (Math.abs(error) > EncoderDriveConstants.kTURN_ANGLE_TOLERANCE && m_LinearOpMode.opModeIsActive()) {
            currentAngle = m_mecanumDriveSubsystem.getRollDegrees();
            error = m_pidController.getError();
            m_pidController.setProcessVariable(currentAngle);

            m_Telemetry.addData("Current Angle: ", currentAngle);
            m_Telemetry.addData("Target Angle: ", m_TargetAngle);
            m_Telemetry.addData("Error: ", error);
            m_Telemetry.update();

            m_mecanumDriveSubsystem.autoRight(-m_pidController.getOutput());
            m_mecanumDriveSubsystem.autoLeft(m_pidController.getOutput());
        }
        m_mecanumDriveSubsystem.shutdown();
    }
    public void Shutdown() {m_mecanumDriveSubsystem.shutdown();}
}