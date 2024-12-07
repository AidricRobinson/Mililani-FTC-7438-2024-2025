package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.Constants.AutoConstants.khorizontalCommandCount;
import static org.firstinspires.ftc.teamcode.Constants.AutoConstants.kliftCommandCount;

import org.firstinspires.ftc.teamcode.commands.autonomous.StrafeDriveCommand;
import org.firstinspires.ftc.teamcode.subsystems.RollerSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.CascadeLiftSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.commands.autonomous.EncoderDriveCommand;
import org.firstinspires.ftc.teamcode.commands.autonomous.AutoHorizontalCommand;
import org.firstinspires.ftc.teamcode.commands.autonomous.AutoLiftCommand;
import org.firstinspires.ftc.teamcode.commands.autonomous.AutoRollerCommand;
import org.firstinspires.ftc.teamcode.commands.autonomous.TurnDriveCommand;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="TestAutonomousPath", group="Linear OpMode")
public class TestAutonomousPath extends LinearOpMode {
    //declaring subsystems and commands
    private RollerSubsystem rollerSubsystem;
    private CascadeLiftSubsystem cascadeLiftSubsystem;
    private MecanumDriveSubsystem mecanumDriveSubsystem;
    private EncoderDriveCommand encoderDriveCommand;
    private AutoRollerCommand autoRollerCommand;
    private AutoHorizontalCommand autoHorizontalCommand;
    private AutoLiftCommand autoLiftCommand;
    private ElapsedTime elapsedTime;
    private TurnDriveCommand turnDriveCommand;
    private StrafeDriveCommand strafeDriveCommand;

    @Override
    public void runOpMode() {
        elapsedTime = new ElapsedTime();

        rollerSubsystem = new RollerSubsystem(this, telemetry);
        cascadeLiftSubsystem = new CascadeLiftSubsystem(this, telemetry);
        mecanumDriveSubsystem = new MecanumDriveSubsystem(this);


        encoderDriveCommand = new EncoderDriveCommand(mecanumDriveSubsystem,telemetry, this);
        autoRollerCommand = new AutoRollerCommand(elapsedTime, rollerSubsystem, telemetry);
        autoHorizontalCommand = new AutoHorizontalCommand(cascadeLiftSubsystem, telemetry);
        autoLiftCommand = new AutoLiftCommand(cascadeLiftSubsystem, telemetry);
        turnDriveCommand = new TurnDriveCommand(mecanumDriveSubsystem,this, telemetry);
        strafeDriveCommand = new StrafeDriveCommand(mecanumDriveSubsystem, telemetry, this);

        waitForStart();
        encoderDriveCommand.EncoderDriveOperate(12, "FORWARD");
        strafeDriveCommand.operate(12, "RIGHT");

        //shutdown
        mecanumDriveSubsystem.shutdown();
        cascadeLiftSubsystem.shutdown();
        rollerSubsystem.shutdown();

    }
}
