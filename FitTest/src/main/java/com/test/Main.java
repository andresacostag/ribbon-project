package com.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class Main {

    public static void main(String[] args) {

        List<Team> teams = new ArrayList<>();
        teams.add(new Team(1, "Team 1", "Conference A"));
        teams.add(new Team(2, "Team 2", "Conference A"));
        teams.add(new Team(3, "Team 3", "Conference B"));
        teams.add(new Team(4, "Team 4", "Conference B"));
        teams.add(new Team(5, "Team 5", "Conference C"));
        teams.add(new Team(6, "Team 6", "Conference C"));

        groupConferences(teams);
    }

    public static Map<String, List<Team>> groupConferences(List<Team> teams){

        Map<String, List<Team>> result = teams.stream().collect(groupingBy(Team::getConference));
        result.forEach((k, v) -> System.out.println(k + ":" + v));

        return result;
    }
}
