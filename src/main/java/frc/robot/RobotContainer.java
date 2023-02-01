package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.RunClaw;
import frc.robot.subsystems.Claw;

public class RobotContainer {

    private final Claw arm = new Claw();
    
    private final Joystick pJoy = new Joystick(Constants.OIConstants.pJoyID);
    private final JoystickButton ButtonA = new JoystickButton(pJoy, 1);

    public RobotContainer(Robot robot){
        configureButtonBindings();

    }

    public void configureButtonBindings(){

        arm.setDefaultCommand( 
           new RunClaw(arm, pJoy)  
        );

        ButtonA.toggleOnTrue(
            new InstantCommand(() ->
             arm.setDoPID(!arm.doPID())
            )
        );
    }

    public void updateSmartDashboard(){
        
    }
}
