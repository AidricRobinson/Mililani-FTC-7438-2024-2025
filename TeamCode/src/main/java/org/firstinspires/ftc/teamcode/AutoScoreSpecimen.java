package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.Constants.AutoConstants.khorizontalCommandCount;
import static org.firstinspires.ftc.teamcode.Constants.AutoConstants.kliftCommandCount;

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

@Autonomous(name="AutoScoreSpecimen", group="Linear OpMode")
public class AutoScoreSpecimen extends LinearOpMode {
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

    @Override
    public void runOpMode() {
        elapsedTime = new ElapsedTime();

        rollerSubsystem = new RollerSubsystem(this, telemetry);
        cascadeLiftSubsystem = new CascadeLiftSubsystem(this, telemetry);
        mecanumDriveSubsystem = new MecanumDriveSubsystem(this);


        encoderDriveCommand = new EncoderDriveCommand(mecanumDriveSubsystem, telemetry, this);
        autoRollerCommand = new AutoRollerCommand(elapsedTime, rollerSubsystem, telemetry);
        autoHorizontalCommand = new AutoHorizontalCommand(cascadeLiftSubsystem, telemetry);
        autoLiftCommand = new AutoLiftCommand(cascadeLiftSubsystem, telemetry);
        turnDriveCommand = new TurnDriveCommand(mecanumDriveSubsystem, this, telemetry);

        // The actual Autonomous code
//      forward/backward movement:         encoderDriveCommand.EncoderDriveOperate(10, "FORWARD");
//      turning the robot left and right:  turnDriveCommand.operate(210);
//      making the vertical lift go move   autoLiftCommand.operate(10, "UP");
//      making the horizontal lift move    autoHorizontalCommand.operate(10, "FORWARD");
//      using the roller thingy:           autoRollerCommand.operate();

        //the numbers are place holders
        //going to the High basket
        encoderDriveCommand.EncoderDriveOperate(4, "FORWARD");
        autoLiftCommand.operate(203, "UP");
        encoderDriveCommand.EncoderDriveOperate(4, "FORWARD");
        autoLiftCommand.operate(203, "DOWN");
        encoderDriveCommand.EncoderDriveOperate(4, "BACKWARD");
        turnDriveCommand.operate(-90);
        encoderDriveCommand.EncoderDriveOperate(4, "FORWARD");
        //grab
        turnDriveCommand.operate(-90);
        encoderDriveCommand.EncoderDriveOperate(4,"FORWARD");
        //drop
        turnDriveCommand.operate(245);
        encoderDriveCommand.EncoderDriveOperate(7,"FORWARD");
        turnDriveCommand.operate(90);
        encoderDriveCommand.EncoderDriveOperate(7,"FORWARD");

        //shutdown
        mecanumDriveSubsystem.shutdown();
        cascadeLiftSubsystem.shutdown();
        rollerSubsystem.shutdown();
    }
}
