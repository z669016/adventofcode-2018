package com.putoet.day4;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.*;

record GuardLog(@NotNull List<GuardEvent> events) {
    private static final Pattern PATTERN = Pattern.compile("\\[(\\d+)-(\\d+)-(\\d+) (\\d+):(\\d+)].*");

    public static GuardLog of(@NotNull List<String> lines) {
        final List<GuardEvent> events = lines.stream()
                .map(GuardLog::of)
                .sorted(Comparator.naturalOrder())
                .toList();

        Guard prev = null;
        for (final var event : events) {
            if (event.event() == WatchEvent.START_WATCH)
                prev = event.guard();
            else
                event.setGuard(prev);
        }

        return new GuardLog(events);
    }

    private static GuardEvent of(String line) {
        final var dateTime = dateTimeOf(line);
        return line.contains("#") ? startWatch(dateTime, line) : sleepAwake(dateTime, line);
    }

    private static GuardEvent sleepAwake(LocalDateTime dateTime, String line) {
        return new GuardEvent(dateTime, line.contains("falls") ? WatchEvent.FALL_ASLEEP : WatchEvent.WAKE_UP);
    }

    private static GuardEvent startWatch(LocalDateTime dateTime, String line) {
        final var hash = line.indexOf("#");
        final var id = id(line.substring(hash + 1));
        return new GuardEvent(dateTime, new Guard(id));
    }

    private static int id(String line) {
        final var sb = new StringBuilder();
        for (var i = 0; Character.isDigit(line.charAt(i)); i++)
            sb.append(line.charAt(i));

        return Integer.parseInt(sb.toString());
    }

    private static LocalDateTime dateTimeOf(String line) {
        final var matcher = PATTERN.matcher(line);
        if (!matcher.matches())
            throw new IllegalArgumentException("Invalid dateTime for line '" + line + "'");

        final var year = Integer.parseInt(matcher.group(1));
        final var month = Integer.parseInt(matcher.group(2));
        final var day = Integer.parseInt(matcher.group(3));
        final var hour = Integer.parseInt(matcher.group(4));
        final var minute = Integer.parseInt(matcher.group(5));

        return LocalDateTime.of(year, month, day, hour, minute);
    }

    public List<GuardEvent> events() {
        return events;
    }

    public List<Guard> guards() {
        return events.stream()
                .map(GuardEvent::guard)
                .distinct()
                .collect(toList());
    }

    public Map<LocalDate, List<GuardEvent>> shifts(@NotNull Guard guard) {
        return events.stream()
                .filter(event -> event.guard().equals(guard))
                .filter(event -> event.event() != WatchEvent.START_WATCH)
                .collect(groupingBy(event -> event.dateTime().toLocalDate(), toList()));
    }

    public Optional<Guard> longestSleeper() {
        final var sleeping = guards().stream().collect(toMap(guard -> guard, this::sleepTime));
        return maxSleepingGuard(sleeping);
    }

    private Optional<Guard> maxSleepingGuard(Map<Guard, Integer> sleeping) {
        var max = Integer.MIN_VALUE;
        Guard sleepiest = null;
        for (var guard : sleeping.keySet()) {
            if (sleeping.get(guard) > max) {
                sleepiest = guard;
                max = sleeping.get(guard);
            }
        }
        return Optional.ofNullable(sleepiest);
    }

    public Optional<Guard> mostFrequentSleeper() {
        final var guards = guards();
        if (guards.isEmpty())
            throw new IllegalArgumentException("No guards in the log");

        final var sleeping = guards.stream().collect(toMap(guard -> guard, this::sleepMinuteFrequency));
        return maxSleepingGuard(sleeping);
    }

    public int sleepTime(@NotNull Guard guard) {
        final var timeLine = timeLine(guard);
        return Arrays.stream(timeLine).filter(i -> i != 0).sum();
    }

    public int sleepMinute(@NotNull Guard guard) {
        final var timeLine = timeLine(guard);

        var max = Integer.MIN_VALUE;
        var maxMinute = Integer.MIN_VALUE;
        for (var m = 0; m < 60; m++) {
            if (timeLine[m] > max) {
                max = timeLine[m];
                maxMinute = m;
            }
        }

        return maxMinute;
    }

    public int sleepMinuteFrequency(@NotNull Guard guard) {
        final var timeLine = timeLine(guard);

        var max = Integer.MIN_VALUE;
        for (var m = 0; m < 60; m++) {
            if (timeLine[m] > max) {
                max = timeLine[m];
            }
        }

        return max;
    }

    private int[] timeLine(Guard guard) {
        final var timeLine = new int[60];

        final var shifts = shifts(guard);
        if (shifts.isEmpty())
            return timeLine;

        for (var entry : shifts.entrySet()) {
            final var events = entry.getValue();
            for (var i = 0; i < events.size(); i += 2) {
                final var asleep = events.get(i);
                final var awake = events.get(i + 1);

                for (var m = asleep.dateTime().getMinute(); m < awake.dateTime().getMinute(); m++) {
                    timeLine[m] = timeLine[m] + 1;
                }
            }
        }
        return timeLine;
    }
}
