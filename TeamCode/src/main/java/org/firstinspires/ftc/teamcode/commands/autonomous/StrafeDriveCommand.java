package org.firstinspires.ftc.teamcode.commands.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.PIDController;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class StrafeDriveCommand {
    MecanumDriveSubsystem m_MecanumDriveSubsystem;
    Telemetry m_Telemetry;
    PIDController m_PIDController;
    LinearOpMode m_linearOpMode;

    public StrafeDriveCommand(MecanumDriveSubsystem mecanumDriveSubsystem, Telemetry telemetry, LinearOpMode linearOpMode){
        m_MecanumDriveSubsystem = mecanumDriveSubsystem;
        m_Telemetry = telemetry;
        m_PIDController = new PIDController(0.001675,0,0,0.15, 0); //0.001655
        m_linearOpMode = linearOpMode;
    }

    public void operate(double distance, String direction){
        double motorTarget = (distance * Constants.EncoderDriveConstants.kCOUNTS_PER_INCH) + m_MecanumDriveSubsystem.encoderReading()[0];
        int i = 0;
        m_PIDController.createSetPoint(motorTarget);

        while (true && m_linearOpMode.opModeIsActive())  {
            m_PIDController.setProcessVariable(m_MecanumDriveSubsystem.encoderReading()[0]);
            double powerOutput = m_PIDController.getOutput();
            m_MecanumDriveSubsystem.autoStrafe(m_PIDController.getOutput());

            m_Telemetry.addData("current", m_MecanumDriveSubsystem.encoderReading()[0]);
            m_Telemetry.addData("target", motorTarget);
            m_Telemetry.addData("Error", m_PIDController.getError());
            m_Telemetry.update();

            if (Math.abs(m_PIDController.getError()) < 10) {
                m_MecanumDriveSubsystem.shutdown();
                break;
            }
        }

    }
    public void shutdown() {
        m_MecanumDriveSubsystem.shutdown();
    }
}