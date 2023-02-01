package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.PIDgroup;
import frc.robot.utils.PWMGroup;
import frc.robot.Constants.ArmConstants;

import com.ctre.phoenix.sensors.CANCoder;

public class Claw extends SubsystemBase {
    private PIDgroup flexGroup, rotateGroup;
    private CANCoder flexEncoder, rotateEncoder;

    private boolean doPID;

    public Claw() {
        flexEncoder = new CANCoder(ArmConstants.flexEncoderCAN);
        rotateEncoder = new CANCoder(ArmConstants.rotateEncoderCAN);
    
        flexGroup = new PIDgroup(
            new PWMGroup(ArmConstants.flexFirstChannel, ArmConstants.flexChannelCount),
            new PIDController(ArmConstants.flexKP, ArmConstants.flexKI, ArmConstants.flexKD),
            flexEncoder::getAbsolutePosition
        );
    
        rotateGroup = new PIDgroup(
            new PWMGroup(ArmConstants.rotateFirstChannel, ArmConstants.rotateChannelCount),
            new PIDController(ArmConstants.rotateKP, ArmConstants.rotateKI, ArmConstants.rotateKD),
            rotateEncoder::getAbsolutePosition
        );

        doPID = false;
    }
    
    public void flex(double speed) {
        speed = normalizeSpeed(speed);
        flexGroup.setPoint += speed;
    }
    
    public void rotate(double speed) {
        speed = normalizeSpeed(speed);
        rotateGroup.setPoint += speed;
    }
    
    public void flexDebug(double speed) {
        speed = normalizeSpeed(speed);
        flexGroup.getMotors().setGroupSignal(speed);
    }
    
    public void rotateDebug(double speed) {
        speed = normalizeSpeed(speed);
        rotateGroup.getMotors().setGroupSignal(speed);
    }

    private double normalizeSpeed(double speed) {
        double magnitude = Math.abs(speed);

        if(magnitude <= 0.3) 
            return 0;
        else if(magnitude > 1)
            return speed / magnitude;
        
        return speed;
    }
    
    public void stop() {
        rotate(0);
        flex(0);
    }
    
    @Override
    public void periodic() {
        if(doPID) {
            // not using flex and rotate because of deadzones
            flexGroup.process();
            rotateGroup.process();
    
            SmartDashboard.putNumber("Flex point", flexGroup.setPoint);
            SmartDashboard.putNumber("Rotate point", rotateGroup.setPoint);
        }
    
        SmartDashboard.putNumber("Flex Encoder", flexEncoder.getAbsolutePosition());
        SmartDashboard.putNumber("Rotate Encoder", rotateEncoder.getAbsolutePosition());
    }
    
    public boolean isDoPID() {
        return doPID;
    }

    public void setDoPID(boolean doPID) {
        this.doPID = doPID; 
    }
}
