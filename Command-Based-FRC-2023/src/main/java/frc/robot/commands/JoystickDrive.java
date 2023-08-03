package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.subsystems.Drivetrain;

public class JoystickDrive extends CommandBase {
  private final Drivetrain drivetrain;
  private final Joystick joystick;
  private final int DRIVE_PORT;
  private final int TURN_PORT;
  private final double TURN_CONSTANT = 0.7;

  public JoystickDrive(Drivetrain drivetrain, Joystick joystick, int DRIVE_PORT, int TURN_PORT) {
    this.drivetrain = drivetrain;
    this.joystick = joystick;
    this.DRIVE_PORT = DRIVE_PORT;
    this.TURN_PORT = TURN_PORT;

    addRequirements(drivetrain);
  }

  @Override
  public void execute() {
    drivetrain.drive(joystick.getRawAxis(DRIVE_PORT) , -joystick.getRawAxis(TURN_PORT) * TURN_CONSTANT );
  }
}
