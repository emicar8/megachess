/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megachess.chesspiece;

import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 *
 * @author Emile
 */
public class ChessPieceTest{
    
    public ChessPieceTest() {
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


    //////////////////////////////TEST ADDIFNOTNULL/////////////////////////////////////////////////////////////////////////////////
    
    static Stream<Arguments> testAddIfNotNull_Parameters(){
        
        int[] Test1 = new int[]{1,1,2,2,100};
        int[] Test2 = null;
        return Stream.of(
                Arguments.of(Test1, false),
                Arguments.of(Test2, true)
        );
    }    
    
    
    @ParameterizedTest(name="Add if not null method test run {index}")
    @MethodSource("testAddIfNotNull_Parameters")
    public void testEquals(int[] move, boolean isEmpty){
        Pawn TestPawn = new Pawn(1,1,"white");
        TestPawn.AddIfNotNull(move);
        assertEquals(TestPawn.getPossibleMoves().isEmpty(), isEmpty);     
    } 

    //////////////////////////////TEST EQUALS/////////////////////////////////////////////////////////////////////////////////
    
    static Stream<Arguments> testEquals_Parameters(){
        Pawn auxPawn = new Pawn(0,0,"white");
        Pawn equalPawn = new Pawn(0,0,"white");
        Pawn diffRowPawn = new Pawn(1,0,"white");
        Pawn diffColPawn = new Pawn(0,1,"white");
        Pawn diffColorPawn = new Pawn(0,0,"black");
        Queen auxQueen = new Queen(0,0,"white");
        String auxString = "";
        return Stream.of(
                Arguments.of(auxPawn, equalPawn, true),
                Arguments.of(auxPawn, auxPawn, true),
                Arguments.of(auxPawn, diffRowPawn, false),
                Arguments.of(auxPawn, diffColPawn, false),
                Arguments.of(auxPawn, diffColorPawn, false),
                Arguments.of(auxPawn, auxQueen, false),
                Arguments.of(auxPawn, auxString, false)
        );
    }    
    
    
    @ParameterizedTest(name="Equals method test run {index}")
    @MethodSource("testEquals_Parameters")
    public void testEquals(Object o1, Object o2, boolean equals){
        assertEquals(o1.equals(o2),equals);     
    }  
    
    //////////////////////////////TEST HASHCODE/////////////////////////////////////////////////////////////////////////////////
    
    static Stream<Arguments> testHashCode_Parameters(){
        Pawn auxPawn = new Pawn(0,0,"white");
        Pawn equalPawn = new Pawn(0,0,"white");
        Pawn diffRowPawn = new Pawn(1,0,"white");
        Pawn diffColPawn = new Pawn(0,1,"white");
        Pawn diffColorPawn = new Pawn(0,0,"black");
        Queen auxQueen = new Queen(0,0,"white");
        String auxString = "";
        return Stream.of(
                Arguments.of(auxPawn, equalPawn, true),
                Arguments.of(auxPawn, auxPawn, true),
                Arguments.of(auxPawn, diffRowPawn, false),
                Arguments.of(auxPawn, diffColPawn, false),
                Arguments.of(auxPawn, diffColorPawn, false),
                Arguments.of(auxPawn, auxQueen, false),
                Arguments.of(auxPawn, auxString, false)
        );
    }    
    
    
    @ParameterizedTest(name="HashCode method test run {index}")
    @MethodSource("testHashCode_Parameters")
    public void testHashCode(Object o1, Object o2, boolean equals){
        assertEquals(o1.hashCode() == o2.hashCode(), equals); 
    }    
    
    
}
