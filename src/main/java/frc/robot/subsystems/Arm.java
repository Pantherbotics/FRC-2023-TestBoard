// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.PWMGroup;

public class Arm extends SubsystemBase {
  private final PWMGroup rotationGroup, flexGroup;

  public Arm() {
    rotationGroup = new PWMGroup(4, 0);
    flexGroup = new PWMGroup(2,rotationGroup.totalMembers());
  }

  public void rotate(double speed) {
    speed = (Math.abs(speed) > 0.3 ? speed : 0);
    if (speed != 0 && Math.abs(speed) > 1) {
      speed /= Math.abs(speed);
    }
    rotationGroup.setGroupSignal(speed);
  }

  public void flex(double speed) {
    speed = (Math.abs(speed) > 0.3 ? speed : 0);
    if (speed != 0 && Math.abs(speed) > 1) {
      speed /= Math.abs(speed);
    }
    flexGroup.setGroupSignal(speed);
  }

  public void stop() {
    rotate(0);
    flex(0);
  }

  @Override
  public void periodic() {
  }

}
