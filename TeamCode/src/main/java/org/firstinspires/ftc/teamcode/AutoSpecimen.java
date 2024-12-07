package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.commands.autonomous.StrafeDriveCommand;
import org.firstinspires.ftc.teamcode.subsystems.RollerSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.CascadeLiftSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.commands.autonomous.EncoderDriveCommand;
import org.firstinspires.ftc.teamcode.commands.autonomous.AutoHorizontalCommand;
import org.firstinspires.ftc.teamcode.commands.autonomous.AutoLiftCommand;
import org.firstinspires.ftc.teamcode.commands.autonomous.AutoRollerCommand;
import org.firstinspires.ftc.teamcode.commands.autonomous.TurnDriveCommand;
import org.firstinspires.ftc.teamcode.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.commands.autonomous.AutoClawCommand;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="AutoSpecimen", group="Linear OpMode")
public class AutoSpecimen extends LinearOpMode {
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
    private ClawSubsystem clawSubsystem;
    private AutoClawCommand autoClawCommand;



    public void runOpMode () {
        elapsedTime = new ElapsedTime();

        rollerSubsystem = new RollerSubsystem(this, telemetry);
        cascadeLiftSubsystem = new CascadeLiftSubsystem(this, telemetry);
        mecanumDriveSubsystem = new MecanumDriveSubsystem(this);
        clawSubsystem = new ClawSubsystem(this);

        encoderDriveCommand = new EncoderDriveCommand(mecanumDriveSubsystem, telemetry, this);
        autoRollerCommand = new AutoRollerCommand(elapsedTime, rollerSubsystem, telemetry);
        autoHorizontalCommand = new AutoHorizontalCommand(cascadeLiftSubsystem, telemetry);
        autoLiftCommand = new AutoLiftCommand(cascadeLiftSubsystem, telemetry);
        turnDriveCommand = new TurnDriveCommand(mecanumDriveSubsystem, this, telemetry);
        strafeDriveCommand = new StrafeDriveCommand(mecanumDriveSubsystem, telemetry, this);
        autoClawCommand = new AutoClawCommand(clawSubsystem);


        waitForStart();
        encoderDriveCommand.EncoderDriveOperate(23, "FORWARD");
        autoLiftCommand.operate(1600, "UP");
        encoderDriveCommand.EncoderDriveOperate(3, "FORWARD");
        autoLiftCommand.operate(-800, "DOWN");
        autoClawCommand.open();
        encoderDriveCommand.EncoderDriveOperate(-26, "BACKWARD");
        autoLiftCommand.operate(-800, "DOWN");
//        strafeDriveCommand.operate(30,"LEFT");



        //Shutdown
        mecanumDriveSubsystem.shutdown();
        cascadeLiftSubsystem.shutdown();
        rollerSubsystem.shutdown();
    }
}