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
public class Queen extends ChessPiece{

    public Queen(int row, int col, String color){
        super(row, col, color);
        this.pointsForMove = 5;
        this.pointsForKill = 50;
    }

    //Straight moves
    
    private List<int[]> moveRight(List<List<ChessPiece>> Board){
        List<int[]> moves = new ArrayList<>();
        for(int i = this.currentCol + 1; i < 16; i++){ //Check moves towards the right
            if(!Board.get(this.currentRow).get(i).isNull()){ //Position is not empty
                if(Board.get(this.currentRow).get(i).getColor().equals(this.color)){ //Check if piece is same color
                    moves.add(new int[]{this.currentRow, this.currentCol, this.currentRow, i - 1, this.getPointsForMove()}); //Piece is same color
                }else{
                    moves.add(new int[]{this.currentRow, this.currentCol, this.currentRow, i, Board.get(this.currentRow).get(i).getPointsForKill()}); //Piece is not same color
                }
                break;
            }else{
                moves.add(new int[]{this.currentRow, this.currentCol, this.currentRow, i, this.getPointsForMove()});
            }
        }
        return moves;
    }   
    
    private List<int[]> moveDown(List<List<ChessPiece>> Board){
        List<int[]> moves = new ArrayList<>();
        for(int i = this.currentRow + 1; i < 16; i++){ //Check moves downwards
            if(!Board.get(i).get(this.currentCol).isNull()){ //Position is not empty
                if(Board.get(i).get(this.currentCol).getColor().equals(this.color)){ //Check if piece is same color
                    moves.add(new int[]{this.currentRow, this.currentCol, i - 1, this.currentCol, this.getPointsForMove()}); //Piece is same color
                }else{
                    moves.add(new int[]{this.currentRow, this.currentCol, i, this.currentCol, Board.get(i).get(this.currentCol).getPointsForKill()}); //Piece is not same color
                }
                break;
            }else{
                moves.add(new int[]{this.currentRow, this.currentCol, i, this.currentCol, this.getPointsForMove()});
            }
        }        
        return moves;
    }
    
    private List<int[]> moveLeft(List<List<ChessPiece>> Board){
        List<int[]> moves = new ArrayList<>();
        for(int i = this.currentCol - 1; i > -1; i--){ //Check moves towards the left
            if(!Board.get(this.currentRow).get(i).isNull()){ //Position is not empty
                if(Board.get(this.currentRow).get(i).getColor().equals(this.color)){ //Check if piece is same color
                    moves.add(new int[]{this.currentRow, this.currentCol, this.currentRow, i + 1, this.getPointsForMove()}); //Piece is same color
                }else{
                    moves.add(new int[]{this.currentRow, this.currentCol, this.currentRow, i, Board.get(this.currentRow).get(i).getPointsForKill()}); //Piece is not same color
                }
                break;
            }else{
                moves.add(new int[]{this.currentRow, this.currentCol, this.currentRow, i, this.getPointsForMove()});
            }
        }        
        return moves;
    }    
    
    private List<int[]> moveUp(List<List<ChessPiece>> Board){
        List<int[]> moves = new ArrayList<>();
        for(int i = this.currentRow - 1; i > -1; i--){ //Check moves upwards
            if(!Board.get(i).get(this.currentCol).isNull()){ //Position is not empty
                if(Board.get(i).get(this.currentCol).getColor().equals(this.color)){ //Check if piece is same color
                    moves.add(new int[]{this.currentRow, this.currentCol, i + 1, this.currentCol, this.getPointsForMove()}); //Piece is same color
                }else{
                    moves.add(new int[]{this.currentRow, this.currentCol, i, this.currentCol, Board.get(i).get(this.currentCol).getPointsForKill()}); //Piece is not same color
                }
                break;
            }else{
                moves.add(new int[]{this.currentRow, this.currentCol, i, this.currentCol, this.getPointsForMove()});
            }
        }        
        return moves;
    }
    
