package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.PIDController;

public class EncoderDriveCommand {
    MecanumDriveSubsystem m_MecanumDrive;
    PIDController m_PIDController;
    Telemetry telemetry;
    LinearOpMode m_linearOpMode;

    public EncoderDriveCommand(MecanumDriveSubsystem mecanumDrive, Telemetry telemetry, LinearOpMode linearOpMode){
        m_MecanumDrive = mecanumDrive;
        m_PIDController = new PIDController(0.0009,0,0,0.15,0);
        m_linearOpMode = linearOpMode;
    }

    public void EncoderDriveOperate(double distance, String direction) {
        int motorTarget = (int)(distance * Constants.EncoderDriveConstants.kCOUNTS_PER_INCH);
        m_PIDController.createSetPoint(motorTarget + (m_MecanumDrive.encoderReading()[0]));

        while (true && m_linearOpMode.opModeIsActive()) {
            m_PIDController.setProcessVariable(m_MecanumDrive.encoderReading()[0]);

            if (Math.abs(m_PIDController.getError()) < 10) {
                break;
            }

            double powerOutput = m_PIDController.getOutput();
            m_MecanumDrive.autoLeft(powerOutput);
            m_MecanumDrive.autoRight(powerOutput);

        }
        m_MecanumDrive.shutdown();
    }
    public void Shutdown() {
        m_MecanumDrive.shutdown();
    }
}