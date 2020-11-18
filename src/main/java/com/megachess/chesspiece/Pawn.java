/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megachess.chesspiece;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Emile
 */
public class Pawn extends ChessPiece{
    
    public Pawn(int col, int row, String color){
        super(col, row, color);
        this.pointsForMove = 10;
        this.pointsForKill = 10*this.pointsForMove;
    }

    @Override
    public void calculatePossibleMoves(List<List<ChessPiece>> Board) {
        switch (this.color) {
            case "black":
                //If pawn is black it must move down.
                if(Board.get(this.currentRow + 1).get(this.currentCol).isNull()){ //Check if free space in front
                    //Prioritize moving faster towards the center to promote.
                    this.possibleMoves.add(new int[] {this.currentRow, this.currentCol, this.currentRow + 1, this.currentCol, this.pointsForMove - 7 + this.currentRow + 1});
                    if(Board.get(this.currentRow + 2).get(this.currentCol).isNull() && (this.currentRow == 2 || this.currentRow == 3)){ //Check if two spaces in front is free and in valid position to move two spaces
                        //Prioritize moving faster towards the center to promote.
                        this.possibleMoves.add(new int[] {this.currentRow, this.currentCol, this.currentRow + 2, this.currentCol, this.pointsForMove - 7 + this.currentRow + 2});
                    }
                }
                break;
            case "white":
                //If pawn is white it must move up.
                if(Board.get(this.currentRow - 1).get(this.currentCol).isNull()){ //Check if free space in front
                    //Prioritize moving faster towards the center to promote.
                    this.possibleMoves.add(new int[] {this.currentRow, this.currentCol, this.currentRow - 1, this.currentCol, this.pointsForMove + 8 - (this.currentRow - 1)});
                    if(Board.get(this.currentRow - 2).get(this.currentCol).isNull() && (this.currentRow == 13 || this.currentRow == 12)){ //Check if two spaces in front is free and in valid position to move two spaces
                        //Prioritize moving faster towards the center to promote.
                        this.possibleMoves.add(new int[] {this.currentRow, this.currentCol, this.currentRow - 2, this.currentCol, this.pointsForMove + 8 - (this.currentRow - 2)});
                    }
                }
                break;            
            default:
                try {
                    throw new Exception("Color must be black or white");
                } catch (Exception ex) {
                    Logger.getLogger(Pawn.class.getName()).log(Level.SEVERE, null, ex);
                }   
                break;
        }
    }

    @Override
    public void calculatePossibleAttacks(List<List<ChessPiece>> Board) {
        switch (this.color) {
            case "black":
                switch(this.currentCol){
                    case 0: //Border position
                        if(!Board.get(this.currentRow + 1).get(this.currentCol + 1).isNull() && Board.get(this.currentRow + 1).get(this.currentCol + 1).getColor().equals("white")){ //Attacking space is not empty and piece is from rival.
                            this.possibleAttacks.add(new int[]{this.currentRow, this.currentCol, this.currentRow + 1, this.currentCol + 1, Board.get(this.currentRow + 1).get(this.currentCol + 1).getPointsForKill()});
                        }                        
                        break;
                    case 15: //Border position
                        if(!Board.get(this.currentRow + 1).get(this.currentCol - 1).isNull() && Board.get(this.currentRow + 1).get(this.currentCol - 1).getColor().equals("white")){ //Attacking space is not empty and piece is from rival.
                            this.possibleAttacks.add(new int[]{this.currentRow, this.currentCol, this.currentRow + 1, this.currentCol - 1, Board.get(this.currentRow + 1).get(this.currentCol - 1).getPointsForKill()});
                        } 
                        break;
                    default:
                        if(!Board.get(this.currentRow + 1).get(this.currentCol - 1).isNull() && Board.get(this.currentRow + 1).get(this.currentCol - 1).getColor().equals("white")){ //Attacking space is not empty and piece is from rival.
                            this.possibleAttacks.add(new int[]{this.currentRow, this.currentCol, this.currentRow + 1, this.currentCol - 1, Board.get(this.currentRow + 1).get(this.currentCol - 1).getPointsForKill()});
                        } 
                        if(!Board.get(this.currentRow + 1).get(this.currentCol + 1).isNull() && Board.get(this.currentRow + 1).get(this.currentCol + 1).getColor().equals("white")){ //Attacking space is not empty and piece is from rival.
                            this.possibleAttacks.add(new int[]{this.currentRow, this.currentCol, this.currentRow + 1, this.currentCol + 1, Board.get(this.currentRow + 1).get(this.currentCol + 1).getPointsForKill()});
                        }
                        break;
                }
                break;
            case "white":
                switch(this.currentCol){
                    case 0: //Border piece
                        if(!Board.get(this.currentRow - 1).get(this.currentCol + 1).isNull() && Board.get(this.currentRow - 1).get(this.currentCol + 1).getColor().equals("black")){ //Attacking space is not empty and piece is from rival.
                            this.possibleAttacks.add(new int[]{this.currentRow, this.currentCol, this.currentRow - 1, this.currentCol + 1, Board.get(this.currentRow - 1).get(this.currentCol + 1).getPointsForKill()});
                        }                        
                        break;
                    case 15: //Border piece
                        if(!Board.get(this.currentRow - 1).get(this.currentCol - 1).isNull() && Board.get(this.currentRow - 1).get(this.currentCol - 1).getColor().equals("black")){ //Attacking space is not empty and piece is from rival.
                            this.possibleAttacks.add(new int[]{this.currentRow, this.currentCol, this.currentRow - 1, this.currentCol - 1, Board.get(this.currentRow - 1).get(this.currentCol - 1).getPointsForKill()});
                        } 
                        break;
                    default:
                        if(!Board.get(this.currentRow - 1).get(this.currentCol - 1).isNull() && Board.get(this.currentRow - 1).get(this.currentCol - 1).getColor().equals("black")){ //Attacking space is not empty and piece is from rival.
                            this.possibleAttacks.add(new int[]{this.currentRow, this.currentCol, this.currentRow - 1, this.currentCol - 1, Board.get(this.currentRow - 1).get(this.currentCol - 1).getPointsForKill()});
                        } 
                        if(!Board.get(this.currentRow - 1).get(this.currentCol + 1).isNull() && Board.get(this.currentRow - 1).get(this.currentCol + 1).getColor().equals("black")){ //Attacking space is not empty and piece is from rival.
                            this.possibleAttacks.add(new int[]{this.currentRow, this.currentCol, this.currentRow - 1, this.currentCol + 1, Board.get(this.currentRow - 1).get(this.currentCol + 1).getPointsForKill()});
                        }   
                        break;
                }
                break;         
            default:
                try {
                    throw new Exception("Color must be black or white");
                } catch (Exception ex) {
                    Logger.getLogger(Pawn.class.getName()).log(Level.SEVERE, null, ex);
                }   
                break;
        }
    }

    @Override
    public boolean isNull() {
        return false;
    }
    
}
