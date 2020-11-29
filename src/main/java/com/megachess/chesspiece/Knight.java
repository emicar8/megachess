/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megachess.chesspiece;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Emile
 */
public class Knight extends ChessPiece{

    public Knight(int row, int col, String color){
        super(row, col, color);
        this.pointsForMove = 30;
        this.pointsForKill = 300;
    }

    private int[] moveUpAndRight(List<List<ChessPiece>> Board){
        if(this.currentRow > 1 && this.currentCol < 15){
            if(Board.get(this.currentRow - 2).get(this.currentCol + 1).isNull()){
                return new int[]{this.currentRow, this.currentCol, this.currentRow - 2, this.currentCol + 1, this.pointsForMove};
            }else if(!Board.get(this.currentRow - 2).get(this.currentCol + 1).getColor().equals(this.color)){
                return new int[]{this.currentRow, this.currentCol, this.currentRow - 2, this.currentCol + 1, Board.get(this.currentRow - 2).get(this.currentCol + 1).getPointsForKill()};
            }
        }
        return null;
    }
    
    private int[] moveRightAndUp(List<List<ChessPiece>> Board){
        if(this.currentRow > 0 && this.currentCol < 14){
            if(Board.get(this.currentRow - 1).get(this.currentCol + 2).isNull()){
                return new int[]{this.currentRow, this.currentCol, this.currentRow - 1, this.currentCol + 2, this.pointsForMove};
            }else if(!Board.get(this.currentRow - 1).get(this.currentCol + 2).getColor().equals(this.color)){
                return new int[]{this.currentRow, this.currentCol, this.currentRow - 1, this.currentCol + 2, Board.get(this.currentRow - 1).get(this.currentCol + 2).getPointsForKill()};
            }
        }
        return null;
    }

    private int[] moveRightAndDown(List<List<ChessPiece>> Board){
        if(this.currentRow < 15 && this.currentCol < 14){
            if(Board.get(this.currentRow + 1).get(this.currentCol + 2).isNull()){
                return new int[]{this.currentRow, this.currentCol, this.currentRow + 1, this.currentCol + 2, this.pointsForMove};
            }else if(!Board.get(this.currentRow + 1).get(this.currentCol + 2).getColor().equals(this.color)){
                return new int[]{this.currentRow, this.currentCol, this.currentRow + 1, this.currentCol + 2, Board.get(this.currentRow + 1).get(this.currentCol + 2).getPointsForKill()};
            }
        }
        return null;
    }
    
    private int[] moveDownAndRight(List<List<ChessPiece>> Board){
        if(this.currentRow < 14 && this.currentCol < 15){
            if(Board.get(this.currentRow + 2).get(this.currentCol + 1).isNull()){
                return new int[]{this.currentRow, this.currentCol, this.currentRow + 2, this.currentCol + 1, this.pointsForMove};
            }else if(!Board.get(this.currentRow + 2).get(this.currentCol + 1).getColor().equals(this.color)){
                return new int[]{this.currentRow, this.currentCol, this.currentRow + 2, this.currentCol + 1, Board.get(this.currentRow + 2).get(this.currentCol + 1).getPointsForKill()};
            }
        }
        return null;
    }

    private int[] moveDownAndLeft(List<List<ChessPiece>> Board){
        if(this.currentRow < 14 && this.currentCol > 0){
            if(Board.get(this.currentRow + 2).get(this.currentCol - 1).isNull()){
                return new int[]{this.currentRow, this.currentCol, this.currentRow + 2, this.currentCol - 1, this.pointsForMove};
            }else if(!Board.get(this.currentRow + 2).get(this.currentCol - 1).getColor().equals(this.color)){
                return new int[]{this.currentRow, this.currentCol, this.currentRow + 2, this.currentCol - 1, Board.get(this.currentRow + 2).get(this.currentCol - 1).getPointsForKill()};
            }
        }
        return null;
    }
    
