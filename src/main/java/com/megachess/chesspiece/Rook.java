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
        this.pointsForMove = 60;
        this.pointsForKill = 600;
    }    
    
    
    private void moveRight(int newRow, int newCol, List<List<ChessPiece>> Board){
        if(newCol < 16){
            if(!Board.get(newRow).get(newCol).isNull()){ //Position is not empty
                if(Board.get(newRow).get(newCol).getColor().equals(this.color)){ //Check if piece is same color
                    if(this.currentCol != newCol - 1){ //Avoid adding current position as possible move
                        this.possibleMoves.add(new int[]{this.currentRow, this.currentCol, newRow, newCol - 1, this.getPointsForMove()}); //Piece is same color
                    }                   
                }else{
                    this.possibleMoves.add(new int[]{this.currentRow, this.currentCol, newRow, newCol, Board.get(newRow).get(newCol).getPointsForKill()}); //Piece is not same color
                }                
            }else{ //Can keep moving.
                this.possibleMoves.add(new int[]{this.currentRow, this.currentCol, newRow, newCol, this.getPointsForMove()});
                this.moveRight(newRow, newCol + 1, Board);
            }
        }
    }
    
    private void moveDown(int newRow, int newCol, List<List<ChessPiece>> Board){
        if(newRow < 16){
            if(!Board.get(newRow).get(newCol).isNull()){ //Position is not empty
                if(Board.get(newRow).get(newCol).getColor().equals(this.color)){ //Check if piece is same color
                    if(this.currentRow != newRow - 1){ //Avoid adding current position as possible move
                        this.possibleMoves.add(new int[]{this.currentRow, this.currentCol, newRow - 1, newCol, this.getPointsForMove()}); //Piece is same color
                    }                    
                }else{
                    this.possibleMoves.add(new int[]{this.currentRow, this.currentCol, newRow, newCol, Board.get(newRow).get(newCol).getPointsForKill()}); //Piece is not same color
                }                
            }else{ //Can keep moving.
                this.possibleMoves.add(new int[]{this.currentRow, this.currentCol, newRow, newCol, this.getPointsForMove()});
                this.moveDown(newRow + 1, newCol, Board);
            }
        }
    }     

    private void moveLeft(int newRow, int newCol, List<List<ChessPiece>> Board){
        if(newCol > -1){
            if(!Board.get(newRow).get(newCol).isNull()){ //Position is not empty
                if(Board.get(newRow).get(newCol).getColor().equals(this.color)){ //Check if piece is same color
                    if(this.currentCol != newCol + 1){ //Avoid adding current position as possible move
                        this.possibleMoves.add(new int[]{this.currentRow, this.currentCol, newRow, newCol + 1, this.getPointsForMove()}); //Piece is same color
                    }                   
                }else{
                    this.possibleMoves.add(new int[]{this.currentRow, this.currentCol, newRow, newCol, Board.get(newRow).get(newCol).getPointsForKill()}); //Piece is not same color
                }                
            }else{ //Can keep moving.
                this.possibleMoves.add(new int[]{this.currentRow, this.currentCol, newRow, newCol, this.getPointsForMove()});
                this.moveLeft(newRow, newCol - 1, Board);
            }
        }
    }
       
    private void moveUp(int newRow, int newCol, List<List<ChessPiece>> Board){
        if(newRow > -1){
            if(!Board.get(newRow).get(newCol).isNull()){ //Position is not empty
                if(Board.get(newRow).get(newCol).getColor().equals(this.color)){ //Check if piece is same color
                    if(this.currentRow != newRow + 1){ //Avoid adding current position as possible move
                        this.possibleMoves.add(new int[]{this.currentRow, this.currentCol, newRow + 1, newCol, this.getPointsForMove()}); //Piece is same color
                    }                    
                }else{
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
    
}
