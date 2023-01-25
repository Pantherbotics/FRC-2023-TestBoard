// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.ctre.phoenix.sensors.CANCoder;


import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.PWMGroup;

import static frc.robot.Constants.ArmConstants.*;

public class Arm extends SubsystemBase {

  private final PIDController flexPID, rotatePID;
  private final PWMGroup flexGroup, rotateGroup;

  private CANCoder flexEcoder, rotateEcoder;
  
  //imagine it had getters and setters
  public double flexPoint, rotatePoint;
  
  public Arm() {
    flexPID = new PIDController(flexKP, flexKI, flexKD);
    rotatePID = new PIDController(rotateKP, rotateKI, rotateKD);
    
    flexGroup      = new PWMGroup(flexFirstChannel, flexChannelCount);
    rotateGroup    = new PWMGroup(rotateFirstChannel,rotateChannelCount);
  }

  public void flex(double speed) {
    speed = (Math.abs(speed) > 0.3 ? speed : 0);
    if (speed != 0 && Math.abs(speed) > 1) {
      speed /= Math.abs(speed);
    }
    rotateGroup.setGroupSignal(speed);
  }

  public void rotate(double speed) {
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
    // not using flex and rotate because of deadzones
    flexGroup.setAll(flexPID.calculate(flexEcoder.getAbsolutePosition(), flexPoint));
    rotateGroup.setAll(rotatePID.calculate(rotateEcoder.getAbsolutePosition(), rotatePoint));
  }

}
