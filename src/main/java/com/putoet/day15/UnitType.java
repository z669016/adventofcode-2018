package com.putoet.day15;

enum UnitType {
    ELF,
    GOBLIN;

    public static UnitType of(char c) {
        return switch (c) {
            case 'E' -> ELF;
            case 'G' -> GOBLIN;
            default -> throw new IllegalArgumentException("Invalid unit type " + c);
        };
    }

    public char toChar() {
        return switch (this) {
            case ELF -> 'E';
            case GOBLIN -> 'G';
        };
    }
}
