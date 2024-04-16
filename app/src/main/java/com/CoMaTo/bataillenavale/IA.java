package com.CoMaTo.bataillenavale;

public class IA {
    public IA() {
    }
    public static int[] randomClick(){
        int[] click = new int[2];
        click[0] = (int) (Math.random() * 10);
        click[1] = (int) (Math.random() * 10);
        return click;
    }
}
