package com.gradle.robotmotion;

public class RobotMotion {

    public static void main(String[] args) {

        Floor floor = new Floor();
        floor.initializeFloor(10);
        Robot robot = new Robot(floor);
        robot.penDown();
        robot.moveForward(15);
        System.out.println(robot.printCurrentPosition());
        floor.printFloor();

        robot.turnRight();
        //robot.penUp();
        robot.moveForward(2);
        System.out.println(robot.printCurrentPosition());
        floor.printFloor();

        robot.turnRight();
        robot.moveForward(5);
        System.out.println(robot.printCurrentPosition());
        floor.printFloor();

        robot.turnLeft();
        //robot.penDown();
        robot.moveForward(15);
        System.out.println(robot.printCurrentPosition());
        floor.printFloor();

        robot.turnLeft();
        //robot.penDown();
        robot.moveForward(3);
        System.out.println(robot.printCurrentPosition());
        floor.printFloor();

        robot.turnLeft();
        //robot.penDown();
        robot.moveForward(6);
        System.out.println(robot.printCurrentPosition());
        floor.printFloor();
    }




}
