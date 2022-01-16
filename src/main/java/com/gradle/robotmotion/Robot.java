package com.gradle.robotmotion;

public class Robot {

    private boolean penUp;
    private Position currentPosition;
    enum Orientation{NORTH,SOUTH,EAST,WEST}
    private Orientation orientation;


    public Robot(){
        penUp = true;
        currentPosition = new Position(0,0);
        orientation = Orientation.NORTH;
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

    public void turnRight()
    {
        switch (orientation){
            case NORTH :
                orientation=Orientation.EAST;
                break;
            case SOUTH:
                orientation=Orientation.WEST;
                break;
            case EAST:
                orientation=Orientation.SOUTH;
                break;
            case WEST:
                orientation=Orientation.NORTH;
                break;
        }
    }

    public void turnLeft()
    {
        switch (orientation){
            case SOUTH:
                orientation=Orientation.EAST;
                break;
            case NORTH:
                orientation=Orientation.WEST;
                break;
            case WEST:
                orientation=Orientation.SOUTH;
                break;
            case EAST:
                orientation=Orientation.NORTH;
                break;
        }
    }

    public void setPenUp(boolean penUp) {
        this.penUp = penUp;
    }

    public void setCurrentPosition(int x, int y) {
        this.currentPosition.setxPosition(x);
        this.currentPosition.setyPosition(y);
    }
}
