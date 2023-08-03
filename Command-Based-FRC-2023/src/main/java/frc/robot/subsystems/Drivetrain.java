// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

//System Imports
import edu.wpi.first.wpilibj2.command.SubsystemBase;

//Motor Imports
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

//Sensor Imports
import edu.wpi.first.wpilibj.Encoder;

public class Drivetrain extends SubsystemBase {

  //Port Initializations
  private final int FRONT_LEFT_PORT = 4;
  private final int FRONT_RIGHT_PORT = 6;
  private final int BACK_LEFT_PORT = 12;
  private final int BACK_RIGHT_PORT = 5;
  private final int DRIVE_ENCODER_PORT_1 = 1;
  private final int DRIVE_ENCODER_PORT_2 = 2;

  //Motor Initializations
  private final WPI_VictorSPX frontLeft = new WPI_VictorSPX(FRONT_LEFT_PORT);
  private final WPI_VictorSPX frontRight = new WPI_VictorSPX(FRONT_RIGHT_PORT);
  private final WPI_VictorSPX backRight = new WPI_VictorSPX(BACK_RIGHT_PORT);
  private final WPI_VictorSPX backLeft = new WPI_VictorSPX(BACK_LEFT_PORT);

  //Motor Controller + Drive Initializations
  private final MotorControllerGroup LEFT_DRIVE = new MotorControllerGroup(frontLeft, frontRight);
  private final MotorControllerGroup RIGHT_DRIVE = new MotorControllerGroup(backLeft, backRight);
  private final DifferentialDrive ROBOT_DRIVE = new DifferentialDrive(LEFT_DRIVE, RIGHT_DRIVE);

  //Sensor Initializations
  private final Encoder DRIVE_ENCODER = new Encoder(DRIVE_ENCODER_PORT_1, DRIVE_ENCODER_PORT_2);

  //Slow Drive Initialization
  private boolean isSlowDrive = false;
  private final double SLOW_SPEED = 0.5;
  private final double SLOW_TURN = 0.8;

  public Drivetrain() {
    //Invert Right Side
    RIGHT_DRIVE.setInverted(true);
  }

  public void drive(double speed, double rotation) {
      if(isSlowDrive){
        ROBOT_DRIVE.arcadeDrive(speed * SLOW_SPEED, rotation * SLOW_TURN);
      }
      else{
        ROBOT_DRIVE.arcadeDrive(speed, rotation);
      }
      
  }

  public void stop() {
      ROBOT_DRIVE.arcadeDrive(0, 0);
  }

  public int getEncoderCount() {
      return DRIVE_ENCODER.get();
  }

  public void resetEncoder() {
      DRIVE_ENCODER.reset();
  }

  public void toggleFastDrive(){
    isSlowDrive = false;
  }

  public void toggleSlowDrive(){
    isSlowDrive = true;
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
