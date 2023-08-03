package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Drivetrain;

public class ToggleFastDrive extends InstantCommand {
    
  private final Drivetrain drivetrain;

  public ToggleFastDrive(Drivetrain drivetrain) {
    this.drivetrain = drivetrain;
    addRequirements(drivetrain);
  }

  @Override
  public void initialize() {
    drivetrain.toggleFastDrive();
  }
}
