package com.putoet.day4;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.*;

public class GuardLog {
    private static final Pattern PATTERN = Pattern.compile("\\[(\\d+)-(\\d+)-(\\d+) (\\d+):(\\d+)].*");

    private final List<GuardEvent> events;

    private GuardLog(List<GuardEvent> events) {
        this.events = events;
    }

    public static GuardLog of (List<String> lines) {
        assert lines != null;

        final List<GuardEvent> events = lines.stream()
                .map(GuardLog::of)
                .sorted(Comparator.naturalOrder())
                .collect(toList());

        Guard prev = null;
        for (final GuardEvent event : events) {
            if (event.event() == WatchEVent.START_WATCH)
                prev = event.guard();
            else
                event.setGuard(prev);
        }

        return new GuardLog(events);
    }

    private static GuardEvent of(String line) {
        final LocalDateTime dateTime = dateTimeOf(line);

        return line.contains("#") ? startWatch(dateTime, line) : sleepAwake(dateTime, line);
    }

    private static GuardEvent sleepAwake(LocalDateTime dateTime, String line) {
        return new GuardEvent(dateTime, line.contains("falls") ? WatchEVent.FALL_ASLEEP : WatchEVent.WAKE_UP);
    }

    private static GuardEvent startWatch(LocalDateTime dateTime, String line) {
        final int hash = line.indexOf("#");
        final int id = id(line.substring(hash + 1));
        return new GuardEvent(dateTime, new Guard(id));
    }

    private static int id(String line) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; Character.isDigit(line.charAt(i)); i++)
            sb.append(line.charAt(i));

        return Integer.parseInt(sb.toString());
    }

    private static LocalDateTime dateTimeOf(String line) {
        final Matcher matcher = PATTERN.matcher(line);
        if (!matcher.matches())
            throw new IllegalArgumentException("Invalid dateTime for line '" + line + "'");

        final int year = Integer.parseInt(matcher.group(1));
        final int month = Integer.parseInt(matcher.group(2));
        final int day = Integer.parseInt(matcher.group(3));
        final int hour = Integer.parseInt(matcher.group(4));
        final int minute = Integer.parseInt(matcher.group(5));

        return LocalDateTime.of(year, month, day, hour, minute);
    }

    public List<GuardEvent> events() { return events; }

    public List<Guard> guards() {
        return events.stream()
                .map(GuardEvent::guard)
                .distinct()
                .collect(toList());
    }

    public Map<LocalDate,List<GuardEvent>> shifts(Guard guard) {
        assert guard != null;

        return events.stream()
                .filter(event -> event.guard().equals(guard))
                .filter(event -> event.event() != WatchEVent.START_WATCH)
                .collect(groupingBy(event -> event.dateTime().toLocalDate(), toList()));
    }

    public Guard longestSleeper() {
        final List<Guard> guards = guards();
        if (guards.size() == 0)
            throw new IllegalArgumentException("No guards in the log");

        final Map<Guard,Long> sleeping =
                guards.stream().collect(toMap(guard -> guard, this::sleepTime));

        long max = -1;
        Guard sleepiest = null;
        for (Guard guard : sleeping.keySet()) {
            if (sleeping.get(guard) > max) {
                sleepiest = guard;
                max = sleeping.get(guard);
            }
        }

        return sleepiest;
    }

    public Guard mostFrequentSleeper() {
        final List<Guard> guards = guards();
        if (guards.size() == 0)
            throw new IllegalArgumentException("No guards in the log");

        final Map<Guard,Integer> sleeping =
                guards.stream().collect(toMap(guard -> guard, this::sleepMinuteFrequency));

        long max = -1;
        Guard sleepiest = null;
        for (Guard guard : sleeping.keySet()) {
            if (sleeping.get(guard) > max) {
                sleepiest = guard;
                max = sleeping.get(guard);
            }
        }

        return sleepiest;
    }

    public long sleepTime(Guard guard) {
        assert guard != null;

        final int[] timeLine = timeLine(guard);
        return Arrays.stream(timeLine).filter(i -> i != 0).sum();
    }

    public int sleepMinute(Guard guard) {
        assert guard != null;

        final int[] timeLine = timeLine(guard);

        int max = -1;
        int maxMinute = -1;
        for (int m = 0; m < 60; m++) {
            if (timeLine[m] > max) {
                max = timeLine[m];
                maxMinute = m;
            }
        }

        return maxMinute;
    }

    public int sleepMinuteFrequency(Guard guard) {
        assert guard != null;

        final int[] timeLine = timeLine(guard);

        int max = -1;
        for (int m = 0; m < 60; m++) {
            if (timeLine[m] > max) {
                max = timeLine[m];
            }
        }

        return max;
    }

    private int[] timeLine(Guard guard) {
        final int[] timeLine = new int[60];

        final Map<LocalDate, List<GuardEvent>> shifts = shifts(guard);
        if (shifts.size() == 0)
            return timeLine;

        for (Map.Entry<LocalDate, List<GuardEvent>> entry : shifts.entrySet()) {
            final List<GuardEvent> events = entry.getValue();
            for (int i = 0; i < events.size(); i += 2) {
                final GuardEvent asleep = events.get(i);
                final GuardEvent awake = events.get(i + 1);

                for (int m = asleep.dateTime().getMinute(); m < awake.dateTime().getMinute(); m++) {
                    timeLine[m] = timeLine[m] + 1;
                }
            }
        }
        return timeLine;
    }
}
