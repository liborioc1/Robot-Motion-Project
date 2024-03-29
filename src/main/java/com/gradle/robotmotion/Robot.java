package com.gradle.robotmotion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Robot {

    private boolean penUp;
    private Position currentPosition;
    public enum Orientation {NORTH, SOUTH, EAST, WEST}
    private Orientation orientation;
    Floor floor;
    public static List<String> historyOfCommands = Collections.synchronizedList(new ArrayList<String>());


    public Robot(Floor f) {
        penUp = true;
        currentPosition = new Position(0, 0);
        orientation = Orientation.NORTH;
        floor = f;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public boolean getPenUp() {
        return penUp;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public void turnRight() {
        if(orientation == Orientation.NORTH){
            orientation = Orientation.EAST;
        }
        else if(orientation == Orientation.SOUTH){
            orientation = Orientation.WEST;
        }
        else if(orientation == Orientation.EAST){
            orientation = Orientation.SOUTH;
        }
        else {
            orientation = Orientation.NORTH;
        }

    }

    public void turnLeft() {

        if(orientation == Orientation.SOUTH){
            orientation = Orientation.EAST;
        }
        else if(orientation == Orientation.NORTH){
            orientation = Orientation.WEST;
        }
        else if(orientation == Orientation.WEST){
            orientation = Orientation.SOUTH;
        }
        else {
            orientation = Orientation.NORTH;
        }
    }

    public void penUp() {
        penUp = true;
    }

    public void penDown() {
        penUp = false;
    }

    public void setCurrentPosition(Position position)
    {currentPosition=new Position(position.getxPosition(),position.getyPosition());}

    public String printCurrentPosition() {
        if (penUp) {
            return "Position: " + currentPosition.toString() + " - Pen: Up - Facing: " + orientation;
        } else {
            return "Position: " + currentPosition.toString() + " - Pen: Down - Facing: " + orientation;
        }
    }

    public Position moveForward(int spaces){

        if(orientation == Orientation.SOUTH){
            if(penUp){
                setPositionOnSouthMovement(spaces);
            }
            else{
                Position previousPosition = new Position(currentPosition);
                Position newPosition = setPositionOnSouthMovement(spaces);
                setFloorWhenSouthMovementPenDown(previousPosition, newPosition);
            }
        }
        else if(orientation == Orientation.NORTH){
            if(penUp){
                setPositionOnNorthMovement(spaces);
            }
            else{
                Position previousPosition = new Position(currentPosition);
                Position newPosition = setPositionOnNorthMovement(spaces);
                setFloorWhenNorthMovementPenDown(previousPosition, newPosition);
            }
        }
        else if(orientation == Orientation.WEST){
            if(penUp){
                setPositionOnWestMovement(spaces);
            }
            else{
                Position previousPosition = new Position(currentPosition);
                Position newPosition = setPositionOnWestMovement(spaces);
                setFloorWhenWestMovementPenDown(previousPosition, newPosition);
            }
        }
        else {
            if(penUp){
                setPositionOnEastMovement(spaces);
            }
            else{
                Position previousPosition = new Position(currentPosition);
                Position newPosition = setPositionOnEastMovement(spaces);
                setFloorWhenEastMovementPenDown(previousPosition, newPosition);
            }
        }
        return currentPosition;
    }


    public Position setPositionOnSouthMovement(int spaces){
        if(currentPosition.getyPosition() - spaces < 0){
            System.out.println("Number of spaces for movement is too large");
            historyOfCommands.remove(historyOfCommands.size()-1);
            return currentPosition;
        }
        else{
            currentPosition.setyPosition(currentPosition.getyPosition() - spaces);
        }
        return currentPosition;
    }
    public void setFloorWhenSouthMovementPenDown(Position previous, Position newPosition){

        for(int i = previous.getyPosition(); i >= newPosition.getyPosition(); i--){
            floor.getFloor()[previous.getxPosition()][i] = 1;
        }
    }
    public Position setPositionOnNorthMovement(int spaces){
        if(currentPosition.getyPosition() + spaces > floor.getFloorSize() - 1){
            System.out.println("Number of spaces for movement is too large");
            historyOfCommands.remove(historyOfCommands.size()-1);
            return currentPosition;
        }
        else{
            currentPosition.setyPosition(currentPosition.getyPosition() + spaces);
        }
        return currentPosition;
    }
    public void setFloorWhenNorthMovementPenDown(Position previous, Position newPosition){

        for(int i = previous.getyPosition(); i <= newPosition.getyPosition(); i++){
            floor.getFloor()[previous.getxPosition()][i] = 1;
        }
    }
    public Position setPositionOnWestMovement(int spaces){
        if(currentPosition.getxPosition() - spaces < 0){
            System.out.println("Number of spaces for movement is too large");
            historyOfCommands.remove(historyOfCommands.size()-1);
            return currentPosition;
        }
        else{
            currentPosition.setxPosition(currentPosition.getxPosition() - spaces);
        }
        return currentPosition;
    }
    public void setFloorWhenWestMovementPenDown(Position previous, Position newPosition){

        for(int i = previous.getxPosition(); i >= newPosition.getxPosition(); i--){
            floor.getFloor()[i][previous.getyPosition()] = 1;
        }
    }
    public Position setPositionOnEastMovement(int spaces){
        if(currentPosition.getxPosition() + spaces > floor.getFloorSize()-1){
            System.out.println("Number of spaces for movement is too large");
            historyOfCommands.remove(historyOfCommands.size()-1);
            return currentPosition;
        }
        else{
            currentPosition.setxPosition(currentPosition.getxPosition() + spaces);
        }
        return currentPosition;
    }
    public void setFloorWhenEastMovementPenDown(Position previous, Position newPosition){
        for(int i = previous.getxPosition(); i <= newPosition.getxPosition(); i++){
            floor.getFloor()[i][previous.getyPosition()] = 1;
        }
    }
}