    private int[] moveLeftAndDown(List<List<ChessPiece>> Board){
        if(this.currentRow < 15 && this.currentCol > 1){
            if(Board.get(this.currentRow + 1).get(this.currentCol - 2).isNull()){
                return new int[]{this.currentRow, this.currentCol, this.currentRow + 1, this.currentCol - 2, this.pointsForMove};
            }else if(!Board.get(this.currentRow + 1).get(this.currentCol - 2).getColor().equals(this.color)){
                return new int[]{this.currentRow, this.currentCol, this.currentRow + 1, this.currentCol - 2, Board.get(this.currentRow + 1).get(this.currentCol - 2).getPointsForKill()};
            }
        }
        return null;
    }
    
    private int[] moveLeftAndUp(List<List<ChessPiece>> Board){
        if(this.currentRow > 0 && this.currentCol > 1){
            if(Board.get(this.currentRow - 1).get(this.currentCol - 2).isNull()){
                return new int[]{this.currentRow, this.currentCol, this.currentRow - 1, this.currentCol - 2, this.pointsForMove};
            }else if(!Board.get(this.currentRow - 1).get(this.currentCol - 2).getColor().equals(this.color)){
                return new int[]{this.currentRow, this.currentCol, this.currentRow - 1, this.currentCol - 2, Board.get(this.currentRow - 1).get(this.currentCol - 2).getPointsForKill()};
            }
        }
        return null;
    }    
    
    private int[] moveUpAndLeft(List<List<ChessPiece>> Board){
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
    public void calculatePossibleAttacks(List<List<ChessPiece>> Board) {
        List<int[]> BaseAttacks = new ArrayList<>();
        
        //Knight basic moves
        BaseAttacks.add(new int[] {this.currentRow - 2, this.currentCol - 1}); //upwards and then left
        BaseAttacks.add(new int[] {this.currentRow - 2, this.currentCol + 1}); //upwards and then right
        BaseAttacks.add(new int[] {this.currentRow - 1, this.currentCol + 2}); //right and then upwards
        BaseAttacks.add(new int[] {this.currentRow + 1, this.currentCol + 2}); //right and then downwards
        BaseAttacks.add(new int[] {this.currentRow + 2, this.currentCol + 1}); //downwards and then right
        BaseAttacks.add(new int[] {this.currentRow + 2, this.currentCol - 1}); //downwards and then left
        BaseAttacks.add(new int[] {this.currentRow + 1, this.currentCol - 2}); //left and then downwards
        BaseAttacks.add(new int[] {this.currentRow - 1, this.currentCol - 2}); //left and then upwards
        
        for(int[] baseAttack : BaseAttacks){
            if(baseAttack[0] >= 0 && baseAttack[0] < 16 && baseAttack[1] >= 0 && baseAttack[1] < 16){
                if(!Board.get(baseAttack[0]).get(baseAttack[1]).isNull()){ //Not empty space
                    switch(this.color){
                        case "black":
                            if(Board.get(baseAttack[0]).get(baseAttack[1]).getColor().equals("white")){ //Check if enemy
                                this.possibleAttacks.add(new int[]{this.currentRow, this.currentCol, baseAttack[0], baseAttack[1], Board.get(baseAttack[0]).get(baseAttack[1]).getPointsForKill()});
                            }
                            break;
                        case "white":
                            if(Board.get(baseAttack[0]).get(baseAttack[1]).getColor().equals("black")){ //Check if enemy
                                this.possibleAttacks.add(new int[]{this.currentRow, this.currentCol, baseAttack[0], baseAttack[1], Board.get(baseAttack[0]).get(baseAttack[1]).getPointsForKill()});
                            }                            
                            break;
                        default:
                            try {
                                throw new Exception("Color must be black or white");
                            } catch (Exception ex) {
                                Logger.getLogger(King.class.getName()).log(Level.SEVERE, null, ex);
                            }   
                            break;
                    }
                }
            }
        }
    }

    @Override
    public boolean isNull() {
        return false;
    }
    
}
