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
public class RookTest {
    
    public RookTest() {
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
    
    static Stream<Arguments> testRook_Parameters(){  
        return Stream.of(
                Arguments.of(1,2,"white"),
                Arguments.of(1,2,"black")              
        );
    }    
    
    
    @ParameterizedTest(name="Rook constructor test run {index}")
    @MethodSource("testRook_Parameters")
    public void testRook(int currentRow, int currentCol, String color){
       
        Rook TestRook = new Rook(currentRow,currentCol,color);
        assertEquals(currentRow, TestRook.getCurrentRow());
        assertEquals(currentCol, TestRook.getCurrentCol());
        assertEquals(color, TestRook.getColor());
        assertEquals(60, TestRook.getPointsForMove());
        assertEquals(600, TestRook.getPointsForKill());
        
    } 
    
    //////////////////////////////MOVE RIGHT TEST/////////////////////////////////////////////////////////////////////////////////
    
    static Stream<Arguments> testMoveRight_Parameters(){
        Pawn auxPawn = new Pawn(0,0,"");
        Rook auxRook = new Rook(0,0,"");
        List<int[]> TestResult1 = new ArrayList<>(); //Adjacent piece of same color, no moves should be generated
        List<int[]> TestResult2 = new ArrayList<>(); //Piece of different color, with possible movement and attack.
        TestResult2.add(new int[] {8,12,8,13,auxRook.getPointsForMove()});
        TestResult2.add(new int[] {8,12,8,14,auxPawn.getPointsForKill()});
        List<int[]> TestResult3 = new ArrayList<>(); //Piece of same color but with possible movement
        TestResult3.add(new int[] {8,12,8,13,auxRook.getPointsForMove()});
        List<int[]> TestResult4 = new ArrayList<>(); //No Pieces in the way, moves till the border
        TestResult4.add(new int[] {8,12,8,13,auxRook.getPointsForMove()});
        TestResult4.add(new int[] {8,12,8,14,auxRook.getPointsForMove()});
        TestResult4.add(new int[] {8,12,8,15,auxRook.getPointsForMove()});
        List<int[]> TestResult5 = new ArrayList<>(); //Piece on the border, no move possible
        
        return Stream.of(Arguments.of("                                                                                                                                            rp                                                                                                                  ", 8, 12, TestResult1),
                Arguments.of("                                                                                                                                            r P                                                                                                                 ", 8, 12, TestResult2),
                Arguments.of("                                                                                                                                            r p                                                                                                                 ", 8, 12, TestResult3),
                Arguments.of("                                                                                                                                            r                                                                                                                   ", 8, 12, TestResult4),
                Arguments.of("                                                                                                                                               r                                                                                                                ", 8, 15, TestResult5)              
        );
    }    
    
    
    @ParameterizedTest(name="Rook move right test run {index}")
    @MethodSource("testMoveRight_Parameters")
    public void testMoveRight(String boardString, int currentRow, int currentCol, List<int[]> expectedMoves){
        //Create board
        List<List<ChessPiece>> TestBoard = Board.generateBoardConfig(boardString);
        //Finished creating board
        
        Rook TestRook = new Rook(currentRow,currentCol,"black");
        TestRook.moveRight(currentRow, currentCol + 1, TestBoard);
        assertArrayEquals(expectedMoves.toArray(), TestRook.getPossibleMoves().toArray());
        
    }
    
    //////////////////////////////MOVE DOWN TEST/////////////////////////////////////////////////////////////////////////////////
    
    static Stream<Arguments> testMoveDown_Parameters(){
        Pawn auxPawn = new Pawn(0,0,"");
        Rook auxRook = new Rook(0,0,"");
        List<int[]> TestResult1 = new ArrayList<>(); //Adjacent piece of same color, no moves should be generated
        List<int[]> TestResult2 = new ArrayList<>(); //Piece of different color, with possible movement and attack.
        TestResult2.add(new int[] {12,12,13,12,auxRook.getPointsForMove()});
        TestResult2.add(new int[] {12,12,14,12,auxPawn.getPointsForKill()});
        List<int[]> TestResult3 = new ArrayList<>(); //Piece of same color but with possible movement
        TestResult3.add(new int[] {12,12,13,12,auxRook.getPointsForMove()});
        List<int[]> TestResult4 = new ArrayList<>(); //No Pieces in the way, moves till the border
        TestResult4.add(new int[] {12,12,13,12,auxRook.getPointsForMove()});
        TestResult4.add(new int[] {12,12,14,12,auxRook.getPointsForMove()});
        TestResult4.add(new int[] {12,12,15,12,auxRook.getPointsForMove()});
        List<int[]> TestResult5 = new ArrayList<>(); //Piece on the border, no move possible
        
        return Stream.of(Arguments.of("                                                                                                                                                                                                            r               p                                   ", 12, 12, TestResult1),
                Arguments.of("                                                                                                                                                                                                            r                               P                   ", 12, 12, TestResult2),
                Arguments.of("                                                                                                                                                                                                            r                               p                   ", 12, 12, TestResult3),
                Arguments.of("                                                                                                                                                                                                            r                                                   ", 12, 12, TestResult4),
                Arguments.of("                                                                                                                                                                                                                                                            r   ", 15, 12, TestResult5)              
        );
    }    
    
    
    @ParameterizedTest(name="Rook move down test run {index}")
    @MethodSource("testMoveDown_Parameters")
    public void testMoveDown(String boardString, int currentRow, int currentCol, List<int[]> expectedMoves){
        //Create board
        List<List<ChessPiece>> TestBoard = Board.generateBoardConfig(boardString);
        //Finished creating board
        
        Rook TestRook = new Rook(currentRow,currentCol,"black");
        TestRook.moveDown(currentRow + 1, currentCol, TestBoard);
        assertArrayEquals(expectedMoves.toArray(), TestRook.getPossibleMoves().toArray());
        
    }

    //////////////////////////////MOVE LEFT TEST/////////////////////////////////////////////////////////////////////////////////
    
    static Stream<Arguments> testMoveLeft_Parameters(){
        Pawn auxPawn = new Pawn(0,0,"");
        Rook auxRook = new Rook(0,0,"");
        List<int[]> TestResult1 = new ArrayList<>(); //Adjacent piece of same color, no moves should be generated
        List<int[]> TestResult2 = new ArrayList<>(); //Piece of different color, with possible movement and attack.
        TestResult2.add(new int[] {12,3,12,2,auxRook.getPointsForMove()});
        TestResult2.add(new int[] {12,3,12,1,auxPawn.getPointsForKill()});
        List<int[]> TestResult3 = new ArrayList<>(); //Piece of same color but with possible movement
        TestResult3.add(new int[] {12,3,12,2,auxRook.getPointsForMove()});
        List<int[]> TestResult4 = new ArrayList<>(); //No Pieces in the way, moves till the border
        TestResult4.add(new int[] {12,3,12,2,auxRook.getPointsForMove()});
        TestResult4.add(new int[] {12,3,12,1,auxRook.getPointsForMove()});
        TestResult4.add(new int[] {12,3,12,0,auxRook.getPointsForMove()});
        List<int[]> TestResult5 = new ArrayList<>(); //Piece on the border, no move possible
        
        return Stream.of(
                Arguments.of("                                                                                                                                                                                                  pr                                                            ", 12, 3, TestResult1),
                Arguments.of("                                                                                                                                                                                                 P r                                                            ", 12, 3, TestResult2),
                Arguments.of("                                                                                                                                                                                                 p r                                                            ", 12, 3, TestResult3),
                Arguments.of("                                                                                                                                                                                                   r                                                            ", 12, 3, TestResult4),
                Arguments.of("                                                                                                                                                                                                r                                                               ", 12, 0, TestResult5)              
        );
    }    
    
    
    @ParameterizedTest(name="Rook move left test run {index}")
    @MethodSource("testMoveLeft_Parameters")
    public void testMoveLeft(String boardString, int currentRow, int currentCol, List<int[]> expectedMoves){
        //Create board
        List<List<ChessPiece>> TestBoard = Board.generateBoardConfig(boardString);
        //Finished creating board
        
        Rook TestRook = new Rook(currentRow,currentCol,"black");
        TestRook.moveLeft(currentRow, currentCol - 1, TestBoard);
        assertArrayEquals(expectedMoves.toArray(), TestRook.getPossibleMoves().toArray());
        
    }    

    //////////////////////////////MOVE UP TEST/////////////////////////////////////////////////////////////////////////////////
    
    static Stream<Arguments> testMoveUp_Parameters(){
        Pawn auxPawn = new Pawn(0,0,"");
        Rook auxRook = new Rook(0,0,"");
        List<int[]> TestResult1 = new ArrayList<>(); //Adjacent piece of same color, no moves should be generated
        List<int[]> TestResult2 = new ArrayList<>(); //Piece of different color, with possible movement and attack.
        TestResult2.add(new int[] {3,2,2,2,auxRook.getPointsForMove()});
        TestResult2.add(new int[] {3,2,1,2,auxPawn.getPointsForKill()});
        List<int[]> TestResult3 = new ArrayList<>(); //Piece of same color but with possible movement
        TestResult3.add(new int[] {3,2,2,2,auxRook.getPointsForMove()});
        List<int[]> TestResult4 = new ArrayList<>(); //No Pieces in the way, moves till the border
        TestResult4.add(new int[] {3,2,2,2,auxRook.getPointsForMove()});
        TestResult4.add(new int[] {3,2,1,2,auxRook.getPointsForMove()});
        TestResult4.add(new int[] {3,2,0,2,auxRook.getPointsForMove()});
        List<int[]> TestResult5 = new ArrayList<>(); //Piece on the border, no move possible
        
        return Stream.of(
                Arguments.of("                                  p               r                                                                                                                                                                                                             ", 3, 2, TestResult1),
                Arguments.of("                  P                               r                                                                                                                                                                                                             ", 3, 2, TestResult2),
                Arguments.of("                  p                               r                                                                                                                                                                                                             ", 3, 2, TestResult3),
                Arguments.of("                                                  r                                                                                                                                                                                                             ", 3, 2, TestResult4),
                Arguments.of("  r                                                                                                                                                                                                                                                             ", 0, 2, TestResult5)              
        );
    }    
    
    
    @ParameterizedTest(name="Rook move up test run {index}")
    @MethodSource("testMoveUp_Parameters")
    public void testMoveUp(String boardString, int currentRow, int currentCol, List<int[]> expectedMoves){
        //Create board
        List<List<ChessPiece>> TestBoard = Board.generateBoardConfig(boardString);
        //Finished creating board
        
        Rook TestRook = new Rook(currentRow,currentCol,"black");
        TestRook.moveUp(currentRow - 1, currentCol, TestBoard);
        assertArrayEquals(expectedMoves.toArray(), TestRook.getPossibleMoves().toArray());
        
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
        
        return Stream.of(
                Arguments.of("                                                                                                      ppp             prp             ppp                                                                                                                       ", 7, 7, TestResult1),
                Arguments.of("                                                                                                      PPP             PrP             PPP                                                                                                                       ", 7, 7, TestResult2)             
        );
    }    
    
    
    @ParameterizedTest(name="Queen calculatePossibleMoves test run {index}")
    @MethodSource("testCalculatePossibleMoves_Parameters")
    public void testCalculatePossbileMoves(String boardString, int currentRow, int currentCol, List<int[]> expectedMoves){
        //Create board
        List<List<ChessPiece>> TestBoard = Board.generateBoardConfig(boardString);
        //Finished creating board
        
        Rook TestRook = new Rook(currentRow,currentCol,"black");
        TestRook.calculatePossibleMoves(TestBoard);
        assertArrayEquals(expectedMoves.toArray(), TestRook.getPossibleMoves().toArray());
        
    }    
    
    /**
     * Test of isNull method, of class Rook.
     */
    @Test
    public void testIsNull() {
        Rook instance = new Rook(0,0,"");
        boolean expResult = false;
        boolean result = instance.isNull();
        assertEquals(expResult, result);
    }

    /**
     * Test of positionBias method, of class Rook.
     */  
    @Test
    public void testPositionBias(){
        Rook testRook = new Rook(1,4,"white");
        assertEquals(0,testRook.positionBias());
    }
    
    /**
     * Test of copy method, of class Rook.
     */  
    @Test
    public void testCopy(){
        Rook testRook = new Rook(3,6,"black");
        Rook testRookCOpy = (Rook)testRook.copy();
        assertEquals(testRook.getCurrentRow(),testRookCOpy.getCurrentRow());
        assertEquals(testRook.getCurrentCol(),testRookCOpy.getCurrentCol());
        assertEquals(testRook.getColor(),testRookCOpy.getColor());
    }    
    
}
