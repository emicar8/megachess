/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megachess.chesspiece;

import java.util.List;

/**
 *
 * @author Emile
 */
public class Pawn extends ChessPiece{
    
    public Pawn(int row, int col, String color){
        super(row, col, color);
        if(Math.abs(7.5 - (double)row) > 1.5){ //Check if in range of promotion on next move.
            this.pointsForMove = 80; //Not in range, but still incentivized over other movements
        }else{
            this.pointsForMove = 500; //In range, promote is worth 500 points.
        }
        this.pointsForKill = 100; //Points for kill and for move will be used to have minmax algorithm start with potencially better moves.
        this.minMaxValueBase = 10; //Base value used to evaluate the board
        this.minMaxValueCorrected = 0; //Corrected value based on position used to evaluate the board      
    } 
    
    public int[] moveUpOnce(List<List<ChessPiece>> Board){ 
        if(Board.get(this.currentRow - 1).get(this.currentCol).isNull()){
            return new int[] {this.currentRow, this.currentCol, this.currentRow - 1, this.currentCol, this.pointsForMove + 8 - (this.currentRow - 1)};
        }
        return null;
    }
    
    public int[] moveUpTwice(List<List<ChessPiece>> Board){
        if(this.currentRow == 12 || this.currentRow == 13){
            if(Board.get(this.currentRow - 1).get(this.currentCol).isNull() && Board.get(this.currentRow - 2).get(this.currentCol).isNull()){
                return new int[] {this.currentRow, this.currentCol, this.currentRow - 2, this.currentCol, this.pointsForMove + 8 - (this.currentRow - 2)};
            }            
        }
        return null;      
    }
    
    public int[] moveDownOnce(List<List<ChessPiece>> Board){
        if(Board.get(this.currentRow + 1).get(this.currentCol).isNull()){
            return new int[] {this.currentRow, this.currentCol, this.currentRow + 1, this.currentCol, this.pointsForMove - 7 + this.currentRow + 1};
        }
        return null;  
    }
    
    public int[] moveDownTwice(List<List<ChessPiece>> Board){
        if(this.currentRow == 2 || this.currentRow == 3){
            if(Board.get(this.currentRow + 1).get(this.currentCol).isNull() && Board.get(this.currentRow + 2).get(this.currentCol).isNull()){
                return new int[] {this.currentRow, this.currentCol, this.currentRow + 2, this.currentCol, this.pointsForMove - 7 + this.currentRow + 2};
            }   
        }
        return null;            
    }  
    
    public int[] moveUpAndRight(List<List<ChessPiece>> Board){
        if(this.currentCol < 15){
            if(Board.get(this.currentRow - 1).get(this.currentCol + 1).getColor().equals("black")){
                return new int[] {this.currentRow, this.currentCol, this.currentRow - 1, this.currentCol + 1, Board.get(this.currentRow - 1).get(this.currentCol + 1).getPointsForKill()};
            }
        }
        return null;
    }
    
    public int[] moveUpAndLeft(List<List<ChessPiece>> Board){
        if(this.currentCol > 0){
            if(Board.get(this.currentRow - 1).get(this.currentCol - 1).getColor().equals("black")){
                return new int[] {this.currentRow, this.currentCol, this.currentRow - 1, this.currentCol - 1, Board.get(this.currentRow - 1).get(this.currentCol - 1).getPointsForKill()};
            }
        }
        return null;       
    }

    public int[] moveDownAndRight(List<List<ChessPiece>> Board){
        if(this.currentCol < 15){
            if(Board.get(this.currentRow + 1).get(this.currentCol + 1).getColor().equals("white")){
                return new int[] {this.currentRow, this.currentCol, this.currentRow + 1, this.currentCol + 1, Board.get(this.currentRow + 1).get(this.currentCol + 1).getPointsForKill()};
            }
        }
        return null;   
    }
    
    public int[] moveDownAndLeft(List<List<ChessPiece>> Board){
        if(this.currentCol > 0){
            if(Board.get(this.currentRow + 1).get(this.currentCol - 1).getColor().equals("white")){
                return new int[] {this.currentRow, this.currentCol, this.currentRow + 1, this.currentCol - 1, Board.get(this.currentRow + 1).get(this.currentCol - 1).getPointsForKill()};
            }
        }
        return null;  
    }       

    @Override
    public void calculatePossibleMoves(List<List<ChessPiece>> Board){
        switch (this.color) {
            case "black":
                this.AddIfNotNull(moveDownOnce(Board));
                this.AddIfNotNull(moveDownTwice(Board));
                this.AddIfNotNull(moveDownAndRight(Board));
                this.AddIfNotNull(moveDownAndLeft(Board));
                break;
            case "white":
                this.AddIfNotNull(moveUpOnce(Board));
                this.AddIfNotNull(moveUpTwice(Board));
                this.AddIfNotNull(moveUpAndRight(Board));
                this.AddIfNotNull(moveUpAndLeft(Board));
                break;         
            default:
                break;  
        }
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public int positionBias() {
       int bias = 0;
        if(this.currentRow >= 5 && this.currentRow <= 10){
            bias += 10;
            if(this.currentCol >= 5 && this.currentCol <= 10){
                bias += 10;
            }
        }
            

        return bias;
    }
    
    @Override
    public ChessPiece copy() {
        return new Pawn(this.currentRow, this.currentCol, this.color);
    }    
    
}
