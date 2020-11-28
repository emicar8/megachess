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
public class Bishop extends ChessPiece{

    public Bishop(int row, int col, String color){
        super(row, col, color);
        this.pointsForMove = 40;
        this.pointsForKill = 400;
    }    

    private List<int[]> moveRightAndDown(List<List<ChessPiece>> Board){
        List<int[]> moves = new ArrayList<>();
        for(int i = 1; i < Math.min(16 - this.currentRow, 16 - this.currentCol); i++){
            if(!Board.get(this.currentRow + i).get(this.currentCol + i).isNull()){ //Position is not empty
                if(Board.get(this.currentRow + i).get(this.currentCol + i).getColor().equals(this.color)){ //Check if piece is same color
                    if(i != 1){ //Check if piece of same color is adjacent 
                        moves.add(new int[]{this.currentRow, this.currentCol, this.currentRow + i - 1, this.currentCol + i - 1, this.getPointsForMove()}); //Piece is same color
                    }                  
                }else{
                    moves.add(new int[]{this.currentRow, this.currentCol, this.currentRow + i, this.currentCol + i, Board.get(this.currentRow + i).get(this.currentCol + i).getPointsForKill()}); //Piece is not same color
                }
                break;
            }else{
                moves.add(new int[]{this.currentRow, this.currentCol, this.currentRow + i, this.currentCol + i, this.getPointsForMove()});
            }            
        }
        return moves;
    }
    
    private List<int[]> moveRightAndUp(List<List<ChessPiece>> Board){
        List<int[]> moves = new ArrayList<>();
        for(int i = 1; i < Math.min(this.currentRow + 1, 16 - this.currentCol); i++){
            if(!Board.get(this.currentRow - i).get(this.currentCol + i).isNull()){ //Position is not empty
                if(Board.get(this.currentRow - i).get(this.currentCol + i).getColor().equals(this.color)){ //Check if piece is same color
                    if(i != 1){ //Check if piece of same color is adjacent 
                        moves.add(new int[]{this.currentRow, this.currentCol, this.currentRow - i + 1, this.currentCol + i - 1, this.getPointsForMove()}); //Piece is same color
                    }                  
                }else{
                    moves.add(new int[]{this.currentRow, this.currentCol, this.currentRow - i, this.currentCol + i, Board.get(this.currentRow - i).get(this.currentCol + i).getPointsForKill()}); //Piece is not same color
                }
                break;
            }else{
                moves.add(new int[]{this.currentRow, this.currentCol, this.currentRow - i, this.currentCol + i, this.getPointsForMove()});
            }            
        }
        return moves;
    }

    private List<int[]> moveLeftAndUp(List<List<ChessPiece>> Board){
        List<int[]> moves = new ArrayList<>();
        for(int i = 1; i < Math.min(this.currentRow + 1, this.currentCol + 1); i++){
            if(!Board.get(this.currentRow - i).get(this.currentCol - i).isNull()){ //Position is not empty
                if(Board.get(this.currentRow - i).get(this.currentCol - i).getColor().equals(this.color)){ //Check if piece is same color
                    if(i != 1){ //Check if piece of same color is adjacent 
                        moves.add(new int[]{this.currentRow, this.currentCol, this.currentRow - i + 1, this.currentCol - i + 1, this.getPointsForMove()}); //Piece is same color
                    }                  
                }else{
                    moves.add(new int[]{this.currentRow, this.currentCol, this.currentRow - i, this.currentCol - i, Board.get(this.currentRow - i).get(this.currentCol - i).getPointsForKill()}); //Piece is not same color
                }
                break;
            }else{
                moves.add(new int[]{this.currentRow, this.currentCol, this.currentRow - i, this.currentCol - i, this.getPointsForMove()});
            }            
        }
        return moves;
    }
    
    private List<int[]> moveLeftAndDown(List<List<ChessPiece>> Board){
        List<int[]> moves = new ArrayList<>();
        for(int i = 1; i < Math.min(16 - this.currentRow, this.currentCol + 1); i++){
            if(!Board.get(this.currentRow + i).get(this.currentCol - i).isNull()){ //Position is not empty
                if(Board.get(this.currentRow + i).get(this.currentCol - i).getColor().equals(this.color)){ //Check if piece is same color
                    if(i != 1){ //Check if piece of same color is adjacent 
                        moves.add(new int[]{this.currentRow, this.currentCol, this.currentRow + i - 1, this.currentCol - i + 1, this.getPointsForMove()}); //Piece is same color
                    }                  
                }else{
                    moves.add(new int[]{this.currentRow, this.currentCol, this.currentRow + i, this.currentCol - i, Board.get(this.currentRow + i).get(this.currentCol - i).getPointsForKill()}); //Piece is not same color
                }
                break;
            }else{
                moves.add(new int[]{this.currentRow, this.currentCol, this.currentRow + i, this.currentCol - i, this.getPointsForMove()});
            }            
        }
        return moves;
    }
    
    @Override
    public void calculatePossibleMoves(List<List<ChessPiece>> Board) {
        this.possibleMoves.addAll(this.moveLeftAndDown(Board));
        this.possibleMoves.addAll(this.moveRightAndDown(Board));
        this.possibleMoves.addAll(this.moveRightAndUp(Board));
        this.possibleMoves.addAll(this.moveLeftAndUp(Board));       
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
