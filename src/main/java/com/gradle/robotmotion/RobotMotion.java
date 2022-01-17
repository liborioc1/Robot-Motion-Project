package com.gradle.robotmotion;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class RobotMotion {

    public static void main(String[] args) {

        Floor floor=new Floor();
        Robot robot=new Robot(floor);

        AtomicBoolean continueLoop=new AtomicBoolean(true);
        CommandsDecoder commandsDecoder=new CommandsDecoder(robot,floor,continueLoop);
        Scanner scanner=new Scanner(System.in);

        System.out.println("List of Commands:");
        System.out.println("" +
                "[U|u] Pen up\n" +
                "[D|d] Pen down\n" +
                "[R|r] Turn right\n" +
                "[L|l] Turn left\n" +
                "[M s|m s] Move forward s spaces (s is a non-negative integer)\n" +
                "[P|p] Print the N by N array and display the indices\n" +
                "[C|c] Print current position of the pen and whether it is up or down and its facing direction\n" +
                "[Q|q] Stop the program\n" +
                "[I n|i n] Initialize the system: The values of the array floor are zeros and the robot\n" +
                "is back to [0, 0], pen up and facing north. n size of the array, an integer\n" +
                "greater than zero ");

        while(continueLoop.get())
        {
            System.out.println("Your command selection");
            String command=scanner.nextLine();
            commandsDecoder.decodeCommand(command);
            //System.out.println(continueLoop);
        }
        scanner.close();


    }

}
