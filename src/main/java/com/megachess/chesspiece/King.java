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
public class King extends ChessPiece{

    public King(int row, int col, String color){
        super(row, col, color);
        this.pointsForMove = 65; //Lower performance of king movement to incentivize pawn movement to promote.
        //this.pointsForKill = 650; //Lowered to target enemy queens more aggressively
        this.pointsForKill = 1000;
    }    

    //Straight moves
    
    private int[] moveDown(List<List<ChessPiece>> Board){
        if(this.currentRow < 15){
            if(Board.get(this.currentRow + 1).get(this.currentCol).isNull()){
                return new int[]{this.currentRow, this.currentCol, this.currentRow + 1, this.currentCol, this.getPointsForMove()};
            }else if(!Board.get(this.currentRow + 1).get(this.currentCol).getColor().equals(this.color)){
                return new int[]{this.currentRow, this.currentCol, this.currentRow + 1, this.currentCol, Board.get(this.currentRow + 1).get(this.currentCol).getPointsForKill()};
            }            
        }
        return null;
    }   
  
    private int[] moveLeft(List<List<ChessPiece>> Board){
        if(this.currentCol > 0){
            if(Board.get(this.currentRow).get(this.currentCol - 1).isNull()){
                return new int[]{this.currentRow, this.currentCol, this.currentRow, this.currentCol - 1, this.getPointsForMove()};
            }else if(!Board.get(this.currentRow).get(this.currentCol - 1).getColor().equals(this.color)){
                return new int[]{this.currentRow, this.currentCol, this.currentRow, this.currentCol - 1, Board.get(this.currentRow).get(this.currentCol - 1).getPointsForKill()};
            }            
        }
        return null;
    }   

    private int[] moveUp(List<List<ChessPiece>> Board){
        if(this.currentRow > 0){
            if(Board.get(this.currentRow - 1).get(this.currentCol).isNull()){
                return new int[]{this.currentRow, this.currentCol, this.currentRow - 1, this.currentCol, this.getPointsForMove()};
            }else if(!Board.get(this.currentRow - 1).get(this.currentCol).getColor().equals(this.color)){
                return new int[]{this.currentRow, this.currentCol, this.currentRow - 1, this.currentCol, Board.get(this.currentRow - 1).get(this.currentCol).getPointsForKill()};
            }            
        }
        return null;
    }       
    
    private int[] moveRight(List<List<ChessPiece>> Board){
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
    
    private int[] moveRightAndDown(List<List<ChessPiece>> Board){
        if(this.currentRow < 15 && this.currentCol < 15){
            if(Board.get(this.currentRow + 1).get(this.currentCol + 1).isNull()){
                return new int[]{this.currentRow, this.currentCol, this.currentRow + 1, this.currentCol + 1, this.getPointsForMove()};
            }else if(!Board.get(this.currentRow + 1).get(this.currentCol + 1).getColor().equals(this.color)){
                return new int[]{this.currentRow, this.currentCol, this.currentRow + 1, this.currentCol + 1, Board.get(this.currentRow + 1).get(this.currentCol + 1).getPointsForKill()};
            }            
        }
        return null;
    }   
  
    private int[] moveLeftAndDown(List<List<ChessPiece>> Board){
        if(this.currentRow < 15 && this.currentCol > 0){
            if(Board.get(this.currentRow + 1).get(this.currentCol - 1).isNull()){
                return new int[]{this.currentRow, this.currentCol, this.currentRow + 1, this.currentCol - 1, this.getPointsForMove()};
            }else if(!Board.get(this.currentRow + 1).get(this.currentCol - 1).getColor().equals(this.color)){
                return new int[]{this.currentRow, this.currentCol, this.currentRow + 1, this.currentCol - 1, Board.get(this.currentRow + 1).get(this.currentCol - 1).getPointsForKill()};
            }            
        }
        return null;
    }   

    private int[] moveLeftAndUp(List<List<ChessPiece>> Board){
        if(this.currentRow > 0 && this.currentCol > 0){
            if(Board.get(this.currentRow - 1).get(this.currentCol - 1).isNull()){
                return new int[]{this.currentRow, this.currentCol, this.currentRow - 1, this.currentCol - 1, this.getPointsForMove()};
            }else if(!Board.get(this.currentRow - 1).get(this.currentCol - 1).getColor().equals(this.color)){
                return new int[]{this.currentRow, this.currentCol, this.currentRow - 1, this.currentCol - 1, Board.get(this.currentRow - 1).get(this.currentCol - 1).getPointsForKill()};
            }            
        }
        return null;
    }       
    
    private int[] moveRightAndUp(List<List<ChessPiece>> Board){
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
        this.AddIfNotNull(moveLeft(Board));
        this.AddIfNotNull(moveUp(Board));
        this.AddIfNotNull(moveRight(Board));
        this.AddIfNotNull(moveRightAndDown(Board));
        this.AddIfNotNull(moveLeftAndDown(Board));
        this.AddIfNotNull(moveLeftAndUp(Board));
        this.AddIfNotNull(moveRightAndUp(Board));      
    }

    @Override
    public void calculatePossibleAttacks(List<List<ChessPiece>> Board) {
        List<int[]> BaseAttacks = new ArrayList<>();
        
        //King basic moves
        BaseAttacks.add(new int[] {this.currentRow - 1, this.currentCol - 1}); //left and upwards
        BaseAttacks.add(new int[] {this.currentRow - 1, this.currentCol}); //upwards
        BaseAttacks.add(new int[] {this.currentRow - 1, this.currentCol + 1}); //right and upwards
        BaseAttacks.add(new int[] {this.currentRow, this.currentCol + 1}); //right
        BaseAttacks.add(new int[] {this.currentRow + 1, this.currentCol + 1}); //right and downwards
        BaseAttacks.add(new int[] {this.currentRow + 1, this.currentCol}); //downwards
        BaseAttacks.add(new int[] {this.currentRow + 1, this.currentCol - 1}); //left and downwards
        BaseAttacks.add(new int[] {this.currentRow, this.currentCol - 1}); //left
        
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
