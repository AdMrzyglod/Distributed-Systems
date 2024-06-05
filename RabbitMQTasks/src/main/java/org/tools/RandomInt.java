package org.tools;

import java.util.Random;

public class RandomInt {
    public static int getRandomInt(int min,int max){
        return new Random().nextInt(max - min + 1) + min;
    }
}
