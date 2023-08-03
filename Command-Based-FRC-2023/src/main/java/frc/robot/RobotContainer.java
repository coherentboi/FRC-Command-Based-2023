// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

//Command Based Import
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.Command;

//Dashboard Imports
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Dashboard;

//Subsystem Import
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Elevator.Elevator;
import frc.robot.subsystems.Elevator.Intake;
import frc.robot.subsystems.Elevator.IntakeRotation;

//Command Import
import frc.robot.commands.EncoderDrive;
import frc.robot.commands.JoystickDrive;
import frc.robot.commands.TimeExtendElevator;
import frc.robot.commands.TimeRetractElevator;
import frc.robot.commands.TimeDrive;
import frc.robot.commands.TimeIntakeIn;
import frc.robot.commands.TimeIntakeOut;
import frc.robot.commands.TimeRotateIntakeIn;
import frc.robot.commands.TimeRotateIntakeOut;
import frc.robot.commands.ToggleFastDrive;
import frc.robot.commands.ToggleSlowDrive;

public class RobotContainer {

  //Constants
  private final double UNTILRELEASED = 10000;
  private final double UNTILEND = 100;

  //Subsystems
  private final Drivetrain drivetrain = new Drivetrain();
  private final Elevator elevator = new Elevator();
  private final IntakeRotation intakeRotation = new IntakeRotation();
  private final Intake intake = new Intake();

  //Controller
  private final Joystick driveStick = new Joystick(0);
  private final Joystick elevatorStick = new Joystick(1);

  //Dashboard
  private final Dashboard dashboard = new Dashboard();

  //Driver Button Mappings
  private final JoystickButton btnSlowDrive = new JoystickButton(driveStick, 1);
  private final JoystickButton btnFastDrive = new JoystickButton(driveStick, 2);

  //Driver Command Mappings
  private final JoystickDrive cmdJoystickDrive = new JoystickDrive(drivetrain, driveStick, 1, 4);
  private final ToggleSlowDrive cmdSlowDrive = new ToggleSlowDrive(drivetrain);
  private final ToggleFastDrive cmdFastDrive = new ToggleFastDrive(drivetrain);

  //Operator Button Mappings
  private final JoystickButton btnExtendArm = new JoystickButton(elevatorStick, 4);
  private final JoystickButton btnRetractArm = new JoystickButton(elevatorStick, 2);
  private final JoystickButton btnIntakeRotateOut = new JoystickButton(elevatorStick, 7);
  private final JoystickButton btnIntakeRotateIn = new JoystickButton(elevatorStick, 5);
  private final JoystickButton btnIntake = new JoystickButton(elevatorStick, 6);
  private final JoystickButton btnOuttake = new JoystickButton(elevatorStick, 8);

  //Operator Command Mappings
  private final TimeExtendElevator cmdExtendArm = new TimeExtendElevator(elevator, 1.2/2, UNTILRELEASED);
  private final TimeRetractElevator cmdRetractArm = new TimeRetractElevator(elevator, 1.2/2, UNTILRELEASED);
  private final TimeRotateIntakeOut cmdRotateOut = new TimeRotateIntakeOut(intakeRotation, 0.55, UNTILRELEASED);
  private final TimeRotateIntakeIn cmdRotateIn = new TimeRotateIntakeIn(intakeRotation, 0.55, UNTILRELEASED);
  private final TimeIntakeIn cmdIntakeIn = new TimeIntakeIn(intake, 0.4, UNTILRELEASED);
  private final TimeIntakeOut cmdIntakeOut = new TimeIntakeOut(intake, 0.5, UNTILRELEASED);

  //Auto Chooser
  private final SendableChooser<Command> autoChooser = new SendableChooser<>();

  //Auto Commands
  SequentialCommandGroup cubeEngage = new SequentialCommandGroup(
    new TimeRotateIntakeOut(intakeRotation, 0.5, 0.3),
    new TimeExtendElevator(elevator, 1.0/2, UNTILEND),
    new TimeRetractElevator(elevator, 0.3/2, 0.1),
    new TimeRotateIntakeIn(intakeRotation, 0.9, 0.7),
    new TimeIntakeOut(intake, 0.9, 0.5),
    new TimeRotateIntakeIn(intakeRotation, 0.6, 0.7),
    new TimeRetractElevator(elevator, 0.8/2, 1.5),
    new TimeDrive(drivetrain, 0.60, 0.0, 3.5),
    new EncoderDrive(drivetrain, 0.55, 0.0, -1240) //Have to adjust encoder drive values as encoder is reset every call
  );
  
  SequentialCommandGroup cubeMobility = new SequentialCommandGroup(
    new TimeRotateIntakeOut(intakeRotation, 0.5, 0.3),
    new TimeExtendElevator(elevator, 0.8/2, UNTILEND),
    new TimeRetractElevator(elevator, 0.3/2, 0.1),
    new TimeRotateIntakeIn(intakeRotation, 0.7, 2),
    new TimeIntakeOut(intake, 0.9, 0.5),
    new TimeRotateIntakeIn(intakeRotation, 0.6, 0.7),
    new TimeRetractElevator(elevator, 0.3/2, 3.5),
    new TimeDrive(drivetrain, 0.60, 0.0, 3.5)
  );

  SequentialCommandGroup coneMobility = new SequentialCommandGroup(
    new TimeRotateIntakeOut(intakeRotation, 0.5, 0.2),
    new TimeExtendElevator(elevator, 0.8, UNTILEND),
    new TimeRetractElevator(elevator, 0.3, 0.1),
    new TimeDrive(drivetrain, -0.45, 0.0, 1.5),
    new TimeIntakeOut(intake, 0.5, 1),
    new TimeRotateIntakeIn(intakeRotation, 0.6, 0.5),
    new TimeRetractElevator(elevator, 0.7, 1.4),
    new TimeDrive(drivetrain, 0.6, 0.0, 3.0),
    new EncoderDrive(drivetrain, 0.55, 0.0, 2800), //Have to adjust encoder drive values as it gets reset every call
    new EncoderDrive(drivetrain, 0.0, 0.4, 430) //Have to adjust encoder drive values as it gets reset every call
  );

  public RobotContainer() {
    //Configure Auto Options
    autoChooser.setDefaultOption("Cube + Engage", cubeEngage);
    autoChooser.addOption("Cube + Mobility", cubeMobility);
    autoChooser.addOption("Cone + Mobility", coneMobility);

    //Configure Button Nindings
    configureButtonBindings();
  }

  private void configureButtonBindings() {

    //Driver Button + Command Mappings
    btnSlowDrive.whenPressed(cmdSlowDrive);
    btnFastDrive.whenPressed(cmdFastDrive);

    //Operator Button + Command Mappings
    btnExtendArm.whileHeld(cmdExtendArm);
    btnRetractArm.whileHeld(cmdRetractArm);
    btnIntakeRotateIn.whileHeld(cmdRotateIn);
    btnIntakeRotateOut.whileHeld(cmdRotateOut);
    btnIntake.whileHeld(cmdIntakeIn);
    btnOuttake.whileHeld(cmdIntakeOut);
  }

  public Command getAutonomousCommand() {
    //Run Auto
    return autoChooser.getSelected();
  }

  public Command getTeleopCommand(){
    //Run TeleOp
    return cmdJoystickDrive;
  }
}
