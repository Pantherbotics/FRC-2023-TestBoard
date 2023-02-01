// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.PIDgroup;
import frc.robot.utils.PWMGroup;

import frc.robot.Constants.ArmConstants;

public class Arm extends SubsystemBase {
  private CANSparkMax rotateMotorA, rotateMotorB;
  private TalonFX telescopingMotor;

  public Arm() {
    rotateMotorA = new CANSparkMax(1, MotorType.kBrushless);
    rotateMotorB = new CANSparkMax(2, MotorType.kBrushless);

    telescopingMotor = new TalonFX(1);
  }
}
