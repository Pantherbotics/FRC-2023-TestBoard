package frc.robot.utils;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;

public class PWMGroup {
    private PWMSparkMax[] members = new PWMSparkMax[] {};
    private boolean[] exists = new boolean[] {};
    private int totalMembers = 0;
    
    public PWMGroup(int memberCount){
        members = new PWMSparkMax[memberCount];
        exists = new boolean[memberCount];
        for (int i = 0; i < exists.length; i++) {
            exists[i] = false;
        }
    }

    public PWMGroup(int memberCount, int startingChannel){
        this(memberCount);
        for (int i = 0; i < members.length; i++) {
            setChannel(i, startingChannel);
        }
    }

    public void setChannel(int sparkIndex, int channel){
        if(sparkIndex < 0 || sparkIndex == members.length){
            throw new IndexOutOfBoundsException("Invalid Spark Index! The max index you can access for this PWMGroup is" + members.length);
        }
        members[sparkIndex] = new PWMSparkMax(channel);
        if(!exists[sparkIndex]){
            totalMembers += 1;
            exists[sparkIndex] = true;
        }
    }

    public void add(int channel){
        if(totalMembers == members.length){
            PWMSparkMax[] temp = new PWMSparkMax[totalMembers + 1];
            for (int i = 0; i < members.length; i++) {
                temp[i] = members[i];
            }
            members = temp;
        }
        members[totalMembers] = new PWMSparkMax(channel);
    }
}