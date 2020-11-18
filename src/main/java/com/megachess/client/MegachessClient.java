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

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.json.JSONObject;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;

import org.java_websocket.handshake.ServerHandshake;

/**
 *
 * @author Emile
 */
public class MegachessClient extends WebSocketClient{
    
    private int expectedDimension = 16;

    public MegachessClient(URI serverUri, Draft draft) {
            super(serverUri, draft);
    }

    public MegachessClient(URI serverURI) {
            super(serverURI);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
            System.out.println("new connection opened");
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
            System.out.println("closed with exit code " + code + " additional info: " + reason);
    }

    @Override
    public void onMessage(String message) {

        JSONObject receivedMessage = new JSONObject(message); //Mensaje Recibido.
        JSONObject messageToSend = new JSONObject();    //Respuesta.
        JSONObject data = new JSONObject(); //Objeto de data en la respuesta.

        char[][] boardMatrix = new char[this.expectedDimension][this.expectedDimension];
        

        String event = receivedMessage.getString("event"); 

        switch (event){
            case "update_user_list":

                System.out.println(receivedMessage.toString());
                messageToSend.put("action", "challenge"); //Solucion temporal
                data.put("username", "Emile");
                data.put("message", "Te reto a un duelo.");
                messageToSend.put("data", data);
                System.out.println(messageToSend.toString());
                try{
                    send(messageToSend.toString());
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
                break;

            case "ask_challenge":

                System.out.println(receivedMessage.toString());
                messageToSend.put("action", "accept_challenge");
                data.put("board_id", receivedMessage.getJSONObject("data").get("board_id"));
                messageToSend.put("data", data);
                System.out.println(messageToSend.toString());
                try{
                    send(messageToSend.toString());
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
                break;

            case "your_turn":

                System.out.println(receivedMessage.toString());
                data = receivedMessage.getJSONObject("data");
                String boardString = data.getString("board");
                List<int[]> AllMoves = new ArrayList<>();
                List<List<ChessPiece>> Board = new ArrayList<>();
                
                
                for(int row = 0; row < this.expectedDimension; row++){
                    List<ChessPiece> BoardRow = new ArrayList<>();
                    for(int column = 0; column < this.expectedDimension; column++){
                        switch(boardString.charAt(row*this.expectedDimension + column)){
                            case 'p':
                               BoardRow.add(new Pawn(row,column,"black"));
                               break;
                            case 'P':
                                BoardRow.add(new Pawn(row,column,"white"));
                                break;                               
                           case 'r':
                               BoardRow.add(new Rook(row,column,"black")); //TODO
                               break;
                            case 'R':
                                BoardRow.add(new Rook(row,column,"white")); //TODO
                                break;                               
                           case 'h':
                               BoardRow.add(new Knight(row,column,"black")); //TODO
                               break;
                            case 'H':
                                BoardRow.add(new Knight(row,column,"white")); //TODO
                                break;                               
                           case 'b':
                               BoardRow.add(new Bishop(row,column,"black")); //TODO
                               break;
                            case 'B':
                                BoardRow.add(new Bishop(row,column,"white")); //TODO
                                break;                               
                           case 'q':
                               BoardRow.add(new Queen(row,column,"black")); //TODO
                               break;
                            case 'Q':
                                BoardRow.add(new Queen(row,column,"white")); //TODO
                                break;                               
                           case 'k':
                               BoardRow.add(new King(row,column,"black")); //TODO
                               break;                          
                            case 'K':
                                BoardRow.add(new King(row,column,"white")); //TODO
                                break;
                            default:
                                BoardRow.add(new NullPiece(row,column));
                                break;                        
                        }                        
                    }
                    Board.add(BoardRow);
                }
                
               

                for(List<ChessPiece> Row : Board){
                    for(ChessPiece Piece : Row){
                        if(data.getString("actual_turn").equals(Piece.getColor())){
                            Piece.calculatePossibleMoves(Board);
                            Piece.calculatePossibleAttacks(Board);
                            AllMoves.addAll(Piece.getPossibleMoves());
                            AllMoves.addAll(Piece.getPossibleAttacks()); 
                        }
                                             
                    }

                }
               
                Collections.sort(AllMoves, new MoveComparator().reversed());
                
                messageToSend.put("action", "move");
                data.put("board_id", receivedMessage.getJSONObject("data").get("board_id"));
                data.put("turn_token", receivedMessage.getJSONObject("data").get("turn_token"));
                data.put("from_row", AllMoves.get(0)[0]);
                data.put("from_col",  AllMoves.get(0)[1]);
                data.put("to_row",  AllMoves.get(0)[2]);
                data.put("to_col",  AllMoves.get(0)[3]);
                messageToSend.put("data", data);
                //System.out.println(messageToSend.toString());
                try{
                    send(messageToSend.toString());
                }catch(Exception e){
                    System.out.println(e.toString());
                }
                break;


            case "gameover":

                System.out.println(receivedMessage.toString());
                break;
        }
    }

    @Override
    public void onMessage(ByteBuffer message) {
        System.out.println("received ByteBuffer");
    }

    @Override
    public void onError(Exception ex) {
            System.err.println("an error occurred:" + ex);
    }

    public static void main(String[] args) throws URISyntaxException {		
            WebSocketClient client = new MegachessClient(new URI("ws://megachess.herokuapp.com/service?authtoken=41144025-261a-4fc6-abd5-04a32350df97"));
            client.connect();
    }
}
