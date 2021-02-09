package com.putoet.day19;

public class Day19Disassembled {
    public static void main(String[] args) {
        int r0 = 0;
        // int r2 = ((19 * 4 * 11) + (6 * 22 + 9)); // part 1
        int r2 = ((19*4*11)+(6*22+9)) + (((30*(29+(27*28)))*14)*32); // part 2
        int r4 = 1;

        do {
            if (r2 % r4 == 0)
                r0 += r4;

            r4 = r4 + 1;
        } while (r4 <= r2);

        System.out.println("The value in r0 is " + r0);
    }
}
