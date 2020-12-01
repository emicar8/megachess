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
public class Bishop extends ChessPiece{

    public Bishop(int row, int col, String color){
        super(row, col, color);
        this.pointsForMove = 40;
        this.pointsForKill = 400;
    }    

     private void moveRightAndDown(int newRow, int newCol, List<List<ChessPiece>> Board){
        if(newRow < 16 && newCol < 16){
            if(!Board.get(newRow).get(newCol).isNull()){ //Position is not empty
                if(Board.get(newRow).get(newCol).getColor().equals(this.color)){ //Check if piece is same color
                    if(this.currentRow != newRow - 1 && this.currentCol != newCol - 1){ //Avoid adding current position as possible move
                        this.possibleMoves.add(new int[]{this.currentRow, this.currentCol, newRow - 1, newCol - 1, this.getPointsForMove()}); //Piece is same color
                    }                    
                }else{
                    this.possibleMoves.add(new int[]{this.currentRow, this.currentCol, newRow, newCol, Board.get(newRow).get(newCol).getPointsForKill()}); //Piece is not same color
                }                
            }else{ //Can keep moving.
                this.possibleMoves.add(new int[]{this.currentRow, this.currentCol, newRow, newCol, this.getPointsForMove()});
                this.moveRightAndDown(newRow + 1, newCol + 1, Board);
            }
        }
    }    
    
      private void moveRightAndUp(int newRow, int newCol, List<List<ChessPiece>> Board){
        if(newRow > -1 && newCol < 16){
            if(!Board.get(newRow).get(newCol).isNull()){ //Position is not empty
                if(Board.get(newRow).get(newCol).getColor().equals(this.color)){ //Check if piece is same color
                    if(this.currentRow != newRow + 1 && this.currentCol != newCol - 1){ //Avoid adding current position as possible move
                        this.possibleMoves.add(new int[]{this.currentRow, this.currentCol, newRow + 1, newCol - 1, this.getPointsForMove()}); //Piece is same color
                    }                    
                }else{
                    this.possibleMoves.add(new int[]{this.currentRow, this.currentCol, newRow, newCol, Board.get(newRow).get(newCol).getPointsForKill()}); //Piece is not same color
                }                
            }else{ //Can keep moving.
                this.possibleMoves.add(new int[]{this.currentRow, this.currentCol, newRow, newCol, this.getPointsForMove()});
                this.moveRightAndUp(newRow - 1, newCol + 1, Board);
            }
        }
    }  
      
     private void moveLeftAndUp(int newRow, int newCol, List<List<ChessPiece>> Board){
        if(newRow > -1 && newCol > -1){
            if(!Board.get(newRow).get(newCol).isNull()){ //Position is not empty
                if(Board.get(newRow).get(newCol).getColor().equals(this.color)){ //Check if piece is same color
                    if(this.currentRow != newRow + 1 && this.currentCol != newCol + 1){ //Avoid adding current position as possible move
                        this.possibleMoves.add(new int[]{this.currentRow, this.currentCol, newRow + 1, newCol + 1, this.getPointsForMove()}); //Piece is same color
                    }                    
                }else{
                    this.possibleMoves.add(new int[]{this.currentRow, this.currentCol, newRow, newCol, Board.get(newRow).get(newCol).getPointsForKill()}); //Piece is not same color
                }                
            }else{ //Can keep moving.
                this.possibleMoves.add(new int[]{this.currentRow, this.currentCol, newRow, newCol, this.getPointsForMove()});
                this.moveLeftAndUp(newRow - 1, newCol - 1, Board);
            }
        }
    }        

     private void moveLeftAndDown(int newRow, int newCol, List<List<ChessPiece>> Board){
        if(newRow < 16 && newCol > -1){
            if(!Board.get(newRow).get(newCol).isNull()){ //Position is not empty
                if(Board.get(newRow).get(newCol).getColor().equals(this.color)){ //Check if piece is same color
                    if(this.currentRow != newRow - 1 && this.currentCol != newCol + 1){ //Avoid adding current position as possible move
                        this.possibleMoves.add(new int[]{this.currentRow, this.currentCol, newRow - 1, newCol + 1, this.getPointsForMove()}); //Piece is same color
                    }                    
                }else{
                    this.possibleMoves.add(new int[]{this.currentRow, this.currentCol, newRow, newCol, Board.get(newRow).get(newCol).getPointsForKill()}); //Piece is not same color
                }                
            }else{ //Can keep moving.
                this.possibleMoves.add(new int[]{this.currentRow, this.currentCol, newRow, newCol, this.getPointsForMove()});
                this.moveLeftAndDown(newRow + 1, newCol - 1, Board);
            }
        }
    }
    
