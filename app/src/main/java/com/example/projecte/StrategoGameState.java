package com.example.projecte;

import android.icu.text.UnicodeSetIterator;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * StrategoGameState
 *
 * (will eventually extend GameState interface in the GameFramework)
 *
 * @author Anne Marie Blank,
 * @author Harry Vu,
 * @author Vincent Truong,
 * @author Kathryn Weidman
 * @version 3/10/2022
 */
public class StrategoGameState {

    private int whoseTurn;

    private Unit[][] gameboard = new Unit[10][10];
    private int roundNumber;     //will be initialized to zero, changed to indicate who's turn it is
    private double timeElapsed;  //for the timer
    private ArrayList<Unit> p1Troops = new ArrayList<>();
    private ArrayList<Unit> p2Troops = new ArrayList<>();
    public String whatsUp = "";

    private boolean flagCaptured;
    private boolean legal;

    /**
     * ctor
     *
     * defines the state of the game on startup
     *
     */
    public StrategoGameState(){
        gameboard = new Unit[10][10];
        roundNumber = 0;
        whoseTurn = 0;
        timeElapsed = 0.0;
        flagCaptured = false;

        this.fillRanks(0);
        this.fillRanks(1);


    }//ctor

    /**
     * fillRanks
     *
     * helper method to fill the player's Troop Arrays
     */
    public void fillRanks(int pID) {
        if (pID == 0) {
            p1Troops.add(new Unit(0, Unit.MARSHAL));
            p1Troops.add(new Unit(0, Unit.GENERAL));
            p1Troops.add(new Unit(0, Unit.FLAG));
            p1Troops.add(new Unit(0, Unit.SPY));

            p1Troops.add(new Unit(0, Unit.COLONEL));
            p1Troops.add(new Unit(0, Unit.COLONEL));

            p1Troops.add(new Unit(0, Unit.MAJOR));
            p1Troops.add(new Unit(0, Unit.MAJOR));
            p1Troops.add(new Unit(0, Unit.MAJOR));

            p1Troops.add(new Unit(0, Unit.SERGEANT));
            p1Troops.add(new Unit(0, Unit.SERGEANT));
            p1Troops.add(new Unit(0, Unit.SERGEANT));
            p1Troops.add(new Unit(0, Unit.SERGEANT));

            p1Troops.add(new Unit(0, Unit.LIEUTENANT));
            p1Troops.add(new Unit(0, Unit.LIEUTENANT));
            p1Troops.add(new Unit(0, Unit.LIEUTENANT));
            p1Troops.add(new Unit(0, Unit.LIEUTENANT));

            p1Troops.add(new Unit(0, Unit.CAPTAIN));
            p1Troops.add(new Unit(0, Unit.CAPTAIN));
            p1Troops.add(new Unit(0, Unit.CAPTAIN));
            p1Troops.add(new Unit(0, Unit.CAPTAIN));

            for (int i = 0; i < 5; i++) {
                p1Troops.add(new Unit(0, Unit.MINER));
            }

            for (int i = 0; i < 8; i++) {
                p1Troops.add(new Unit(0, Unit.SCOUT));
            }

            for (int i = 0; i < 6; i++) {
                p1Troops.add(new Unit(0, Unit.BOMB));
            }
        } else if (pID == 1)
        {
            p2Troops.add(new Unit(1, Unit.MARSHAL));
            p2Troops.add(new Unit(1, Unit.GENERAL));
            p2Troops.add(new Unit(1, Unit.FLAG));
            p2Troops.add(new Unit(1, Unit.SPY));

            p2Troops.add(new Unit(1, Unit.COLONEL));
            p2Troops.add(new Unit(1, Unit.COLONEL));

            p2Troops.add(new Unit(1, Unit.MAJOR));
            p2Troops.add(new Unit(1, Unit.MAJOR));
            p2Troops.add(new Unit(1, Unit.MAJOR));

            p2Troops.add(new Unit(1, Unit.SERGEANT));
            p2Troops.add(new Unit(1, Unit.SERGEANT));
            p2Troops.add(new Unit(1, Unit.SERGEANT));
            p2Troops.add(new Unit(1, Unit.SERGEANT));

            p2Troops.add(new Unit(1, Unit.LIEUTENANT));
            p2Troops.add(new Unit(1, Unit.LIEUTENANT));
            p2Troops.add(new Unit(1, Unit.LIEUTENANT));
            p2Troops.add(new Unit(1, Unit.LIEUTENANT));

            p2Troops.add(new Unit(1, Unit.CAPTAIN));
            p2Troops.add(new Unit(1, Unit.CAPTAIN));
            p2Troops.add(new Unit(1, Unit.CAPTAIN));
            p2Troops.add(new Unit(1, Unit.CAPTAIN));

            for (int i = 0; i < 5; i++) {
                p2Troops.add(new Unit(1, Unit.MINER));
            }

            for (int i = 0; i < 8; i++) {
                p2Troops.add(new Unit(1, Unit.SCOUT));
            }

            for (int i = 0; i < 6; i++) {
                p2Troops.add(new Unit(1, Unit.BOMB));
            }
        }
    }//fillRanks

