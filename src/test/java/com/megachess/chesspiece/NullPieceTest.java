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
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 *
 * @author Emile
 */
public class NullPieceTest {
    
    public NullPieceTest() {
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
    
    static Stream<Arguments> testNullPiece_Parameters(){  
        return Stream.of(
                Arguments.of(1,2),
                Arguments.of(14,5)              
        );
    }    
    
    
    @ParameterizedTest(name="NullPiece constructor test run {index}")
    @MethodSource("testNullPiece_Parameters")
    public void testNullPiece(int currentRow, int currentCol){
       
        NullPiece TestNullPiece = new NullPiece(currentRow,currentCol);
        assertEquals(currentRow, TestNullPiece.getCurrentRow());
        assertEquals(currentCol, TestNullPiece.getCurrentCol());
        assertEquals("", TestNullPiece.getColor());
        assertEquals(0, TestNullPiece.getPointsForMove());
        assertEquals(0, TestNullPiece.getPointsForKill());
        
    }     
    
    /**
     * Test of isNull method, of class NullPiece.
     */
    @Test
    public void testIsNull() {
        NullPiece instance = new NullPiece(0,0);
        boolean expResult = true;
        boolean result = instance.isNull();
        assertEquals(expResult, result);
    }
    
}
