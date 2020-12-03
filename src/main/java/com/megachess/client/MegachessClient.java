/*
 * Copyright (c) 2010-2020 Nathan Rajlich
 *
 *  Permission is hereby granted, free of charge, to any person
 *  obtaining a copy of this software and associated documentation
 *  files (the "Software"), to deal in the Software without
 *  restriction, including without limitation the rights to use,
 *  copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the
 *  Software is furnished to do so, subject to the following
 *  conditions:
 *
 *  The above copyright notice and this permission notice shall be
 *  included in all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 *  OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *  NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 *  HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 *  WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 *  FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 *  OTHER DEALINGS IN THE SOFTWARE.
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
import java.util.Collections;
import java.util.List;

import org.json.JSONObject;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;

import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;

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

        JSONObject receivedMessage = new JSONObject(message); //Received message
        JSONObject messageToSend = new JSONObject();    //Sent message.
        JSONObject dataIn = new JSONObject(); //In data object
        JSONObject dataOut = new JSONObject(); //Out data object
        
        String event = receivedMessage.getString("event"); 

        switch (event){
            case "ask_challenge":
                
                System.out.println(receivedMessage.toString());
                messageToSend.put("action", "accept_challenge");
                dataOut.put("board_id", receivedMessage.getJSONObject("data").get("board_id"));
                messageToSend.put("data", dataOut);
                System.out.println(messageToSend.toString());
                send(messageToSend.toString());
                break;

            case "your_turn":
                //System.out.println(receivedMessage.toString());
                dataIn = receivedMessage.getJSONObject("data");
                String boardString = dataIn.getString("board");
                List<int[]> AllMoves = new ArrayList<>();
                List<int[]> BestMoves = new ArrayList<>();
                List<List<ChessPiece>> Board = new ArrayList<>();
                int[] selectedMove;
                
                
                /*for(int row = 0; row < this.expectedDimension; row++){
                    System.out.println(boardString.substring(row*16, row*16 + 16));
                }*/
                
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
                
                for(List<ChessPiece> Row : Board){
                    for(ChessPiece Piece : Row){
                        if(dataIn.getString("actual_turn").equals(Piece.getColor())){
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
                
                try{
                    messageToSend.put("action", "move");
                    dataOut.put("board_id", receivedMessage.getJSONObject("data").get("board_id"));
                    dataOut.put("turn_token", receivedMessage.getJSONObject("data").get("turn_token"));
                    dataOut.put("from_row", selectedMove[0]);
                    dataOut.put("from_col", selectedMove[1]);
                    dataOut.put("to_row",  selectedMove[2]);
                    dataOut.put("to_col",  selectedMove[3]);
                    messageToSend.put("data", dataOut);                    
                }catch(JSONException e){
                    System.out.println(e.toString());
                }
                
                //System.out.println(messageToSend.toString());
                send(messageToSend.toString());
                break;

            case "update_user_list":
            case "gameover":
            default:
                
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
            WebSocketClient client = new MegachessClient(new URI("ws://megachess.herokuapp.com/service?authtoken=b5b7dec8-5668-4909-83db-5b4db4bf81e9"));
            client.connect();
    }
}