    /**
     * copy ctor
     *
     * @param orig  the original GameState to be copied
     * @Override
     */
    public StrategoGameState(StrategoGameState orig){
        //initialize new gameboard to be just like the old one
        for(int i = 0; i < gameboard.length; i++){
            for(int j = 0; j < gameboard[i].length; j++){
                gameboard[i][j] = orig.gameboard[i][j];
            }
        }
        flagCaptured = orig.flagCaptured;
        whoseTurn = orig.whoseTurn;
        roundNumber = orig.roundNumber;
        p1Troops = orig.p1Troops;
        p2Troops = orig.p2Troops;
    }//copy ctor

    /**
     * toString
     *
     * @return a String representation of the current StrategoGameState
     */
    @Override
    public String toString() {
        return "Turn:" + whoseTurn + "Player 1 Troops: " + p1Troops.size()
                + "Player 2 Troops: " + p2Troops.size() + "Time Elapsed: " + timeElapsed
                + "Flag Captured?: " + flagCaptured;
    }//toString/

    /**
     * movePiece
     *
     *
     * External Citation
     * Date 2/23/2022
     * Issue: unsure what methods to implement
     *
     * "Link": Office Hours help from Nux
     *
     *
     * @param playerID id of the player moving the piece
     * @param dir      direction of intended movement (up/down/l/r)
     *
     * @return  false if move is illegal, true otherwise
     */
    public boolean movePiece(int playerID, Unit chosen, int dir){
        int chosenY = chosen.getyLoc();
        int chosenX = chosen.getxLoc();

        //1 = up, 2 = down, 3 = left, 4 = right
        switch(dir) {
            case 1:  //aka "up"
                if (chosenY - 1 >= 0 && gameboard[chosenX][chosenY - 1] == null) {
                    chosen.setyLoc(chosenY - 1);
                    gameboard[chosenX][chosenY] = chosen;
                    legal = true;
                    whatsUp = whatsUp + "\nPlayer " + playerID + " has moved their " +
                        chosen + " forward.";
                } else if (chosenY - 1 >= 0 && gameboard[chosenX][chosenY - 1].getRank() == Unit.WATER) {
                    legal = false;
                    whatsUp = whatsUp + "\nPlayer " + playerID + " tried to swim, but got scared.";
                } else if (gameboard[chosenX][chosenY] != null && gameboard[chosenX][chosenY].getOwnerID() != playerID) {
                    //attack
                    int opponentRank = gameboard[chosenX][chosenY].getRank();
                    if (opponentRank > chosen.getRank()) {
                        chosen.setStatus(false);
                        gameboard[chosenX][chosenY] = null;
                        legal = true;
                        whatsUp = whatsUp + "\nPlayer " + playerID + " has lost their " + chosen.nameRank() + ".";
                    } else {
                        gameboard[chosenX][chosenY].setStatus(false);
                        gameboard[chosenX][chosenY] = null;
                        chosen.setxLoc(chosenY - 1);
                        gameboard[chosenX][chosenY] = chosen;
                        legal = true;
                        whatsUp = whatsUp + "\nPlayer " + playerID +
                                " has triumphed in a battle with their " + chosen.nameRank() + ".";
                    }
                } else {
                    whatsUp = whatsUp + "\nUnit " + chosen.nameRank() + " can't move there.";
                    legal = false;
                }
                break;
            //End case 1

            case 2:  //aka "down"
                if (chosenY + 1 <= 9 && gameboard[chosenX][chosenY + 1] == null) {  //aka space is empty
                    chosen.setyLoc(chosenY + 1);  //move into space
                    gameboard[chosenX][chosenY] = chosen;
                    legal = true;
                    whatsUp = whatsUp + "\nPlayer " + playerID + " has moved their " +
                            chosen + " down.";
                } else if (gameboard[chosenX][chosenY + 1].getRank() == Unit.WATER) {
                    legal = false;  //can't walk on water
                    whatsUp = whatsUp + "\nPlayer " + playerID + " tried to swim, but got scared.";
                } else if (gameboard[chosenX][chosenY] != null && gameboard[chosenX][chosenY + 1].getOwnerID() != playerID) {
                    //attack
                    int opponentRank = gameboard[chosenX][chosenY + 1].getRank();
                    if (opponentRank > chosen.getRank()) {
                        chosen.setStatus(false);  //you died
                        gameboard[chosenX][chosenY] = null;  //empty space you were just in
                        legal = true;
                        whatsUp = whatsUp + "\nPlayer " + playerID + " has lost their " +
                                chosen.nameRank() + ".";
                    } else {
                        gameboard[chosenX][chosenY + 1].setStatus(false);  //they died
                        gameboard[chosenX][chosenY] = null;  //empty the space you were just in
                        chosen.setyLoc(chosenY + 1);  //move into opponent's space
                        gameboard[chosenX][chosenY + 1] = chosen;  //report location to array
                        legal = true;
                        whatsUp = whatsUp + "\nPlayer " + playerID +
                                " has triumphed in a battle with their " + chosen.nameRank() + ".";
                    }
                } else {
                    whatsUp = whatsUp + "\nUnit " + chosen + " can't move there.";
                    legal = false;
                }
                break;
            //End case 2

            case 3:  //aka "left"
                if (chosenX - 1 >= 0 && gameboard[chosenX - 1][chosenY] == null) {
                    chosen.setxLoc(chosenX - 1);
                    gameboard[chosenX - 1][chosenY] = chosen;
                    legal = true;
                    whatsUp = whatsUp + "\nPlayer " + playerID + " has moved their " +
                            chosen + " left.";
                } else if (gameboard[chosenX - 1][chosenY].getRank() == Unit.WATER) {
                    legal = false;
                    whatsUp = whatsUp + "\nPlayer " + playerID + " tried to swim, but got scared.";
                } else if (gameboard[chosenX][chosenY] != null && gameboard[chosenX - 1][chosenY].getOwnerID() != playerID) {
                    //attack
                    int opponentRank = gameboard[chosenX - 1][chosenY].getRank();

                    if (opponentRank > chosen.getRank()) {
                        chosen.setStatus(false);  //you died
                        gameboard[chosenX][chosenY] = null;
                        legal = true;
                        whatsUp = whatsUp + "\nPlayer " + playerID + " has lost their "
                                + chosen.nameRank() + ".";
                    } else {
                        gameboard[chosenX - 1][chosenY].setStatus(false);  //they died
                        gameboard[chosenX][chosenY] = null;  //empty your spot
                        chosen.setxLoc(chosenX - 1);
                        gameboard[chosenX - 1][chosenY] = chosen;  //take their spot
                        legal = true;
                        whatsUp = whatsUp + "\nPlayer " + playerID +
                                " has triumphed in a battle with their " + chosen.nameRank() + ".";
                    }
                } else {
                    whatsUp = whatsUp + "\nUnit " + chosen.nameRank() + " can't move there.";
                    legal = false;
                }
                break;
            //End case 3

            case 4:  //aka "right"
                if (chosenX + 1 <= 9) {
                    if (chosenX + 1 <= 9 && gameboard[chosenX + 1][chosenY] == null) {
                        chosen.setxLoc(chosenX + 1);
                        gameboard[chosenX + 1][chosenY] = chosen;
                        legal = true;
                        whatsUp = whatsUp + "\nPlayer " + playerID + " has moved their " +
                                chosen.nameRank() + " right.";
                    } else if (gameboard[chosenX + 1][chosenY].getRank() == Unit.WATER) {
                        legal = false;
                        whatsUp = whatsUp + "\nPlayer " + playerID + " tried to swim, but got scared.";
                    } else {
                        if (gameboard[chosenX][chosenY] != null && gameboard[chosenX + 1][chosenY].getOwnerID() != playerID) {
                            //attack
                            int opponentRank = gameboard[chosenX + 1][chosenY].getRank();

                            if (opponentRank > chosen.getRank()) {
                                chosen.setStatus(false);  //you died
                                gameboard[chosenX][chosenY] = null;
                                whatsUp = whatsUp + "\nPlayer " + playerID + " has lost their "
                                        + chosen.nameRank() + ".";
                                legal = true;
                            } else if (opponentRank <= chosen.getRank()) {
                                gameboard[chosenX + 1][chosenY].setStatus(false);  //they died
                                gameboard[chosenX][chosenY] = null;  //empty your space
                                chosen.setxLoc(chosenX + 1);
                                gameboard[chosenX + 1][chosenY] = chosen;  //take theirs
                                legal = true;
                                whatsUp = whatsUp + "\nPlayer " + playerID +
                                        " has triumphed in a battle with their " + chosen.nameRank() + ".";
                            } else {
                                whatsUp = whatsUp + "\nWhat happened to your opponent?";
                                legal = false;
                            }
                        } else {
                            whatsUp = whatsUp + "\nUnit " + chosen.nameRank() + " can't move there.";
                            legal = false;
                        }

                    }
                }
                break;
            //End of case 4

            default:
                legal = false;
                break;
            //End of default case
        }//End switch-case

        return legal;
    }//movePiece


