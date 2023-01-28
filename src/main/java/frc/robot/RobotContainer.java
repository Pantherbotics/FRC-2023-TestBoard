package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.RunArm;
import frc.robot.subsystems.Arm;

public class RobotContainer {

    private final Arm arm = new Arm();
    
    private final Joystick pJoy = new Joystick(Constants.OIConstants.pJoyID);
    private final JoystickButton ButtonA = new JoystickButton(pJoy, 1);

    public RobotContainer(Robot robot){
        configureButtonBindings();

    }

    public void configureButtonBindings(){

        arm.setDefaultCommand( 
           new RunArm(arm, pJoy)  
        );

        ButtonA.toggleOnTrue(
            new InstantCommand(() ->
             arm.setDoPID(!arm.DoPID())
            )
        );
    }

    public void updateSmartDashboard(){
        
    }
}
