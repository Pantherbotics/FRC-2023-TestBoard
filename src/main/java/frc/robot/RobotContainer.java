package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.commands.RunArm;
import frc.robot.subsystems.Arm;

public class RobotContainer {

    private final Arm arm = new Arm();
    
    private final Joystick pJoy = new Joystick(Constants.OIConstants.pJoyID);

    public RobotContainer(Robot robot){
        arm.setDefaultCommand(new RunArm(arm, pJoy));
        configureButtonBindings();
    }

    public void configureButtonBindings(){
        
    }

    public void updateSmartDashboard(){
        
    }
}
