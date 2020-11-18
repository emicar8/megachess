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
public abstract class ChessPiece {
    
    protected int currentCol,currentRow,pointsForMove,pointsForKill;
    protected List<int[]> possibleMoves, possibleAttacks;
    protected String color;
    
    public ChessPiece(){
        
    }
    
    public ChessPiece(int row, int col, String color){
        this.currentCol = col;
        this.currentRow = row;
        this.color = color;
        this.possibleAttacks = new ArrayList<>();
        this.possibleMoves = new ArrayList<>();
    }

    public int getCurrentCol() {
        return currentCol;
    }

    public void setCurrentCol(int currentCol) {
        this.currentCol = currentCol;
    }

    public int getCurrentRow() {
        return currentRow;
    }

    public void setCurrentRow(int currentRow) {
        this.currentRow = currentRow;
    }

    public int getPointsForMove() {
        return pointsForMove;
    }

    public void setPointsForMove(int pointsForMove) {
        this.pointsForMove = pointsForMove;
    }

    public int getPointsForKill() {
        return pointsForKill;
    }

    public void setPointsForKill(int pointsForKill) {
        this.pointsForKill = pointsForKill;
    }

    public List<int[]> getPossibleMoves() {
        return possibleMoves;
    }

    public void setPossibleMoves(List<int[]> possibleMoves) {
        this.possibleMoves = possibleMoves;
    }

    public List<int[]> getPossibleAttacks() {
        return possibleAttacks;
    }

    public void setPossibleAttacks(List<int[]> possibleAttacks) {
        this.possibleAttacks = possibleAttacks;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
    public int[] getMaxValueMove(){
        int[] currentMax = {0, 0, 0, 0, 0};
        if(!this.possibleMoves.isEmpty()){ //List not empty
            for(int[] move : this.possibleMoves){
                if(move[4] > currentMax[4]){
                    currentMax = move;
                }
            }
        }
        
        if(!this.possibleAttacks.isEmpty()){ //List not empty
            for(int[] attack : this.possibleAttacks){
                if(attack[4] > currentMax[4]){
                    currentMax = attack;
                }
            }
        }
        return currentMax;
    }
    
    public abstract void calculatePossibleMoves(List<List<ChessPiece>> Board);
    
    public abstract void calculatePossibleAttacks(List<List<ChessPiece>> Board);
    
    public abstract boolean isNull();
    
}
