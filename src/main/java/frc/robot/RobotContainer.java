package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.commands.RunClaw;
import frc.robot.subsystems.Claw;

public class RobotContainer {

    private final Claw claw = new Claw();
    
    private final Joystick pJoy = new Joystick(Constants.OIConstants.pJoyID);

    public RobotContainer(Robot robot){
        configureButtonBindings();

    }

    public void configureButtonBindings(){

        claw.setDefaultCommand( 
           new RunClaw(claw, pJoy)  
        );
    }

    public void updateSmartDashboard(){
        
    }
}
