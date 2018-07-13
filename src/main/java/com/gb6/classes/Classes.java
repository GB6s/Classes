package com.gb6.classes;

import com.gb6.classes.commands.ClassCommand;
import com.gb6.classes.objects.PlayerObject;
import com.gb6.classes.utils.Configuration;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
import java.util.UUID;

import static com.gb6.classes.utils.Constants.PLAYERS;
import static com.gb6.classes.utils.FileUtils.*;

public final class Classes extends JavaPlugin {
    @Getter private static Classes instance;
    @Getter private static Configuration configuration;

    @Override
    public void onEnable() {
        instance = this;
        configuration = new Configuration();
        new EventsListener();

        saveDefaultConfig();

        configuration.loadClasses();

        if (createFile("players.json")) {
            saveJSON(PLAYERS, "players");
        }
        PLAYERS.putAll(readJSON(new TypeToken<Map<UUID, PlayerObject>>() {{
        }}, "players"));

        //PlaceholderAPI.registerPlaceholderHook(this, new ClassPlaceHolderHook());
        new ClassPlaceHolderHook().register();

        getCommand("class").setExecutor(new ClassCommand());
    }

    @Override
    public void onDisable() {
        saveJSON(PLAYERS, "players");
        // Plugin shutdown logic
    }
}
