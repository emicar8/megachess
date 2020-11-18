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

    public Bishop(int col, int row, String color){
        super(col, row, color);
        this.pointsForMove = 40;
        this.pointsForKill = 10*this.pointsForMove;
    }    
    
    @Override
    public void calculatePossibleMoves(List<List<ChessPiece>> Board) {
        //TODO
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
