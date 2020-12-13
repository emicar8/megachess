/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megachess.chesspiece;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Emile
 */
public abstract class ChessPiece{
    
    protected int currentCol,currentRow,pointsForMove,pointsForKill, minMaxValueBase, minMaxValueCorrected;
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

    public int getPointsForKill() {
        return pointsForKill;
    }


    public List<int[]> getPossibleMoves() {
        List<int[]> temp = new ArrayList<>(this.possibleMoves);
        possibleMoves.clear();
        return temp;
    }

    public String getColor() {
        return color;
    }
    
    public int getMinMaxValueBase(){
        return minMaxValueBase;
    }    
    
    public int getMinMaxValueCorrected(){
        this.minMaxValueCorrected = this.minMaxValueBase + this.positionBias();
        return minMaxValueCorrected;
    }
    
    public void AddIfNotNull(int [] move){
        if(move != null){
            this.possibleMoves.add(move);
        }
    }
         
    @Override
    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        
        if(!(o instanceof ChessPiece)){
            return false;
        }
        
        ChessPiece compared = (ChessPiece) o;
        
        return compared.color.equals(this.color) && compared.getCurrentCol() == this.currentCol && compared.getCurrentRow() == this.currentRow && compared.getClass().equals(this.getClass());
        
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.currentCol;
        hash = 29 * hash + this.currentRow;
        hash = 29 * hash + this.pointsForMove;
        hash = 29 * hash + this.pointsForKill;
        hash = 29 * hash + Objects.hashCode(this.possibleMoves);
        hash = 29 * hash + Objects.hashCode(this.color);
        return hash;
    }
    
    public abstract void calculatePossibleMoves(List<List<ChessPiece>> Board);
    
    public abstract boolean isNull();   
    
    public abstract int positionBias();
    
    public abstract ChessPiece copy();
        
}
