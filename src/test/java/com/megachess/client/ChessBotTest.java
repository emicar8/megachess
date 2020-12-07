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
import java.util.List;
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
        String expResult = "{\"data\":{\"board_id\":\"2d348323-2e79-4961-ac36-1b000e8c42a5\"},\"action\":\"accept_challenge\"}";
        String result = instance.acceptChallenge(receivedChallenge);
        assertEquals(expResult, result);
    }

    /**
     * Test of acceptChallenge exception handling, of class ChessBot.
     */
    @Test
    public void testAcceptChallengeException() {
        JSONObject receivedChallenge = new JSONObject("{\"action\":\"ask_challenge\",\"da\":{\"username\": \"gabriel\",\"board_id\":\"2d348323-2e79-4961-ac36-1b000e8c42a5\"}}"); //Broken json message
        ChessBot instance = new ChessBot();
        String result = instance.acceptChallenge(receivedChallenge);
        assertNull(result);
    }    
    

    /**
     * Test of generateBoard method, of class ChessBot.
     */
    @Test
    public void testGenerateBoard() {

        String boardString = "abcdefghijklmnopqrstuvwxyz                                                                                                                      123456789                                                                             ZYXWVUTSRQPONMLKJIHGFEDCBA";
        List<List<ChessPiece>> expResult = new ArrayList<>();
        for( int i = 0; i < 16; i++){
            List<ChessPiece> Row = new ArrayList<>();
            for( int j = 0; j < 16; j++){
                Row.add(new NullPiece(i,j));
            }
            expResult.add(Row);
        }
        Bishop BlackBishop = new Bishop(0,1,"black");
        Knight BlackKnight = new Knight(0,7,"black");
        King BlackKing = new King(0,10,"black");
        Pawn BlackPawn = new Pawn(0,15,"black");
        Queen BlackQueen = new Queen(1,0,"black");
        Rook BlackRook = new Rook(1,1,"black");
        Bishop WhiteBishop = new Bishop(15,14,"white");
        Knight WhiteKnight = new Knight(15,8,"white");
        King WhiteKing = new King(15,5,"white");
        Pawn WhitePawn = new Pawn(15,0,"white");
        Queen WhiteQueen = new Queen(14,15,"white");
        Rook WhiteRook = new Rook(14,14,"white");
        
        expResult.get(0).set(1, BlackBishop);
        expResult.get(0).set(7, BlackKnight);
        expResult.get(0).set(10, BlackKing);
        expResult.get(0).set(15, BlackPawn);
        expResult.get(1).set(0, BlackQueen);
        expResult.get(1).set(1, BlackRook);
        expResult.get(15).set(14, WhiteBishop);
        expResult.get(15).set(8, WhiteKnight);
        expResult.get(15).set(5, WhiteKing);
        expResult.get(15).set(0, WhitePawn);
        expResult.get(14).set(15, WhiteQueen);
        expResult.get(14).set(14, WhiteRook);
        
        List<List<ChessPiece>> result = ChessBot.generateBoard(boardString);
        for(int i = 0; i < 16; i++){
            assertArrayEquals(expResult.get(i).toArray(), result.get(i).toArray());
        }
    }
    
}
