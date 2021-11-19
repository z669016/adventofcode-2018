package com.putoet.day25;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Galaxy {
    private final List<Constellation> constellations = new ArrayList<>();

    public Galaxy add(List<Point4D> points) {
        points.forEach(this::add);

        return this;
    }

    public Constellation add(Point4D point) {
        Optional<Constellation> primary = Optional.empty();
        Optional<Constellation> secondary = Optional.empty();

        for(int idx = 0; idx< constellations.size(); idx++) {
            final Constellation constellation = constellations.get(idx);
            if (constellation.contains(point)) {
                constellation.add(point);

                if (primary.isEmpty())
                    primary = Optional.of(constellation);
                else {
                    primary.get().merge(constellation);
                    constellations.remove(idx);
                    continue;
                }
            }
        }

        if (primary.isEmpty()) {
            primary = Optional.of(new Constellation().add(point));
            constellations.add(primary.get());
        }

        return primary.get();
    }

    public int size() {
        return constellations.size();
    }
}
