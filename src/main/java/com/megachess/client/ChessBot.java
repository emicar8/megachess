/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megachess.client;

import com.megachess.board.Board;
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
    
    public JSONObject acceptChallenge(JSONObject receivedChallenge) throws JSONException{
        
        JSONObject messageToSend = new JSONObject();    //Sent message.
        JSONObject dataOut = new JSONObject(); //Out data object

        messageToSend.put("action", "accept_challenge");
        dataOut.put("board_id", receivedChallenge.getJSONObject("data").get("board_id"));
        messageToSend.put("data", dataOut);            
        
        return messageToSend;
        
    }
    
    public JSONObject myTurn(JSONObject receivedTurn) throws JSONException, IndexOutOfBoundsException{

        JSONObject messageToSend = new JSONObject();    //Sent message.
        JSONObject dataOut = new JSONObject(); //Out data object
        String boardString = receivedTurn.getJSONObject("data").getString("board");

        List<Board> BoardList = new ArrayList<>();
        BoardList.add(new Board(boardString));
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
    
    public int[] calculateBestMove(int depth, List<Board> BoardList, String currentColor){
        
        Board LastBoard =  BoardList.get(BoardList.size() - 1);
        List<int[]> AllMoves = new ArrayList<>();
        
        for(List<ChessPiece> Row : LastBoard.getBoardConfig()){
            for(ChessPiece Piece : Row){
                if(Piece.getColor().equals(currentColor)){
                    Piece.calculatePossibleMoves(LastBoard.getBoardConfig());
                    AllMoves.addAll(Piece.getPossibleMoves());
                }                   
            }
        }
        
        
        return null;
    }
    
}
