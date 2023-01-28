// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Arm;

public class RunArm extends CommandBase {

  private final Arm arm;

  private final Joystick pJoy;

  public RunArm(Arm _arm, Joystick _pJoy) {
    this.arm = _arm;
    this.pJoy = _pJoy;
    addRequirements(arm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    double dx = pJoy.getRawAxis(Constants.OIConstants.kDriverXL);
    double dy = pJoy.getRawAxis(Constants.OIConstants.kDriverYL);

    if (arm.isDoPID()) {
      arm.rotate(dx);
      arm.flex(dy);
    } else {
      //arm.rotateDebug(pJoy.getRawAxis(Constants.OIConstants.kDriverXL));
      arm.flex(pJoy.getRawAxis(Constants.OIConstants.kDriverYL));
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    arm.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}