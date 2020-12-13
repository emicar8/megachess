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
import org.junit.jupiter.params.ParameterizedTest;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 *
 * @author Emile
 */
public class QueenTest {
    
    public QueenTest() {
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
    
    static Stream<Arguments> testQueen_Parameters(){  
        return Stream.of(
                Arguments.of(1,2,"white"),
                Arguments.of(1,2,"black")              
        );
    }    
    
    
    @ParameterizedTest(name="Queen constructor test run {index}")
    @MethodSource("testQueen_Parameters")
    public void testQueen(int currentRow, int currentCol, String color){
       
        Queen TestQueen = new Queen(currentRow,currentCol,color);
        assertEquals(currentRow, TestQueen.getCurrentRow());
        assertEquals(currentCol, TestQueen.getCurrentCol());
        assertEquals(color, TestQueen.getColor());
        assertEquals(5, TestQueen.getPointsForMove());
        assertEquals(50, TestQueen.getPointsForKill());
        
    }     
    
    //////////////////////////////MOVE RIGHT TEST/////////////////////////////////////////////////////////////////////////////////
    
    static Stream<Arguments> testMoveRight_Parameters(){
        Pawn AuxPawn = new Pawn(0,0,"");
        Queen AuxQueen = new Queen(0,0,"");
        List<int[]> TestResult1 = new ArrayList<>(); //Adjacent piece of same color, no moves should be generated
        List<int[]> TestResult2 = new ArrayList<>(); //Piece of different color, with possible movement and attack.
        TestResult2.add(new int[] {8,12,8,13,AuxQueen.getPointsForMove()});
        TestResult2.add(new int[] {8,12,8,14,AuxPawn.getPointsForKill()});
        List<int[]> TestResult3 = new ArrayList<>(); //Piece of same color but with possible movement
        TestResult3.add(new int[] {8,12,8,13,AuxQueen.getPointsForMove()});
        List<int[]> TestResult4 = new ArrayList<>(); //No Pieces in the way, moves till the border
        TestResult4.add(new int[] {8,12,8,13,AuxQueen.getPointsForMove()});
        TestResult4.add(new int[] {8,12,8,14,AuxQueen.getPointsForMove()});
        TestResult4.add(new int[] {8,12,8,15,AuxQueen.getPointsForMove()});
        List<int[]> TestResult5 = new ArrayList<>(); //Piece on the border, no move possible
        
        return Stream.of(Arguments.of("                                                                                                                                            qp                                                                                                                  ", 8, 12, TestResult1),
                Arguments.of("                                                                                                                                            q P                                                                                                                 ", 8, 12, TestResult2),
                Arguments.of("                                                                                                                                            q p                                                                                                                 ", 8, 12, TestResult3),
                Arguments.of("                                                                                                                                            q                                                                                                                   ", 8, 12, TestResult4),
                Arguments.of("                                                                                                                                               q                                                                                                                ", 8, 15, TestResult5)              
        );
    }    
    
    
    @ParameterizedTest(name="Queen move right test run {index}")
    @MethodSource("testMoveRight_Parameters")
    public void testMoveRight(String boardString, int currentRow, int currentCol, List<int[]> expectedMoves){
        //Create board
        List<List<ChessPiece>> TestBoard = Board.generateBoardConfig(boardString);
        //Finished creating board
        
        Queen TestQueen = new Queen(currentRow,currentCol,"black");
        TestQueen.moveRight(currentRow, currentCol + 1, TestBoard);
        assertArrayEquals(expectedMoves.toArray(), TestQueen.getPossibleMoves().toArray());
        
    }
    
    //////////////////////////////MOVE DOWN TEST/////////////////////////////////////////////////////////////////////////////////
    
    static Stream<Arguments> testMoveDown_Parameters(){
        Pawn AuxPawn = new Pawn(0,0,"");
        Queen AuxQueen = new Queen(0,0,"");
        List<int[]> TestResult1 = new ArrayList<>(); //Adjacent piece of same color, no moves should be generated
        List<int[]> TestResult2 = new ArrayList<>(); //Piece of different color, with possible movement and attack.
        TestResult2.add(new int[] {12,12,13,12,AuxQueen.getPointsForMove()});
        TestResult2.add(new int[] {12,12,14,12,AuxPawn.getPointsForKill()});
        List<int[]> TestResult3 = new ArrayList<>(); //Piece of same color but with possible movement
        TestResult3.add(new int[] {12,12,13,12,AuxQueen.getPointsForMove()});
        List<int[]> TestResult4 = new ArrayList<>(); //No Pieces in the way, moves till the border
        TestResult4.add(new int[] {12,12,13,12,AuxQueen.getPointsForMove()});
        TestResult4.add(new int[] {12,12,14,12,AuxQueen.getPointsForMove()});
        TestResult4.add(new int[] {12,12,15,12,AuxQueen.getPointsForMove()});
        List<int[]> TestResult5 = new ArrayList<>(); //Piece on the border, no move possible
        
        return Stream.of(Arguments.of("                                                                                                                                                                                                            q               p                                   ", 12, 12, TestResult1),
                Arguments.of("                                                                                                                                                                                                            q                               P                   ", 12, 12, TestResult2),
                Arguments.of("                                                                                                                                                                                                            q                               p                   ", 12, 12, TestResult3),
                Arguments.of("                                                                                                                                                                                                            q                                                   ", 12, 12, TestResult4),
                Arguments.of("                                                                                                                                                                                                                                                            q   ", 15, 12, TestResult5)              
        );
    }    
    
    
    @ParameterizedTest(name="Queen move down test run {index}")
    @MethodSource("testMoveDown_Parameters")
    public void testMoveDown(String boardString, int currentRow, int currentCol, List<int[]> expectedMoves){
        //Create board
        List<List<ChessPiece>> TestBoard = Board.generateBoardConfig(boardString);
        //Finished creating board
        
        Queen TestQueen = new Queen(currentRow,currentCol,"black");
        TestQueen.moveDown(currentRow + 1, currentCol, TestBoard);
        assertArrayEquals(expectedMoves.toArray(), TestQueen.getPossibleMoves().toArray());
        
    }

    //////////////////////////////MOVE LEFT TEST/////////////////////////////////////////////////////////////////////////////////
    
    static Stream<Arguments> testMoveLeft_Parameters(){
        Pawn AuxPawn = new Pawn(0,0,"");
        Queen AuxQueen = new Queen(0,0,"");
        List<int[]> TestResult1 = new ArrayList<>(); //Adjacent piece of same color, no moves should be generated
        List<int[]> TestResult2 = new ArrayList<>(); //Piece of different color, with possible movement and attack.
        TestResult2.add(new int[] {12,3,12,2,AuxQueen.getPointsForMove()});
        TestResult2.add(new int[] {12,3,12,1,AuxPawn.getPointsForKill()});
        List<int[]> TestResult3 = new ArrayList<>(); //Piece of same color but with possible movement
        TestResult3.add(new int[] {12,3,12,2,AuxQueen.getPointsForMove()});
        List<int[]> TestResult4 = new ArrayList<>(); //No Pieces in the way, moves till the border
        TestResult4.add(new int[] {12,3,12,2,AuxQueen.getPointsForMove()});
        TestResult4.add(new int[] {12,3,12,1,AuxQueen.getPointsForMove()});
        TestResult4.add(new int[] {12,3,12,0,AuxQueen.getPointsForMove()});
        List<int[]> TestResult5 = new ArrayList<>(); //Piece on the border, no move possible
        
        return Stream.of(
                Arguments.of("                                                                                                                                                                                                  pq                                                            ", 12, 3, TestResult1),
                Arguments.of("                                                                                                                                                                                                 P q                                                            ", 12, 3, TestResult2),
                Arguments.of("                                                                                                                                                                                                 p q                                                            ", 12, 3, TestResult3),
                Arguments.of("                                                                                                                                                                                                   q                                                            ", 12, 3, TestResult4),
                Arguments.of("                                                                                                                                                                                                q                                                               ", 12, 0, TestResult5)              
        );
    }    
    
    
    @ParameterizedTest(name="Queen move left test run {index}")
    @MethodSource("testMoveLeft_Parameters")
    public void testMoveLeft(String boardString, int currentRow, int currentCol, List<int[]> expectedMoves){
        //Create board
        List<List<ChessPiece>> TestBoard = Board.generateBoardConfig(boardString);
        //Finished creating board
        
        Queen TestQueen = new Queen(currentRow,currentCol,"black");
        TestQueen.moveLeft(currentRow, currentCol - 1, TestBoard);
        assertArrayEquals(expectedMoves.toArray(), TestQueen.getPossibleMoves().toArray());
        
    }    

    //////////////////////////////MOVE UP TEST/////////////////////////////////////////////////////////////////////////////////
    
    static Stream<Arguments> testMoveUp_Parameters(){
        Pawn AuxPawn = new Pawn(0,0,"");
        Queen AuxQueen = new Queen(0,0,"");
        List<int[]> TestResult1 = new ArrayList<>(); //Adjacent piece of same color, no moves should be generated
        List<int[]> TestResult2 = new ArrayList<>(); //Piece of different color, with possible movement and attack.
        TestResult2.add(new int[] {3,2,2,2,AuxQueen.getPointsForMove()});
        TestResult2.add(new int[] {3,2,1,2,AuxPawn.getPointsForKill()});
        List<int[]> TestResult3 = new ArrayList<>(); //Piece of same color but with possible movement
        TestResult3.add(new int[] {3,2,2,2,AuxQueen.getPointsForMove()});
        List<int[]> TestResult4 = new ArrayList<>(); //No Pieces in the way, moves till the border
        TestResult4.add(new int[] {3,2,2,2,AuxQueen.getPointsForMove()});
        TestResult4.add(new int[] {3,2,1,2,AuxQueen.getPointsForMove()});
        TestResult4.add(new int[] {3,2,0,2,AuxQueen.getPointsForMove()});
        List<int[]> TestResult5 = new ArrayList<>(); //Piece on the border, no move possible
        
        return Stream.of(
                Arguments.of("                                  p               q                                                                                                                                                                                                             ", 3, 2, TestResult1),
                Arguments.of("                  P                               q                                                                                                                                                                                                             ", 3, 2, TestResult2),
                Arguments.of("                  p                               q                                                                                                                                                                                                             ", 3, 2, TestResult3),
                Arguments.of("                                                  q                                                                                                                                                                                                             ", 3, 2, TestResult4),
                Arguments.of("  q                                                                                                                                                                                                                                                             ", 0, 2, TestResult5)              
        );
    }    
    
    
    @ParameterizedTest(name="Queen move up test run {index}")
    @MethodSource("testMoveUp_Parameters")
    public void testMoveUp(String boardString, int currentRow, int currentCol, List<int[]> expectedMoves){
        //Create board
        List<List<ChessPiece>> TestBoard = Board.generateBoardConfig(boardString);
        //Finished creating board
        
        Queen TestQueen = new Queen(currentRow,currentCol,"black");
        TestQueen.moveUp(currentRow - 1, currentCol, TestBoard);
        assertArrayEquals(expectedMoves.toArray(), TestQueen.getPossibleMoves().toArray());
        
    }    
    
    //////////////////////////////MOVE RIGHT AND UP TEST/////////////////////////////////////////////////////////////////////////////////
    
    static Stream<Arguments> testMoveRightAndUp_Parameters(){
        Pawn AuxPawn = new Pawn(0,0,"");
        Queen AuxQueen = new Queen(0,0,"");
        List<int[]> TestResult1 = new ArrayList<>(); //Adjacent piece of same color, no moves should be generated
        List<int[]> TestResult2 = new ArrayList<>(); //Piece of different color, with possible movement and attack.
        TestResult2.add(new int[] {3,12,2,13,AuxQueen.getPointsForMove()});
        TestResult2.add(new int[] {3,12,1,14,AuxPawn.getPointsForKill()});
        List<int[]> TestResult3 = new ArrayList<>(); //Piece of same color but with possible movement
        TestResult3.add(new int[] {3,12,2,13,AuxQueen.getPointsForMove()});
        List<int[]> TestResult4 = new ArrayList<>(); //No Pieces in the way, moves till the border
        TestResult4.add(new int[] {3,12,2,13,AuxQueen.getPointsForMove()});
        TestResult4.add(new int[] {3,12,1,14,AuxQueen.getPointsForMove()});
        TestResult4.add(new int[] {3,12,0,15,AuxQueen.getPointsForMove()});
        List<int[]> TestResult5 = new ArrayList<>(); //Piece on the border, no move possible
        
        return Stream.of(
                Arguments.of("                                             p              q                                                                                                                                                                                                   ", 3, 12, TestResult1),
                Arguments.of("                              P                             q                                                                                                                                                                                                   ", 3, 12, TestResult2),
                Arguments.of("                              p                             q                                                                                                                                                                                                   ", 3, 12, TestResult3),
                Arguments.of("                                                            q                                                                                                                                                                                                   ", 3, 12, TestResult4),
                Arguments.of("               q                                                                                                                                                                                                                                                ", 0, 15, TestResult5)              
        );
    }    
    
    
    @ParameterizedTest(name="Queen move right and up test run {index}")
    @MethodSource("testMoveRightAndUp_Parameters")
    public void testMoveRightAndUp(String boardString, int currentRow, int currentCol, List<int[]> expectedMoves){
        //Create board
        List<List<ChessPiece>> TestBoard = Board.generateBoardConfig(boardString);
        //Finished creating board
        
        Queen TestQueen = new Queen(currentRow,currentCol,"black");
        TestQueen.moveRightAndUp(currentRow - 1, currentCol + 1, TestBoard);
        assertArrayEquals(expectedMoves.toArray(), TestQueen.getPossibleMoves().toArray());
        
    }
    
    //////////////////////////////MOVE RIGHT AND DOWN TEST/////////////////////////////////////////////////////////////////////////////////
    
    static Stream<Arguments> testMoveRightAndDown_Parameters(){
        Pawn AuxPawn = new Pawn(0,0,"");
        Queen AuxQueen = new Queen(0,0,"");
        List<int[]> TestResult1 = new ArrayList<>(); //Adjacent piece of same color, no moves should be generated
        List<int[]> TestResult2 = new ArrayList<>(); //Piece of different color, with possible movement and attack.
        TestResult2.add(new int[] {12,12,13,13,AuxQueen.getPointsForMove()});
        TestResult2.add(new int[] {12,12,14,14,AuxPawn.getPointsForKill()});
        List<int[]> TestResult3 = new ArrayList<>(); //Piece of same color but with possible movement
        TestResult3.add(new int[] {12,12,13,13,AuxQueen.getPointsForMove()});
        List<int[]> TestResult4 = new ArrayList<>(); //No Pieces in the way, moves till the border
        TestResult4.add(new int[] {12,12,13,13,AuxQueen.getPointsForMove()});
        TestResult4.add(new int[] {12,12,14,14,AuxQueen.getPointsForMove()});
        TestResult4.add(new int[] {12,12,15,15,AuxQueen.getPointsForMove()});
        List<int[]> TestResult5 = new ArrayList<>(); //Piece on the border, no move possible
        
        return Stream.of(
                Arguments.of("                                                                                                                                                                                                            q                p                                  ", 12, 12, TestResult1),
                Arguments.of("                                                                                                                                                                                                            q                                 P                 ", 12, 12, TestResult2),
                Arguments.of("                                                                                                                                                                                                            q                                 p                 ", 12, 12, TestResult3),
                Arguments.of("                                                                                                                                                                                                            q                                                   ", 12, 12, TestResult4),
                Arguments.of("                                                                                                                                                                                                                                                               q", 15, 15, TestResult5)              
        );
    }    
    
    
    @ParameterizedTest(name="Queen move right and down test run {index}")
    @MethodSource("testMoveRightAndDown_Parameters")
    public void testMoveRightAndDown(String boardString, int currentRow, int currentCol, List<int[]> expectedMoves){
        //Create board
        List<List<ChessPiece>> TestBoard = Board.generateBoardConfig(boardString);
        //Finished creating board
        
        Queen TestQueen = new Queen(currentRow,currentCol,"black");
        TestQueen.moveRightAndDown(currentRow + 1, currentCol + 1, TestBoard);
        assertArrayEquals(expectedMoves.toArray(), TestQueen.getPossibleMoves().toArray());
        
    }    
    
    //////////////////////////////MOVE LEFT AND UP TEST/////////////////////////////////////////////////////////////////////////////////
    
    static Stream<Arguments> testMoveLeftAndUp_Parameters(){
        Pawn AuxPawn = new Pawn(0,0,"");
        Queen AuxQueen = new Queen(0,0,"");
        List<int[]> TestResult1 = new ArrayList<>(); //Adjacent piece of same color, no moves should be generated
        List<int[]> TestResult2 = new ArrayList<>(); //Piece of different color, with possible movement and attack.
        TestResult2.add(new int[] {3,3,2,2,AuxQueen.getPointsForMove()});
        TestResult2.add(new int[] {3,3,1,1,AuxPawn.getPointsForKill()});
        List<int[]> TestResult3 = new ArrayList<>(); //Piece of same color but with possible movement
        TestResult3.add(new int[] {3,3,2,2,AuxQueen.getPointsForMove()});
        List<int[]> TestResult4 = new ArrayList<>(); //No Pieces in the way, moves till the border
        TestResult4.add(new int[] {3,3,2,2,AuxQueen.getPointsForMove()});
        TestResult4.add(new int[] {3,3,1,1,AuxQueen.getPointsForMove()});
        TestResult4.add(new int[] {3,3,0,0,AuxQueen.getPointsForMove()});
        List<int[]> TestResult5 = new ArrayList<>(); //Piece on the border, no move possible
        
        return Stream.of(
                Arguments.of("                                  p                q                                                                                                                                                                                                            ", 3, 3, TestResult1),
                Arguments.of("                 P                                 q                                                                                                                                                                                                            ", 3, 3, TestResult2),
                Arguments.of("                 p                                 q                                                                                                                                                                                                            ", 3, 3, TestResult3),
                Arguments.of("                                                   q                                                                                                                                                                                                            ", 3, 3, TestResult4),
                Arguments.of("q                                                                                                                                                                                                                                                               ", 0, 0, TestResult5)              
        );
    }    
    
    
    @ParameterizedTest(name="Queen move left and up test run {index}")
    @MethodSource("testMoveLeftAndUp_Parameters")
    public void testMoveLeftAndUp(String boardString, int currentRow, int currentCol, List<int[]> expectedMoves){
        //Create board
        List<List<ChessPiece>> TestBoard = Board.generateBoardConfig(boardString);
        //Finished creating board
        
        Queen TestQueen = new Queen(currentRow,currentCol,"black");
        TestQueen.moveLeftAndUp(currentRow - 1, currentCol - 1, TestBoard);
        assertArrayEquals(expectedMoves.toArray(), TestQueen.getPossibleMoves().toArray());
        
    }  

    //////////////////////////////MOVE LEFT AND DOWN TEST/////////////////////////////////////////////////////////////////////////////////
    
    static Stream<Arguments> testMoveLeftAndDown_Parameters(){
        Pawn AuxPawn = new Pawn(0,0,"");
        Queen AuxQueen = new Queen(0,0,"");
        List<int[]> TestResult1 = new ArrayList<>(); //Adjacent piece of same color, no moves should be generated
        List<int[]> TestResult2 = new ArrayList<>(); //Piece of different color, with possible movement and attack.
        TestResult2.add(new int[] {12,3,13,2,AuxQueen.getPointsForMove()});
        TestResult2.add(new int[] {12,3,14,1,AuxPawn.getPointsForKill()});
        List<int[]> TestResult3 = new ArrayList<>(); //Piece of same color but with possible movement
        TestResult3.add(new int[] {12,3,13,2,AuxQueen.getPointsForMove()});
        List<int[]> TestResult4 = new ArrayList<>(); //No Pieces in the way, moves till the border
        TestResult4.add(new int[] {12,3,13,2,AuxQueen.getPointsForMove()});
        TestResult4.add(new int[] {12,3,14,1,AuxQueen.getPointsForMove()});
        TestResult4.add(new int[] {12,3,15,0,AuxQueen.getPointsForMove()});
        List<int[]> TestResult5 = new ArrayList<>(); //Piece on the border, no move possible
        
        return Stream.of(
                Arguments.of("                                                                                                                                                                                                   q              p                                             ", 12, 3, TestResult1),
                Arguments.of("                                                                                                                                                                                                   q                             P                              ", 12, 3, TestResult2),
                Arguments.of("                                                                                                                                                                                                   q                             p                              ", 12, 3, TestResult3),
                Arguments.of("                                                                                                                                                                                                   q                                                            ", 12, 3, TestResult4),
                Arguments.of("                                                                                                                                                                                                                                                q               ", 15, 0, TestResult5)              
        );
    }    
    
    
    @ParameterizedTest(name="Queen move left and down test run {index}")
    @MethodSource("testMoveLeftAndDown_Parameters")
    public void testMoveLeftAndDown(String boardString, int currentRow, int currentCol, List<int[]> expectedMoves){
        //Create board
        List<List<ChessPiece>> TestBoard = Board.generateBoardConfig(boardString);
        //Finished creating board
        
        Queen TestQueen = new Queen(currentRow,currentCol,"black");
        TestQueen.moveLeftAndDown(currentRow + 1, currentCol - 1, TestBoard);
        assertArrayEquals(expectedMoves.toArray(), TestQueen.getPossibleMoves().toArray());
        
    }    
    
    //////////////////////////////CALCULATE POSSIBLE MOVES TEST/////////////////////////////////////////////////////////////////////////////////
    
    static Stream<Arguments> testCalculatePossibleMoves_Parameters(){
        Pawn AuxPawn = new Pawn(0,0,"");
        List<int[]> TestResult1 = new ArrayList<>(); //Surrounded by pieces of same color, no moves should be generated
        List<int[]> TestResult2 = new ArrayList<>(); //Surrounded by pieces of different color, all possible attacks.
        TestResult2.add(new int[] {7,7,8,7,AuxPawn.getPointsForKill()}); //Down
        TestResult2.add(new int[] {7,7,7,8,AuxPawn.getPointsForKill()}); //Right
        TestResult2.add(new int[] {7,7,7,6,AuxPawn.getPointsForKill()}); //Left
        TestResult2.add(new int[] {7,7,6,7,AuxPawn.getPointsForKill()}); //Up
        TestResult2.add(new int[] {7,7,8,6,AuxPawn.getPointsForKill()}); //Down and left
        TestResult2.add(new int[] {7,7,6,6,AuxPawn.getPointsForKill()}); //Up and left
        TestResult2.add(new int[] {7,7,6,8,AuxPawn.getPointsForKill()}); //Up and right
        TestResult2.add(new int[] {7,7,8,8,AuxPawn.getPointsForKill()}); //Down and right
        
        return Stream.of(
                Arguments.of("                                                                                                      ppp             pqp             ppp                                                                                                                       ", 7, 7, TestResult1),
                Arguments.of("                                                                                                      PPP             PqP             PPP                                                                                                                       ", 7, 7, TestResult2)             
        );
    }    
    
    
    @ParameterizedTest(name="Queen calculatePossibleMoves test run {index}")
    @MethodSource("testCalculatePossibleMoves_Parameters")
    public void testCalculatePossbileMoves(String boardString, int currentRow, int currentCol, List<int[]> expectedMoves){
        //Create board
        List<List<ChessPiece>> TestBoard = Board.generateBoardConfig(boardString);
        //Finished creating board
        
        Queen TestQueen = new Queen(currentRow,currentCol,"black");
        TestQueen.calculatePossibleMoves(TestBoard);
        assertArrayEquals(expectedMoves.toArray(), TestQueen.getPossibleMoves().toArray());
        
    }     
    
    /**
     * Test of isNull method, of class Queen.
     */      
    @Test
    public void testIsNull() {
        Queen instance = new Queen(0,0,"");
        boolean expResult = false;
        boolean result = instance.isNull();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of positionBias method, of class Queen.
     */  
    @Test
    public void testPositionBias(){
        Queen testQueen = new Queen(1,4,"white");
        assertEquals(0,testQueen.positionBias());
    }    
    
    /**
     * Test of copy method, of class Queen.
     */  
    @Test
    public void testCopy(){
        Queen TestQueen = new Queen(6,15,"black");
        Queen testQueenCopy = (Queen)TestQueen.copy();
        assertEquals(TestQueen.getCurrentRow(),testQueenCopy.getCurrentRow());
        assertEquals(TestQueen.getCurrentCol(),testQueenCopy.getCurrentCol());
        assertEquals(TestQueen.getColor(),testQueenCopy.getColor());
    }      
    
}
