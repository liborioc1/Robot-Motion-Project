package com.gradle.robotmotion;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
                        Robot.historyOfCommands.add(command);
                        robot.moveForward(number);
                        break;
                    case "i":
                        if(floor.getFloorSize() != 0){
                            robot = new Robot(floor);
                        }
                        Robot.historyOfCommands.add(command);
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
                System.out.println("Floor has not been initialized. Floor must be initialized to perform requested command");
                return;
            }
            switch (letter)
            {
                case "u":
                    Robot.historyOfCommands.add(command);
                    robot.penUp();
                    break;
                case "d":
                    Robot.historyOfCommands.add(command);
                    robot.penDown();
                    break;
                case "r":
                    Robot.historyOfCommands.add(command);
                    robot.turnRight();
                    break;
                case "l":
                    Robot.historyOfCommands.add(command);
                    robot.turnLeft();
                    break;
                case "p":
                    Robot.historyOfCommands.add(command);
                    floor.printFloor();
                    break;
                case "c":
                    Robot.historyOfCommands.add(command);
                    System.out.println(robot.printCurrentPosition());
                    break;
                case "h":
                    //Robot.historyOfCommands.add(command);
                    replayCommands();
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

    public void decodeReplayCommands(String command)
    {
        command=command.toLowerCase();

        if(command.matches("^[a-zA-Z]{1}\\s{1}([0-9]|[1-3][0-9]|40){1}$"))
        {

            String letter=command.split(" ")[0];
            BigDecimal numberBigDecimal = new BigDecimal(command.split(" ")[1]);
            int number = numberBigDecimal.intValue();
            //one char followed by one digit
            if(letter.equals("m")){
                robot.moveForward(number);
            }
            else {
                if(floor.getFloorSize() != 0){
                    robot = new Robot(floor);
                }
                floor.initializeFloor(number);
            }

        }
        else
        {
            String letter=command.split(" ")[0];


            if(letter.equals("u"))
                robot.penUp();
            else if(letter.equals("d"))
                robot.penDown();
            else if(letter.equals("r"))
                robot.turnRight();
            else if(letter.equals("l"))
                robot.turnLeft();
            else if(letter.equals("p"))
                floor.printFloor();
            else
            {
                System.out.println(robot.printCurrentPosition());
            }
        }
    }

    public void replayCommands(){

        int size = floor.getFloorSize();
        floor.initializeFloor(size);
        List<String> historyOfCommands = Robot.historyOfCommands;
        robot = new Robot(floor);
        System.out.println("\nReplaying: ");
        for(String s : historyOfCommands){
            System.out.println("Command: " + s);
            decodeReplayCommands(s);
        }
        System.out.println("End Replay.\n");
    }

}
