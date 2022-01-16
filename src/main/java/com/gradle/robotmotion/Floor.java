package com.gradle.robotmotion;

public class Floor {

    private int floorSize;
    private int [][] floor;

    public Floor(){}

    public void setFloorSize(int floorSize) {
        this.floorSize = floorSize;
    }

    public int getFloorSize() {
        return floorSize;
    }

    public int[][] getFloor() {
        return floor;
    }

    public void initializeFloor(int size){
        floorSize = size;
        floor = new int[floorSize][floorSize];
    }
}
