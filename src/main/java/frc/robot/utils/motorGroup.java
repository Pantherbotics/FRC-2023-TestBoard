package frc.robot.utils;

public interface MotorGroup {
    public void setGroupSignal(double speed);
    public void setSignal(int channel, double speed);
}
