/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megachess.client;

import com.megachess.chesspiece.Bishop;
import com.megachess.chesspiece.ChessPiece;
import com.megachess.chesspiece.King;
import com.megachess.chesspiece.Knight;
import com.megachess.chesspiece.NullPiece;
import com.megachess.chesspiece.Pawn;
import com.megachess.chesspiece.Queen;
import com.megachess.chesspiece.Rook;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Emile
 */
public class ChessBot {
    
    ChessBot(){
        
    }
    
    public JSONObject acceptChallenge(JSONObject receivedChallenge){
        
        JSONObject messageToSend = new JSONObject();    //Sent message.
        JSONObject dataOut = new JSONObject(); //Out data object
        
        try{
            messageToSend.put("action", "accept_challenge");
            dataOut.put("board_id", receivedChallenge.getJSONObject("data").get("board_id"));
            messageToSend.put("data", dataOut);            
        }catch(JSONException jsonEx){
            return null;
        }
        return messageToSend;
        
    }
    
    public JSONObject myTurn(JSONObject receivedTurn) throws JSONException, NullPointerException{

        JSONObject messageToSend = new JSONObject();    //Sent message.
        JSONObject dataOut = new JSONObject(); //Out data object
        String boardString = receivedTurn.getJSONObject("data").getString("board");
        List<int[]> AllMoves = new ArrayList<>();
        List<int[]> BestMoves = new ArrayList<>();
        List<List<ChessPiece>> Board = ChessBot.generateBoard(boardString);
        int[] selectedMove;

        for(List<ChessPiece> Row : Board){
            for(ChessPiece Piece : Row){
                if(receivedTurn.getJSONObject("data").getString("actual_turn").equals(Piece.getColor())){
                    Piece.calculatePossibleMoves(Board);
                    AllMoves.addAll(Piece.getPossibleMoves());
                }                   
            }
        }

        Collections.sort(AllMoves, new MoveComparator().reversed());

        for(int[] move : AllMoves){
            //System.out.println(move[0] + "," + move[1] + "," + move[2] + "," + move[3] + "," + move[4]);
            if(AllMoves.get(0)[4] == move[4]){
                BestMoves.add(move);
            }  
        }

        selectedMove = BestMoves.get((int)Math.floor(Math.random()*BestMoves.size()));
        messageToSend.put("action", "move");
        dataOut.put("board_id", receivedTurn.getJSONObject("data").get("board_id"));
        dataOut.put("turn_token", receivedTurn.getJSONObject("data").get("turn_token"));
        dataOut.put("from_row", selectedMove[0]);
        dataOut.put("from_col", selectedMove[1]);
        dataOut.put("to_row",  selectedMove[2]);
        dataOut.put("to_col",  selectedMove[3]);
        messageToSend.put("data", dataOut);                    

        return messageToSend;
    }
    
    public static List<List<ChessPiece>> generateBoard(String boardString){
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
