// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.Elevator;

//System Imports
import edu.wpi.first.wpilibj2.command.SubsystemBase;

//Motor Imports
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

//Time Import
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.DigitalInput;

public class Elevator extends SubsystemBase {
  
  //Port Initializations
  private final int LEFT_ELEVATOR_PORT = 1;
  private final int RIGHT_ELEVATOR_PORT = 2;
  private final int TOP_LIMIT_SWITCH_CHANNEL = 0;

  //Motor Initializations
  private final CANSparkMax LEFT_ELEVATOR = new CANSparkMax(LEFT_ELEVATOR_PORT, MotorType.kBrushless);
  private final CANSparkMax RIGHT_ELEVATOR = new CANSparkMax(RIGHT_ELEVATOR_PORT, MotorType.kBrushless);

  //Motor Controller + Drive Initializations
  private final MotorControllerGroup ELEVATOR = new MotorControllerGroup(LEFT_ELEVATOR, RIGHT_ELEVATOR);

  //Sensor Initializations
  private final RelativeEncoder ELEVATOR_ENCODER = RIGHT_ELEVATOR.getEncoder();
  private final DigitalInput TOP_LIMIT_SWITCH = new DigitalInput(TOP_LIMIT_SWITCH_CHANNEL);

  public Elevator() {
    //Invert Right
    RIGHT_ELEVATOR.setInverted(true);
    ELEVATOR_ENCODER.setPosition(0);
  }

  public void extend(double speed){
    ELEVATOR.set(-1 * speed);
  }

  public boolean atTop(){
    ELEVATOR_ENCODER.setPosition(-219);
    return TOP_LIMIT_SWITCH.get();
  }

  public boolean atBottom(){
    return ELEVATOR_ENCODER.getPosition() == 0;
  }

  public void stopElevator(){
    ELEVATOR.set(0);
  }

  public double getHeight(){
    return ELEVATOR_ENCODER.getPosition();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
