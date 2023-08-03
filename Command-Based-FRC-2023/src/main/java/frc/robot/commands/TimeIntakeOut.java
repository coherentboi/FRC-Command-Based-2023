package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

//Subsystem Import
import frc.robot.subsystems.Elevator.Intake;

//Timer Import
import edu.wpi.first.wpilibj.Timer;

public class TimeIntakeOut extends CommandBase {
    private final Intake intake;
    private final double speed;
    private final double time;
    private final Timer timer = new Timer();

    public TimeIntakeOut(Intake intake, double speed, double time) {
        this.intake = intake;
        this.speed = -1 * speed;
        this.time = time;
        addRequirements(intake);
    }

    @Override
    public void initialize() {
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        intake.intake(speed);
    }

    @Override
    public boolean isFinished() {
        return timer.get() >= time;
    }

    @Override
    public void end(boolean interrupted) {
        intake.stopIntake();
        timer.stop();
    }
}
