// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Arm extends SubsystemBase {
  private final PWMSparkMax rotateA, rotateB, wristA, wristB;

  public Arm() {
    rotateA = new PWMSparkMax(Constants.ArmConstants.rotationA);
    rotateB = new PWMSparkMax(Constants.ArmConstants.rotationB);
    wristA = new PWMSparkMax(Constants.ArmConstants.wristA);
    wristB = new PWMSparkMax(Constants.ArmConstants.wristB);
  }
  @Override
  public void periodic() {
  }

}
