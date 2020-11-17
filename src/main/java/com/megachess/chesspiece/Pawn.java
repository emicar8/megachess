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
public class Pawn extends ChessPiece{
    
    public Pawn(int col, int row, String color){
        super(col, row, color);
        this.pointsForMove = 10;
        this.pointsForKill = 10*this.pointsForMove;
    }

    @Override
    public List<int[]> calculatePossibleMoves(char[][] Board) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<int[]> calculatePossibleAttacks(char[][] Board) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
