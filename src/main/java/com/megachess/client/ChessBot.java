/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megachess.client;

import com.megachess.board.Board;
import com.megachess.chesspiece.ChessPiece;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Emile
 */
public class ChessBot{
    
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
        
        /*
        for(int i = 0; i < boardString.length(); i = i + 16){
            System.out.println(boardString.substring(i, i+15));
        }
        System.out.println();
        */
        Board InitialBoard = new Board(boardString);
        int[] selectedMove;

        selectedMove = calculateBestMove(3, InitialBoard, receivedTurn.getJSONObject("data").getString("actual_turn"), Integer.MIN_VALUE, Integer.MAX_VALUE);

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
    
    public int[] calculateBestMove(int depth, Board Board, String currentColor, int alpha, int beta){
               
        List<int[]> AllMoves = new ArrayList<>();
        int moveValue, bestMoveValue = currentColor.equals("black")? Integer.MAX_VALUE : Integer.MIN_VALUE;
        int[] bestMove = null;
        int maxChildren = 1000, childCount = 0;
        
        if(depth == 0){
            moveValue = Board.evaluateBoardConfig();
            //System.out.println("Depth:" + depth + ", bestMoveValue:" + moveValue + ", Color: " + currentColor);           
            return new int[] {0, 0, 0, 0, moveValue};
        }

        for(List<ChessPiece> Row : Board.getBoardConfig()){
            for(ChessPiece Piece : Row){
                if(Piece.getColor().equals(currentColor)){
                    Piece.calculatePossibleMoves(Board.getBoardConfig());
                    AllMoves.addAll(Piece.getPossibleMoves());
                }                   
            }
        }
        //System.out.println(depth + "," + AllMoves.size() + "," + currentColor);
        Collections.sort(AllMoves, new MoveComparator().reversed());
        
        for (int[] move : AllMoves){
            Board.movePiece(move[0], move[1], move[2], move[3]);
            childCount++;
            moveValue = calculateBestMove(depth - 1, Board, currentColor.equals("black")? "white":"black",alpha,beta)[4];
            
            if(currentColor.equals("black")){ //Minimizing player
                if(moveValue < bestMoveValue){
                    bestMoveValue = moveValue;
                    bestMove = move;
                }
                beta = Math.min(beta, moveValue);
            }else{ //Maximizing player
                if(moveValue > bestMoveValue){
                    bestMoveValue = moveValue;
                    bestMove = move;
                }
                alpha = Math.max(alpha, moveValue);
            }
            Board.undoMovePiece();
            //System.out.println("Depth:" + depth + ", bestMoveValue:" + bestMoveValue + ", Color: " + currentColor);
            
            if(beta <= alpha){
                //System.out.println("prune");
                break;
            }
            if(childCount >= maxChildren){
                System.out.println("Max child reached");
                break;
            }
            
        }
        return new int[] {bestMove[0], bestMove[1], bestMove[2], bestMove[3], bestMoveValue};
    }
    
}
