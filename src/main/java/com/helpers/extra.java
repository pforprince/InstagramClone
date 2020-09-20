/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpers;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author princ
 */
public class extra {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        int userId = 2;
        list.add(2);
        for (int user : list) {
            if (user == userId) {
                list.remove(user);
            }
        }

    }
}