    //Diagonal moves
    
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
        this.possibleMoves.addAll(this.moveDown(Board));
        this.possibleMoves.addAll(this.moveRight(Board));
        this.possibleMoves.addAll(this.moveUp(Board));
        this.possibleMoves.addAll(this.moveLeft(Board));
        this.possibleMoves.addAll(this.moveLeftAndDown(Board));
        this.possibleMoves.addAll(this.moveRightAndDown(Board));
        this.possibleMoves.addAll(this.moveRightAndUp(Board));
        this.possibleMoves.addAll(this.moveLeftAndUp(Board));
    }

    @Override
    public void calculatePossibleAttacks(List<List<ChessPiece>> Board) {
        
        for(int i = this.currentCol + 1; i < 16; i++){ //Check attack towards the right
            if(!Board.get(this.currentRow).get(i).isNull()){ //Position is not empty
                if(this.color.equals("white") && Board.get(this.currentRow).get(i).getColor().equals("black")){ //Valid attack
                    this.possibleAttacks.add(new int[]{this.currentRow, this.currentCol, this.currentRow, i, Board.get(this.currentRow).get(i).getPointsForKill()});
                }else if(this.color.equals("black") && Board.get(this.currentRow).get(i).getColor().equals("white")){ ////Valid attack
                    this.possibleAttacks.add(new int[]{this.currentRow, this.currentCol, this.currentRow, i, Board.get(this.currentRow).get(i).getPointsForKill()});
                }
                break;
            }
        }
        for(int i = this.currentCol - 1; i > -1; i--){ //Check attack towards the left
            if(!Board.get(this.currentRow).get(i).isNull()){ //Position is not empty
                if(this.color.equals("white") && Board.get(this.currentRow).get(i).getColor().equals("black")){ //Valid attack
                    this.possibleAttacks.add(new int[]{this.currentRow, this.currentCol, this.currentRow, i, Board.get(this.currentRow).get(i).getPointsForKill()});
                }else if(this.color.equals("black") && Board.get(this.currentRow).get(i).getColor().equals("white")){ ////Valid attack
                    this.possibleAttacks.add(new int[]{this.currentRow, this.currentCol, this.currentRow, i, Board.get(this.currentRow).get(i).getPointsForKill()});
                }
                break;
            }
        }
        for(int i = this.currentRow - 1; i > -1; i--){ //Check attack upwards
            if(!Board.get(i).get(this.currentCol).isNull()){ //Position is not empty
                if(this.color.equals("white") && Board.get(i).get(this.currentCol).getColor().equals("black")){ //Valid attack
                    this.possibleAttacks.add(new int[]{this.currentRow, this.currentCol, i, this.currentCol, Board.get(i).get(this.currentCol).getPointsForKill()});
                }else if(this.color.equals("black") && Board.get(i).get(this.currentCol).getColor().equals("white")){ ////Valid attack
                    this.possibleAttacks.add(new int[]{this.currentRow, this.currentCol, i, this.currentCol, Board.get(i).get(this.currentCol).getPointsForKill()});
                }
                break;
            }
        }
        for(int i = this.currentRow + 1; i < 16; i++){ //Check attack downwards
            if(!Board.get(i).get(this.currentCol).isNull()){ //Position is not empty
                if(this.color.equals("white") && Board.get(i).get(this.currentCol).getColor().equals("black")){ //Valid attack
                    this.possibleAttacks.add(new int[]{this.currentRow, this.currentCol, i, this.currentCol, Board.get(i).get(this.currentCol).getPointsForKill()});
                }else if(this.color.equals("black") && Board.get(i).get(this.currentCol).getColor().equals("white")){ ////Valid attack
                    this.possibleAttacks.add(new int[]{this.currentRow, this.currentCol, i, this.currentCol, Board.get(i).get(this.currentCol).getPointsForKill()});
                }
                break;
            }
        }
        
        //Diagonal attacks
        
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
