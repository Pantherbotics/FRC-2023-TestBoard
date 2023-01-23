package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

public class RobotContainer {

    private final Joystick pJoy = new Joystick(Constants.OIConstants.pJoyID);

    public RobotContainer(Robot robot){
        configureButtonBindings();
    }

    public void configureButtonBindings(){
        
    }

    public void updateSmartDashboard(){
        
    }
}
