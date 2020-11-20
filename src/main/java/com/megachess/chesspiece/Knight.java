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

    public Knight(int col, int row, String color){
        super(col, row, color);
        this.pointsForMove = 30;
        this.pointsForKill = 300;
    }    
    
    @Override
    public void calculatePossibleMoves(List<List<ChessPiece>> Board) {
                    
        List<int[]> BaseMoves = new ArrayList<>();
        
        //Knight basic moves
        BaseMoves.add(new int[] {this.currentRow - 2, this.currentCol - 1}); //upwards and then left
        BaseMoves.add(new int[] {this.currentRow - 2, this.currentCol + 1}); //upwards and then right
        BaseMoves.add(new int[] {this.currentRow - 1, this.currentCol + 2}); //right and then upwards
        BaseMoves.add(new int[] {this.currentRow + 1, this.currentCol + 2}); //right and then downwards
        BaseMoves.add(new int[] {this.currentRow + 2, this.currentCol + 1}); //downwards and then right
        BaseMoves.add(new int[] {this.currentRow + 2, this.currentCol - 1}); //downwards and then left
        BaseMoves.add(new int[] {this.currentRow + 1, this.currentCol - 2}); //left and then downwards
        BaseMoves.add(new int[] {this.currentRow - 1, this.currentCol - 2}); //left and then upwards
        
        for(int[] baseMove : BaseMoves){
            if(baseMove[0] >= 0 && baseMove[0] < 16 && baseMove[1] >= 0 && baseMove[1] < 16){
                if(Board.get(baseMove[0]).get(baseMove[1]).isNull()){
                    this.possibleMoves.add(new int[]{this.currentRow, this.currentCol, baseMove[0], baseMove[1], this.pointsForMove});
                }
            }
        }        
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
