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
import frc.robot.utils.PIDgroup;
import frc.robot.utils.PWMGroup;

import static frc.robot.Constants.ArmConstants.*;

public class Arm extends SubsystemBase {

  private final PIDgroup flexGroup, rotateGroup;
  private CANCoder flexEncoder, rotateEncoder;

  DoubleSolenoid clawSolenoid;

  private boolean doPID = false;

  public Arm() {
    flexEncoder = new CANCoder(flexEncoderCAN);
    rotateEncoder = new CANCoder(rotateEncoderCAN);

    flexGroup = new PIDgroup(
        new PWMGroup(flexFirstChannel, flexChannelCount),
        new PIDController(flexKP, flexKI, flexKD),
        flexEncoder::getAbsolutePosition);

    rotateGroup = new PIDgroup(
        new PWMGroup(rotateFirstChannel, rotateChannelCount),
        new PIDController(rotateKP, rotateKI, rotateKD),
        rotateEncoder::getAbsolutePosition);

    clawSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, pcmCANa, pcmCANb);
    clawSolenoid.set(DoubleSolenoid.Value.kForward);
  }

  public void flex(double speed) {
    speed = (Math.abs(speed) > 0.3 ? speed : 0);
    if (speed != 0 && Math.abs(speed) > 1) {
      speed /= Math.abs(speed);
    }

    flexGroup.setPoint += speed;

  }

  public void rotate(double speed) {
    speed = (Math.abs(speed) > 0.3 ? speed : 0);

    if (speed != 0 && Math.abs(speed) > 1) {
      speed /= Math.abs(speed);
    }

    flexGroup.setPoint += speed;

  }

  public void flexDebug(double speed) {
    speed = (Math.abs(speed) > 0.3 ? speed : 0);
    if (speed != 0 && Math.abs(speed) > 1) {
      speed /= Math.abs(speed);
    }

    flexGroup.getMotors().setGroupSignal(speed);

  }

  public void rotateDebug(double speed) {
    speed = (Math.abs(speed) > 0.3 ? speed : 0);

    if (speed != 0 && Math.abs(speed) > 1) {
      speed /= Math.abs(speed);
    }

    rotateGroup.getMotors().setGroupSignal(speed);

  }

  public void stop() {
    rotate(0);
    flex(0);
  }

  @Override
  public void periodic() {
    if (doPID) {
      // not using flex and rotate because of deadzones
      flexGroup.process();
      rotateGroup.process();

      SmartDashboard.putNumber("Flex point", flexGroup.setPoint);
      SmartDashboard.putNumber("Rotate point", rotateGroup.setPoint);

    }

    SmartDashboard.putNumber("Flex Encoder", flexEncoder.getAbsolutePosition());
    SmartDashboard.putNumber("Rotate Encoder", rotateEncoder.getAbsolutePosition());
  }

  public boolean isDoPID() {
    return doPID;
  }

  public void setDoPID(boolean doPID) {
    this.doPID = doPID;
  }
}
