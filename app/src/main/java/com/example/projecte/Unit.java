package com.example.projecte;

/**
 * class Unit
 *
 * @author Anne Marie Blank,
 * @author Harry Vu,
 * @author Vincent Truong,
 * @author Kathryn Weidman
 * @version 2/24/2022
 */
public class Unit {
    /**
     *  External Citation
     *  Date 2/23/2022
     *  Issue: unsure what methods to implement
     *  "Link": Office Hours help from Nux
     *
     */
    public static final int SPY = 1;
    public static final int SCOUT = 2;
    public static final int MINER = 3;
    public static final int SERGEANT = 4;
    public static final int LIEUTENANT = 5;
    public static final int CAPTAIN = 6;
    public static final int MAJOR = 7;
    public static final int COLONEL = 8;
    public static final int GENERAL = 9;
    public static final int MARSHAL = 10;
    public static final int BOMB = 11; //we probably want water
    public static final int FLAG = 12;
    public static final int WATER = 13;

    /*** Nothing else needs to be added in this Unit class
     * IMPORTANT ELABORATION: Every unit has their ownerID, meaning that
     * the owner's id that comes with the unit,*/


    private int ownerID;  //the id of the player who owns the piece
    private int rank;     //what kind of unit is this?
    private boolean isSelected;
    private boolean isDead;
    private int xLoc;
    private int yLoc;

    public Unit(int id, int initRank) {
        ownerID = id;
        rank = initRank;
        isSelected = false;
        isDead = false;
    }

    public int getOwnerID(){
        return this.ownerID;
    }

    public int getRank() {
        return rank;
    }

    public void setSelected(boolean selected) {
        if(isDead == false) {
            isSelected = selected;
        }
    }

    public void setStatus(boolean dead){
        isDead = dead;
    }

    public boolean getStatus(){
        return isDead;
    }

    public int getxLoc() {
        return xLoc;
    }

    public int getyLoc() {
        return yLoc;
    }

    public void setxLoc(int xLoc) {
        this.xLoc = xLoc;
    }

    public void setyLoc(int yLoc) {
        this.yLoc = yLoc;
    }

    public String nameRank() {
        String name;
        switch (this.rank)
        {
            case 1:
                return "Spy";
            case 2:
                return "Scout";
            case 3:
                return "Miner";
            case 4:
                return "Sergeant";
            case 5:
                return "Lieutenant";
            case 6:
                return "Captain";
            case 7:
                return "Major";
            case 8:
                return "Colonel";
            case 9:
                return "General";
            case 10:
                return "Marshal";
            case 11:
                return "Bomb";
            case 12:
                return "Flag";
        }
        return "bad";
    }



}
