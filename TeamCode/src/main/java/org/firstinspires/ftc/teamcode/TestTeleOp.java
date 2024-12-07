package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.opMode;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.commands.TeleOp.ClawCommand;
import org.firstinspires.ftc.teamcode.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.RollerSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.CascadeLiftSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.commands.TeleOp.MeasurementSpecificLiftCommands;
import org.firstinspires.ftc.teamcode.commands.TeleOp.CascadeLiftCommand;
import org.firstinspires.ftc.teamcode.commands.TeleOp.RollerOperateCommand;
import org.firstinspires.ftc.teamcode.commands.autonomous.AutoLiftCommand;
import org.firstinspires.ftc.teamcode.commands.TeleOp.HorizontalLiftCommand;

@TeleOp(name="TestTeleOp")

public class TestTeleOp extends OpMode {
    //declaring subsystems and commands
    private RollerSubsystem rollerSubsystem;
    private CascadeLiftSubsystem cascadeLiftSubsystem;
    private MecanumDriveSubsystem mecanumDriveSubsystem;
    private MeasurementSpecificLiftCommands measurementSpecificLiftCommands;
    private CascadeLiftCommand cascadeLiftCommand;
    private RollerOperateCommand rollerOperateCommand;
    private AutoLiftCommand autoLiftCommand;
    private ClawSubsystem clawSubsystem;
    private ClawCommand clawCommand;
    private HorizontalLiftCommand horizontalLiftCommand;

    //When you initialize:
    public void init () {
        rollerSubsystem = new RollerSubsystem(this, telemetry);
        cascadeLiftSubsystem = new CascadeLiftSubsystem(this, telemetry);
        mecanumDriveSubsystem = new MecanumDriveSubsystem(this);
        clawSubsystem = new ClawSubsystem(this);

        cascadeLiftCommand = new CascadeLiftCommand(gamepad2, cascadeLiftSubsystem, telemetry);
        //measurementSpecificLiftCommands = new MeasurementSpecificLiftCommands(gamepad1, cascadeLiftSubsystem, telemetry, autoLiftCommand);
        rollerOperateCommand = new RollerOperateCommand(gamepad1, rollerSubsystem, telemetry);
        clawCommand = new ClawCommand(clawSubsystem);
        horizontalLiftCommand = new HorizontalLiftCommand(gamepad1, cascadeLiftSubsystem,telemetry);
        autoLiftCommand = new AutoLiftCommand(cascadeLiftSubsystem, telemetry);
    }

    @Override
    public void loop(){
        mecanumDriveSubsystem.operate(gamepad2, telemetry);
        cascadeLiftCommand.operate(gamepad2);
        //measurementSpecificLiftCommands.operate(gamepad1);
        clawCommand.operate(gamepad2);
        rollerOperateCommand.operate();
        horizontalLiftCommand.operate(gamepad1);
        /*
        * Test code for using auto lift command in teleop.
        * */
        if (gamepad2.y) {
            autoLiftCommand.operate(1600, "UP");
        }
        if (gamepad2.b) {
            autoLiftCommand.operate(-1000, "DOWN");
        }
        telemetry.update();
    }
    public void stop(){
        mecanumDriveSubsystem.shutdown();
        cascadeLiftSubsystem.shutdown();
        rollerSubsystem.shutdown();

        cascadeLiftCommand.shutdown();
        measurementSpecificLiftCommands.shutdown();
        rollerOperateCommand.shutdown();
    }
}
