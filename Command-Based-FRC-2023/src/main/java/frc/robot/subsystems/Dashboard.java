// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

//System Imports
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;

//Sensor Imports
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.SPI;

//NetworkTable Imports
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;


public class Dashboard extends SubsystemBase {

  //Port Initializations
  private final int DRIVE_ENCODER_PORT_1 = 1;
  private final int DRIVE_ENCODER_PORT_2 = 2;
  private final int LEFT_ELEVATOR_PORT = 1;
  private final int RIGHT_ELEVATOR_PORT = 2;
  private final int TOP_LIMIT_SWITCH_CHANNEL = 0;

  //Table Initializations
  private NetworkTable TABLE;
  private NetworkTableEntry TX;
  private NetworkTableEntry TY;
  private NetworkTableEntry TA;

  //Variable Initializations
  private double x = 0.0;
  private double y = 0.0;
  private double area = 0.0;

  //Motor Initialzations
  private final CANSparkMax RIGHT_ELEVATOR = new CANSparkMax(RIGHT_ELEVATOR_PORT, MotorType.kBrushless);

  //Sensor Initializations
  private final AHRS gyro = new AHRS(SPI.Port.kMXP);
  private final Encoder DRIVE_ENCODER = new Encoder(DRIVE_ENCODER_PORT_1, DRIVE_ENCODER_PORT_2);
  private final RelativeEncoder ELEVATOR_ENCODER = RIGHT_ELEVATOR.getEncoder();
  private final DigitalInput TOP_LIMIT_SWITCH = new DigitalInput(TOP_LIMIT_SWITCH_CHANNEL);

  public Dashboard() {
    TABLE = NetworkTableInstance.getDefault().getTable("limelight");
    TX = TABLE.getEntry("tx");
    TY = TABLE.getEntry("ty");
    TA = TABLE.getEntry("ta");
  }

  public double getX(){
    return x;
  }

  public double getY(){
    return y;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    x = TX.getDouble(0.0);
    y = TY.getDouble(0.0);
    area = TA.getDouble(0.0);

    SmartDashboard.putNumber("LimelightX", x);
    SmartDashboard.putNumber("LimelightY", y);
    SmartDashboard.putNumber("LimelightArea", area);
    SmartDashboard.putNumber("Roll", gyro.getRoll());
    SmartDashboard.putNumber("Pitch", gyro.getPitch());
    SmartDashboard.putNumber("Yaw", gyro.getYaw());

    SmartDashboard.putNumber("Elevator Encoder", ELEVATOR_ENCODER.getPosition());
    SmartDashboard.putBoolean("Limit Switch", TOP_LIMIT_SWITCH.get());
    SmartDashboard.putNumber("Drive Encoder", DRIVE_ENCODER.getDistance());
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
