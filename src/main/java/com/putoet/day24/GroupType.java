package com.putoet.day24;

public enum GroupType {
    IMMUNE_SYSTEM,
    INFECTION;

    @Override
    public String toString(){
        return switch (this) {
            case IMMUNE_SYSTEM -> "Immune System";
            case INFECTION -> "Infection";
        };
    }
}
