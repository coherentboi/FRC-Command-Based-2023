package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

//Timer Import
import edu.wpi.first.wpilibj.Timer;

//Subsystem Import
import frc.robot.subsystems.Drivetrain;

public class TimeDrive extends CommandBase {
    private final Drivetrain drivetrain;
    private final double speed;
    private final double rotation;
    private final double time;
    private final Timer timer = new Timer();

    public TimeDrive(Drivetrain drivetrain, double speed, double rotation, double time) {
        this.drivetrain = drivetrain;
        this.speed = speed;
        this.rotation = rotation;
        this.time = time;
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        drivetrain.drive(speed, rotation);
    }

    @Override
    public boolean isFinished() {
        return timer.get() >= time;
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.stop();
        timer.stop();
    }
}
