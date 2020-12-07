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
        List<List<ChessPiece>> Board = this.generateBoard(boardString);
        //Finished creating board
        
        Bishop TestBishop = new Bishop(currentRow,currentCol,"black");
        TestBishop.moveRightAndUp(currentRow - 1, currentCol + 1, Board);
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
        List<List<ChessPiece>> Board = this.generateBoard(boardString);
        //Finished creating board
        
        Bishop TestBishop = new Bishop(currentRow,currentCol,"black");
        TestBishop.moveRightAndDown(currentRow + 1, currentCol + 1, Board);
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
        List<List<ChessPiece>> Board = this.generateBoard(boardString);
        //Finished creating board
        
        Bishop TestBishop = new Bishop(currentRow,currentCol,"black");
        TestBishop.moveLeftAndUp(currentRow - 1, currentCol - 1, Board);
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
        List<List<ChessPiece>> Board = this.generateBoard(boardString);
        //Finished creating board
        
        Bishop TestBishop = new Bishop(currentRow,currentCol,"black");
        TestBishop.moveLeftAndDown(currentRow + 1, currentCol - 1, Board);
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
    
}
