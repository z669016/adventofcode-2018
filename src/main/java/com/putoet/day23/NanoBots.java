package com.putoet.day23;

import com.putoet.grid.Point3D;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class NanoBots {
    public static List<NanoBot> of(List<String> lines) {
        return lines.stream().map(NanoBot::of).collect(Collectors.toList());
    }

    public static Optional<NanoBot> strongest(List<NanoBot> bots) {
        assert bots != null;

        return bots.stream().max(Comparator.comparing(bot -> bot.r));
    }

    public static List<NanoBot> inRange(NanoBot target, List<NanoBot> bots) {
        return bots.stream()
                .filter(bot -> bot.coordinate.manhattanDistance(target.coordinate) <= target.r)
                .collect(Collectors.toList());
    }
}
