package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxAnalogSensor;
import com.revrobotics.CANAnalog.AnalogMode;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Claw extends SubsystemBase {
    private final CANSparkMax flexMotor, rotateMotor; 

    private final RelativeEncoder flexEncoder, rotateEncoder;
    private final PIDController flexPID, rotatePID;

    public Claw() {
        //flex motor
        flexMotor = new CANSparkMax(8, MotorType.kBrushless);
        flexMotor.restoreFactoryDefaults();
        flexMotor.setIdleMode(IdleMode.kCoast);
        flexMotor.setSoftLimit(SoftLimitDirection.kForward, 50);
        flexMotor.setSoftLimit(SoftLimitDirection.kReverse, -50);
        flexMotor.enableSoftLimit(SoftLimitDirection.kForward, true);
        flexMotor.enableSoftLimit(SoftLimitDirection.kReverse, true);
        flexMotor.burnFlash();

        //flex encoder
        flexEncoder = flexMotor.getEncoder();
        flexEncoder.setPositionConversionFactor(360);
        flexEncoder.setVelocityConversionFactor(6);

        //flex pid
        flexPID = new PIDController(0.05, 0.1, 0);
        SmartDashboard.putNumber("Flex kP", flexPID.getP());
        SmartDashboard.putNumber("Flex kI", flexPID.getI());
        SmartDashboard.putNumber("Flex kD", flexPID.getD());

        //rotate motor
        rotateMotor = new CANSparkMax(7, MotorType.kBrushless);
        rotateMotor.restoreFactoryDefaults();
        rotateMotor.setIdleMode(IdleMode.kCoast);
        rotateMotor.burnFlash();

        //rotate encoder
        rotateEncoder = rotateMotor.getEncoder();
        rotateEncoder.setPositionConversionFactor(360);
        rotateEncoder.setVelocityConversionFactor(6);

        //rotate pid
        rotatePID = new PIDController(0.1, 0, 0);
        SmartDashboard.putNumber("Rotate kP", rotatePID.getP());
        SmartDashboard.putNumber("Rotate kI", rotatePID.getI());
        SmartDashboard.putNumber("Rotate kD", rotatePID.getD());
    }
    
    private double normalizeSpeed(double speed) {
        double magnitude = Math.abs(speed);
        if(magnitude <= 0.3) return 0;
        if(magnitude > 1) return speed / magnitude;
        return speed;
    }

    public void flexOpenLoop(double speed) {
        flexMotor.set(speed);
    }

    public void rotateOpenLoop(double speed) {
        rotateMotor.set(speed);
    }

    public void flex(double speed){
        double newSetpoint = flexPID.getSetpoint() + speed;
        flexPID.setSetpoint(newSetpoint);
    }

    public double getFlexPosition() {
        return flexEncoder.getPosition();
    }

    public void rotate(double speed) {
        double newSetpoint = rotatePID.getSetpoint() + speed;
        rotatePID.setSetpoint(newSetpoint);
    }

    public double getRotatePosition() {
        return rotateEncoder.getPosition();
    }

    public void stop() {
        flexMotor.set(0);
        rotateMotor.set(0);
    }

    @Override
    public void periodic() {
        // flexMotor.set(flexPID.calculate(getFlexPosition()));
        rotateMotor.set(rotatePID.calculate(getRotatePosition()));

        SmartDashboard.putNumber("Flex setpoint", flexPID.getSetpoint());
        SmartDashboard.putNumber("Rotate setpoint", rotatePID.getSetpoint());

        SmartDashboard.putNumber("Flex encoder", flexEncoder.getPosition());
        SmartDashboard.putNumber("Rotate encoder", rotateEncoder.getPosition());

        flexPID.setP(SmartDashboard.getNumber("Flex kP", flexPID.getP()));
        flexPID.setI(SmartDashboard.getNumber("Flex kI", flexPID.getI()));
        flexPID.setD(SmartDashboard.getNumber("Flex kD", flexPID.getD()));

        rotatePID.setP(SmartDashboard.getNumber("Rotate kP", rotatePID.getP()));
        rotatePID.setI(SmartDashboard.getNumber("Rotate kI", rotatePID.getI()));
        rotatePID.setD(SmartDashboard.getNumber("Rotate kD", rotatePID.getD()));
    }
}
