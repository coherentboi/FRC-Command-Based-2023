// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.Elevator;

//System Imports
import edu.wpi.first.wpilibj2.command.SubsystemBase;

//Motor Imports
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Intake extends SubsystemBase {
  
  //Port Initializations
  private final int INTAKE_PORT = 3;

  //Motor Initializations
  private final CANSparkMax INTAKE = new CANSparkMax(INTAKE_PORT, MotorType.kBrushless);

  public Intake() {
  }

  public void intake(double speed){
    INTAKE.set(speed);
  }

  public void stopIntake(){
    INTAKE.set(0);
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
