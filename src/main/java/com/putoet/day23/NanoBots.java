package com.putoet.day23;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

class NanoBots {
    public static List<NanoBot> of(@NotNull List<String> lines) {
        return lines.stream().map(NanoBot::of).toList();
    }

    public static Optional<NanoBot> strongest(@NotNull List<NanoBot> bots) {
        return bots.stream().max(Comparator.comparing(NanoBot::r));
    }

    public static List<NanoBot> inRange(NanoBot target, List<NanoBot> bots) {
        return bots.stream()
                .filter(bot -> bot.coordinate().manhattanDistance(target.coordinate()) <= target.r())
                .toList();
    }
}
