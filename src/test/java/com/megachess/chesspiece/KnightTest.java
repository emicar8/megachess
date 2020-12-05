/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megachess.chesspiece;

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

    private List<List<ChessPiece>> generateBoard(String boardString){
        List<List<ChessPiece>> Board = new ArrayList<>();
        for(int row = 0; row < 16; row++){
            List<ChessPiece> BoardRow = new ArrayList<>();
            for(int column = 0; column < 16; column++){
                switch(boardString.charAt(row*16 + column)){
                    case 'p':
                       BoardRow.add(new Pawn(row,column,"black"));
                       break;
                    case 'P':
                        BoardRow.add(new Pawn(row,column,"white"));
                        break;                               
                   case 'r':
                       BoardRow.add(new Rook(row,column,"black")); 
                       break;
                    case 'R':
                        BoardRow.add(new Rook(row,column,"white")); 
                        break;                               
                   case 'h':
                       BoardRow.add(new Knight(row,column,"black"));
                       break;
                    case 'H':
                        BoardRow.add(new Knight(row,column,"white")); 
                        break;                               
                   case 'b':
                       BoardRow.add(new Bishop(row,column,"black")); 
                       break;
                    case 'B':
                        BoardRow.add(new Bishop(row,column,"white"));
                        break;                               
                   case 'q':
                       BoardRow.add(new Queen(row,column,"black"));
                       break;
                    case 'Q':
                        BoardRow.add(new Queen(row,column,"white"));
                        break;                               
                   case 'k':
                       BoardRow.add(new King(row,column,"black"));
                       break;                          
                    case 'K':
                        BoardRow.add(new King(row,column,"white")); 
                        break;
                    default:
                        BoardRow.add(new NullPiece(row,column));
                        break;                        
                }                        
            }
            Board.add(BoardRow);
        } 
        return Board;
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
        List<List<ChessPiece>> Board = this.generateBoard(boardString);
        //Finished creating board
        
        Knight TestKnight = new Knight(currentRow,currentCol,"black");
        assertArrayEquals(expectedMove, TestKnight.moveUpAndRight(Board));
        
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
        List<List<ChessPiece>> Board = this.generateBoard(boardString);
        //Finished creating board
        
        Knight TestKnight = new Knight(currentRow,currentCol,"black");
        assertArrayEquals(expectedMove, TestKnight.moveDownAndRight(Board));
        
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
        List<List<ChessPiece>> Board = this.generateBoard(boardString);
        //Finished creating board
        
        Knight TestKnight = new Knight(currentRow,currentCol,"black");
        assertArrayEquals(expectedMove, TestKnight.moveUpAndLeft(Board));
        
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
        List<List<ChessPiece>> Board = this.generateBoard(boardString);
        //Finished creating board
        
        Knight TestKnight = new Knight(currentRow,currentCol,"black");
        assertArrayEquals(expectedMove, TestKnight.moveDownAndLeft(Board));
        
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
        List<List<ChessPiece>> Board = this.generateBoard(boardString);
        //Finished creating board
        
        Knight TestKnight = new Knight(currentRow,currentCol,"black");
        assertArrayEquals(expectedMove, TestKnight.moveRightAndUp(Board));
        
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
        List<List<ChessPiece>> Board = this.generateBoard(boardString);
        //Finished creating board
        
        Knight TestKnight = new Knight(currentRow,currentCol,"black");
        assertArrayEquals(expectedMove, TestKnight.moveRightAndDown(Board));
        
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
        List<List<ChessPiece>> Board = this.generateBoard(boardString);
        //Finished creating board
        
        Knight TestKnight = new Knight(currentRow,currentCol,"black");
        assertArrayEquals(expectedMove, TestKnight.moveLeftAndUp(Board));
        
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
        List<List<ChessPiece>> Board = this.generateBoard(boardString);
        //Finished creating board
        
        Knight TestKnight = new Knight(currentRow,currentCol,"black");
        assertArrayEquals(expectedMove, TestKnight.moveLeftAndDown(Board));
        
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
    
}
