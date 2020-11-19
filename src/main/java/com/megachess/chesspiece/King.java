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

    public King(int col, int row, String color){
        super(col, row, color);
        this.pointsForMove = 100;
        this.pointsForKill = 10*this.pointsForMove;
        this.pointsForMove = 65; //Lower performance of king movement to incentivize pawn movement to promote.
    }    
    
    @Override
    public void calculatePossibleMoves(List<List<ChessPiece>> Board) {
        switch(this.currentRow){
           case 0:
               switch(this.currentCol){
                   case 0: //First row and column
                       if(Board.get(this.currentRow).get(this.currentCol + 1).isNull()){ //Check if free space to the right
                           this.possibleMoves.add(new int[] {this.currentRow, this.currentCol, this.currentRow, this.currentCol + 1, this.pointsForMove});
                       }else if(Board.get(this.currentRow + 1).get(this.currentCol + 1).isNull()){ //Check if free space to the right and down
                           this.possibleMoves.add(new int[] {this.currentRow, this.currentCol, this.currentRow + 1, this.currentCol + 1, this.pointsForMove});
                       }else if(Board.get(this.currentRow + 1).get(this.currentCol).isNull()){ //Check if free space down
                           this.possibleMoves.add(new int[] {this.currentRow, this.currentCol, this.currentRow + 1, this.currentCol, this.pointsForMove});
                       }
                       break;
                   case 15: //First row and last column
                       if(Board.get(this.currentRow + 1).get(this.currentCol).isNull()){ //Check if free space down
                           this.possibleMoves.add(new int[] {this.currentRow, this.currentCol, this.currentRow + 1, this.currentCol, this.pointsForMove});
                       }else if(Board.get(this.currentRow + 1).get(this.currentCol - 1).isNull()){ //Check if free space to the left and down
                           this.possibleMoves.add(new int[] {this.currentRow, this.currentCol, this.currentRow + 1, this.currentCol - 1, this.pointsForMove});
                       }else if(Board.get(this.currentRow).get(this.currentCol - 1).isNull()){ //Check if free space to the left
                           this.possibleMoves.add(new int[] {this.currentRow, this.currentCol, this.currentRow, this.currentCol - 1, this.pointsForMove});
                       }                                
                       break;
                   default: //First row
                       if(Board.get(this.currentRow).get(this.currentCol + 1).isNull()){ //Check if free space to the right
                           this.possibleMoves.add(new int[] {this.currentRow, this.currentCol, this.currentRow, this.currentCol + 1, this.pointsForMove});
                       }else if(Board.get(this.currentRow + 1).get(this.currentCol + 1).isNull()){ //Check if free space to the right and down
                           this.possibleMoves.add(new int[] {this.currentRow, this.currentCol, this.currentRow + 1, this.currentCol + 1, this.pointsForMove});
                       }else if(Board.get(this.currentRow + 1).get(this.currentCol).isNull()){ //Check if free space down
                           this.possibleMoves.add(new int[] {this.currentRow, this.currentCol, this.currentRow + 1, this.currentCol, this.pointsForMove});
                       }else if(Board.get(this.currentRow + 1).get(this.currentCol - 1).isNull()){ //Check if free space to the left and down
                           this.possibleMoves.add(new int[] {this.currentRow, this.currentCol, this.currentRow + 1, this.currentCol - 1, this.pointsForMove});
                       }else if(Board.get(this.currentRow).get(this.currentCol - 1).isNull()){ //Check if free space to the left
                           this.possibleMoves.add(new int[] {this.currentRow, this.currentCol, this.currentRow, this.currentCol - 1, this.pointsForMove});
                       }                                  
                       break;
               }
               break;
           case 15:
               switch(this.currentCol){
                   case 0: //Last row and first column
                       if(Board.get(this.currentRow).get(this.currentCol + 1).isNull()){ //Check if free space to the right
                           this.possibleMoves.add(new int[] {this.currentRow, this.currentCol, this.currentRow, this.currentCol + 1, this.pointsForMove});
                       }else if(Board.get(this.currentRow - 1).get(this.currentCol).isNull()){ //Check if free space up
                           this.possibleMoves.add(new int[] {this.currentRow, this.currentCol, this.currentRow - 1, this.currentCol, this.pointsForMove});
                       }else if(Board.get(this.currentRow - 1).get(this.currentCol + 1 ).isNull()){ //Check if free space to the right and up
                           this.possibleMoves.add(new int[] {this.currentRow, this.currentCol, this.currentRow - 1, this.currentCol + 1, this.pointsForMove});
                       }                                  
                       break;
                   case 15: //Last row and last column
                       if(Board.get(this.currentRow).get(this.currentCol - 1).isNull()){ //Check if free space to the left
                           this.possibleMoves.add(new int[] {this.currentRow, this.currentCol, this.currentRow, this.currentCol - 1, this.pointsForMove});
                       }else if(Board.get(this.currentRow - 1).get(this.currentCol - 1).isNull()){ //Check if free space to the left and up
                           this.possibleMoves.add(new int[] {this.currentRow, this.currentCol, this.currentRow - 1, this.currentCol - 1, this.pointsForMove});
                       }else if(Board.get(this.currentRow - 1).get(this.currentCol).isNull()){ //Check if free space up
                           this.possibleMoves.add(new int[] {this.currentRow, this.currentCol, this.currentRow - 1, this.currentCol, this.pointsForMove});
                       }                                  
                       break;
                   default: //Last row
                       if(Board.get(this.currentRow).get(this.currentCol + 1).isNull()){ //Check if free space to the right
                           this.possibleMoves.add(new int[] {this.currentRow, this.currentCol, this.currentRow, this.currentCol + 1, this.pointsForMove});
                       }else if(Board.get(this.currentRow).get(this.currentCol - 1).isNull()){ //Check if free space to the left
                           this.possibleMoves.add(new int[] {this.currentRow, this.currentCol, this.currentRow, this.currentCol - 1, this.pointsForMove});
                       }else if(Board.get(this.currentRow - 1).get(this.currentCol - 1).isNull()){ //Check if free space to the left and up
                           this.possibleMoves.add(new int[] {this.currentRow, this.currentCol, this.currentRow - 1, this.currentCol - 1, this.pointsForMove});
                       }else if(Board.get(this.currentRow - 1).get(this.currentCol).isNull()){ //Check if free space up
                           this.possibleMoves.add(new int[] {this.currentRow, this.currentCol, this.currentRow - 1, this.currentCol, this.pointsForMove});
                       }else if(Board.get(this.currentRow - 1).get(this.currentCol + 1 ).isNull()){ //Check if free space to the right and up
                           this.possibleMoves.add(new int[] {this.currentRow, this.currentCol, this.currentRow - 1, this.currentCol + 1, this.pointsForMove});
                       }                                     
                       break;
               }                        
               break;
           default:
               switch(this.currentCol){
                   case 0: //First column
                       if(Board.get(this.currentRow).get(this.currentCol + 1).isNull()){ //Check if free space to the right
                           this.possibleMoves.add(new int[] {this.currentRow, this.currentCol, this.currentRow, this.currentCol + 1, this.pointsForMove});
                       }else if(Board.get(this.currentRow + 1).get(this.currentCol + 1).isNull()){ //Check if free space to the right and down
                           this.possibleMoves.add(new int[] {this.currentRow, this.currentCol, this.currentRow + 1, this.currentCol + 1, this.pointsForMove});
                       }else if(Board.get(this.currentRow + 1).get(this.currentCol).isNull()){ //Check if free space down
                           this.possibleMoves.add(new int[] {this.currentRow, this.currentCol, this.currentRow + 1, this.currentCol, this.pointsForMove});
                       }else if(Board.get(this.currentRow - 1).get(this.currentCol).isNull()){ //Check if free space up
                           this.possibleMoves.add(new int[] {this.currentRow, this.currentCol, this.currentRow - 1, this.currentCol, this.pointsForMove});
                       }else if(Board.get(this.currentRow - 1).get(this.currentCol + 1 ).isNull()){ //Check if free space to the right and up
                           this.possibleMoves.add(new int[] {this.currentRow, this.currentCol, this.currentRow - 1, this.currentCol + 1, this.pointsForMove});
                       }                                 
                       break;
                   case 15: //Last column
                       if(Board.get(this.currentRow + 1).get(this.currentCol).isNull()){ //Check if free space down
                           this.possibleMoves.add(new int[] {this.currentRow, this.currentCol, this.currentRow + 1, this.currentCol, this.pointsForMove});
                       }else if(Board.get(this.currentRow + 1).get(this.currentCol - 1).isNull()){ //Check if free space to the left and down
                           this.possibleMoves.add(new int[] {this.currentRow, this.currentCol, this.currentRow + 1, this.currentCol - 1, this.pointsForMove});
                       }else if(Board.get(this.currentRow).get(this.currentCol - 1).isNull()){ //Check if free space to the left
                           this.possibleMoves.add(new int[] {this.currentRow, this.currentCol, this.currentRow, this.currentCol - 1, this.pointsForMove});
                       }else if(Board.get(this.currentRow - 1).get(this.currentCol - 1).isNull()){ //Check if free space to the left and up
                           this.possibleMoves.add(new int[] {this.currentRow, this.currentCol, this.currentRow - 1, this.currentCol - 1, this.pointsForMove});
                       }else if(Board.get(this.currentRow - 1).get(this.currentCol).isNull()){ //Check if free space up
                           this.possibleMoves.add(new int[] {this.currentRow, this.currentCol, this.currentRow - 1, this.currentCol, this.pointsForMove});
                       }                                
                       break;
                   default: //All other combinations
                       if(Board.get(this.currentRow).get(this.currentCol + 1).isNull()){ //Check if free space to the right
                           this.possibleMoves.add(new int[] {this.currentRow, this.currentCol, this.currentRow, this.currentCol + 1, this.pointsForMove});
                       }else if(Board.get(this.currentRow + 1).get(this.currentCol + 1).isNull()){ //Check if free space to the right and down
                           this.possibleMoves.add(new int[] {this.currentRow, this.currentCol, this.currentRow + 1, this.currentCol + 1, this.pointsForMove});
                       }else if(Board.get(this.currentRow + 1).get(this.currentCol).isNull()){ //Check if free space down
                           this.possibleMoves.add(new int[] {this.currentRow, this.currentCol, this.currentRow + 1, this.currentCol, this.pointsForMove});
                       }else if(Board.get(this.currentRow + 1).get(this.currentCol - 1).isNull()){ //Check if free space to the left and down
                           this.possibleMoves.add(new int[] {this.currentRow, this.currentCol, this.currentRow + 1, this.currentCol - 1, this.pointsForMove});
                       }else if(Board.get(this.currentRow).get(this.currentCol - 1).isNull()){ //Check if free space to the left
                           this.possibleMoves.add(new int[] {this.currentRow, this.currentCol, this.currentRow, this.currentCol - 1, this.pointsForMove});
                       }else if(Board.get(this.currentRow - 1).get(this.currentCol - 1).isNull()){ //Check if free space to the left and up
                           this.possibleMoves.add(new int[] {this.currentRow, this.currentCol, this.currentRow - 1, this.currentCol - 1, this.pointsForMove});
                       }else if(Board.get(this.currentRow - 1).get(this.currentCol).isNull()){ //Check if free space up
                           this.possibleMoves.add(new int[] {this.currentRow, this.currentCol, this.currentRow - 1, this.currentCol, this.pointsForMove});
                       }else if(Board.get(this.currentRow - 1).get(this.currentCol + 1 ).isNull()){ //Check if free space to the right and up
                           this.possibleMoves.add(new int[] {this.currentRow, this.currentCol, this.currentRow - 1, this.currentCol + 1, this.pointsForMove});
                       }                                 
                       break;
               }                        
               break;
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