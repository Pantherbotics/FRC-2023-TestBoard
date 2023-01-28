// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.sensors.CANCoder;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.PWMGroup;

import static frc.robot.Constants.ArmConstants.*;

public class Arm extends SubsystemBase {

  private final PWMGroup flexGroup, rotateGroup;

  private final PIDController flexPID, rotatePID;
  private CANCoder flexEncoder, rotateEncoder;
  private double flexPoint, rotatePoint;

  DoubleSolenoid clawSolenoid;

  private boolean doPID = false;

  public double getFlexPoint() {
    return flexPoint;
  }

  public boolean DoPID() {
    return doPID;
  }

  public double getFlexAbsolutePosition(){
    return flexEncoder.getAbsolutePosition() + (flexEncoder.getAbsolutePosition() < 250 ? 360 : 0);
  }

  public boolean setDoPID(boolean doPID) {
    this.doPID = doPID;
    if (DoPID()) {
    }
    return doPID;
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

    flexGroup = new PWMGroup(flexChannelCount, flexFirstChannel);
    rotateGroup = new PWMGroup(rotateChannelCount, rotateFirstChannel);

    flexEncoder = new CANCoder(flexEncoderCAN);
    rotateEncoder = new CANCoder(rotateEncoderCAN);

    flexPoint = flexEncoder.getAbsolutePosition();

    clawSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, pcmCANa, pcmCANb);
    clawSolenoid.set(DoubleSolenoid.Value.kForward);
  }

  public void flexPID(double speed) {
    speed = (Math.abs(speed) > 0.3 ? speed : 0);
    if (speed != 0 && Math.abs(speed) > 1) {
      speed /= Math.abs(speed);
    }

    flexPoint += (getFlexAbsolutePosition() < 456 ? speed : 0);

  }

  public void rotatePID(double speed) {
    speed = (Math.abs(speed) > 0.3 ? speed : 0);

    if (speed != 0 && Math.abs(speed) > 1) {
      speed /= Math.abs(speed);
    }

    rotatePoint += speed;
  }

  public void flexOpen(double speed) {
    speed = (Math.abs(speed) > 0.3 ? speed : 0);
    if (speed != 0 && Math.abs(speed) > 1) {
      speed /= Math.abs(speed);
    }

    flexPoint = getFlexAbsolutePosition();
    flexGroup.setGroupSignal(speed);
  }

  public void rotateOpen(double speed) {
    speed = (Math.abs(speed) > 0.3 ? speed : 0);

    if (speed != 0 && Math.abs(speed) > 1) {
      speed /= Math.abs(speed);
    }
    rotateGroup.setGroupSignal(speed);

  }

  public void stop() {
    rotateOpen(0);
    flexOpen(0);
  }

  @Override
  public void periodic() {
    if (doPID) {
        flexGroup.setGroupSignal(flexPID.calculate(getFlexAbsolutePosition(), flexPoint));
    }
    SmartDashboard.putNumber("Flex point", getFlexPoint());
    SmartDashboard.putNumber("Rotate point", getRotatePoint());

    SmartDashboard.putNumber("Flex Encoder", getFlexAbsolutePosition());
    SmartDashboard.putNumber("Raw Flex Encoder", flexEncoder.getAbsolutePosition());
    SmartDashboard.putNumber("Rotate Encoder",
        rotateEncoder.getAbsolutePosition());
  }
}
