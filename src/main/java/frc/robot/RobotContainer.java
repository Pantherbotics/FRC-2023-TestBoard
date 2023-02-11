package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.RunClaw;
import frc.robot.subsystems.Claw;

public class RobotContainer {

    private final Claw claw = new Claw();
    
    private final Joystick pJoy = new Joystick(Constants.OIConstants.pJoyID);
    private final JoystickButton ButtonA = new JoystickButton(pJoy, 1);

    public RobotContainer(Robot robot){
        configureButtonBindings();

    }

    public void configureButtonBindings(){

        claw.setDefaultCommand( 
           new RunClaw(claw, pJoy)  
        );

        ButtonA.toggleOnTrue(
            new InstantCommand(() ->
                claw.setDoPID(!claw.getDoPID())
            )
        );
    }

    public void updateSmartDashboard(){
        
    }
}
