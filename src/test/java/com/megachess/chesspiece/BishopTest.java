/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megachess.chesspiece;

import com.megachess.board.Board;
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
public class BishopTest {
    
    public BishopTest() {
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

    //////////////////////////////CONSTRUCTOR TEST/////////////////////////////////////////////////////////////////////////////////
    
    static Stream<Arguments> testBishop_Parameters(){  
        return Stream.of(
                Arguments.of(1,2,"white"),
                Arguments.of(1,2,"black")              
        );
    }    
    
    
    @ParameterizedTest(name="Bishop constructor test run {index}")
    @MethodSource("testBishop_Parameters")
    public void testBishop(int currentRow, int currentCol, String color){
       
        Bishop TestBishop = new Bishop(currentRow,currentCol,color);
        assertEquals(currentRow, TestBishop.getCurrentRow());
        assertEquals(currentCol, TestBishop.getCurrentCol());
        assertEquals(color, TestBishop.getColor());
        assertEquals(40, TestBishop.getPointsForMove());
        assertEquals(400, TestBishop.getPointsForKill());
        
    }     
    
    //////////////////////////////MOVE RIGHT AND UP TEST/////////////////////////////////////////////////////////////////////////////////
    
    static Stream<Arguments> testMoveRightAndUp_Parameters(){
        Pawn AuxPawn = new Pawn(0,0,"");
        Bishop AuxBishop = new Bishop(0,0,"");
        List<int[]> TestResult1 = new ArrayList<>(); //Adjacent piece of same color, no moves should be generated
        List<int[]> TestResult2 = new ArrayList<>(); //Piece of different color, with possible movement and attack.
        TestResult2.add(new int[] {3,12,2,13,AuxBishop.getPointsForMove()});
        TestResult2.add(new int[] {3,12,1,14,AuxPawn.getPointsForKill()});
        List<int[]> TestResult3 = new ArrayList<>(); //Piece of same color but with possible movement
        TestResult3.add(new int[] {3,12,2,13,AuxBishop.getPointsForMove()});
        List<int[]> TestResult4 = new ArrayList<>(); //No Pieces in the way, moves till the border
        TestResult4.add(new int[] {3,12,2,13,AuxBishop.getPointsForMove()});
        TestResult4.add(new int[] {3,12,1,14,AuxBishop.getPointsForMove()});
        TestResult4.add(new int[] {3,12,0,15,AuxBishop.getPointsForMove()});
        List<int[]> TestResult5 = new ArrayList<>(); //Piece on the border, no move possible
        
        return Stream.of(
                Arguments.of("                                             p              b                                                                                                                                                                                                   ", 3, 12, TestResult1),
                Arguments.of("                              P                             b                                                                                                                                                                                                   ", 3, 12, TestResult2),
                Arguments.of("                              p                             b                                                                                                                                                                                                   ", 3, 12, TestResult3),
                Arguments.of("                                                            b                                                                                                                                                                                                   ", 3, 12, TestResult4),
                Arguments.of("               b                                                                                                                                                                                                                                                ", 0, 15, TestResult5)              
        );
    }    
    
    
    @ParameterizedTest(name="Bishop move right and up test run {index}")
    @MethodSource("testMoveRightAndUp_Parameters")
    public void testMoveRightAndUp(String boardString, int currentRow, int currentCol, List<int[]> expectedMoves){
        //Create board
        List<List<ChessPiece>> TestBoard = Board.generateBoardConfig(boardString);
        //Finished creating board
        
        Bishop TestBishop = new Bishop(currentRow,currentCol,"black");
        TestBishop.moveRightAndUp(currentRow - 1, currentCol + 1, TestBoard);
        assertArrayEquals(expectedMoves.toArray(), TestBishop.getPossibleMoves().toArray());
        
    }
    
    //////////////////////////////MOVE RIGHT AND DOWN TEST/////////////////////////////////////////////////////////////////////////////////
    
    static Stream<Arguments> testMoveRightAndDown_Parameters(){
        Pawn AuxPawn = new Pawn(0,0,"");
        Bishop AuxBishop = new Bishop(0,0,"");
        List<int[]> TestResult1 = new ArrayList<>(); //Adjacent piece of same color, no moves should be generated
        List<int[]> TestResult2 = new ArrayList<>(); //Piece of different color, with possible movement and attack.
        TestResult2.add(new int[] {12,12,13,13,AuxBishop.getPointsForMove()});
        TestResult2.add(new int[] {12,12,14,14,AuxPawn.getPointsForKill()});
        List<int[]> TestResult3 = new ArrayList<>(); //Piece of same color but with possible movement
        TestResult3.add(new int[] {12,12,13,13,AuxBishop.getPointsForMove()});
        List<int[]> TestResult4 = new ArrayList<>(); //No Pieces in the way, moves till the border
        TestResult4.add(new int[] {12,12,13,13,AuxBishop.getPointsForMove()});
        TestResult4.add(new int[] {12,12,14,14,AuxBishop.getPointsForMove()});
        TestResult4.add(new int[] {12,12,15,15,AuxBishop.getPointsForMove()});
        List<int[]> TestResult5 = new ArrayList<>(); //Piece on the border, no move possible
        
        return Stream.of(
                Arguments.of("                                                                                                                                                                                                            b                p                                  ", 12, 12, TestResult1),
                Arguments.of("                                                                                                                                                                                                            b                                 P                 ", 12, 12, TestResult2),
                Arguments.of("                                                                                                                                                                                                            b                                 p                 ", 12, 12, TestResult3),
                Arguments.of("                                                                                                                                                                                                            b                                                   ", 12, 12, TestResult4),
                Arguments.of("                                                                                                                                                                                                                                                               b", 15, 15, TestResult5)              
        );
    }    
    
    
    @ParameterizedTest(name="Bishop move right and down test run {index}")
    @MethodSource("testMoveRightAndDown_Parameters")
    public void testMoveRightAndDown(String boardString, int currentRow, int currentCol, List<int[]> expectedMoves){
        //Create board
        List<List<ChessPiece>> TestBoard = Board.generateBoardConfig(boardString);
        //Finished creating board
        
        Bishop TestBishop = new Bishop(currentRow,currentCol,"black");
        TestBishop.moveRightAndDown(currentRow + 1, currentCol + 1, TestBoard);
        assertArrayEquals(expectedMoves.toArray(), TestBishop.getPossibleMoves().toArray());
        
    }    
    
    //////////////////////////////MOVE LEFT AND UP TEST/////////////////////////////////////////////////////////////////////////////////
    
    static Stream<Arguments> testMoveLeftAndUp_Parameters(){
        Pawn AuxPawn = new Pawn(0,0,"");
        Bishop AuxBishop = new Bishop(0,0,"");
        List<int[]> TestResult1 = new ArrayList<>(); //Adjacent piece of same color, no moves should be generated
        List<int[]> TestResult2 = new ArrayList<>(); //Piece of different color, with possible movement and attack.
        TestResult2.add(new int[] {3,3,2,2,AuxBishop.getPointsForMove()});
        TestResult2.add(new int[] {3,3,1,1,AuxPawn.getPointsForKill()});
        List<int[]> TestResult3 = new ArrayList<>(); //Piece of same color but with possible movement
        TestResult3.add(new int[] {3,3,2,2,AuxBishop.getPointsForMove()});
        List<int[]> TestResult4 = new ArrayList<>(); //No Pieces in the way, moves till the border
        TestResult4.add(new int[] {3,3,2,2,AuxBishop.getPointsForMove()});
        TestResult4.add(new int[] {3,3,1,1,AuxBishop.getPointsForMove()});
        TestResult4.add(new int[] {3,3,0,0,AuxBishop.getPointsForMove()});
        List<int[]> TestResult5 = new ArrayList<>(); //Piece on the border, no move possible
        
        return Stream.of(
                Arguments.of("                                  p                b                                                                                                                                                                                                            ", 3, 3, TestResult1),
                Arguments.of("                 P                                 b                                                                                                                                                                                                            ", 3, 3, TestResult2),
                Arguments.of("                 p                                 b                                                                                                                                                                                                            ", 3, 3, TestResult3),
                Arguments.of("                                                   b                                                                                                                                                                                                            ", 3, 3, TestResult4),
                Arguments.of("b                                                                                                                                                                                                                                                               ", 0, 0, TestResult5)              
        );
    }    
    
    
    @ParameterizedTest(name="Bishop move left and up test run {index}")
    @MethodSource("testMoveLeftAndUp_Parameters")
    public void testMoveLeftAndUp(String boardString, int currentRow, int currentCol, List<int[]> expectedMoves){
        //Create board
        List<List<ChessPiece>> TesBoard = Board.generateBoardConfig(boardString);
        //Finished creating board
        
        Bishop TestBishop = new Bishop(currentRow,currentCol,"black");
        TestBishop.moveLeftAndUp(currentRow - 1, currentCol - 1, TesBoard);
        assertArrayEquals(expectedMoves.toArray(), TestBishop.getPossibleMoves().toArray());
        
    }  

    //////////////////////////////MOVE LEFT AND DOWN TEST/////////////////////////////////////////////////////////////////////////////////
    
    static Stream<Arguments> testMoveLeftAndDown_Parameters(){
        Pawn AuxPawn = new Pawn(0,0,"");
        Bishop AuxBishop = new Bishop(0,0,"");
        List<int[]> TestResult1 = new ArrayList<>(); //Adjacent piece of same color, no moves should be generated
        List<int[]> TestResult2 = new ArrayList<>(); //Piece of different color, with possible movement and attack.
        TestResult2.add(new int[] {12,3,13,2,AuxBishop.getPointsForMove()});
        TestResult2.add(new int[] {12,3,14,1,AuxPawn.getPointsForKill()});
        List<int[]> TestResult3 = new ArrayList<>(); //Piece of same color but with possible movement
        TestResult3.add(new int[] {12,3,13,2,AuxBishop.getPointsForMove()});
        List<int[]> TestResult4 = new ArrayList<>(); //No Pieces in the way, moves till the border
        TestResult4.add(new int[] {12,3,13,2,AuxBishop.getPointsForMove()});
        TestResult4.add(new int[] {12,3,14,1,AuxBishop.getPointsForMove()});
        TestResult4.add(new int[] {12,3,15,0,AuxBishop.getPointsForMove()});
        List<int[]> TestResult5 = new ArrayList<>(); //Piece on the border, no move possible
        
        return Stream.of(
                Arguments.of("                                                                                                                                                                                                   b              p                                             ", 12, 3, TestResult1),
                Arguments.of("                                                                                                                                                                                                   b                             P                              ", 12, 3, TestResult2),
                Arguments.of("                                                                                                                                                                                                   b                             p                              ", 12, 3, TestResult3),
                Arguments.of("                                                                                                                                                                                                   b                                                            ", 12, 3, TestResult4),
                Arguments.of("                                                                                                                                                                                                                                                b               ", 15, 0, TestResult5)              
        );
    }    
    
    
    @ParameterizedTest(name="Bishop move left and down test run {index}")
    @MethodSource("testMoveLeftAndDown_Parameters")
    public void testMoveLeftAndDown(String boardString, int currentRow, int currentCol, List<int[]> expectedMoves){
        //Create board
        List<List<ChessPiece>> TestBoard = Board.generateBoardConfig(boardString);
        //Finished creating board
        
        Bishop TestBishop = new Bishop(currentRow,currentCol,"black");
        TestBishop.moveLeftAndDown(currentRow + 1, currentCol - 1, TestBoard);
        assertArrayEquals(expectedMoves.toArray(), TestBishop.getPossibleMoves().toArray());
        
    } 
    
    //////////////////////////////CALCULATE POSSIBLE MOVES TEST/////////////////////////////////////////////////////////////////////////////////
    
    static Stream<Arguments> testCalculatePossibleMoves_Parameters(){
        Pawn AuxPawn = new Pawn(0,0,"");
        List<int[]> TestResult1 = new ArrayList<>(); //Surrounded by pieces of same color, no moves should be generated
        List<int[]> TestResult2 = new ArrayList<>(); //Surrounded by pieces of different color, all possible attacks.
        TestResult2.add(new int[] {7,7,8,6,AuxPawn.getPointsForKill()}); //Down and left
        TestResult2.add(new int[] {7,7,6,6,AuxPawn.getPointsForKill()}); //Up and left
        TestResult2.add(new int[] {7,7,6,8,AuxPawn.getPointsForKill()}); //Up and right
        TestResult2.add(new int[] {7,7,8,8,AuxPawn.getPointsForKill()}); //Down and right
        
        return Stream.of(
                Arguments.of("                                                                                                      ppp             pbp             ppp                                                                                                                       ", 7, 7, TestResult1),
                Arguments.of("                                                                                                      PPP             PbP             PPP                                                                                                                       ", 7, 7, TestResult2)             
        );
    }    
    
    
    @ParameterizedTest(name="Bishop calculatePossibleMoves test run {index}")
    @MethodSource("testCalculatePossibleMoves_Parameters")
    public void testCalculatePossbileMoves(String boardString, int currentRow, int currentCol, List<int[]> expectedMoves){
        //Create board
        List<List<ChessPiece>> TestBoard = Board.generateBoardConfig(boardString);
        //Finished creating board
        
        Bishop TestBishop = new Bishop(currentRow,currentCol,"black");
        TestBishop.calculatePossibleMoves(TestBoard);
        assertArrayEquals(expectedMoves.toArray(), TestBishop.getPossibleMoves().toArray());
        
    }    
    
    /**
     * Test of isNull method, of class Bishop.
     */
    @Test
    public void testIsNull() {
        Bishop instance = new Bishop(0,0,"");
        boolean expResult = false;
        boolean result = instance.isNull();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of positionBias method, of class Bishop.
     */  
    @Test
    public void testPositionBias(){
        Bishop testBishop = new Bishop(1,4,"white");
        assertEquals(0,testBishop.positionBias());
    }    
    
    /**
     * Test of copy method, of class Bishop.
     */  
    @Test
    public void testCopy(){
        Bishop testBishop = new Bishop(1,4,"white");
        Bishop testBishopCopy = (Bishop)testBishop.copy();
        assertEquals(testBishop.getCurrentRow(),testBishopCopy.getCurrentRow());
        assertEquals(testBishop.getCurrentCol(),testBishopCopy.getCurrentCol());
        assertEquals(testBishop.getColor(),testBishopCopy.getColor());
    }
    
}
