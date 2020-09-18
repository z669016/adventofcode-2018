package com.putoet.day7;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConcurrentStepWalkerTest {

    @Test
    void walkOne() {
        final Steps steps = Steps.of(ResourceLines.list("/day7.txt"));
        final ConcurrentStepWalker walker = new ConcurrentStepWalker(steps);

        assertEquals("CABDFE", walker.walk());
        assertEquals(6, walker.duration());
    }

    @Test
    void walkMultiple() {
        final Steps steps = Steps.of(ResourceLines.list("/day7.txt"));
        final ConcurrentStepWalker walker = new ConcurrentStepWalker(steps, 2);

        assertEquals("CABFDE", walker.walkMultiple());
        assertEquals(15, walker.duration());
    }
}