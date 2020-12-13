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
public class King extends ChessPiece{

    public King(int row, int col, String color){
        super(row, col, color);
        this.pointsForMove = 65;  //Points for kill and for move will be used to have minmax algorithm start with potencially better moves.
        this.pointsForKill = 1000;
        this.minMaxValueBase = 100; //Base value used to evaluate the board
        this.minMaxValueCorrected = this.minMaxValueBase; //Corrected value based on position used to evaluate the board
    }    

    //Straight moves
    
    public int[] moveDown(List<List<ChessPiece>> Board){
        if(this.currentRow < 15){
            if(Board.get(this.currentRow + 1).get(this.currentCol).isNull()){
                return new int[]{this.currentRow, this.currentCol, this.currentRow + 1, this.currentCol, this.getPointsForMove()};
            }else if(!Board.get(this.currentRow + 1).get(this.currentCol).getColor().equals(this.color)){
                return new int[]{this.currentRow, this.currentCol, this.currentRow + 1, this.currentCol, Board.get(this.currentRow + 1).get(this.currentCol).getPointsForKill()};
            }            
        }
        return null;
    }   
  
    public int[] moveLeft(List<List<ChessPiece>> Board){
        if(this.currentCol > 0){
            if(Board.get(this.currentRow).get(this.currentCol - 1).isNull()){
                return new int[]{this.currentRow, this.currentCol, this.currentRow, this.currentCol - 1, this.getPointsForMove()};
            }else if(!Board.get(this.currentRow).get(this.currentCol - 1).getColor().equals(this.color)){
                return new int[]{this.currentRow, this.currentCol, this.currentRow, this.currentCol - 1, Board.get(this.currentRow).get(this.currentCol - 1).getPointsForKill()};
            }            
        }
        return null;
    }   

    public int[] moveUp(List<List<ChessPiece>> Board){
        if(this.currentRow > 0){
            if(Board.get(this.currentRow - 1).get(this.currentCol).isNull()){
                return new int[]{this.currentRow, this.currentCol, this.currentRow - 1, this.currentCol, this.getPointsForMove()};
            }else if(!Board.get(this.currentRow - 1).get(this.currentCol).getColor().equals(this.color)){
                return new int[]{this.currentRow, this.currentCol, this.currentRow - 1, this.currentCol, Board.get(this.currentRow - 1).get(this.currentCol).getPointsForKill()};
            }            
        }
        return null;
    }       
    
    public int[] moveRight(List<List<ChessPiece>> Board){
        if(this.currentCol < 15){
            if(Board.get(this.currentRow).get(this.currentCol + 1).isNull()){
                return new int[]{this.currentRow, this.currentCol, this.currentRow, this.currentCol + 1, this.getPointsForMove()};
            }else if(!Board.get(this.currentRow).get(this.currentCol + 1).getColor().equals(this.color)){
                return new int[]{this.currentRow, this.currentCol, this.currentRow, this.currentCol + 1, Board.get(this.currentRow).get(this.currentCol + 1).getPointsForKill()};
            }            
        }
        return null;
    }
    
    //Diagonal moves
    
    public int[] moveRightAndDown(List<List<ChessPiece>> Board){
        if(this.currentRow < 15 && this.currentCol < 15){
            if(Board.get(this.currentRow + 1).get(this.currentCol + 1).isNull()){
                return new int[]{this.currentRow, this.currentCol, this.currentRow + 1, this.currentCol + 1, this.getPointsForMove()};
            }else if(!Board.get(this.currentRow + 1).get(this.currentCol + 1).getColor().equals(this.color)){
                return new int[]{this.currentRow, this.currentCol, this.currentRow + 1, this.currentCol + 1, Board.get(this.currentRow + 1).get(this.currentCol + 1).getPointsForKill()};
            }            
        }
        return null;
    }   
  
    public int[] moveLeftAndDown(List<List<ChessPiece>> Board){
        if(this.currentRow < 15 && this.currentCol > 0){
            if(Board.get(this.currentRow + 1).get(this.currentCol - 1).isNull()){
                return new int[]{this.currentRow, this.currentCol, this.currentRow + 1, this.currentCol - 1, this.getPointsForMove()};
            }else if(!Board.get(this.currentRow + 1).get(this.currentCol - 1).getColor().equals(this.color)){
                return new int[]{this.currentRow, this.currentCol, this.currentRow + 1, this.currentCol - 1, Board.get(this.currentRow + 1).get(this.currentCol - 1).getPointsForKill()};
            }            
        }
        return null;
    }   

    public int[] moveLeftAndUp(List<List<ChessPiece>> Board){
        if(this.currentRow > 0 && this.currentCol > 0){
            if(Board.get(this.currentRow - 1).get(this.currentCol - 1).isNull()){
                return new int[]{this.currentRow, this.currentCol, this.currentRow - 1, this.currentCol - 1, this.getPointsForMove()};
            }else if(!Board.get(this.currentRow - 1).get(this.currentCol - 1).getColor().equals(this.color)){
                return new int[]{this.currentRow, this.currentCol, this.currentRow - 1, this.currentCol - 1, Board.get(this.currentRow - 1).get(this.currentCol - 1).getPointsForKill()};
            }            
        }
        return null;
    }       
    
    public int[] moveRightAndUp(List<List<ChessPiece>> Board){
        if(this.currentRow > 0 && this.currentCol < 15){
            if(Board.get(this.currentRow - 1).get(this.currentCol + 1).isNull()){
                return new int[]{this.currentRow, this.currentCol, this.currentRow - 1, this.currentCol + 1, this.getPointsForMove()};
            }else if(!Board.get(this.currentRow - 1).get(this.currentCol + 1).getColor().equals(this.color)){
                return new int[]{this.currentRow, this.currentCol, this.currentRow - 1, this.currentCol + 1, Board.get(this.currentRow - 1).get(this.currentCol + 1).getPointsForKill()};
            }            
        }
        return null;
    }     
    
    @Override
    public void calculatePossibleMoves(List<List<ChessPiece>> Board) {       
        this.AddIfNotNull(moveDown(Board));
        this.AddIfNotNull(moveRight(Board));
        this.AddIfNotNull(moveLeft(Board));
        this.AddIfNotNull(moveUp(Board));
        this.AddIfNotNull(moveLeftAndDown(Board));
        this.AddIfNotNull(moveLeftAndUp(Board));      
        this.AddIfNotNull(moveRightAndUp(Board));  
        this.AddIfNotNull(moveRightAndDown(Board));
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
        return new King(this.currentRow, this.currentCol, this.color);
    }    
    
}
