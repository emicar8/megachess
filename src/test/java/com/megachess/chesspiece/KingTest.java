/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megachess.chesspiece;

import com.megachess.board.Board;
import com.megachess.client.ChessBot;
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
public class KingTest {
    
    public KingTest() {
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
    
    static Stream<Arguments> testKing_Parameters(){  
        return Stream.of(
                Arguments.of(1,2,"white"),
                Arguments.of(1,2,"black")              
        );
    }    
    
    
    @ParameterizedTest(name="King constructor test run {index}")
    @MethodSource("testKing_Parameters")
    public void testKing(int currentRow, int currentCol, String color){
       
        King TestKing = new King(currentRow,currentCol,color);
        assertEquals(currentRow, TestKing.getCurrentRow());
        assertEquals(currentCol, TestKing.getCurrentCol());
        assertEquals(color, TestKing.getColor());
        assertEquals(65, TestKing.getPointsForMove());
        assertEquals(1000, TestKing.getPointsForKill());
        
    } 

    //////////////////////////////MOVE RIGHT TEST/////////////////////////////////////////////////////////////////////////////////
    
    static Stream<Arguments> testMoveRight_Parameters(){
        Pawn AuxPawn = new Pawn(0,0,"");
        King AuxKing = new King(0,0,"");
        int[] TestResult1 = null; //Adjacent piece of same color, no moves should be generated
        int[] TestResult2 = new int[] {4,13,4,14,AuxPawn.getPointsForKill()}; //Piece of different color, with possible attack.
        int[] TestResult3 = new int[] {4,13,4,14,AuxKing.getPointsForMove()}; //No piece in the way, movement possible
        int[] TestResult4 = null; //On border, no movement possible
        
        return Stream.of(
                Arguments.of("                                                                             kp                                                                                                                                                                                 ", 4, 13, TestResult1),
                Arguments.of("                                                                             kP                                                                                                                                                                                 ", 4, 13, TestResult2),
                Arguments.of("                                                                             k                                                                                                                                                                                  ", 4, 13, TestResult3),
                Arguments.of("                                                                               k                                                                                                                                                                                ", 4, 15, TestResult4)              
        );
    }    
    
    
    @ParameterizedTest(name="King move right test run {index}")
    @MethodSource("testMoveRight_Parameters")
    public void testMoveRight(String boardString, int currentRow, int currentCol, int[] expectedMove){
        //Create board
        List<List<ChessPiece>> TestBoard = Board.generateBoardConfig(boardString);
        //Finished creating board
        
        King TestKing = new King(currentRow,currentCol,"black");
        assertArrayEquals(expectedMove, TestKing.moveRight(TestBoard));
    }
    
    //////////////////////////////MOVE LEFT TEST/////////////////////////////////////////////////////////////////////////////////
    
    static Stream<Arguments> testMoveLeft_Parameters(){
        Pawn AuxPawn = new Pawn(0,0,"");
        King AuxKing = new King(0,0,"");
        int[] TestResult1 = null; //Adjacent piece of same color, no moves should be generated
        int[] TestResult2 = new int[] {4,2,4,1,AuxPawn.getPointsForKill()}; //Piece of different color, with possible attack.
        int[] TestResult3 = new int[] {4,2,4,1,AuxKing.getPointsForMove()}; //No piece in the way, movement possible
        int[] TestResult4 = null; //On border, no movement possible
        
        return Stream.of(
                Arguments.of("                                                                 pk                                                                                                                                                                                             ", 4, 2, TestResult1),
                Arguments.of("                                                                 Pk                                                                                                                                                                                             ", 4, 2, TestResult2),
                Arguments.of("                                                                  k                                                                                                                                                                                             ", 4, 2, TestResult3),
                Arguments.of("                                                                k                                                                                                                                                                                               ", 4, 0, TestResult4)              
        );
    }    
    
    
    @ParameterizedTest(name="King move left test run {index}")
    @MethodSource("testMoveLeft_Parameters")
    public void testMoveLeft(String boardString, int currentRow, int currentCol, int[] expectedMove){
        //Create board
        List<List<ChessPiece>> TestBoard = Board.generateBoardConfig(boardString);
        //Finished creating board
        
        King TestKing = new King(currentRow,currentCol,"black");
        assertArrayEquals(expectedMove, TestKing.moveLeft(TestBoard));
    }   
    
    //////////////////////////////MOVE UP TEST/////////////////////////////////////////////////////////////////////////////////
    
    static Stream<Arguments> testMoveUp_Parameters(){
        Pawn AuxPawn = new Pawn(0,0,"");
        King AuxKing = new King(0,0,"");
        int[] TestResult1 = null; //Adjacent piece of same color, no moves should be generated
        int[] TestResult2 = new int[] {3,7,2,7,AuxPawn.getPointsForKill()}; //Piece of different color, with possible attack.
        int[] TestResult3 = new int[] {3,7,2,7,AuxKing.getPointsForMove()}; //No piece in the way, movement possible
        int[] TestResult4 = null; //On border, no movement possible
        
        return Stream.of(
                Arguments.of("                                       p               k                                                                                                                                                                                                        ", 3, 7, TestResult1),
                Arguments.of("                                       P               k                                                                                                                                                                                                        ", 3, 7, TestResult2),
                Arguments.of("                                                       k                                                                                                                                                                                                        ", 3, 7, TestResult3),
                Arguments.of("       k                                                                                                                                                                                                                                                        ", 0, 7, TestResult4)              
        );
    }    
    
    
    @ParameterizedTest(name="King move up test run {index}")
    @MethodSource("testMoveUp_Parameters")
    public void testMoveUp(String boardString, int currentRow, int currentCol, int[] expectedMove){
        //Create board
        List<List<ChessPiece>> TestBoard = Board.generateBoardConfig(boardString);
        //Finished creating board
        
        King TestKing = new King(currentRow,currentCol,"black");
        assertArrayEquals(expectedMove, TestKing.moveUp(TestBoard));
    }      

    //////////////////////////////MOVE DOWN TEST/////////////////////////////////////////////////////////////////////////////////
    
    static Stream<Arguments> testMoveDown_Parameters(){
        Pawn AuxPawn = new Pawn(0,0,"");
        King AuxKing = new King(0,0,"");
        int[] TestResult1 = null; //Adjacent piece of same color, no moves should be generated
        int[] TestResult2 = new int[] {12,7,13,7,AuxPawn.getPointsForKill()}; //Piece of different color, with possible attack.
        int[] TestResult3 = new int[] {12,7,13,7,AuxKing.getPointsForMove()}; //No piece in the way, movement possible
        int[] TestResult4 = null; //On border, no movement possible
        
        return Stream.of(
                Arguments.of("                                                                                                                                                                                                       k               p                                        ", 12, 7, TestResult1),
                Arguments.of("                                                                                                                                                                                                       k               P                                        ", 12, 7, TestResult2),
                Arguments.of("                                                                                                                                                                                                       k                                                        ", 12, 7, TestResult3),
                Arguments.of("                                                                                                                                                                                                                                                       k        ", 15, 7, TestResult4)              
        );
    }    
    
    
    @ParameterizedTest(name="King move down test run {index}")
    @MethodSource("testMoveDown_Parameters")
    public void testMoveDown(String boardString, int currentRow, int currentCol, int[] expectedMove){
        //Create board
        List<List<ChessPiece>> TestBoard = Board.generateBoardConfig(boardString);
        //Finished creating board
        
        King TestKing = new King(currentRow,currentCol,"black");
        assertArrayEquals(expectedMove, TestKing.moveDown(TestBoard));
    }
    
    //////////////////////////////MOVE RIGHT AND UP TEST/////////////////////////////////////////////////////////////////////////////////
    
    static Stream<Arguments> testMoveRightAndUp_Parameters(){
        Pawn AuxPawn = new Pawn(0,0,"");
        King AuxKing = new King(0,0,"");
        int[] TestResult1 = null; //Adjacent piece of same color, no moves should be generated
        int[] TestResult2 = new int[] {2,13,1,14,AuxPawn.getPointsForKill()}; //Piece of different color, with possible attack.
        int[] TestResult3 = new int[] {2,13,1,14,AuxKing.getPointsForMove()}; //No piece in the way, movement possible
        int[] TestResult4 = null; //On border, no movement possible
        
        return Stream.of(
                Arguments.of("                              p              k                                                                                                                                                                                                                  ", 2, 13, TestResult1),
                Arguments.of("                              P              k                                                                                                                                                                                                                  ", 2, 13, TestResult2),
                Arguments.of("                                             k                                                                                                                                                                                                                  ", 2, 13, TestResult3),
                Arguments.of("               k                                                                                                                                                                                                                                                ", 0, 15, TestResult4)              
        );
    }    
    
    
    @ParameterizedTest(name="King move right and up test run {index}")
    @MethodSource("testMoveRightAndUp_Parameters")
    public void testMoveRightAndUp(String boardString, int currentRow, int currentCol, int[] expectedMove){
        //Create board
        List<List<ChessPiece>> TestBoard = Board.generateBoardConfig(boardString);
        //Finished creating board
        
        King TestKing = new King(currentRow,currentCol,"black");
        assertArrayEquals(expectedMove, TestKing.moveRightAndUp(TestBoard));
    }
    
    //////////////////////////////MOVE LEFT AND UP TEST/////////////////////////////////////////////////////////////////////////////////
    
    static Stream<Arguments> testMoveLeftAndUp_Parameters(){
        Pawn AuxPawn = new Pawn(0,0,"");
        King AuxKing = new King(0,0,"");
        int[] TestResult1 = null; //Adjacent piece of same color, no moves should be generated
        int[] TestResult2 = new int[] {2,2,1,1,AuxPawn.getPointsForKill()}; //Piece of different color, with possible attack.
        int[] TestResult3 = new int[] {2,2,1,1,AuxKing.getPointsForMove()}; //No piece in the way, movement possible
        int[] TestResult4 = null; //On border, no movement possible
        
        return Stream.of(
                Arguments.of("                 p                k                                                                                                                                                                                                                             ", 2, 2, TestResult1),
                Arguments.of("                 P                k                                                                                                                                                                                                                             ", 2, 2, TestResult2),
                Arguments.of("                                  k                                                                                                                                                                                                                             ", 2, 2, TestResult3),
                Arguments.of("k                                                                                                                                                                                                                                                               ", 0, 0, TestResult4)              
        );
    }    
    
    
    @ParameterizedTest(name="King move left and Up test run {index}")
    @MethodSource("testMoveLeftAndUp_Parameters")
    public void testMoveLeftAndUp(String boardString, int currentRow, int currentCol, int[] expectedMove){
        //Create board
        List<List<ChessPiece>> TestBoard = Board.generateBoardConfig(boardString);
        //Finished creating board
        
        King TestKing = new King(currentRow,currentCol,"black");
        assertArrayEquals(expectedMove, TestKing.moveLeftAndUp(TestBoard));
    }   
    
    //////////////////////////////MOVE LEFT AND DOWN TEST/////////////////////////////////////////////////////////////////////////////////
    
    static Stream<Arguments> testMoveLeftAndDown_Parameters(){
        Pawn AuxPawn = new Pawn(0,0,"");
        King AuxKing = new King(0,0,"");
        int[] TestResult1 = null; //Adjacent piece of same color, no moves should be generated
        int[] TestResult2 = new int[] {13,2,14,1,AuxPawn.getPointsForKill()}; //Piece of different color, with possible attack.
        int[] TestResult3 = new int[] {13,2,14,1,AuxKing.getPointsForMove()}; //No piece in the way, movement possible
        int[] TestResult4 = null; //On border, no movement possible
        
        return Stream.of(
                Arguments.of("                                                                                                                                                                                                                  k              p                              ", 13, 2, TestResult1),
                Arguments.of("                                                                                                                                                                                                                  k              P                              ", 13, 2, TestResult2),
                Arguments.of("                                                                                                                                                                                                                  k                                             ", 13, 2, TestResult3),
                Arguments.of("                                                                                                                                                                                                                                                k               ", 15, 0, TestResult4)              
        );
    }    
    
    
    @ParameterizedTest(name="King move left and down test run {index}")
    @MethodSource("testMoveLeftAndDown_Parameters")
    public void testMoveLeftAndDown(String boardString, int currentRow, int currentCol, int[] expectedMove){
        //Create board
        List<List<ChessPiece>> TestBoard = Board.generateBoardConfig(boardString);
        //Finished creating board
        
        King TestKing = new King(currentRow,currentCol,"black");
        assertArrayEquals(expectedMove, TestKing.moveLeftAndDown(TestBoard));
    }      

    //////////////////////////////MOVE RIGHT AND DOWN TEST/////////////////////////////////////////////////////////////////////////////////
    
    static Stream<Arguments> testMoveRightAndDown_Parameters(){
        Pawn AuxPawn = new Pawn(0,0,"");
        King AuxKing = new King(0,0,"");
        int[] TestResult1 = null; //Adjacent piece of same color, no moves should be generated
        int[] TestResult2 = new int[] {13,13,14,14,AuxPawn.getPointsForKill()}; //Piece of different color, with possible attack.
        int[] TestResult3 = new int[] {13,13,14,14,AuxKing.getPointsForMove()}; //No piece in the way, movement possible
        int[] TestResult4 = null; //On border, no movement possible
        
        return Stream.of(
                Arguments.of("                                                                                                                                                                                                                             k                p                 ", 13, 13, TestResult1),
                Arguments.of("                                                                                                                                                                                                                             k                P                 ", 13, 13, TestResult2),
                Arguments.of("                                                                                                                                                                                                                             k                                  ", 13, 13, TestResult3),
                Arguments.of("                                                                                                                                                                                                                                                               k", 15, 15, TestResult4)              
        );
    }    
    
    
    @ParameterizedTest(name="King move right and down test run {index}")
    @MethodSource("testMoveRightAndDown_Parameters")
    public void testMoveRightAndDown(String boardString, int currentRow, int currentCol, int[] expectedMove){
        //Create board
        List<List<ChessPiece>> TestBoard = Board.generateBoardConfig(boardString);
        //Finished creating board
        
        King TestKing = new King(currentRow,currentCol,"black");
        assertArrayEquals(expectedMove, TestKing.moveRightAndDown(TestBoard));
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
                Arguments.of("                                                                                                      ppp             pkp             ppp                                                                                                                       ", 7, 7, TestResult1),
                Arguments.of("                                                                                                      PPP             PkP             PPP                                                                                                                       ", 7, 7, TestResult2)             
        );
    }    
    
    
    @ParameterizedTest(name="King calculatePossibleMoves test run {index}")
    @MethodSource("testCalculatePossibleMoves_Parameters")
    public void testCalculatePossbileMoves(String boardString, int currentRow, int currentCol, List<int[]> expectedMoves){
        //Create board
        List<List<ChessPiece>> TestBoard = Board.generateBoardConfig(boardString);
        //Finished creating board
        
        King TestKing = new King(currentRow,currentCol,"black");
        TestKing.calculatePossibleMoves(TestBoard);
        assertArrayEquals(expectedMoves.toArray(), TestKing.getPossibleMoves().toArray());
        
    }    
    
    
    /**
     * Test of isNull method, of class King.
     */
    @Test
    public void testIsNull() {
        King instance = new King(0,0,"");
        boolean expResult = false;
        boolean result = instance.isNull();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of copy method, of class King.
     */  
    @Test
    public void testCopy(){
        King testKing = new King(13,5,"white");
        King testKingCopy = (King)testKing.copy();
        assertEquals(testKing.getCurrentRow(),testKingCopy.getCurrentRow());
        assertEquals(testKing.getCurrentCol(),testKingCopy.getCurrentCol());
        assertEquals(testKing.getColor(),testKingCopy.getColor());
    }    
    
}
