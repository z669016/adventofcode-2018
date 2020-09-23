package com.putoet.day14;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecipeScoresTest {
    private RecipeScores recipeScores;

    @BeforeEach
    void setup() {
        recipeScores = new RecipeScores(100, List.of(3, 7));
    }

    @Test
    void bake() {
        assertEquals(List.of(3,7,1,0,1,0,1,2,4,5,1,5,8,9,1,6,7,7,9,2), recipeScores.bake(18).asList());
    }

    @Test
    void bakeUntil() {
        assertEquals(9, recipeScores.bake("51589").size() - 5);

        setup();
        assertEquals(5, recipeScores.bake("01245").size() - 5);

        setup();
        assertEquals(18, recipeScores.bake("92510").size() - 5);

        setup();
        assertEquals(2018, recipeScores.bake("59414").size() - 5);
    }

    @Test
    void skipAndLimit() {
        assertEquals("5158916779", recipeScores.bake(18).skipAndLimit(9, 10));
    }
}