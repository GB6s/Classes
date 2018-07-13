package com.gb6.classes.objects;

import lombok.Getter;

import java.util.List;
import java.util.NavigableMap;

public class ClassObject {
    @Getter private NavigableMap<Integer, Integer> levels;
    @Getter private List<String> commands;

    public ClassObject(NavigableMap<Integer, Integer> levels, List<String> commands) {
        this.levels = levels;
        this.commands = commands;
    }


}
