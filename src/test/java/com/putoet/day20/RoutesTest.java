package com.putoet.day20;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class RoutesTest {

    @Test
    void ofSimple() {
        final List<Route> routes = Routes.of("^WNE$");
        routes.forEach(System.out::println);

        assertEquals(1, routes.size());
        assertEquals("WNE", routes.get(0).toString());
    }

    @Test
    void ofComplicated() {
        final List<Route> routes = Routes.of("^ENWWW(NEEE|SSE(EE|N))$");

        routes.forEach(System.out::println);
    }

    @Test
    void ofSample() {
        final List<Route> routes = Routes.of("^ENNWSWW(NEWS|)SSSEEN(WNSE|)EE(SWEN|)NNN$");
        routes.forEach(System.out::println);

        final List<String> textRoutes = routes.stream().map(Route::toString).collect(Collectors.toList());
        assertTrue(textRoutes.contains("ENNWSWWSSSEENEENNN"));
        assertTrue(textRoutes.contains("ENNWSWWNEWSSSSEENEENNN"));
        assertTrue(textRoutes.contains("ENNWSWWNEWSSSSEENEESWENNNN"));
        assertTrue(textRoutes.contains("ENNWSWWSSSEENWNSEEENNN"));
    }
 }