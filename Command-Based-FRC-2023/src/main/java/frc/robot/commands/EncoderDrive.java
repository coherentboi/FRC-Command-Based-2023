package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

//Subsystem Import
import frc.robot.subsystems.Drivetrain;

public class EncoderDrive extends CommandBase {
    private final Drivetrain drivetrain;
    private final double speed;
    private final double rotation;
    private final int targetCount;

    private static final double TOLERANCE = 10;

    public EncoderDrive(Drivetrain drivetrain, double speed, double rotation, int targetCount) {
        this.drivetrain = drivetrain;
        this.speed = speed;
        this.rotation = rotation;
        this.targetCount = targetCount;
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        drivetrain.resetEncoder();
    }

    @Override
    public void execute() {
        drivetrain.drive(speed, rotation);
    }

    @Override
    public boolean isFinished() {
        return Math.abs(drivetrain.getEncoderCount() - targetCount) <= TOLERANCE;
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.stop();
    }
}
