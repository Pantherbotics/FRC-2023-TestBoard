package frc.robot;

public class Constants {
    public static final class OIConstants{
        public static final int pJoyID = 0;
        public static final int kDriverXL = 0;
        public static final int kDriverYL = 1;
    }

    public static final class ArmConstants {
        public static final int flexFirstChannel = 0;
        public static final int flexChannelCount = 4;

        public static final int rotateFirstChannel = 4;
        public static final int rotateChannelCount = 1;

        public static final double rotateKP = 0.9;
        public static final double rotateKI = 0;
        public static final double rotateKD = 0;

        public static final double flexKP = 0.9;
        public static final double flexKI = 0;
        public static final double flexKD = 0;
    }
}
