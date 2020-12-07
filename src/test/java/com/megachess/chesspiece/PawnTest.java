/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megachess.chesspiece;

import com.megachess.client.ChessBot;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class PawnTest {
    
    public PawnTest() {
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
    
    static Stream<Arguments> testPawn_Parameters(){  
        return Stream.of(
                Arguments.of(1,2,"white"),
                Arguments.of(1,2,"black"),
                Arguments.of(9,2,"white"),
                Arguments.of(6,2,"black")
        );
    }    
    
    
    @ParameterizedTest(name="Pawn constructor test run {index}")
    @MethodSource("testPawn_Parameters")
    public void testPawn(int currentRow, int currentCol, String color){
       
        Pawn TestPawn = new Pawn(currentRow,currentCol,color);
        assertEquals(currentRow, TestPawn.getCurrentRow());
        assertEquals(currentCol, TestPawn.getCurrentCol());
        assertEquals(color, TestPawn.getColor());
        if(currentRow == 6 || currentRow == 9){
           assertEquals(500, TestPawn.getPointsForMove()); 
        }else{
            assertEquals(80, TestPawn.getPointsForMove()); 
        }
        
        assertEquals(100, TestPawn.getPointsForKill());
        
    }     
    
    //////////////////////////////MOVE UP TEST/////////////////////////////////////////////////////////////////////////////////
    
    static Stream<Arguments> testMoveUpOnce_Parameters(){
        Pawn AuxPawn = new Pawn(0,0,"");
        int[] TestResult1 = null; //Adjacent piece of same color, no moves should be generated
        int[] TestResult2 = null; //Adjacent piece of different color, no moves should be generated
        int[] TestResult3 = new int[] {12,7,11,7,AuxPawn.getPointsForMove() + 8 - (12 - 1)}; //No piece in the way, movement possible
        
        return Stream.of(
                Arguments.of("                                                                                                                                                                                       p               p                                                        ", 12, 7, TestResult1),
                Arguments.of("                                                                                                                                                                                       P               p                                                        ", 12, 7, TestResult2),
                Arguments.of("                                                                                                                                                                                                       p                                                        ", 12, 7, TestResult3)              
        );
    }    
    
    
    @ParameterizedTest(name="Pawn move up once test run {index}")
    @MethodSource("testMoveUpOnce_Parameters")
    public void testMoveUpOnce(String boardString, int currentRow, int currentCol, int[] expectedMove){
        //Create board
        List<List<ChessPiece>> Board = ChessBot.generateBoard(boardString);
        //Finished creating board
        
        Pawn TestPawn = new Pawn(currentRow,currentCol,"black");
        assertArrayEquals(expectedMove, TestPawn.moveUpOnce(Board));
    }  

    //////////////////////////////MOVE UP TWICE TEST/////////////////////////////////////////////////////////////////////////////////
    
    static Stream<Arguments> testMoveUpTwice_Parameters(){
        Pawn AuxPawn = new Pawn(0,0,"");
        int[] TestResult1 = null; //Adjacent piece of same color, no moves should be generated
        int[] TestResult2 = null; //Adjacent piece of different color, no moves should be generated
        int[] TestResult3 = new int[] {12,7,10,7,AuxPawn.getPointsForMove() + 8 - (12 - 2)}; //No piece in the way, movement possible
        int[] TestResult4 = null; //Piece in the second position, can't move up two places
        int[] TestResult5 = null; //Piece in the first position, can't move up two places
        int[] TestResult6 = null; //Piece in the second position, can't move up two places
        int[] TestResult7 = new int[] {13,7,11,7,AuxPawn.getPointsForMove() + 8 - (13 - 2)}; //No piece in the way, movement possible
        
        return Stream.of(
                Arguments.of("                                                                                                                                                                                       p               p                                                        ", 12, 7, TestResult1),
                Arguments.of("                                                                                                                                                                                       P               p                                                        ", 12, 7, TestResult2),
                Arguments.of("                                                                                                                                                                                                       p                                                        ", 12, 7, TestResult3),
                Arguments.of("                                                                                                                                                                       p                               p                                                        ", 12, 7, TestResult4),
                Arguments.of("                                                                                                                                                                                                       p               p                                        ", 13, 7, TestResult5),
                Arguments.of("                                                                                                                                                                                       p                               p                                        ", 13, 7, TestResult6),
                Arguments.of("                                                                                                                                                                                                                       p                                        ", 13, 7, TestResult7)
        );
    }    
    
    
    @ParameterizedTest(name="Pawn move up twice test run {index}")
    @MethodSource("testMoveUpTwice_Parameters")
    public void testMoveUpTwice(String boardString, int currentRow, int currentCol, int[] expectedMove){
        //Create board
        List<List<ChessPiece>> Board = ChessBot.generateBoard(boardString);
        //Finished creating board
        
        Pawn TestPawn = new Pawn(currentRow,currentCol,"black");
        assertArrayEquals(expectedMove, TestPawn.moveUpTwice(Board));
    }

    //////////////////////////////MOVE DOWN TEST/////////////////////////////////////////////////////////////////////////////////
    
    static Stream<Arguments> testMoveDownOnce_Parameters(){
        Pawn AuxPawn = new Pawn(0,0,"");
        int[] TestResult1 = null; //Adjacent piece of same color, no moves should be generated
        int[] TestResult2 = null; //Adjacent piece of different color, no moves should be generated
        int[] TestResult3 = new int[] {3,7,4,7,AuxPawn.getPointsForMove() - 7 + 3 + 1}; //No piece in the way, movement possible
        
        return Stream.of(
                Arguments.of("                                                       p               p                                                                                                                                                                                        ", 3, 7, TestResult1),
                Arguments.of("                                                       p               P                                                                                                                                                                                        ", 3, 7, TestResult2),
                Arguments.of("                                                       p                                                                                                                                                                                                        ", 3, 7, TestResult3)              
        );
    }    
    
    
    @ParameterizedTest(name="Pawn move down once test run {index}")
    @MethodSource("testMoveDownOnce_Parameters")
    public void testMoveUpDown(String boardString, int currentRow, int currentCol, int[] expectedMove){
        //Create board
        List<List<ChessPiece>> Board = ChessBot.generateBoard(boardString);
        //Finished creating board
        
        Pawn TestPawn = new Pawn(currentRow,currentCol,"black");
        assertArrayEquals(expectedMove, TestPawn.moveDownOnce(Board));
    }  

    //////////////////////////////MOVE DOWN TWICE TEST/////////////////////////////////////////////////////////////////////////////////
    
    static Stream<Arguments> testMoveDownTwice_Parameters(){
        Pawn AuxPawn = new Pawn(0,0,"");
        int[] TestResult1 = null; //Adjacent piece of same color, no moves should be generated
        int[] TestResult2 = null; //Adjacent piece of different color, no moves should be generated
        int[] TestResult3 = new int[] {3,7,5,7,AuxPawn.getPointsForMove() - 7 + 3 + 2}; //No piece in the way, movement possible
        int[] TestResult4 = null; //Piece in the second position, can't move up two places
        int[] TestResult5 = null; //Piece in the first position, can't move up two places
        int[] TestResult6 = null; //Piece in the second position, can't move up two places
        int[] TestResult7 = new int[] {2,7,4,7,AuxPawn.getPointsForMove() - 7 + 2 + 2}; //No piece in the way, movement possible
        
        return Stream.of(
                Arguments.of("                                                       p               p                                                                                                                                                                                        ", 3, 7, TestResult1),
                Arguments.of("                                                       p               P                                                                                                                                                                                        ", 3, 7, TestResult2),
                Arguments.of("                                                       p                                                                                                                                                                                                        ", 3, 7, TestResult3),
                Arguments.of("                                                       p                               p                                                                                                                                                                        ", 3, 7, TestResult4),
                Arguments.of("                                       p               p                                                                                                                                                                                                        ", 2, 7, TestResult5),
                Arguments.of("                                       p                               p                                                                                                                                                                                        ", 2, 7, TestResult6),
                Arguments.of("                                       p                                                                                                                                                                                                                        ", 2, 7, TestResult7)
        );
    }    
    
    
    @ParameterizedTest(name="Pawn move down twice test run {index}")
    @MethodSource("testMoveDownTwice_Parameters")
    public void testMoveDownTwice(String boardString, int currentRow, int currentCol, int[] expectedMove){
        //Create board
        List<List<ChessPiece>> Board = ChessBot.generateBoard(boardString);
        //Finished creating board
        
        Pawn TestPawn = new Pawn(currentRow,currentCol,"black");
        assertArrayEquals(expectedMove, TestPawn.moveDownTwice(Board));
    }

    //////////////////////////////MOVE RIGHT AND UP TEST/////////////////////////////////////////////////////////////////////////////////
    
    static Stream<Arguments> testMoveUpAndRight_Parameters(){
        Pawn AuxPawn = new Pawn(0,0,"");
        int[] TestResult1 = new int[] {9,14,8,15,AuxPawn.getPointsForKill()}; //Piece of different color, with possible attack.
        int[] TestResult2 = null; //Adjacent piece of same color, no moves should be generated
        int[] TestResult3 = null; //No piece in the way, no movement possible
        int[] TestResult4 = null; //On border, no movement possible
        
        return Stream.of(
                Arguments.of("                                                                                                                                               p              P                                                                                                 ", 9, 14, TestResult1),
                Arguments.of("                                                                                                                                               P              P                                                                                                 ", 9, 14, TestResult2),
                Arguments.of("                                                                                                                                                              p                                                                                                 ", 9, 14, TestResult3),
                Arguments.of("                                                                                                                                               p                                                                                                                ", 8, 15, TestResult4)              
        );
    }    
    
    
    @ParameterizedTest(name="Pawn move right and up test run {index}")
    @MethodSource("testMoveUpAndRight_Parameters")
    public void testMoveUpAndRight(String boardString, int currentRow, int currentCol, int[] expectedMove){
        //Create board
        List<List<ChessPiece>> Board = ChessBot.generateBoard(boardString);
        //Finished creating board

        Pawn TestPawn = new Pawn(currentRow,currentCol,"white");
        assertArrayEquals(expectedMove, TestPawn.moveUpAndRight(Board));
    }
    
    //////////////////////////////MOVE LEFT AND UP TEST/////////////////////////////////////////////////////////////////////////////////
    
    static Stream<Arguments> testMoveUpAndLeft_Parameters(){
        Pawn AuxPawn = new Pawn(0,0,"");
        int[] TestResult1 = new int[] {9,1,8,0,AuxPawn.getPointsForKill()}; //Piece of different color, with possible attack.
        int[] TestResult2 = null; //Adjacent piece of same color, no moves should be generated
        int[] TestResult3 = null; //No piece in the way, no movement possible
        int[] TestResult4 = null; //On border, no movement possible
        
        return Stream.of(
                Arguments.of("                                                                                                                                p                P                                                                                                              ", 9, 1, TestResult1),
                Arguments.of("                                                                                                                                P                P                                                                                                              ", 9, 1, TestResult2),
                Arguments.of("                                                                                                                                                 P                                                                                                              ", 9, 1, TestResult3),
                Arguments.of("                                                                                                                                P                                                                                                                               ", 8, 0, TestResult4)              
        );
    }    
    
    
    @ParameterizedTest(name="Pawn move left and up test run {index}")
    @MethodSource("testMoveUpAndLeft_Parameters")
    public void testMoveUpAndLeft(String boardString, int currentRow, int currentCol, int[] expectedMove){
        //Create board
        List<List<ChessPiece>> Board = ChessBot.generateBoard(boardString);
        //Finished creating board

        Pawn TestPawn = new Pawn(currentRow,currentCol,"white");
        assertArrayEquals(expectedMove, TestPawn.moveUpAndLeft(Board));
    } 
    
    //////////////////////////////MOVE RIGHT AND DOWN TEST/////////////////////////////////////////////////////////////////////////////////
    
    static Stream<Arguments> testMoveDownAndRight_Parameters(){
        Pawn AuxPawn = new Pawn(0,0,"");
        int[] TestResult1 = new int[] {5,14,6,15,AuxPawn.getPointsForKill()}; //Piece of different color, with possible attack.
        int[] TestResult2 = null; //Adjacent piece of same color, no moves should be generated
        int[] TestResult3 = null; //No piece in the way, no movement possible
        int[] TestResult4 = null; //On border, no movement possible
        
        return Stream.of(
                Arguments.of("                                                                                              p                P                                                                                                                                                ", 5, 14, TestResult1),
                Arguments.of("                                                                                              p                p                                                                                                                                                ", 5, 14, TestResult2),
                Arguments.of("                                                                                              p                                                                                                                                                                 ", 5, 14, TestResult3),
                Arguments.of("                                                                                                               p                                                                                                                                                ", 6, 15, TestResult4)              
        );
    }    
    
    
    @ParameterizedTest(name="Pawn move right and down test run {index}")
    @MethodSource("testMoveDownAndRight_Parameters")
    public void testMoveDownAndRight(String boardString, int currentRow, int currentCol, int[] expectedMove){
        //Create board
        List<List<ChessPiece>> Board = ChessBot.generateBoard(boardString);
        //Finished creating board

        Pawn TestPawn = new Pawn(currentRow,currentCol,"black");
        assertArrayEquals(expectedMove, TestPawn.moveDownAndRight(Board));
    }
    
    //////////////////////////////MOVE LEFT AND DOWN TEST/////////////////////////////////////////////////////////////////////////////////
    
    static Stream<Arguments> testMoveDownAndLeft_Parameters(){
        Pawn AuxPawn = new Pawn(0,0,"");
        int[] TestResult1 = new int[] {5,1,6,0,AuxPawn.getPointsForKill()}; //Piece of different color, with possible attack.
        int[] TestResult2 = null; //Adjacent piece of same color, no moves should be generated
        int[] TestResult3 = null; //No piece in the way, no movement possible
        int[] TestResult4 = null; //On border, no movement possible
        
        return Stream.of(
                Arguments.of("                                                                                 p              P                                                                                                                                                               ", 5, 1, TestResult1),
                Arguments.of("                                                                                 p              p                                                                                                                                                               ", 5, 1, TestResult2),
                Arguments.of("                                                                                 p                                                                                                                                                                              ", 5, 1, TestResult3),
                Arguments.of("                                                                                                p                                                                                                                                                               ", 6, 0, TestResult4)              
        );
    }    
    
    
    @ParameterizedTest(name="Pawn move left and down test run {index}")
    @MethodSource("testMoveDownAndLeft_Parameters")
    public void testMoveDownAndLeft(String boardString, int currentRow, int currentCol, int[] expectedMove){
        //Create board
        List<List<ChessPiece>> Board = ChessBot.generateBoard(boardString);
        //Finished creating board

        Pawn TestPawn = new Pawn(currentRow,currentCol,"black");
        assertArrayEquals(expectedMove, TestPawn.moveDownAndLeft(Board));
    }        

    //////////////////////////////CALCULATE POSSIBLE MOVES TEST/////////////////////////////////////////////////////////////////////////////////
    
    static Stream<Arguments> testCalculatePossibleMoves_Parameters(){
        Pawn AuxPawn = new Pawn(0,0,"");
        //Black Pawn
        List<int[]> TestResult1 = new ArrayList<>(); //All moves blocked, no moves should be generated
        List<int[]> TestResult2 = new ArrayList<>(); //All moves possible.
        TestResult2.add(new int[] {2,7,3,7,AuxPawn.getPointsForMove() - 7 + 2 + 1}); //Down once
        TestResult2.add(new int[] {2,7,4,7,AuxPawn.getPointsForMove() - 7 + 2 + 2}); //Down twice
        TestResult2.add(new int[] {2,7,3,8,AuxPawn.getPointsForKill()}); //Down and right
        TestResult2.add(new int[] {2,7,3,6,AuxPawn.getPointsForKill()}); //Down and left
        //White Pawn
        List<int[]> TestResult3 = new ArrayList<>(); //All moves blocked, no moves should be generated
        List<int[]> TestResult4 = new ArrayList<>(); //All moves possible.
        TestResult4.add(new int[] {13,7,12,7,AuxPawn.getPointsForMove() + 8 - (13 - 1)}); //Up once
        TestResult4.add(new int[] {13,7,11,7,AuxPawn.getPointsForMove() + 8 - (13 - 2)}); //Up twice
        TestResult4.add(new int[] {13,7,12,8,AuxPawn.getPointsForKill()}); //Up and right
        TestResult4.add(new int[] {13,7,12,6,AuxPawn.getPointsForKill()}); //Up and left        
        
        return Stream.of(
                Arguments.of("                                       p              ppp                                                                                                                                                                                                       ", 2, 7, "black", TestResult1),
                Arguments.of("                                       p              P P                                                                                                                                                                                                       ", 2, 7, "black", TestResult2),
                Arguments.of("                                                                                                                                                                                                      PPP              P                                        ", 13,7, "white", TestResult3),
                Arguments.of("                                                                                                                                                                                                      p p              P                                        ", 13,7, "white", TestResult4)                
        );
    }    
    
    
    @ParameterizedTest(name="Queen calculatePossibleMoves test run {index}")
    @MethodSource("testCalculatePossibleMoves_Parameters")
    public void testCalculatePossbileMoves(String boardString, int currentRow, int currentCol, String color, List<int[]> expectedMoves){
        //Create board
        List<List<ChessPiece>> Board = ChessBot.generateBoard(boardString);
        //Finished creating board
        
        Pawn TestPawn = new Pawn(currentRow,currentCol,color);
        try{
            TestPawn.calculatePossibleMoves(Board);            
        }catch(Exception ex){
            Logger.getLogger(PawnTest.class.getName()).log(Level.SEVERE, null, ex);            
        }

        assertArrayEquals(expectedMoves.toArray(), TestPawn.getPossibleMoves().toArray());
        
    }    
    
    /**
     * Test of isNull method, of class Pawn.
     */
    @Test
    public void testIsNull() {
        Pawn instance = new Pawn(0,0,"");
        boolean expResult = false;
        boolean result = instance.isNull();
        assertEquals(expResult, result);
    }
    
}
