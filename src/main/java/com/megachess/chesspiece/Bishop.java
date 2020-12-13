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
public class Bishop extends ChessPiece{

    public Bishop(int row, int col, String color){
        super(row, col, color);
        this.pointsForMove = 40; //Points for kill and for move will be used to have minmax algorithm start with potencially better moves.
        this.pointsForKill = 400;
        this.minMaxValueBase = 30; //Base value used to evaluate the board
        this.minMaxValueCorrected = this.minMaxValueBase; //Corrected value based on position used to evaluate the board
    }    

     public void moveRightAndDown(int newRow, int newCol, List<List<ChessPiece>> Board){
        if(newRow < 16 && newCol < 16){
            if(!Board.get(newRow).get(newCol).isNull()){ //Position is not empty
                if(!Board.get(newRow).get(newCol).getColor().equals(this.color)){ //Check if piece is different color
                    this.possibleMoves.add(new int[]{this.currentRow, this.currentCol, newRow, newCol, Board.get(newRow).get(newCol).getPointsForKill()}); //Piece is not same color                  
                }                 
            }else{ //Can keep moving.
                this.possibleMoves.add(new int[]{this.currentRow, this.currentCol, newRow, newCol, this.getPointsForMove()});
                this.moveRightAndDown(newRow + 1, newCol + 1, Board);
            }
        }
    }    
    
      public void moveRightAndUp(int newRow, int newCol, List<List<ChessPiece>> Board){
        if(newRow > -1 && newCol < 16){
            if(!Board.get(newRow).get(newCol).isNull()){ //Position is not empty
                if(!Board.get(newRow).get(newCol).getColor().equals(this.color)){ //Check if piece is different color
                    this.possibleMoves.add(new int[]{this.currentRow, this.currentCol, newRow, newCol, Board.get(newRow).get(newCol).getPointsForKill()}); //Piece is not same color                  
                }               
            }else{ //Can keep moving.
                this.possibleMoves.add(new int[]{this.currentRow, this.currentCol, newRow, newCol, this.getPointsForMove()});
                this.moveRightAndUp(newRow - 1, newCol + 1, Board);
            }
        }
    }  
      
     public void moveLeftAndUp(int newRow, int newCol, List<List<ChessPiece>> Board){
        if(newRow > -1 && newCol > -1){
            if(!Board.get(newRow).get(newCol).isNull()){ //Position is not empty
                if(!Board.get(newRow).get(newCol).getColor().equals(this.color)){ //Check if piece is different color
                    this.possibleMoves.add(new int[]{this.currentRow, this.currentCol, newRow, newCol, Board.get(newRow).get(newCol).getPointsForKill()}); //Piece is not same color                  
                }                 
            }else{ //Can keep moving.
                this.possibleMoves.add(new int[]{this.currentRow, this.currentCol, newRow, newCol, this.getPointsForMove()});
                this.moveLeftAndUp(newRow - 1, newCol - 1, Board);
            }
        }
    }        

     public void moveLeftAndDown(int newRow, int newCol, List<List<ChessPiece>> Board){
        if(newRow < 16 && newCol > -1){
            if(!Board.get(newRow).get(newCol).isNull()){ //Position is not empty
                if(!Board.get(newRow).get(newCol).getColor().equals(this.color)){ //Check if piece is different color
                    this.possibleMoves.add(new int[]{this.currentRow, this.currentCol, newRow, newCol, Board.get(newRow).get(newCol).getPointsForKill()}); //Piece is not same color                  
                }                 
            }else{ //Can keep moving.
                this.possibleMoves.add(new int[]{this.currentRow, this.currentCol, newRow, newCol, this.getPointsForMove()});
                this.moveLeftAndDown(newRow + 1, newCol - 1, Board);
            }
        }
    }
    
    @Override
    public void calculatePossibleMoves(List<List<ChessPiece>> Board) {
        this.moveLeftAndDown(this.currentRow + 1, this.currentCol - 1, Board);
        this.moveLeftAndUp(this.currentRow - 1, this.currentCol - 1, Board);
        this.moveRightAndUp(this.currentRow - 1, this.currentCol + 1, Board);
        this.moveRightAndDown(this.currentRow + 1, this.currentCol + 1, Board);      
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public int positionBias() {
        return 0;
    }

    @Override
    public ChessPiece copy() {
        return new Bishop(this.currentRow, this.currentCol, this.color);
    }
    
}
