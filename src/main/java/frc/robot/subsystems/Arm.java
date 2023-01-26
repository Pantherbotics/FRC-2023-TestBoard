// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.ctre.phoenix.sensors.CANCoder;


import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.PWMGroup;

import static frc.robot.Constants.ArmConstants.*;

public class Arm extends SubsystemBase {

  private final PWMGroup flexGroup, rotateGroup;

  private final PIDController flexPID, rotatePID;
  private CANCoder flexEncoder, rotateEncoder;
  private double flexPoint, rotatePoint;

  DoubleSolenoid clawSolenoid;

  public double getFlexPoint() {
    return flexPoint;
  }

  public void setFlexPoint(double flexPoint) {
    this.flexPoint = flexPoint;
  }

  public double getRotatePoint() {
    return rotatePoint;
  }

  public void setRotatePoint(double rotatePoint) {
    this.rotatePoint = rotatePoint;
  }

  public Arm() {
    flexPID = new PIDController(flexKP, flexKI, flexKD);
    rotatePID = new PIDController(rotateKP, rotateKI, rotateKD);
    
    flexGroup      = new PWMGroup(flexFirstChannel, flexChannelCount);
    rotateGroup    = new PWMGroup(rotateFirstChannel,rotateChannelCount);

    flexEncoder = new CANCoder(flexEncoderCAN);
    rotateEncoder = new CANCoder(rotateEncoderCAN);

    clawSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, pcmCANa, pcmCANb);
    clawSolenoid.set(DoubleSolenoid.Value.kForward);


  }

  public void flex(double speed) {
    speed = (Math.abs(speed) > 0.3 ? speed : 0);
    if (speed != 0 && Math.abs(speed) > 1) {
      speed /= Math.abs(speed);
    }

    flexPoint += speed;
    
  }

  public void toggle() {
    clawSolenoid.toggle();
  }

  public void rotate(double speed) {
    speed = (Math.abs(speed) > 0.3 ? speed : 0);
    
    if (speed != 0 && Math.abs(speed) > 1) {
      speed /= Math.abs(speed);
    }

    rotatePoint += speed;

  }

  public void stop() {
    rotate(0);
    flex(0);
  }



  @Override
  public void periodic() {
    // not using flex and rotate because of deadzones
    flexGroup.setAll(flexPID.calculate(flexEncoder.getAbsolutePosition(), flexPoint));
    rotateGroup.setAll(rotatePID.calculate(rotateEncoder.getAbsolutePosition(), rotatePoint));
  }

}
