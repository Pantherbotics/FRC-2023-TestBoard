package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.subsystems.Arm;

public class RobotContainer {

    private final Arm arm = new Arm();
    
    private final Joystick pJoy = new Joystick(Constants.OIConstants.pJoyID);
    private final JoystickButton joyBA = new JoystickButton(pJoy, 1); //Button A
    private final JoystickButton joyBB = new JoystickButton(pJoy, 2); //Button B
    private final JoystickButton joyBX = new JoystickButton(pJoy, 3); //Button X
    private final JoystickButton joyBY = new JoystickButton(pJoy, 4); //Button Y

    public RobotContainer(Robot robot){
        configureButtonBindings();
    }

    public void configureButtonBindings(){
        
    }

    public void updateSmartDashboard(){
        
    }
}
