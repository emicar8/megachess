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
    private List<int[]> possibleMoves, possibleAttacks;
    private String color;
    
    public ChessPiece(int col, int row, String color){
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
    
    public abstract List<int[]> calculatePossibleMoves(char[][] Board);
    
    public abstract List<int[]> calculatePossibleAttacks(char[][] Board);
    
}
