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
public class KnightTest {
    
    public KnightTest() {
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
    
    static Stream<Arguments> testKnight_Parameters(){  
        return Stream.of(
                Arguments.of(1,2,"white"),
                Arguments.of(1,2,"black")              
        );
    }    
    
    
    @ParameterizedTest(name="Knight constructor test run {index}")
    @MethodSource("testKnight_Parameters")
    public void testKnight(int currentRow, int currentCol, String color){
       
        Knight TestKnight = new Knight(currentRow,currentCol,color);
        assertEquals(currentRow, TestKnight.getCurrentRow());
        assertEquals(currentCol, TestKnight.getCurrentCol());
        assertEquals(color, TestKnight.getColor());
        assertEquals(30, TestKnight.getPointsForMove());
        assertEquals(300, TestKnight.getPointsForKill());
        
    }     
    
    //////////////////////////////MOVE UP AND RIGHT TEST/////////////////////////////////////////////////////////////////////////////////
    
    static Stream<Arguments> testMoveUpAndRight_Parameters(){
        Pawn AuxPawn = new Pawn(0,0,"");
        Knight AuxKnight = new Knight(0,0,"");
        int[] TestResult1 = null; //Piece of same color blocking, no movement possible
        int[] TestResult2 = new int[] {2,14,0,15,AuxPawn.getPointsForKill()}; //Piece of different color, possible attack.
        int[] TestResult3 = new int[] {2,14,0,15,AuxKnight.getPointsForMove()}; //No piece, movement possible
        int[] TestResult4 = null; //Movement would be out of range, no movement possible
        int[] TestResult5 = null; //Movement would be out of range, no movement possible
        
        return Stream.of(
                Arguments.of("               p                              h                                                                                                                                                                                                                 ", 2, 14, TestResult1),
                Arguments.of("               P                              h                                                                                                                                                                                                                 ", 2, 14, TestResult2),
                Arguments.of("                                              h                                                                                                                                                                                                                 ", 2, 14, TestResult3),
                Arguments.of("                              h                                                                                                                                                                                                                                 ", 1, 14, TestResult4),
                Arguments.of("                                               h                                                                                                                                                                                                                ", 2, 15, TestResult5)              
        );
    }    
    
    
    @ParameterizedTest(name="Knight move up and right test run {index}")
    @MethodSource("testMoveUpAndRight_Parameters")
    public void testMoveUpAndRight(String boardString, int currentRow, int currentCol, int[] expectedMove){
        //Create board
        List<List<ChessPiece>> TestBoard =  Board.generateBoardConfig(boardString);
        //Finished creating board
        
        Knight TestKnight = new Knight(currentRow,currentCol,"black");
        assertArrayEquals(expectedMove, TestKnight.moveUpAndRight(TestBoard));
        
    }    
    
    //////////////////////////////MOVE DOWN AND RIGHT TEST/////////////////////////////////////////////////////////////////////////////////
    
    static Stream<Arguments> testMoveDownAndRight_Parameters(){
        Pawn AuxPawn = new Pawn(0,0,"");
        Knight AuxKnight = new Knight(0,0,"");
        int[] TestResult1 = null; //Piece of same color blocking, no movement possible
        int[] TestResult2 = new int[] {13,14,15,15,AuxPawn.getPointsForKill()}; //Piece of different color, possible attack.
        int[] TestResult3 = new int[] {13,14,15,15,AuxKnight.getPointsForMove()}; //No piece, movement possible
        int[] TestResult4 = null; //Movement would be out of range, no movement possible
        int[] TestResult5 = null; //Movement would be out of range, no movement possible
        
        return Stream.of(
                Arguments.of("                                                                                                                                                                                                                              h                                p", 13, 14, TestResult1),
                Arguments.of("                                                                                                                                                                                                                              h                                P", 13, 14, TestResult2),
                Arguments.of("                                                                                                                                                                                                                              h                                 ", 13, 14, TestResult3),
                Arguments.of("                                                                                                                                                                                                                                              h                 ", 14, 14, TestResult4),
                Arguments.of("                                                                                                                                                                                                                               h                                ", 13, 15, TestResult5)              
        );
    }    
    
    
    @ParameterizedTest(name="Knight move down and right test run {index}")
    @MethodSource("testMoveDownAndRight_Parameters")
    public void testMoveDownAndRight(String boardString, int currentRow, int currentCol, int[] expectedMove){
        //Create board
        List<List<ChessPiece>> TestBoard = Board.generateBoardConfig(boardString);
        //Finished creating board
        
        Knight TestKnight = new Knight(currentRow,currentCol,"black");
        assertArrayEquals(expectedMove, TestKnight.moveDownAndRight(TestBoard));
        
    }
    
    //////////////////////////////MOVE UP AND LEFT TEST/////////////////////////////////////////////////////////////////////////////////
    
    static Stream<Arguments> testMoveUpAndLeft_Parameters(){
        Pawn AuxPawn = new Pawn(0,0,"");
        Knight AuxKnight = new Knight(0,0,"");
        int[] TestResult1 = null; //Piece of same color blocking, no movement possible
        int[] TestResult2 = new int[] {2,1,0,0,AuxPawn.getPointsForKill()}; //Piece of different color, possible attack.
        int[] TestResult3 = new int[] {2,1,0,0,AuxKnight.getPointsForMove()}; //No piece, movement possible
        int[] TestResult4 = null; //Movement would be out of range, no movement possible
        int[] TestResult5 = null; //Movement would be out of range, no movement possible
        
        return Stream.of(
                Arguments.of("p                                h                                                                                                                                                                                                                              ", 2, 1, TestResult1),
                Arguments.of("P                                h                                                                                                                                                                                                                              ", 2, 1, TestResult2),
                Arguments.of("                                 h                                                                                                                                                                                                                              ", 2, 1, TestResult3),
                Arguments.of("                 h                                                                                                                                                                                                                                              ", 1, 1, TestResult4),
                Arguments.of("                                h                                                                                                                                                                                                                               ", 2, 0, TestResult5)              
        );
    }    
    
    
    @ParameterizedTest(name="Knight move up and left test run {index}")
    @MethodSource("testMoveUpAndLeft_Parameters")
    public void testMoveUpAndLeft(String boardString, int currentRow, int currentCol, int[] expectedMove){
        //Create board
        List<List<ChessPiece>> TestBoard = Board.generateBoardConfig(boardString);
        //Finished creating board
        
        Knight TestKnight = new Knight(currentRow,currentCol,"black");
        assertArrayEquals(expectedMove, TestKnight.moveUpAndLeft(TestBoard));
        
    }    
    
    //////////////////////////////MOVE DOWN AND LEFT TEST/////////////////////////////////////////////////////////////////////////////////
    
    static Stream<Arguments> testMoveDownAndLeft_Parameters(){
        Pawn AuxPawn = new Pawn(0,0,"");
        Knight AuxKnight = new Knight(0,0,"");
        int[] TestResult1 = null; //Piece of same color blocking, no movement possible
        int[] TestResult2 = new int[] {13,1,15,0,AuxPawn.getPointsForKill()}; //Piece of different color, possible attack.
        int[] TestResult3 = new int[] {13,1,15,0,AuxKnight.getPointsForMove()}; //No piece, movement possible
        int[] TestResult4 = null; //Movement would be out of range, no movement possible
        int[] TestResult5 = null; //Movement would be out of range, no movement possible
        
        return Stream.of(
                Arguments.of("                                                                                                                                                                                                                 h                              p               ", 13, 1, TestResult1),
                Arguments.of("                                                                                                                                                                                                                 h                              P               ", 13, 1, TestResult2),
                Arguments.of("                                                                                                                                                                                                                 h                                              ", 13, 1, TestResult3),
                Arguments.of("                                                                                                                                                                                                                                 h                              ", 14, 1, TestResult4),
                Arguments.of("                                                                                                                                                                                                                h                                               ", 13, 0, TestResult5)              
        );
    }    
    
    
    @ParameterizedTest(name="Knight move down and left test run {index}")
    @MethodSource("testMoveDownAndLeft_Parameters")
    public void testMoveDownAndLeft(String boardString, int currentRow, int currentCol, int[] expectedMove){
        //Create board
        List<List<ChessPiece>> TestBoard = Board.generateBoardConfig(boardString);
        //Finished creating board
        
        Knight TestKnight = new Knight(currentRow,currentCol,"black");
        assertArrayEquals(expectedMove, TestKnight.moveDownAndLeft(TestBoard));
        
    }    
    
    //////////////////////////////MOVE RIGHT AND UP TEST/////////////////////////////////////////////////////////////////////////////////
    
    static Stream<Arguments> testMoveRightAndUp_Parameters(){
        Pawn AuxPawn = new Pawn(0,0,"");
        Knight AuxKnight = new Knight(0,0,"");
        int[] TestResult1 = null; //Piece of same color blocking, no movement possible
        int[] TestResult2 = new int[] {1,13,0,15,AuxPawn.getPointsForKill()}; //Piece of different color, possible attack.
        int[] TestResult3 = new int[] {1,13,0,15,AuxKnight.getPointsForMove()}; //No piece, movement possible
        int[] TestResult4 = null; //Movement would be out of range, no movement possible
        int[] TestResult5 = null; //Movement would be out of range, no movement possible
        
        return Stream.of(
                Arguments.of("               p             h                                                                                                                                                                                                                                  ", 1, 13, TestResult1),
                Arguments.of("               P             h                                                                                                                                                                                                                                  ", 1, 13, TestResult2),
                Arguments.of("                             h                                                                                                                                                                                                                                  ", 1, 13, TestResult3),
                Arguments.of("                              h                                                                                                                                                                                                                                 ", 1, 14, TestResult4),
                Arguments.of("             h                                                                                                                                                                                                                                                  ", 0, 12, TestResult5)              
        );
    }    
    
    
    @ParameterizedTest(name="Knight move right and up test run {index}")
    @MethodSource("testMoveRightAndUp_Parameters")
    public void testMoveRightAndUp(String boardString, int currentRow, int currentCol, int[] expectedMove){
        //Create board
        List<List<ChessPiece>> TestBoard = Board.generateBoardConfig(boardString);
        //Finished creating board
        
        Knight TestKnight = new Knight(currentRow,currentCol,"black");
        assertArrayEquals(expectedMove, TestKnight.moveRightAndUp(TestBoard));
        
    }    
    
    //////////////////////////////MOVE RIGHT AND DOWN TEST/////////////////////////////////////////////////////////////////////////////////
    
    static Stream<Arguments> testMoveRightAndDown_Parameters(){
        Pawn AuxPawn = new Pawn(0,0,"");
        Knight AuxKnight = new Knight(0,0,"");
        int[] TestResult1 = null; //Piece of same color blocking, no movement possible
        int[] TestResult2 = new int[] {14,13,15,15,AuxPawn.getPointsForKill()}; //Piece of different color, possible attack.
        int[] TestResult3 = new int[] {14,13,15,15,AuxKnight.getPointsForMove()}; //No piece, movement possible
        int[] TestResult4 = null; //Movement would be out of range, no movement possible
        int[] TestResult5 = null; //Movement would be out of range, no movement possible
        
        return Stream.of(
                Arguments.of("                                                                                                                                                                                                                                             h                 p", 14, 13, TestResult1),
                Arguments.of("                                                                                                                                                                                                                                             h                 P", 14, 13, TestResult2),
                Arguments.of("                                                                                                                                                                                                                                             h                  ", 14, 13, TestResult3),
                Arguments.of("                                                                                                                                                                                                                                              h                 ", 14, 14, TestResult4),
                Arguments.of("                                                                                                                                                                                                                                                             h  ", 15, 13, TestResult5)              
        );
    }    
    
    
    @ParameterizedTest(name="Knight move right and down test run {index}")
    @MethodSource("testMoveRightAndDown_Parameters")
    public void testMoveRightAndDown(String boardString, int currentRow, int currentCol, int[] expectedMove){
        //Create board
        List<List<ChessPiece>> TestBoard = Board.generateBoardConfig(boardString);
        //Finished creating board
        
        Knight TestKnight = new Knight(currentRow,currentCol,"black");
        assertArrayEquals(expectedMove, TestKnight.moveRightAndDown(TestBoard));
        
    }
    
    //////////////////////////////MOVE LEFT AND UP TEST/////////////////////////////////////////////////////////////////////////////////
    
    static Stream<Arguments> testMoveLeftAndUp_Parameters(){
        Pawn AuxPawn = new Pawn(0,0,"");
        Knight AuxKnight = new Knight(0,0,"");
        int[] TestResult1 = null; //Piece of same color blocking, no movement possible
        int[] TestResult2 = new int[] {1,2,0,0,AuxPawn.getPointsForKill()}; //Piece of different color, possible attack.
        int[] TestResult3 = new int[] {1,2,0,0,AuxKnight.getPointsForMove()}; //No piece, movement possible
        int[] TestResult4 = null; //Movement would be out of range, no movement possible
        int[] TestResult5 = null; //Movement would be out of range, no movement possible
        
        return Stream.of(
                Arguments.of("p                 h                                                                                                                                                                                                                                             ", 1, 2, TestResult1),
                Arguments.of("P                 h                                                                                                                                                                                                                                             ", 1, 2, TestResult2),
                Arguments.of("                  h                                                                                                                                                                                                                                             ", 1, 2, TestResult3),
                Arguments.of("                 h                                                                                                                                                                                                                                              ", 1, 1, TestResult4),
                Arguments.of("  h                                                                                                                                                                                                                                                             ", 0, 2, TestResult5)              
        );
    }    
    
    
    @ParameterizedTest(name="Knight move left and up test run {index}")
    @MethodSource("testMoveLeftAndUp_Parameters")
    public void testMoveLeftAndUp(String boardString, int currentRow, int currentCol, int[] expectedMove){
        //Create board
        List<List<ChessPiece>> TestBoard = Board.generateBoardConfig(boardString);
        //Finished creating board
        
        Knight TestKnight = new Knight(currentRow,currentCol,"black");
        assertArrayEquals(expectedMove, TestKnight.moveLeftAndUp(TestBoard));
        
    }    
    
    //////////////////////////////MOVE LEFT AND DOWN TEST/////////////////////////////////////////////////////////////////////////////////
    
    static Stream<Arguments> testMoveLeftAndDown_Parameters(){
        Pawn AuxPawn = new Pawn(0,0,"");
        Knight AuxKnight = new Knight(0,0,"");
        int[] TestResult1 = null; //Piece of same color blocking, no movement possible
        int[] TestResult2 = new int[] {14,2,15,0,AuxPawn.getPointsForKill()}; //Piece of different color, possible attack.
        int[] TestResult3 = new int[] {14,2,15,0,AuxKnight.getPointsForMove()}; //No piece, movement possible
        int[] TestResult4 = null; //Movement would be out of range, no movement possible
        int[] TestResult5 = null; //Movement would be out of range, no movement possible
        
        return Stream.of(
                Arguments.of("                                                                                                                                                                                                                                  h             p               ", 14, 2, TestResult1),
                Arguments.of("                                                                                                                                                                                                                                  h             P               ", 14, 2, TestResult2),
                Arguments.of("                                                                                                                                                                                                                                  h                             ", 14, 2, TestResult3),
                Arguments.of("                                                                                                                                                                                                                                 h                              ", 14, 1, TestResult4),
                Arguments.of("                                                                                                                                                                                                                                                  h             ", 15, 2, TestResult5)              
        );
    }    
    
    
    @ParameterizedTest(name="Knight move left and down test run {index}")
    @MethodSource("testMoveLeftAndDown_Parameters")
    public void testMoveLeftAndDown(String boardString, int currentRow, int currentCol, int[] expectedMove){
        //Create board
        List<List<ChessPiece>> TestBoard = Board.generateBoardConfig(boardString);
        //Finished creating board
        
        Knight TestKnight = new Knight(currentRow,currentCol,"black");
        assertArrayEquals(expectedMove, TestKnight.moveLeftAndDown(TestBoard));
        
    }
    
    //////////////////////////////CALCULATE POSSIBLE MOVES TEST/////////////////////////////////////////////////////////////////////////////////
    
    static Stream<Arguments> testCalculatePossibleMoves_Parameters(){
        Pawn AuxPawn = new Pawn(0,0,"");
        List<int[]> TestResult1 = new ArrayList<>(); //Surrounded by pieces of same color, no moves should be generated
        List<int[]> TestResult2 = new ArrayList<>(); //Surrounded by pieces of different color, all possible attacks.
        TestResult2.add(new int[] {7,7,9,6,AuxPawn.getPointsForKill()}); //Down and left
        TestResult2.add(new int[] {7,7,9,8,AuxPawn.getPointsForKill()}); //Down and right
        TestResult2.add(new int[] {7,7,8,9,AuxPawn.getPointsForKill()}); //Right and down
        TestResult2.add(new int[] {7,7,6,9,AuxPawn.getPointsForKill()}); //Right and up
        TestResult2.add(new int[] {7,7,5,8,AuxPawn.getPointsForKill()}); //Up and right
        TestResult2.add(new int[] {7,7,5,6,AuxPawn.getPointsForKill()}); //Up and left
        TestResult2.add(new int[] {7,7,6,5,AuxPawn.getPointsForKill()}); //Left and up
        TestResult2.add(new int[] {7,7,8,5,AuxPawn.getPointsForKill()}); //Left and down
        
        return Stream.of(
                Arguments.of("                                                                                      p p            p   p             h             p   p            p p                                                                                                       ", 7, 7, TestResult1),
                Arguments.of("                                                                                      P P            P   P             h             P   P            P P                                                                                                       ", 7, 7, TestResult2)             
        );
    }    
    
    
    @ParameterizedTest(name="Queen calculatePossibleMoves test run {index}")
    @MethodSource("testCalculatePossibleMoves_Parameters")
    public void testCalculatePossbileMoves(String boardString, int currentRow, int currentCol, List<int[]> expectedMoves){
        //Create board
        List<List<ChessPiece>> TestBoard = Board.generateBoardConfig(boardString);
        //Finished creating board
        
        Knight TestKnight = new Knight(currentRow,currentCol,"black");
        TestKnight.calculatePossibleMoves(TestBoard);
        assertArrayEquals(expectedMoves.toArray(), TestKnight.getPossibleMoves().toArray());
        
    }    
    
    /**
     * Test of isNull method, of class Knight.
     */
    @Test
    public void testIsNull() {
        Knight instance = new Knight(0,0,"");
        boolean expResult = false;
        boolean result = instance.isNull();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of copy method, of class Knight.
     */  
    @Test
    public void testCopy(){
        Knight testKnight = new Knight(13,5,"white");
        Knight testKnightCopy = (Knight)testKnight.copy();
        assertEquals(testKnight.getCurrentRow(),testKnightCopy.getCurrentRow());
        assertEquals(testKnight.getCurrentCol(),testKnightCopy.getCurrentCol());
        assertEquals(testKnight.getColor(),testKnightCopy.getColor());
    }      
    
}