    /**
     * selectPiece
     *
     *
     * @param playerID   the id of the player attempting to choose
     * @param chosenP    the Unit being selected
     */
    public boolean selectPiece(int playerID, Unit chosenP) {
        if (chosenP.getOwnerID() == playerID){
            clearSelection(playerID);  //sets all Units to false
            chosenP.setSelected(true); //sets selection to true
            whatsUp = whatsUp + "\nPlayer " + playerID + " selected their " + chosenP.nameRank() + ".";
            return true;
        }
        else {
            whatsUp = whatsUp + "\nPlayer " + playerID + " has failed to select opponent's "
                    + chosenP.nameRank() + ".";
            return false;
        }
    }//selectPiece


    /**
     * clearSelection
     *
     * sets the isSelected value of all Units in the player's "hand" to false
     *
     * @param playerId  the ID of the user attempting to make a selection
     */
    public void clearSelection(int playerId) {
        switch (playerId) {
            case 0:
                for(int i= 0; i < p1Troops.size(); i++) {
                    p1Troops.get(i).setSelected(false);
                }
                break;
            case 1:
                for(int i= 0; i < p2Troops.size(); i++) {
                    p2Troops.get(i).setSelected(false);
                }
                break;
        }
        whatsUp = whatsUp + "\nPlayer " + playerId + " has cleared their choice.";
    }//clearSelection

