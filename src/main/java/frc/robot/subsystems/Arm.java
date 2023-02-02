package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.sensors.AbsoluteSensorRange;
import com.ctre.phoenix.sensors.CANCoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.PWMGroup;

import static frc.robot.Constants.ArmConstant;

public class Arm extends SubsystemBase {
    private final CANSparkMax leftPivotMotor, rightPivotMotor; //perspective from the front of the robot
    private final PWMGroup pivotMotorGroup; 
    private final TalonFX talon;
    private final CANCoder rotationEncoder;
    
    public Arm() {
        
        leftPivotMotor = new CANSparkMax(0, MotorType.kBrushless);
        leftPivotMotor.restoreFactoryDefaults();
        leftPivotMotor.setIdleMode(IdleMode.kCoast);
        leftPivotMotor.burnFlash();

        rightPivotMotor = new CANSparkMax(1, MotorType.kBrushless);
        rightPivotMotor.restoreFactoryDefaults();
        rightPivotMotor.setIdleMode(IdleMode.kCoast);
        rightPivotMotor.burnFlash();

        leftPivotMotor.follow(rightPivotMotor);

        talon = new TalonFX(0);
        talon.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 20);
        talon.setNeutralMode(NeutralMode.Coast);
        talon.config_kP(0, 0);
        talon.config_kI(0, 0);
        talon.config_kD(0, 0);

        rotationEncoder = new CANCoder(0);
        rotationEncoder.configAbsoluteSensorRange(AbsoluteSensorRange.Unsigned_0_to_360);
    }


}
