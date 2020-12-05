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
        List<List<ChessPiece>> Board = this.generateBoard(boardString);
        //Finished creating board
        
        Queen TestQueen = new Queen(currentRow,currentCol,"black");
        TestQueen.moveRight(currentRow, currentCol + 1, Board);
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
        List<List<ChessPiece>> Board = this.generateBoard(boardString);
        //Finished creating board
        
        Queen TestQueen = new Queen(currentRow,currentCol,"black");
        TestQueen.moveDown(currentRow + 1, currentCol, Board);
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
        List<List<ChessPiece>> Board = this.generateBoard(boardString);
        //Finished creating board
        
        Queen TestQueen = new Queen(currentRow,currentCol,"black");
        TestQueen.moveLeft(currentRow, currentCol - 1, Board);
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
        List<List<ChessPiece>> Board = this.generateBoard(boardString);
        //Finished creating board
        
        Queen TestQueen = new Queen(currentRow,currentCol,"black");
        TestQueen.moveUp(currentRow - 1, currentCol, Board);
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
        List<List<ChessPiece>> Board = this.generateBoard(boardString);
        //Finished creating board
        
        Queen TestQueen = new Queen(currentRow,currentCol,"black");
        TestQueen.moveRightAndUp(currentRow - 1, currentCol + 1, Board);
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
        List<List<ChessPiece>> Board = this.generateBoard(boardString);
        //Finished creating board
        
        Queen TestQueen = new Queen(currentRow,currentCol,"black");
        TestQueen.moveRightAndDown(currentRow + 1, currentCol + 1, Board);
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
        List<List<ChessPiece>> Board = this.generateBoard(boardString);
        //Finished creating board
        
        Queen TestQueen = new Queen(currentRow,currentCol,"black");
        TestQueen.moveLeftAndUp(currentRow - 1, currentCol - 1, Board);
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
        List<List<ChessPiece>> Board = this.generateBoard(boardString);
        //Finished creating board
        
        Queen TestQueen = new Queen(currentRow,currentCol,"black");
        TestQueen.moveLeftAndDown(currentRow + 1, currentCol - 1, Board);
        assertArrayEquals(expectedMoves.toArray(), TestQueen.getPossibleMoves().toArray());
        
    }    
    
    @Test
    public void testIsNull() {
        Queen instance = new Queen(0,0,"");
        boolean expResult = false;
        boolean result = instance.isNull();
        assertEquals(expResult, result);
    }
    
}
