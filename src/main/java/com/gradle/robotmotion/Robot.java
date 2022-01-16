package com.gradle.robotmotion;

public class Robot {

    private boolean penUp;
    private Position currentPosition;
    private String orientation;

    public Robot(){
        penUp = true;
        currentPosition = new Position(0,0);
        orientation = "north";
    }

    public String getOrientation() {
        return orientation;
    }

    public boolean getPenUp() {
        return penUp;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public void setPenUp(boolean penUp) {
        this.penUp = penUp;
    }

    public void setCurrentPosition(int x, int y) {
        this.currentPosition.setxPosition(x);
        this.currentPosition.setyPosition(y);
    }
}
