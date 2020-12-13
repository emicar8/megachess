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
public class Rook extends ChessPiece{
    
    public Rook(int row, int col, String color){
        super(row, col, color);
        this.pointsForMove = 60; //Points for kill and for move will be used to have minmax algorithm start with potencially better moves.
        this.pointsForKill = 600;
        this.minMaxValueBase = 40; //Base value used to evaluate the board
        this.minMaxValueCorrected = this.minMaxValueBase; //Corrected value based on position used to evaluate the board       
    }    
    
    
    public void moveRight(int newRow, int newCol, List<List<ChessPiece>> Board){
        if(newCol < 16){
            if(!Board.get(newRow).get(newCol).isNull()){ //Position is not empty
                if(!Board.get(newRow).get(newCol).getColor().equals(this.color)){ //Check if piece is different color
                    this.possibleMoves.add(new int[]{this.currentRow, this.currentCol, newRow, newCol, Board.get(newRow).get(newCol).getPointsForKill()}); //Piece is not same color                  
                }                
            }else{ //Can keep moving.
                this.possibleMoves.add(new int[]{this.currentRow, this.currentCol, newRow, newCol, this.getPointsForMove()});
                this.moveRight(newRow, newCol + 1, Board);
            }
        }
    }
    
    public void moveDown(int newRow, int newCol, List<List<ChessPiece>> Board){
        if(newRow < 16){
            if(!Board.get(newRow).get(newCol).isNull()){ //Position is not empty
                if(!Board.get(newRow).get(newCol).getColor().equals(this.color)){ //Check if piece is different color
                    this.possibleMoves.add(new int[]{this.currentRow, this.currentCol, newRow, newCol, Board.get(newRow).get(newCol).getPointsForKill()}); //Piece is not same color                  
                }               
            }else{ //Can keep moving.
                this.possibleMoves.add(new int[]{this.currentRow, this.currentCol, newRow, newCol, this.getPointsForMove()});
                this.moveDown(newRow + 1, newCol, Board);
            }
        }
    }     

    public void moveLeft(int newRow, int newCol, List<List<ChessPiece>> Board){
        if(newCol > -1){
            if(!Board.get(newRow).get(newCol).isNull()){ //Position is not empty
                if(!Board.get(newRow).get(newCol).getColor().equals(this.color)){ //Check if piece is different color
                    this.possibleMoves.add(new int[]{this.currentRow, this.currentCol, newRow, newCol, Board.get(newRow).get(newCol).getPointsForKill()}); //Piece is not same color                  
                }                 
            }else{ //Can keep moving.
                this.possibleMoves.add(new int[]{this.currentRow, this.currentCol, newRow, newCol, this.getPointsForMove()});
                this.moveLeft(newRow, newCol - 1, Board);
            }
        }
    }
       
    public void moveUp(int newRow, int newCol, List<List<ChessPiece>> Board){
        if(newRow > -1){
            if(!Board.get(newRow).get(newCol).isNull()){ //Position is not empty
                if(!Board.get(newRow).get(newCol).getColor().equals(this.color)){ //Check if piece is different color
                    this.possibleMoves.add(new int[]{this.currentRow, this.currentCol, newRow, newCol, Board.get(newRow).get(newCol).getPointsForKill()}); //Piece is not same color                  
                }               
            }else{ //Can keep moving.
                this.possibleMoves.add(new int[]{this.currentRow, this.currentCol, newRow, newCol, this.getPointsForMove()});
                this.moveUp(newRow - 1, newCol, Board);
            }
        }
    } 
    
    @Override
    public void calculatePossibleMoves(List<List<ChessPiece>> Board) {
        this.moveDown(this.currentRow + 1, this.currentCol, Board);
        this.moveRight(this.currentRow, this.currentCol + 1, Board);
        this.moveLeft(this.currentRow, this.currentCol - 1, Board);
        this.moveUp(this.currentRow - 1, this.currentCol, Board);
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public int positionBias() {
        return 0; //TODO
    }
    
    @Override
    public ChessPiece copy() {
        return new Rook(this.currentRow, this.currentCol, this.color);
    }    
    
}
