package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

//Subsystem Import
import frc.robot.subsystems.Elevator.IntakeRotation;

//Timer Import
import edu.wpi.first.wpilibj.Timer;

public class TimeRotateIntakeOut extends CommandBase {
    private final IntakeRotation intakeRotation;
    private final double speed;
    private final double time;
    private final Timer timer = new Timer();

    public TimeRotateIntakeOut(IntakeRotation intakeRotation, double speed, double time) {
        this.intakeRotation = intakeRotation;
        this.speed = speed * -1;
        this.time = time;
        addRequirements(intakeRotation);
    }

    @Override
    public void initialize() {
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        intakeRotation.rotate(speed);
    }

    @Override
    public boolean isFinished() {
        return timer.get() >= time;
    }

    @Override
    public void end(boolean interrupted) {
        intakeRotation.stopRotation();
        timer.stop();
    }
}
