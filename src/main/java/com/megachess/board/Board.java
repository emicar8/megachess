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
public class Board{
    
    private List<List<ChessPiece>> BoardConfig;
    private List<List<ChessPiece>> MoveHistory;
     
    public Board(String BoardString){
        BoardConfig = Board.generateBoardConfig(BoardString);
        MoveHistory = new ArrayList<>();
    }

    public List<List<ChessPiece>> getBoardConfig() {
        return BoardConfig;
    }
    
    public void movePiece(int fromRow, int fromCol, int toRow, int toCol){
        List<ChessPiece> AuxList = new ArrayList<>(), FromRow = BoardConfig.get(fromRow), ToRow = BoardConfig.get(toRow);
        ChessPiece FromPiece = BoardConfig.get(fromRow).get(fromCol);
        ChessPiece ToPiece = BoardConfig.get(toRow).get(toCol);
                
        //Saving moves to later undo
        AuxList.add(FromPiece.copy());
        AuxList.add(ToPiece.copy());
        MoveHistory.add(AuxList);
        
        
        if(FromPiece instanceof Pawn){
            if(FromPiece.getColor().equals("black") && toRow == 7){
                ToRow.set(toCol, new Queen(toRow,toCol,"black"));
            }else if(FromPiece.getColor().equals("white") && toRow == 8){
                ToRow.set(toCol, new Queen(toRow,toCol,"white"));
            }else{
                ToRow.set(toCol, FromPiece.copy());
            }  
        }else{
            ToRow.set(toCol, FromPiece.copy());
        }
        FromRow.set(fromCol, new NullPiece(fromRow,fromCol));
        
        BoardConfig.set(fromRow, FromRow);
        BoardConfig.set(toRow, ToRow);   
    }
    
    public void undoMovePiece(){ //Undo lastest move
                       
        int fromRow = MoveHistory.get(MoveHistory.size() - 1).get(0).getCurrentRow();
        int fromCol = MoveHistory.get(MoveHistory.size() - 1).get(0).getCurrentCol();
        int toRow = MoveHistory.get(MoveHistory.size() - 1).get(1).getCurrentRow();
        int toCol = MoveHistory.get(MoveHistory.size() - 1).get(1).getCurrentCol();

        List<ChessPiece> FromRow = BoardConfig.get(fromRow), ToRow = BoardConfig.get(toRow);
        
        FromRow.set(fromCol, MoveHistory.get(MoveHistory.size() - 1).get(0).copy());
        ToRow.set(toCol, MoveHistory.get(MoveHistory.size() - 1).get(1).copy());
        
        BoardConfig.set(fromRow, FromRow);
        BoardConfig.set(toRow, ToRow);
        
        MoveHistory.remove(MoveHistory.size() - 1);
    }
    
    public int evaluateBoardConfig(){
        int result = 0;
        for(List<ChessPiece> Row : BoardConfig){
            for(ChessPiece Piece : Row){
                result += (Piece.getColor().equals("black")? -1 : 1) * Piece.getMinMaxValueCorrected();
            }
        }
        return result;
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
