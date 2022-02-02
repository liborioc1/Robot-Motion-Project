package com.gradle.robotmotion;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class CommandsDecoder {

    private String userCommand;
    private Robot robot;
    private Floor floor;
    private AtomicBoolean flag;

    public CommandsDecoder( Robot robot, Floor floor,AtomicBoolean flag)
    {
        this.robot=robot;
        this.floor=floor;
        this.flag=flag;
    }

    public Robot getRobot()
    {
        return robot;
    }
    public void decodeCommand(String command)
    {

        command=command.toLowerCase();

        if(command.matches("^[a-zA-Z]{1}\\s{1}([0-9]|[1-3][0-9]|40){1}$"))
        {

            String letter=command.split(" ")[0];
            BigDecimal numberBigDecimal = new BigDecimal(command.split(" ")[1]);
            int number = numberBigDecimal.intValue();
                //one char followed by one digit
                switch (letter)
                {
                    case "m":
                        if(floor.getFloorSize() == 0)
                        {
                            System.out.println("Floor has not been initialized. Floor must be initialized to perform requested move");
                            return;
                        }
                        robot.moveForward(number);
                        break;
                    case "i":
                        if(floor.getFloorSize() != 0){
                            robot = new Robot(floor);
                        }
                        floor.initializeFloor(number);
                        break;
                    default:
                        System.out.println("Invalid Input Format. Please Check Spaces");
                }

        }
        else if(command.matches("^[a-zA-Z]{1}$"))
        {
            String letter=command.split(" ")[0];

            if(letter.equals("q"))
            {
                flag.set(false);
                System.out.println("End of Program");
                return;
            }
            if(floor.getFloorSize()==0)
            {
                System.out.println("Floor has not been initialized. Floor must be initialized to perform requested move");
                return;
            }
            switch (letter)
            {
                case "u":
                    robot.penUp();
                    break;
                case "d":
                    robot.penDown();
                    break;
                case "r":
                    robot.turnRight();
                    break;
                case "l":
                    robot.turnLeft();
                    break;
                case "p":
                    floor.printFloor();
                    break;
                case "c":
                    System.out.println(robot.printCurrentPosition());
                    break;
                default:
                    System.out.println("Invalid Input Format. Please Check Spaces");

            }
        }
        else
        {
            System.out.println("Invalid Input Format or Incorrect Command or Value(must be <= 40)");
        }

    }

}
