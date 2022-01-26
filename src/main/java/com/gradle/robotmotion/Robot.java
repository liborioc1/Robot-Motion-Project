package com.gradle.robotmotion;

public class Robot {

    //TODO extract interface for requirements
    //TODO redefine the requirements

    private boolean penUp;
    private Position currentPosition;
    public enum Orientation {NORTH, SOUTH, EAST, WEST}
    private Orientation orientation;
    Floor floor;


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
        switch (orientation) {
            case NORTH:
                orientation = Orientation.EAST;
                break;
            case SOUTH:
                orientation = Orientation.WEST;
                break;
            case EAST:
                orientation = Orientation.SOUTH;
                break;
            case WEST:
                orientation = Orientation.NORTH;
                break;
        }
    }

    public void turnLeft() {
        switch (orientation) {
            case SOUTH:
                orientation = Orientation.EAST;
                break;
            case NORTH:
                orientation = Orientation.WEST;
                break;
            case WEST:
                orientation = Orientation.SOUTH;
                break;
            case EAST:
                orientation = Orientation.NORTH;
                break;
        }
    }

    public void penUp() {
        penUp = true;
    }

    public void penDown() {
        penUp = false;
    }

    public void setCurrentPosition(Position position)
    {
        currentPosition=new Position(position.getxPosition(),position.getyPosition());
    }
    public String printCurrentPosition() {
        if (penUp) {
            return "Position: " + currentPosition.toString() + " - Pen: Up - Facing: " + orientation;
        } else {
            return "Position: " + currentPosition.toString() + " - Pen: Down - Facing: " + orientation;
        }
    }

    public Position moveForward(int spaces){

        switch (orientation){
            case SOUTH:
                if(penUp){
                    setPositionOnSouthMovement(spaces);
                }
                else{
                    Position previousPosition = new Position(currentPosition);
                    Position newPosition = setPositionOnSouthMovement(spaces);
                    setFloorWhenSouthMovementPenDown(previousPosition, newPosition);
                }
                break;
            case NORTH:
                if(penUp){
                    setPositionOnNorthMovement(spaces);
                }
                else{
                    Position previousPosition = new Position(currentPosition);
                    Position newPosition = setPositionOnNorthMovement(spaces);
                    setFloorWhenNorthMovementPenDown(previousPosition, newPosition);
                }
                break;
            case WEST:
                if(penUp){
                    setPositionOnWestMovement(spaces);
                }
                else{
                    Position previousPosition = new Position(currentPosition);
                    Position newPosition = setPositionOnWestMovement(spaces);
                    setFloorWhenWestMovementPenDown(previousPosition, newPosition);
                }
                break;
            case EAST:
                if(penUp){
                    setPositionOnEastMovement(spaces);
                }
                else{
                    Position previousPosition = new Position(currentPosition);
                    Position newPosition = setPositionOnEastMovement(spaces);
                    setFloorWhenEastMovementPenDown(previousPosition, newPosition);
                }
                break;
        }
        return currentPosition;
    }


    public Position setPositionOnSouthMovement(int spaces){
        if(currentPosition.getyPosition() - spaces < 0){
            System.out.println("Number of spaces for movement is too large");
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
            return currentPosition;
        }
        else{
            currentPosition.setxPosition(currentPosition.getyPosition() - spaces);
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
