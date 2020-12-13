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
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 *
 * @author Emile
 */
public class BoardTest {
    
    public BoardTest() {
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

    //////////////////////////////MOVE PIECE TEST/////////////////////////////////////////////////////////////////////////////////
    
    static Stream<Arguments> testMovePiece_Parameters(){   
        //Test 1 pawn movement
        Board ResultBoard1 = new Board("                                                                                   p                                                                                                                                                                            ");
        //Test 2 pawn attack
        Board ResultBoard2 = new Board("                                                                                    p                                                                                                                                                                           ");
        //Test 3 black pawn promotion
        Board ResultBoard3 = new Board("                                                                                                                   q                                                                                                                                            ");
        //Test 4 white pawn promotion
        Board ResultBoard4 = new Board("                                                                                                                                        Q                                                                                                                       ");
        //Test 5 general piece movement
        Board ResultBoard5 = new Board("Q                                                                                                                                                                                                                                                               ");
        return Stream.of(
                Arguments.of("                                                                   p                                                                                                                                                                                            ", 4, 3, 5, 3, ResultBoard1),
                Arguments.of("                                                                   p                R                                                                                                                                                                           ", 4, 3, 5, 4, ResultBoard2),
                Arguments.of("                                                                                                   p                                                                                                                                                            ", 6, 3, 7, 3, ResultBoard3),
                Arguments.of("                                                                                                                                                        P                                                                                                       ", 9, 8, 8, 8, ResultBoard4),
                Arguments.of("                                                                                                                                        Q                                                                                                                       ", 8, 8, 0, 0, ResultBoard5)                
        );
    }    
    
    
    @ParameterizedTest(name="Move Piece test run {index}")
    @MethodSource("testMovePiece_Parameters")
    public void testMovePiece(String boardString,int fromRow, int fromCol, int toRow, int toCol, Board ExpectedBoard){
        //Create board
        Board TestBoard = new Board(boardString);
        //Finished creating board
        
        TestBoard.movePiece(fromRow, fromCol, toRow, toCol);
        int row = 0;
        int col = 0;
        for(List<ChessPiece> Row : TestBoard.getBoardConfig()){
            for(ChessPiece Piece : Row){
                assertEquals(ExpectedBoard.getBoardConfig().get(row).get(col), Piece);
                col++;
            }
            col = 0;
            row++;
        }
        
    }

    //////////////////////////////UNDO MOVE TEST/////////////////////////////////////////////////////////////////////////////////
    
    static Stream<Arguments> testUndoMove_Parameters(){   
        //Test 1 undo move
        Board ResultBoard1 = new Board("                                                                   p                                                                                                                                                                                            ");
        //Test 2 undo attack
        Board ResultBoard2 = new Board("                                                                   p                R                                                                                                                                                                           ");
        //Test 3 undo pawn promotion
        Board ResultBoard3 = new Board("                                                                                                   p                                                                                                                                                            ");
        return Stream.of(
                Arguments.of("                                                                   p                                                                                                                                                                                            ", 4, 3, 5, 3, ResultBoard1),
                Arguments.of("                                                                   p                R                                                                                                                                                                           ", 4, 3, 5, 4, ResultBoard2),
                Arguments.of("                                                                                                   p                                                                                                                                                            ", 6, 3, 7, 3, ResultBoard3)              
        );
    }    
    
    
    @ParameterizedTest(name="Undo move test run {index}")
    @MethodSource("testUndoMove_Parameters")
    public void testUndoMove(String boardString,int fromRow, int fromCol, int toRow, int toCol, Board ExpectedBoard){
        //Create board
        Board TestBoard = new Board(boardString);
        //Finished creating board
        
        TestBoard.movePiece(fromRow, fromCol, toRow, toCol);
        TestBoard.undoMovePiece();
        int row = 0;
        int col = 0;
        for(List<ChessPiece> Row : TestBoard.getBoardConfig()){
            for(ChessPiece Piece : Row){
                assertEquals(ExpectedBoard.getBoardConfig().get(row).get(col), Piece);
                col++;
            }
            col = 0;
            row++;
        }
        
    }

    //////////////////////////////EVALUATE BOARD TEST/////////////////////////////////////////////////////////////////////////////////
    
    static Stream<Arguments> testEvaluateBoard_Parameters(){
        int testResult1 = -390; //All black board
        int testResult2 = 390; //All white board
        int testResult3 = 0; //Zero value board      
        return Stream.of(
                Arguments.of("                r b h q k h b r                                                                        p                                                                                                                                                        ", testResult1),
                Arguments.of("                R B H Q K H B R                                                                        P                                                                                                                                                        ", testResult2),
                Arguments.of("                r b h q k h b r                                                                        p                                               P                                                                        R B H Q K H B R                 ", testResult3)              
        );
    }    
    
    
    @ParameterizedTest(name="Evaluate board test run {index}")
    @MethodSource("testEvaluateBoard_Parameters")
    public void testEvaluateBoard(String boardString, int expectedValue){
        //Create board
        Board TestBoard = new Board(boardString);
        //Finished creating board
        
        assertEquals(expectedValue, TestBoard.evaluateBoardConfig());
        
    }

    /**
     * Test of generateBoardConfig method, of class Board.
     */
    @Test
    public void testGenerateBoardConfig() {
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
        
        List<List<ChessPiece>> result = Board.generateBoardConfig(boardString);
        for(int i = 0; i < 16; i++){
            assertArrayEquals(expResult.get(i).toArray(), result.get(i).toArray());
        }
    }
    
}
