package com.gb6.classes.utils;

import com.gb6.classes.Classes;
import com.gb6.classes.objects.ClassObject;
import com.gb6.classes.objects.ClassType;
import com.gb6.classes.objects.PlayerObject;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Gijs on 18-3-2018.
 */
public interface Constants {
    BiMap<UUID, PlayerObject> PLAYERS = HashBiMap.create();

    Map<ClassType, ClassObject> CLASSES = new HashMap<>();

    Classes INSTANCE = Classes.getInstance();

    Gson GSON = new GsonBuilder().setPrettyPrinting().create();
}