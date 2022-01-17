package com.gradle.robotmotion;

public class Floor {

    private int floorSize;
    private int [][] floor;

    public Floor(){}


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

    public void printFloor(){
        System.out.println("Floor is of size: " + floorSize + " x " + floorSize);

        for(int i = floorSize-1 ; i >= 0 ; i--){
            System.out.print(i + " ");
            for(int j = 0 ; j < floorSize ; j++){
                if(floor[j][i] == 1) {
                    System.out.print("*" + " ");
                }
                else{
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
        System.out.print("  ");
        for(int i = 0; i < floorSize; i++){
            System.out.print(i + " ");
        }
        System.out.println("");
    }
}
