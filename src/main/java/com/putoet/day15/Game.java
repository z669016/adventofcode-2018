package com.putoet.day15;

import java.util.List;
import java.util.Queue;

public class Game {
    public static int play(List<String> lines) {
        assert lines != null && lines.size() > 0;

        BeverageBanditsBoard board = BeverageBanditsBoard.of(lines);
        System.out.println("Initial");
        board.print();
        System.out.println();

        int rounds = 0;
        boolean combat = true;
        while (combat){
            final Queue<Unit> units = board.units();
            while (!units.isEmpty() && combat) {
                final Unit unit = units.poll();
                if(unit.isAlive())
                    combat = unit.turn(board);
            }

            if (combat) {
                rounds++;
                board = new BeverageBanditsBoard(board.board(), board.units());

                System.out.println("After " + rounds + " round(s)");
                board.print();
                System.out.println();
            }
        }

        board.print();
        System.out.println();

        return board.units().stream().mapToInt(Unit::hitPoints).sum() * rounds;
    }
}
