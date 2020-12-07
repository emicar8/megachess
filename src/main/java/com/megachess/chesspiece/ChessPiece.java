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
    protected List<int[]> possibleMoves;
    protected String color;
    
    public ChessPiece(){
        
    }
    
    public ChessPiece(int row, int col, String color){
        this.currentCol = col;
        this.currentRow = row;
        this.color = color;
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
        //return pointsForKill + this.prioritizeCenterKill(this.currentCol);
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
    public void AddIfNotNull(int [] move){
        if(move != null){
            this.possibleMoves.add(move);
        }
    }
       
    private int prioritizeCenterKill(int col){
        return (int)Math.round(-Math.pow(col-7.5, 2));
    }
    
    public abstract void calculatePossibleMoves(List<List<ChessPiece>> Board);
    
    public abstract boolean isNull();
}
