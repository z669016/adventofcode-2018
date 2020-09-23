package com.putoet.day14;

import java.util.List;

public class Day14 {
    public static void main(String[] args) {
        final int input = 509671;
        RecipeScores recipeScores = new RecipeScores(input, List.of(3, 7));
        System.out.println(recipeScores.bake(input + 10).skipAndLimit(input, 10));

        recipeScores = new RecipeScores(input, List.of(3, 7));
        final String code = String.valueOf(input);
        recipeScores.bake(code);
        int offset = recipeScores.size() - (recipeScores.endsWith(code) ? code.length() : code.length() + 1);
        System.out.println(offset);
    }
}
