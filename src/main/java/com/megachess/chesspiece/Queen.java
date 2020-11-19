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

    public Queen(int col, int row, String color){
        super(col, row, color);
        this.pointsForMove = 70;
        this.pointsForKill = 700;
    }    
    
    @Override
    public void calculatePossibleMoves(List<List<ChessPiece>> Board) {
        //Queen will only move one tile
        
        List<int[]> BaseMoves = new ArrayList<>();
        
        //Queen basic moves
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
