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
    
    @Override
    public void calculatePossibleMoves(List<List<ChessPiece>> Board) {
        
        List<int[]> BaseMoves = new ArrayList<>();
        
        //King basic moves
        BaseMoves.add(new int[] {this.currentRow - 1, this.currentCol - 1}); //left and upwards
        BaseMoves.add(new int[] {this.currentRow - 1, this.currentCol}); //upwards
        BaseMoves.add(new int[] {this.currentRow - 1, this.currentCol + 1}); //right and upwards
        BaseMoves.add(new int[] {this.currentRow, this.currentCol + 1}); //right
        BaseMoves.add(new int[] {this.currentRow + 1, this.currentCol + 1}); //right and downwards
        BaseMoves.add(new int[] {this.currentRow + 1, this.currentCol}); //downwards
        BaseMoves.add(new int[] {this.currentRow + 1, this.currentCol - 1}); //left and downwards
        BaseMoves.add(new int[] {this.currentRow, this.currentCol - 1}); //left
        
        for(int[] baseMove : BaseMoves){
            if(baseMove[0] >= 0 && baseMove[0] < 16 && baseMove[1] >= 0 && baseMove[1] < 16){
                if(Board.get(baseMove[0]).get(baseMove[1]).isNull()){ //Empty space
                    this.possibleMoves.add(new int[]{this.currentRow, this.currentCol, baseMove[0], baseMove[1], this.pointsForMove});
                }
            }
        } 
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
