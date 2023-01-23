// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Arm extends SubsystemBase {
  private final PWMSparkMax rotateChannelA, rotateChannelB, rotateChannelC, rotateChannelD, rotateChannelE, rotateChannelF;
  private final PWMSparkMax flexChannelA, flexChannelB;

  public Arm() {
    rotateChannelA = new PWMSparkMax(Constants.ArmConstants.rotationChannelA);
    rotateChannelB = new PWMSparkMax(Constants.ArmConstants.rotationChannelB);
    rotateChannelC = new PWMSparkMax(Constants.ArmConstants.rotationChannelC);
    rotateChannelD = new PWMSparkMax(Constants.ArmConstants.rotationChannelD);
    rotateChannelE = new PWMSparkMax(Constants.ArmConstants.rotationChannelE);
    rotateChannelF = new PWMSparkMax(Constants.ArmConstants.rotationChannelF);
    flexChannelA = new PWMSparkMax(Constants.ArmConstants.flexChannelA);
    flexChannelB = new PWMSparkMax(Constants.ArmConstants.flexChannelB);
  }

  public void rotate(double speed) {
    speed = (Math.abs(speed) > 0.3 ? speed : 0);
    if (speed != 0 && Math.abs(speed) > 1) {
      speed /= Math.abs(speed);
    }
    rotateChannelA.set(speed);
    rotateChannelB.set(speed);
    rotateChannelC.set(speed);
    rotateChannelD.set(speed);
    rotateChannelE.set(speed);
    rotateChannelF.set(speed);
  }

  public void flex(double speed) {
    speed = (Math.abs(speed) > 0.3 ? speed : 0);
    if (speed != 0 && Math.abs(speed) > 1) {
      speed /= Math.abs(speed);
    }
    flexChannelA.set(speed);
    flexChannelB.set(speed);
  }

  public void stop() {
    rotate(0);
    flex(0);
  }

  @Override
  public void periodic() {
  }

}
