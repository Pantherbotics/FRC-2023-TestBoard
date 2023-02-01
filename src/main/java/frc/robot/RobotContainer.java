package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Claw;
public class RobotContainer {
    private final Arm arm = new Arm();
    private final Claw claw = new Claw();
    
    private final Joystick pJoy = new Joystick(Constants.OIConstants.pJoyID);
    private final JoystickButton ButtonA = new JoystickButton(pJoy, 1);

    public RobotContainer(Robot robot) {
        configureButtonBindings();
    }

    public void configureButtonBindings() {
        double dx = pJoy.getRawAxis(Constants.OIConstants.kDriverXL);
        double dy = pJoy.getRawAxis(Constants.OIConstants.kDriverYL);
    
        if(claw.isDoPID()) {
            claw.rotate(dx);
            claw.flex(dy);
        } else {
            claw.rotateDebug(dx);
            claw.flexDebug(dy);
        }

        ButtonA.toggleOnTrue(
            new InstantCommand(
                () -> claw.setDoPID(!claw.isDoPID())
            )
        );

    }

    public void updateSmartDashboard() {
        
    }
}
