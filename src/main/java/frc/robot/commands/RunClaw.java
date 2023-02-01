// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Claw;

public class RunClaw extends CommandBase {

  private final Claw claw;

  private final Joystick pJoy;

  public RunClaw(Claw _claw, Joystick _pJoy) {
    this.claw = _claw;
    this.pJoy = _pJoy;
    addRequirements(claw);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    double dx = pJoy.getRawAxis(Constants.OIConstants.kDriverXL);
    double dy = pJoy.getRawAxis(Constants.OIConstants.kDriverYL);

    if (claw.doPID()) {
      claw.flexPID(dy);
      claw.rotatePID(dx);
    } else {
      claw.rotateOpen(dx);
      claw.flexOpen(dy);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    claw.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}