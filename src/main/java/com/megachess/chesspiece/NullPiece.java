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
public class NullPiece extends ChessPiece{
    
    public NullPiece(int row, int column){
        super(row,column,"");
        this.minMaxValueBase = 0;
    }
    
    @Override
    public void calculatePossibleMoves(List<List<ChessPiece>> Board) {
        //Do nothing
    }

    @Override
    public boolean isNull() {
        return true;
    }

    @Override
    public int positionBias() {
        return 0;
    }
    
    @Override
    public ChessPiece copy() {
        return new NullPiece(this.currentRow, this.currentCol);
    }    
    
}
