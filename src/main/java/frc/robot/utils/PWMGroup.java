package frc.robot.utils;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;

public class PWMGroup {
    private PWMSparkMax[] members = new PWMSparkMax[] {};
    private boolean[] exists = new boolean[] {};
    private boolean[] reversed = new boolean[] {};
    private int totalMembers = 0;
    private static ArrayList<Integer> activeChannels = new ArrayList<Integer>();
    
    /**
     * Create a new PWMGroup of unitialized PWMSparkMaxs.
     * @param memberCount The size of the PWMGroup
     */
    public PWMGroup(int memberCount){
        members = new PWMSparkMax[memberCount];
        exists = new boolean[memberCount];
        for (int i = 0; i < exists.length; i++) {
            exists[i] = false;
        }
        reversed = exists;
    }
    
    /**
     * Create a new PWMGroup of initialized PWMSparkMaxs.
     * @param memberCount The size of the PWMGroup.
     * @param startingChannel The channel of the first PWMSparkMax.  
     * Subsequent members will have a channel of the previous PWMSParkMax's channel + 1.
     */
    public PWMGroup(int memberCount, int startingChannel){
        this(memberCount);
        for (int i = 0; i < members.length; i++) {
            add(startingChannel + i);
        }
    }

    /**
     * Set the channel of a PWMSParkMax
     * @param sparkIndex The index of the PWMSparkMax to set.
     * @param channel The channel to attatch to the PWMSparkMax.
     */
    public void setChannel(int sparkIndex, int channel){
        if(sparkIndex < 0 || sparkIndex >= members.length){
            throw new IndexOutOfBoundsException("Invalid Spark Index! The max index you can access for this PWMGroup is" + (members.length - 1));
        }
        if(channelExists(channel)) throw new IndexOutOfBoundsException("The channel " + channel + " is already in use.");
        members[sparkIndex] = new PWMSparkMax(channel);
        activeChannels.add(channel);
        if(!exists[sparkIndex]){
            totalMembers += 1;
            exists[sparkIndex] = true;
        }
    }

    /**
     * Adds a new PWMSparkMax to the group, increasing the group size if necessary.
     * @param channel The PWM Channel the new PWMSparkMax should listen to.
     */
    public void add(int channel){
        if(totalMembers == members.length){
            PWMSparkMax[] temp = new PWMSparkMax[totalMembers + 1];
            for (int i = 0; i < members.length; i++) {
                temp[i] = members[i];
            }
            members = temp;
        }
        setChannel(totalMembers, channel);
    }

    /**
     * Allows users to specify whether a PWMSparkMax should invert any input it receives.
     * @param sparkToReverse The index of the PWMSparkMax to alter.
     * @param reversedValue True if reversed, falsed if normal processing.
     */
    public void setReversed(int sparkToReverse, boolean reversedValue){
        if(sparkToReverse < 0 || sparkToReverse >= totalMembers) throw new IndexOutOfBoundsException("No PWMSparkMax exists at the specified index");
        reversed[sparkToReverse] = reversedValue;
    }

    public void setSignal(double signal){
        for (int i = 0; i < totalMembers; i++) {
            members[i].set(signal);
        }
    }

    /**
     * Checks to see if a channel is in use by this group.
     * @param channel The channel to check.
     * @return False if the specified channel is not in use.
     */
    public static boolean channelExists(int channel){
        for (int checkChannel : activeChannels) {
            if(checkChannel == channel){
                return true;
            }
        }
        return false;
    }

    /**
     * Access function for the total members of a group.
     * @return The total members of the group.
     */
    public int totalMembers(){
        return totalMembers;
    }

    /**
     * Added access function to edit individual PWMSparkMaxs.
     * @param sparkIndex The index to retrieve from.
     * @return The PWMSparkMax at the specified index.
     */
    public PWMSparkMax getPWMSparkMax(int sparkIndex){
        if(!exists[sparkIndex]) throw new IndexOutOfBoundsException("No PWMSparkMax exists at the specified index");
        return members[sparkIndex];
    }
}