    /**
     * placePiece
     *
     * meant for the beginning stage of the game, when players
     * move their pieces from the starting location (graveyard) and onto the board
     *
     * @param playerID  the id of the player making the move
     * @param unit      the unit they're moving
     * @param x         x coord of new location
     * @param y         y coord of new location
     * @return          true if alive and movement is valid, false if not
     */
    public boolean placePiece(int playerID, Unit unit, int x, int y) {
        if (unit.getStatus()) {
            if (playerID == 0 && y < 4) {  //< 4 is for boundary purposes, ensures piece is on your side
                unit.setxLoc(x);
                unit.setxLoc(y);
                gameboard[x][y] = unit;
                whatsUp = whatsUp + "\nPlayer " + playerID + " has placed their " + unit.nameRank() + ".";
                return true;
            }
            else if (playerID == 1 && y > 5) {
                unit.setxLoc(x);
                unit.setxLoc(y);
                gameboard[x][y] = unit;
                whatsUp = whatsUp + "\nPlayer " + playerID + " has placed their " + unit.nameRank() + ".";
                return true;
            }
            else {
                whatsUp = whatsUp + "\nCan't place pieces in enemy territory.";
                return false;
            }
        }
        else {
            whatsUp = whatsUp + "\nPlayer " + playerID + " did... something wrong.";
            return false;
        }
    }//placePiece

    /**
     * getUnit
     *
     * @param id    the id of the player whose "hand" you want to access
     * @param index the index you want to access
     * @return      the unit at the given index in the player's "hand"
     */
    public Unit getUnit(int id, int index) {
        if (id == 0){
            return p1Troops.get(index);
        }
        else {
            return p2Troops.get(index);
        }
    }//getUnit

    /**
     * getTurn
     *
     *
     * @return      return the round number
     */

    public int getTurn()
    {
        return this.roundNumber;
    }

}//StrategoGameState.

