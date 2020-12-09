/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megachess.board;

import com.megachess.chesspiece.Bishop;
import com.megachess.chesspiece.ChessPiece;
import com.megachess.chesspiece.King;
import com.megachess.chesspiece.Knight;
import com.megachess.chesspiece.NullPiece;
import com.megachess.chesspiece.Pawn;
import com.megachess.chesspiece.Queen;
import com.megachess.chesspiece.Rook;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Emile
 */
public class Board {
    
    private List<List<ChessPiece>> BoardConfig; 
    private int boardValue;
    
    public Board(String BoardString){
        boardValue = 0;
        BoardConfig = Board.generateBoardConfig(BoardString);
    }

    public List<List<ChessPiece>> getBoardConfig() {
        return BoardConfig;
    }

    public int getBoardValue() {
        return boardValue;
    }
    
    
    
    public int movePiece(int fromRow, int fromCol, int toRow, int toCol){
        ChessPiece FromPiece = BoardConfig.get(fromRow).get(fromCol);
        ChessPiece ToPiece = BoardConfig.get(toRow).get(toCol);
        List<ChessPiece> FromRow = BoardConfig.get(fromRow);
        List<ChessPiece> ToRow = BoardConfig.get(toRow);
        
        if(!ToPiece.isNull()){ //Piece not null
            boardValue += (FromPiece.getColor().equals("black")? -1 : 1) * ToPiece.getPointsForKill();
        }
        boardValue += (FromPiece.getColor().equals("black")? -1 : 1) * FromPiece.getPointsForMove();        
        
        FromPiece.setCurrentRow(toRow);
        FromPiece.setCurrentCol(toCol);
        FromRow.set(fromCol, new NullPiece(fromRow, fromCol));
        if(FromPiece instanceof Pawn){ //PawnPromotion check
            if(FromPiece.getColor().equals("white") && toRow == 8){
                ToRow.set(toCol, new Queen(toRow, toCol, "white"));
            }else if(FromPiece.getColor().equals("black") && toRow == 7){
                ToRow.set(toCol, new Queen(toRow, toCol, "black"));
            }
        }else{
           ToRow.set(toCol, FromPiece); 
        }
        BoardConfig.set(fromRow, FromRow);
        BoardConfig.set(toRow, ToRow);
        

        return boardValue;
    }
    
    public static List<List<ChessPiece>> generateBoardConfig(String boardString){
        List<List<ChessPiece>> Board = new ArrayList<>();
        for(int row = 0; row < 16; row++){
            List<ChessPiece> BoardRow = new ArrayList<>();
            for(int column = 0; column < 16; column++){
                switch(boardString.charAt(row*16 + column)){
                    case 'p':
                       BoardRow.add(new Pawn(row,column,"black"));
                       break;
                    case 'P':
                        BoardRow.add(new Pawn(row,column,"white"));
                        break;                               
                   case 'r':
                       BoardRow.add(new Rook(row,column,"black")); 
                       break;
                    case 'R':
                        BoardRow.add(new Rook(row,column,"white")); 
                        break;                               
                   case 'h':
                       BoardRow.add(new Knight(row,column,"black"));
                       break;
                    case 'H':
                        BoardRow.add(new Knight(row,column,"white")); 
                        break;                               
                   case 'b':
                       BoardRow.add(new Bishop(row,column,"black")); 
                       break;
                    case 'B':
                        BoardRow.add(new Bishop(row,column,"white"));
                        break;                               
                   case 'q':
                       BoardRow.add(new Queen(row,column,"black"));
                       break;
                    case 'Q':
                        BoardRow.add(new Queen(row,column,"white"));
                        break;                               
                   case 'k':
                       BoardRow.add(new King(row,column,"black"));
                       break;                          
                    case 'K':
                        BoardRow.add(new King(row,column,"white")); 
                        break;
                    default:
                        BoardRow.add(new NullPiece(row,column));
                        break;                        
                }                        
            }
            Board.add(BoardRow);
        } 
        return Board;
    }     
    
}
