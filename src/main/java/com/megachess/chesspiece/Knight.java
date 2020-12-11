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
public class Knight extends ChessPiece{

    public Knight(int row, int col, String color){
        super(row, col, color);
        this.pointsForMove = 30; //Points for kill and for move will be used to have minmax algorithm start with potencially better moves.
        this.pointsForKill = 300;
        this.minMaxValueBase = 30; //Base value used to evaluate the board
        this.minMaxValueCorrected = this.minMaxValueBase + this.positionBias(row, col); //Corrected value based on position used to evaluate the board
    }

    public int[] moveUpAndRight(List<List<ChessPiece>> Board){
        if(this.currentRow > 1 && this.currentCol < 15){
            if(Board.get(this.currentRow - 2).get(this.currentCol + 1).isNull()){
                return new int[]{this.currentRow, this.currentCol, this.currentRow - 2, this.currentCol + 1, this.pointsForMove};
            }else if(!Board.get(this.currentRow - 2).get(this.currentCol + 1).getColor().equals(this.color)){
                return new int[]{this.currentRow, this.currentCol, this.currentRow - 2, this.currentCol + 1, Board.get(this.currentRow - 2).get(this.currentCol + 1).getPointsForKill()};
            }
        }
        return null;
    }
    
    public int[] moveRightAndUp(List<List<ChessPiece>> Board){
        if(this.currentRow > 0 && this.currentCol < 14){
            if(Board.get(this.currentRow - 1).get(this.currentCol + 2).isNull()){
                return new int[]{this.currentRow, this.currentCol, this.currentRow - 1, this.currentCol + 2, this.pointsForMove};
            }else if(!Board.get(this.currentRow - 1).get(this.currentCol + 2).getColor().equals(this.color)){
                return new int[]{this.currentRow, this.currentCol, this.currentRow - 1, this.currentCol + 2, Board.get(this.currentRow - 1).get(this.currentCol + 2).getPointsForKill()};
            }
        }
        return null;
    }

    public int[] moveRightAndDown(List<List<ChessPiece>> Board){
        if(this.currentRow < 15 && this.currentCol < 14){
            if(Board.get(this.currentRow + 1).get(this.currentCol + 2).isNull()){
                return new int[]{this.currentRow, this.currentCol, this.currentRow + 1, this.currentCol + 2, this.pointsForMove};
            }else if(!Board.get(this.currentRow + 1).get(this.currentCol + 2).getColor().equals(this.color)){
                return new int[]{this.currentRow, this.currentCol, this.currentRow + 1, this.currentCol + 2, Board.get(this.currentRow + 1).get(this.currentCol + 2).getPointsForKill()};
            }
        }
        return null;
    }
    
    public int[] moveDownAndRight(List<List<ChessPiece>> Board){
        if(this.currentRow < 14 && this.currentCol < 15){
            if(Board.get(this.currentRow + 2).get(this.currentCol + 1).isNull()){
                return new int[]{this.currentRow, this.currentCol, this.currentRow + 2, this.currentCol + 1, this.pointsForMove};
            }else if(!Board.get(this.currentRow + 2).get(this.currentCol + 1).getColor().equals(this.color)){
                return new int[]{this.currentRow, this.currentCol, this.currentRow + 2, this.currentCol + 1, Board.get(this.currentRow + 2).get(this.currentCol + 1).getPointsForKill()};
            }
        }
        return null;
    }

    public int[] moveDownAndLeft(List<List<ChessPiece>> Board){
        if(this.currentRow < 14 && this.currentCol > 0){
            if(Board.get(this.currentRow + 2).get(this.currentCol - 1).isNull()){
                return new int[]{this.currentRow, this.currentCol, this.currentRow + 2, this.currentCol - 1, this.pointsForMove};
            }else if(!Board.get(this.currentRow + 2).get(this.currentCol - 1).getColor().equals(this.color)){
                return new int[]{this.currentRow, this.currentCol, this.currentRow + 2, this.currentCol - 1, Board.get(this.currentRow + 2).get(this.currentCol - 1).getPointsForKill()};
            }
        }
        return null;
    }
    
    public int[] moveLeftAndDown(List<List<ChessPiece>> Board){
        if(this.currentRow < 15 && this.currentCol > 1){
            if(Board.get(this.currentRow + 1).get(this.currentCol - 2).isNull()){
                return new int[]{this.currentRow, this.currentCol, this.currentRow + 1, this.currentCol - 2, this.pointsForMove};
            }else if(!Board.get(this.currentRow + 1).get(this.currentCol - 2).getColor().equals(this.color)){
                return new int[]{this.currentRow, this.currentCol, this.currentRow + 1, this.currentCol - 2, Board.get(this.currentRow + 1).get(this.currentCol - 2).getPointsForKill()};
            }
        }
        return null;
    }
    
    public int[] moveLeftAndUp(List<List<ChessPiece>> Board){
        if(this.currentRow > 0 && this.currentCol > 1){
            if(Board.get(this.currentRow - 1).get(this.currentCol - 2).isNull()){
                return new int[]{this.currentRow, this.currentCol, this.currentRow - 1, this.currentCol - 2, this.pointsForMove};
            }else if(!Board.get(this.currentRow - 1).get(this.currentCol - 2).getColor().equals(this.color)){
                return new int[]{this.currentRow, this.currentCol, this.currentRow - 1, this.currentCol - 2, Board.get(this.currentRow - 1).get(this.currentCol - 2).getPointsForKill()};
            }
        }
        return null;
    }    
    
    public int[] moveUpAndLeft(List<List<ChessPiece>> Board){
        if(this.currentRow > 1 && this.currentCol > 0){
            if(Board.get(this.currentRow - 2).get(this.currentCol - 1).isNull()){
                return new int[]{this.currentRow, this.currentCol, this.currentRow - 2, this.currentCol - 1, this.pointsForMove};
            }else if(!Board.get(this.currentRow - 2).get(this.currentCol - 1).getColor().equals(this.color)){
                return new int[]{this.currentRow, this.currentCol, this.currentRow - 2, this.currentCol - 1, Board.get(this.currentRow - 2).get(this.currentCol - 1).getPointsForKill()};
            }
        }
        return null;
    }    
    
    @Override
    public void calculatePossibleMoves(List<List<ChessPiece>> Board) {
        
        this.AddIfNotNull(moveDownAndLeft(Board));
        this.AddIfNotNull(moveDownAndRight(Board));
        this.AddIfNotNull(moveRightAndDown(Board));
        this.AddIfNotNull(moveRightAndUp(Board));
        this.AddIfNotNull(moveUpAndRight(Board));
        this.AddIfNotNull(moveUpAndLeft(Board));
        this.AddIfNotNull(moveLeftAndUp(Board));
        this.AddIfNotNull(moveLeftAndDown(Board));
        
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public int positionBias(int row, int col) {
        return 0;
    }
    
    @Override
    public ChessPiece copy() {
        return new Knight(this.currentRow, this.currentCol, this.color);
    }    
    
}
