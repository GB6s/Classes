package com.gb6.classes.objects;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static com.gb6.classes.utils.Constants.CLASSES;
import static com.gb6.classes.utils.Constants.PLAYERS;

public class PlayerObject {
    @Getter @Setter private ClassType type;
    @Getter @Setter private Integer amount = 0;

    public PlayerObject(ClassType type) {
        this.type = type;
    }

    public void increment() {
        if (CLASSES.get(type).getLevels().floorKey(amount) != CLASSES.get(type).getLevels().floorKey(++amount)) {
            Player player = Bukkit.getPlayer(PLAYERS.inverse().get(this));
            CLASSES.get(type).getCommands().forEach(s -> Bukkit.getServer()
                    .dispatchCommand(Bukkit.getConsoleSender(), s.replace("%player%", player.getName()).replace("%level%", "" + CLASSES.get(type).getLevels().floorEntry(amount).getValue())));
        }
    }
}
