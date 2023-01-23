// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Arm extends SubsystemBase {
  private final PWMSparkMax flexChannelA, flexChannelB, flexChannelC, flexChannelD;
  private final PWMSparkMax rotateChannelA, rotateChannelB;

  public Arm() {
    flexChannelA = new PWMSparkMax(Constants.ArmConstants.flexChannelA);
    flexChannelB = new PWMSparkMax(Constants.ArmConstants.flexChannelB);
    flexChannelC = new PWMSparkMax(Constants.ArmConstants.flexChannelC);
    flexChannelD = new PWMSparkMax(Constants.ArmConstants.flexChannelD);
    rotateChannelA = new PWMSparkMax(Constants.ArmConstants.rotateChannelA);
    rotateChannelB = new PWMSparkMax(Constants.ArmConstants.rotateChannelB);
  }

  public void flex(double speed) {
    speed = (Math.abs(speed) > 0.3 ? speed : 0);
    if (speed != 0 && Math.abs(speed) > 1) {
      speed /= Math.abs(speed);
    }
    flexChannelA.set(speed);
    flexChannelB.set(speed);
    flexChannelC.set(speed);
    flexChannelD.set(speed);
  }

  public void rotate(double speed) {
    speed = (Math.abs(speed) > 0.3 ? speed : 0);
    if (speed != 0 && Math.abs(speed) > 1) {
      speed /= Math.abs(speed);
    }
    rotateChannelA.set(speed);
    rotateChannelB.set(speed);
  }

  public void stop() {
    rotate(0);
    flex(0);
  }

  @Override
  public void periodic() {
  }

}
