package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Claw;
public class RobotContainer {

    private final Claw claw = new Claw();
    
    private final Joystick pJoy = new Joystick(Constants.OIConstants.pJoyID);
    private final JoystickButton ButtonA = new JoystickButton(pJoy, 1);

    public RobotContainer(Robot robot) {
        configureButtonBindings();

    }

    public void configureButtonBindings() {
       
        ButtonA.toggleOnTrue(
            new InstantCommand(
                () -> claw.setClaw(!claw.getClawOpen())
            )
        );

    }

    public void updateSmartDashboard() {
        
    }
}