    @Override
    public void calculatePossibleMoves(List<List<ChessPiece>> Board) {
        this.moveLeftAndDown(this.currentRow + 1, this.currentCol - 1, Board);
        this.moveLeftAndUp(this.currentRow - 1, this.currentCol - 1, Board);
        this.moveRightAndUp(this.currentRow - 1, this.currentCol + 1, Board);
        this.moveRightAndDown(this.currentRow + 1, this.currentCol + 1, Board);      
    }

    @Override
    public void calculatePossibleAttacks(List<List<ChessPiece>> Board) {
        for(int i = 1; i < Math.min(16 - this.currentRow, 16 - this.currentCol); i++){ //Attack to the right and down
            if(!Board.get(this.currentRow + i).get(this.currentCol + i).isNull()){ //Position is not empty
                if(this.color.equals("white") && Board.get(this.currentRow + i).get(this.currentCol + i).getColor().equals("black")){ //Valid attack
                    this.possibleAttacks.add(new int[]{this.currentRow, this.currentCol, this.currentRow + i, this.currentCol + i, Board.get(this.currentRow + i).get(this.currentCol + i).getPointsForKill()});
                }else if(this.color.equals("black") && Board.get(this.currentRow + i).get(this.currentCol + i).getColor().equals("white")){ ////Valid attack
                    this.possibleAttacks.add(new int[]{this.currentRow, this.currentCol, this.currentRow + i, this.currentCol + i, Board.get(this.currentRow + i).get(this.currentCol + i).getPointsForKill()});
                }
                break;
            }            
        }
        
        for(int i = 1; i < Math.min(this.currentRow + 1, this.currentCol + 1); i++){ //Attack to the left and up
            if(!Board.get(this.currentRow - i).get(this.currentCol - i).isNull()){ //Position is not empty
                if(this.color.equals("white") && Board.get(this.currentRow - i).get(this.currentCol - i).getColor().equals("black")){ //Valid attack
                    this.possibleAttacks.add(new int[]{this.currentRow, this.currentCol, this.currentRow - i, this.currentCol - i, Board.get(this.currentRow - i).get(this.currentCol - i).getPointsForKill()});
                }else if(this.color.equals("black") && Board.get(this.currentRow - i).get(this.currentCol - i).getColor().equals("white")){ ////Valid attack
                    this.possibleAttacks.add(new int[]{this.currentRow, this.currentCol, this.currentRow - i, this.currentCol - i, Board.get(this.currentRow - i).get(this.currentCol - i).getPointsForKill()});
                }
                break;
            }            
        }

        for(int i = 1; i < Math.min(16 - this.currentRow, this.currentCol + 1); i++){ //Attack to the left and down
            if(!Board.get(this.currentRow + i).get(this.currentCol - i).isNull()){ //Position is not empty
                if(this.color.equals("white") && Board.get(this.currentRow + i).get(this.currentCol - i).getColor().equals("black")){ //Valid attack
                    this.possibleAttacks.add(new int[]{this.currentRow, this.currentCol, this.currentRow + i, this.currentCol - i, Board.get(this.currentRow + i).get(this.currentCol - i).getPointsForKill()});
                }else if(this.color.equals("black") && Board.get(this.currentRow + i).get(this.currentCol - i).getColor().equals("white")){ ////Valid attack
                    this.possibleAttacks.add(new int[]{this.currentRow, this.currentCol, this.currentRow + i, this.currentCol - i, Board.get(this.currentRow + i).get(this.currentCol - i).getPointsForKill()});
                }
                break;
            }            
        }
        
        for(int i = 1; i < Math.min(this.currentRow + 1, 16 - this.currentCol); i++){ //Attack to the right and up
            if(!Board.get(this.currentRow - i).get(this.currentCol + i).isNull()){ //Position is not empty
                if(this.color.equals("white") && Board.get(this.currentRow - i).get(this.currentCol + i).getColor().equals("black")){ //Valid attack
                    this.possibleAttacks.add(new int[]{this.currentRow, this.currentCol, this.currentRow - i, this.currentCol + i, Board.get(this.currentRow - i).get(this.currentCol + i).getPointsForKill()});
                }else if(this.color.equals("black") && Board.get(this.currentRow - i).get(this.currentCol + i).getColor().equals("white")){ ////Valid attack
                    this.possibleAttacks.add(new int[]{this.currentRow, this.currentCol, this.currentRow - i, this.currentCol + i, Board.get(this.currentRow - i).get(this.currentCol + i).getPointsForKill()});
                }
                break;
            }            
        }
    }

    @Override
    public boolean isNull() {
        return false;
    }
    
}
