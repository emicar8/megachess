/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megachess.client;

import java.util.Comparator;

/**
 *
 * @author Emile
 */
public class MoveComparator implements Comparator<int[]>{

    @Override
    public int compare(int[] arg0, int[] arg1) {
        return arg0[4] - arg1[4];
    }
    
}
