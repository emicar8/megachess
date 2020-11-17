/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megachess.client;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;

import org.json.JSONObject;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;

import org.java_websocket.handshake.ServerHandshake;

/**
 *
 * @author Emile
 */
public class MegachessClient extends WebSocketClient{
    
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
            
            String event = receivedMessage.getString("event"); 
            
            switch (event){
                case "update_user_list":

                    System.out.println(receivedMessage.toString());
                    messageToSend.put("action", "challenge");
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
                    messageToSend.put("action", "move");
                    data.put("board_id", receivedMessage.getJSONObject("data").get("board_id"));
                    data.put("turn_token", receivedMessage.getJSONObject("data").get("turn_token"));
                    data.put("from_row", Math.floor(Math.random()*16));
                    data.put("from_col", Math.floor(Math.random()*16));
                    data.put("to_row", Math.floor(Math.random()*16));
                    data.put("to_col", Math.floor(Math.random()*16));
                    messageToSend.put("data", data);
                    System.out.println(messageToSend.toString());
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
