// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.Elevator;

//System Imports
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class IntakeRotation extends SubsystemBase {
  
  //Port Initializations
  private final int INTAKE_ROTATION_PORT = 13;

  //Motor Initializations
  private final WPI_VictorSPX INTAKE_ROTATION = new WPI_VictorSPX(INTAKE_ROTATION_PORT);

  public IntakeRotation() {
  }

  public void rotate(double speed){
    INTAKE_ROTATION.set(speed);
  }

  public void stopRotation(){
    INTAKE_ROTATION.set(0);
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
