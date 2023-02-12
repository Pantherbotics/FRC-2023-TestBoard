package frc.robot;

public class Constants {
    public static final class OIConstants{
        public static final int pJoyID = 0;
        public static final int kDriverXL = 0;
        public static final int kDriverYL = 1;

        public static final int kDriverXR = 4;
        public static final int kDriverYR = 5;
        
    }

    public static final class ArmConstant {
        public static final int leftPivotChannel = 0;
        public static final int rightPivotChannel = 1;

        public static final int linearActuatorChannel = 2;
    }

    public static final class ClawConstants {
        public static final int flexFirstChannel = 0;
        public static final int flexChannelCount = 4;

        public static final int rotateFirstChannel = 4;
        public static final int rotateChannelCount = 2;

        public static final double rotateKP = 0.8;
        public static final double rotateKI = 0.2;
        public static final double rotateKD = 0;

        public static final double flexKP = 0.05;
        public static final double flexKI = 0.1;
        public static final double flexKD = 0;
        
        public static final int rotateEncoderCAN = 0;
        public static final int flexEncoderCAN = 1;

        public static final int pcmCANa = 2;
        public static final int pcmCANb = 3;
    }
}
