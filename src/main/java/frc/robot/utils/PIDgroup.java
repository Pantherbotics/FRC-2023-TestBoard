package frc.robot.utils;

import java.util.function.Supplier;

import edu.wpi.first.math.controller.PIDController;

// basically the same things as PID subsytem, but for use within subsystems, and less flexible
public class PIDgroup {
    private motorGroup motors;
    private Supplier<Double> callback;
    private PIDController pid;
    public double setPoint; // making this private doubles the number of methods, and for what?

    public PIDgroup(motorGroup motors, PIDController pid, Supplier<Double> measurement) {
        this.motors = motors;
        this.callback = measurement;
        this.pid = pid;
    }

    // expects to be called at a regular interval
    public void process() {
        motors.setGroupSignal(
                pid.calculate((double) callback.get(), setPoint));
    }

    public motorGroup getMotors() {
        return motors;
    }
}
