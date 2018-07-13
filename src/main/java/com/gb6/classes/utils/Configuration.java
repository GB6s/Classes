package com.gb6.classes.utils;

import com.gb6.classes.objects.ClassObject;
import com.gb6.classes.objects.ClassType;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Arrays;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static com.gb6.classes.objects.ClassType.*;
import static com.gb6.classes.utils.Constants.CLASSES;
import static com.gb6.classes.utils.Constants.INSTANCE;

public class Configuration {
    private static FileConfiguration config;
    private List<ClassType> types = Arrays.asList(FARMER, MINER, LUMBERJACK);

    public Configuration() {
        config = INSTANCE.getConfig();
    }

    public void loadClasses() {
        CLASSES.clear();
        ConfigurationSection section = config.getConfigurationSection("classes");

        for (String key : section.getKeys(false)) {
            if (Arrays.stream(ClassType.values()).map(ClassType::toString).noneMatch(s -> s.equalsIgnoreCase(key))) {
                return;
            }
            ConfigurationSection clazz = section.getConfigurationSection(key);
            NavigableMap<Integer, Integer> map = new TreeMap<>();

            clazz.getConfigurationSection("amounts").getKeys(false).forEach(s ->
                    map.put(clazz.getInt("amounts" + "." + s), Integer.parseInt(s))
            );

            map.put(0, 0);

            ClassType type = ClassType.valueOf(key.toUpperCase());

            if (types.contains(type)) {
                type.setMaterials(clazz.getStringList("materials").stream().map(Material::valueOf).collect(Collectors.toList()));
            }

            CLASSES.put(type, new ClassObject(map, clazz.getStringList("commands")));
        }
    }
}
