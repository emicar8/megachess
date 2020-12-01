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
public class Rook extends ChessPiece{
    
    public Rook(int row, int col, String color){
        super(row, col, color);
        this.pointsForMove = 60;
        this.pointsForKill = 600;
    }    
    
    
    private void moveRight(int newRow, int newCol, List<List<ChessPiece>> Board){
        if(newCol < 16){
            if(!Board.get(newRow).get(newCol).isNull()){ //Position is not empty
                if(Board.get(newRow).get(newCol).getColor().equals(this.color)){ //Check if piece is same color
                    if(this.currentCol != newCol - 1){ //Avoid adding current position as possible move
                        this.possibleMoves.add(new int[]{this.currentRow, this.currentCol, newRow, newCol - 1, this.getPointsForMove()}); //Piece is same color
                    }                   
                }else{
                    this.possibleMoves.add(new int[]{this.currentRow, this.currentCol, newRow, newCol, Board.get(newRow).get(newCol).getPointsForKill()}); //Piece is not same color
                }                
            }else{ //Can keep moving.
                this.possibleMoves.add(new int[]{this.currentRow, this.currentCol, newRow, newCol, this.getPointsForMove()});
                this.moveRight(newRow, newCol + 1, Board);
            }
        }
    }
    
    private void moveDown(int newRow, int newCol, List<List<ChessPiece>> Board){
        if(newRow < 16){
            if(!Board.get(newRow).get(newCol).isNull()){ //Position is not empty
                if(Board.get(newRow).get(newCol).getColor().equals(this.color)){ //Check if piece is same color
                    if(this.currentRow != newRow - 1){ //Avoid adding current position as possible move
                        this.possibleMoves.add(new int[]{this.currentRow, this.currentCol, newRow - 1, newCol, this.getPointsForMove()}); //Piece is same color
                    }                    
                }else{
                    this.possibleMoves.add(new int[]{this.currentRow, this.currentCol, newRow, newCol, Board.get(newRow).get(newCol).getPointsForKill()}); //Piece is not same color
                }                
            }else{ //Can keep moving.
                this.possibleMoves.add(new int[]{this.currentRow, this.currentCol, newRow, newCol, this.getPointsForMove()});
                this.moveDown(newRow + 1, newCol, Board);
            }
        }
    }     

    private void moveLeft(int newRow, int newCol, List<List<ChessPiece>> Board){
        if(newCol > -1){
            if(!Board.get(newRow).get(newCol).isNull()){ //Position is not empty
                if(Board.get(newRow).get(newCol).getColor().equals(this.color)){ //Check if piece is same color
                    if(this.currentCol != newCol + 1){ //Avoid adding current position as possible move
                        this.possibleMoves.add(new int[]{this.currentRow, this.currentCol, newRow, newCol + 1, this.getPointsForMove()}); //Piece is same color
                    }                   
                }else{
                    this.possibleMoves.add(new int[]{this.currentRow, this.currentCol, newRow, newCol, Board.get(newRow).get(newCol).getPointsForKill()}); //Piece is not same color
                }                
            }else{ //Can keep moving.
                this.possibleMoves.add(new int[]{this.currentRow, this.currentCol, newRow, newCol, this.getPointsForMove()});
                this.moveLeft(newRow, newCol - 1, Board);
            }
        }
    }
       
    private void moveUp(int newRow, int newCol, List<List<ChessPiece>> Board){
        if(newRow > -1){
            if(!Board.get(newRow).get(newCol).isNull()){ //Position is not empty
                if(Board.get(newRow).get(newCol).getColor().equals(this.color)){ //Check if piece is same color
                    if(this.currentRow != newRow + 1){ //Avoid adding current position as possible move
                        this.possibleMoves.add(new int[]{this.currentRow, this.currentCol, newRow + 1, newCol, this.getPointsForMove()}); //Piece is same color
                    }                    
                }else{
                    this.possibleMoves.add(new int[]{this.currentRow, this.currentCol, newRow, newCol, Board.get(newRow).get(newCol).getPointsForKill()}); //Piece is not same color
                }                
            }else{ //Can keep moving.
                this.possibleMoves.add(new int[]{this.currentRow, this.currentCol, newRow, newCol, this.getPointsForMove()});
                this.moveUp(newRow - 1, newCol, Board);
            }
        }
    } 
    
    @Override
    public void calculatePossibleMoves(List<List<ChessPiece>> Board) {
        this.moveDown(this.currentRow + 1, this.currentCol, Board);
        this.moveRight(this.currentRow, this.currentCol + 1, Board);
        this.moveLeft(this.currentRow, this.currentCol - 1, Board);
        this.moveUp(this.currentRow - 1, this.currentCol, Board);
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
    }

    @Override
    public boolean isNull() {
        return false;
    }
    
}
