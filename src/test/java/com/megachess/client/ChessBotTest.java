/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megachess.client;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Emile
 */
public class ChessBotTest {
    
    public ChessBotTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of acceptChallenge method, of class ChessBot.
     */
    @Test
    public void testAcceptChallenge() {
        JSONObject receivedChallenge = new JSONObject("{\"action\":\"ask_challenge\",\"data\":{\"username\": \"gabriel\",\"board_id\":\"2d348323-2e79-4961-ac36-1b000e8c42a5\"}}");
        ChessBot instance = new ChessBot();
        JSONObject expResult = new JSONObject("{\"data\":{\"board_id\":\"2d348323-2e79-4961-ac36-1b000e8c42a5\"},\"action\":\"accept_challenge\"}");
        JSONObject result = instance.acceptChallenge(receivedChallenge);
        assertTrue(expResult.similar(result));
    }

    /**
     * Test of acceptChallenge exception handling, of class ChessBot.
     */
    @Test
    public void testAcceptChallengeException() {
        JSONObject receivedChallenge = new JSONObject("{\"action\":\"ask_challenge\",\"da\":{\"username\": \"gabriel\",\"board_id\":\"2d348323-2e79-4961-ac36-1b000e8c42a5\"}}"); //Broken json message
        ChessBot instance = new ChessBot();
        assertThrows(JSONException.class, ()->{
            instance.acceptChallenge(receivedChallenge);
        });
    }  
    
    /**
     * Test of myTurn method, of class ChessBot.
     */
    
    @Test
    public void testMyTurn() {
        JSONObject receivedTurn = new JSONObject("{\"event\":\"your_turn\","
                                                + "\"data\":{\"board_id\":\"2d348323-2e79-4961-ac36-1b000e8c42a5\","
                                                          + "\"turn_token\":\"e40573bb-138f-4171-a200-66258f546755\","
                                                          + "\"username\":\"gabriel\","
                                                          + "\"actual_turn\":\"white\","
                                                          + "\"board\":\"                                                                   p       p  p                                                                                                                 PPPPPPPPPPPPPPPP                                                \","
                                                          + "\"move_left\":19,"
                                                          + "\"opponent_username\": \"maria\"}}"); //Board only has one move possible
        ChessBot instance = new ChessBot();
        JSONObject result = instance.myTurn(receivedTurn);
        JSONObject expResult = new JSONObject("{\"action\":\"move\","
                                             + "\"data\":{"
                                                  + "\"board_id\":\"2d348323-2e79-4961-ac36-1b000e8c42a5\","
                                                  + "\"turn_token\":\"e40573bb-138f-4171-a200-66258f546755\","
                                                  + "\"from_row\":12,"
                                                  + "\"from_col\":5,"
                                                  + "\"to_row\":10,"
                                                  + "\"to_col\":5}}");
        assertTrue(expResult.similar(result));
    }     

    /**
     * Test of generateBoard JSONException, of class ChessBot.
     */
    @Test
    public void testMyTurnJSONException() {

            JSONObject receivedTurn = new JSONObject("{\"event\":\"your_turn\","
                                                + "\"da\":{\"board_id\":\"2d348323-2e79-4961-ac36-1b000e8c42a5\","
                                                          + "\"turn_token\":\"e40573bb-138f-4171-a200-66258f546755\","
                                                          + "\"username\":\"gabriel\","
                                                          + "\"actual_turn\":\"black\","
                                                          + "\"board\":\"                                                                                                                                                                                                                                 P              b               \","
                                                          + "\"move_left\":19,"
                                                          + "\"opponent_username\": \"maria\"}}"); //Board only has one move possible
        ChessBot instance = new ChessBot();
        assertThrows(JSONException.class, ()->{
            instance.myTurn(receivedTurn);
        });
    }
 

    /**
     * Test of myTurn IndexOutOfBoundsException, of class ChessBot.
     */
    /*
    @Test
    public void testMyTurnIndexOutOfBounds() {

            JSONObject receivedTurn = new JSONObject("{\"event\":\"your_turn\","
                                                + "\"data\":{\"board_id\":\"2d348323-2e79-4961-ac36-1b000e8c42a5\","
                                                          + "\"turn_token\":\"e40573bb-138f-4171-a200-66258f546755\","
                                                          + "\"username\":\"gabriel\","
                                                          + "\"actual_turn\":\"black\","
                                                          + "\"board\":\"                                                                                                                                                                                                                                 P                              \","
                                                          + "\"move_left\":19,"
                                                          + "\"opponent_username\": \"maria\"}}"); //Board only has one move possible
        ChessBot instance = new ChessBot();
        assertThrows(IndexOutOfBoundsException.class, ()->{
            instance.myTurn(receivedTurn);
        });
    }  */     
    

      
    

    
    
}
