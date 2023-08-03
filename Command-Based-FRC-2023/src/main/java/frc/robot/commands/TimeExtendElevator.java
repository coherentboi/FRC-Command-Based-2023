package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

//Timer Import
import edu.wpi.first.wpilibj.Timer;

//Subsystem Import
import frc.robot.subsystems.Elevator.Elevator;

public class TimeExtendElevator extends CommandBase {
    private final Elevator elevator;
    private final double speed;
    private final double time;
    private final Timer timer = new Timer();

    public TimeExtendElevator(Elevator elevator, double speed, double time) {
        this.elevator = elevator;
        this.speed = -1 * speed;
        this.time = time;
        addRequirements(elevator);
    }

    @Override
    public void initialize() {
        elevator.stopElevator();
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        elevator.extend(speed);
    }

    @Override
    public boolean isFinished() {
        return elevator.atBottom() || timer.get() >= time;
    }

    @Override
    public void end(boolean interrupted) {
        elevator.stopElevator();
    }
}
