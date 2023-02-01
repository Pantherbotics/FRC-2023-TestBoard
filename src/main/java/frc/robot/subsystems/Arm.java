package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.sensors.CANCoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {
    private final CANSparkMax neoA, neoB;
    private final TalonFX talon;
    private final CANCoder rotationEncoder;
    
    public Arm() {
        neoA = new CANSparkMax(0, MotorType.kBrushless);
        neoB = new CANSparkMax(1, MotorType.kBrushless);
        talon = new TalonFX(0);
        rotationEncoder = new CANCoder(0);
    }
}
