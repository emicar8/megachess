/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megachess.chesspiece;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Emile
 */
public class King extends ChessPiece{

    public King(int col, int row, String color){
        super(col, row, color);
        this.pointsForMove = 65; //Lower performance of king movement to incentivize pawn movement to promote.
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
                if(Board.get(baseMove[0]).get(baseMove[1]).isNull()){
                    this.possibleMoves.add(new int[]{this.currentRow, this.currentCol, baseMove[0], baseMove[1], this.pointsForMove});
                }
            }
        } 
    }

    @Override
    public void calculatePossibleAttacks(List<List<ChessPiece>> Board) {
        //TODO
    }

    @Override
    public boolean isNull() {
        return false;
    }
    
}
