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

import static frc.robot.Constants.ClawConstants.*;

public class Claw extends SubsystemBase {

  private final PWMGroup flexGroup, rotateGroup;

  private final PIDController flexPID, rotatePID;
  private CANCoder flexEncoder, rotateEncoder;
  private double flexPoint, rotatePoint;

  private DoubleSolenoid clawSolenoid;

  private boolean doPID = false;

  public Claw() {
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

  public double getFlexAbsolutePosition() {
    return flexEncoder.getAbsolutePosition() + (flexEncoder.getAbsolutePosition() < 250 ? 360 : 0);
  }

  public boolean getDoPID() {
    return doPID;
  }

  public void setDoPID(boolean doPID) {
    this.doPID = doPID;
  }

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

  public void flexPID(double speed) {
    boolean withinBounds = getFlexAbsolutePosition() < 485 && getFlexAbsolutePosition() > 253;
    flexPoint += (withinBounds ? normalizeSpeed(speed) : 0);
  }

  public void rotatePID(double speed) {
    rotatePoint += normalizeSpeed(speed);
  }

  public void flexOpen(double speed) {
    flexGroup.setGroupSignal(normalizeSpeed(speed));
  }

  public void rotateOpen(double speed) {
    rotateGroup.setGroupSignal(normalizeSpeed(speed));
  }

  private double normalizeSpeed(double speed) {
    double magnitude = Math.abs(speed);
    if(magnitude <= 0.3) return 0;
    if(magnitude > 1) return speed / magnitude;
    return speed;
  }

  public void stop() {
    rotateOpen(0);
    flexOpen(0);
  }

  @Override
  public void periodic() {
    if(doPID) {
      flexGroup.setGroupSignal(flexPID.calculate(getFlexAbsolutePosition(), flexPoint));
    }
    
    SmartDashboard.putNumber("Flex point", getFlexPoint());
    SmartDashboard.putNumber("Rotate point", getRotatePoint());

    SmartDashboard.putNumber("Flex Encoder", getFlexAbsolutePosition());
    SmartDashboard.putNumber("Raw Flex Encoder", flexEncoder.getAbsolutePosition());
    SmartDashboard.putNumber("Rotate Encoder", rotateEncoder.getAbsolutePosition());

    SmartDashboard.putNumber("Flex kP", flexKP);
    SmartDashboard.getNumber("Flex kP", flexKP);

    SmartDashboard.putNumber("Flex kI", flexKI);
    SmartDashboard.getNumber("Flex kI", flexKI);

    SmartDashboard.putNumber("Flex kD", flexKD);
    SmartDashboard.getNumber("Flex kD", flexKD);
  }
}
