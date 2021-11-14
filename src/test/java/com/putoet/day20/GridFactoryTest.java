package com.putoet.day20;

import com.putoet.grid.GridUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GridFactoryTest {
    static final String REGEX1 = "^ENWWW(NEEE|SSE(EE|N))$";
    static final List<String> GRID1 = List.of(
            "#########",
            "#.|.|.|.#",
            "#-#######",
            "#.|.|.|.#",
            "#-#####-#",
            "#.#.#X|.#",
            "#-#-#####",
            "#.|.|.|.#",
            "#########"
    );

    static final String REGEX2 = "^ENNWSWW(NEWS|)SSSEEN(WNSE|)EE(SWEN|)NNN$";
    static final List<String> GRID2 = List.of(
            "###########",
            "#.|.#.|.#.#",
            "#-###-#-#-#",
            "#.|.|.#.#.#",
            "#-#####-#-#",
            "#.#.#X|.#.#",
            "#-#-#####-#",
            "#.#.|.|.|.#",
            "#-###-###-#",
            "#.|.|.#.|.#",
            "###########"
    );

    static final String REGEX3 = "^ESSWWN(E|NNENN(EESS(WNSE|)SSS|WWWSSSSE(SW|NNNE)))$";
    static final List<String> GRID3 = List.of(
            "#############",
            "#.|.|.|.|.|.#",
            "#-#####-###-#",
            "#.#.|.#.#.#.#",
            "#-#-###-#-#-#",
            "#.#.#.|.#.|.#",
            "#-#-#-#####-#",
            "#.#.#.#X|.#.#",
            "#-#-#-###-#-#",
            "#.|.#.|.#.#.#",
            "###-#-###-#-#",
            "#.|.#.|.|.#.#",
            "#############"
    );

    static final String REGEX4 = "^WSSEESWWWNW(S|NENNEEEENN(ESSSSW(NWSW|SSEN)|WSWWN(E|WWS(E|SS))))$";
    static final List<String> GRID4 = List.of(
            "###############",
            "#.|.|.|.#.|.|.#",
            "#-###-###-#-#-#",
            "#.|.#.|.|.#.#.#",
            "#-#########-#-#",
            "#.#.|.|.|.|.#.#",
            "#-#-#########-#",
            "#.#.#.|X#.|.#.#",
            "###-#-###-#-#-#",
            "#.|.#.#.|.#.|.#",
            "#-###-#####-###",
            "#.|.#.|.|.#.#.#",
            "#-#-#####-#-#-#",
            "#.#.|.|.|.#.|.#",
            "###############"
    );

    @Test
    void of() {
        assertEquals(GRID1, GridUtils.toList(GridFactory.of(REGEX1).grid()));
        assertEquals(GRID2, GridUtils.toList(GridFactory.of(REGEX2).grid()));
        assertEquals(GRID3, GridUtils.toList(GridFactory.of(REGEX3).grid()));
        assertEquals(GRID4, GridUtils.toList(GridFactory.of(REGEX4).grid()));
    }
}