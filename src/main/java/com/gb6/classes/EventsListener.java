package com.gb6.classes;

import com.gb6.classes.objects.PlayerObject;
import org.bukkit.Material;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityTameEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import static com.gb6.classes.objects.ClassType.*;
import static com.gb6.classes.utils.Constants.PLAYERS;
import static com.gb6.classes.utils.Events.listen;
import static org.bukkit.Material.LOG;
import static org.bukkit.Material.LOG_2;

class EventsListener {

    EventsListener() {
        listen(PlayerJoinEvent.class, event ->
                PLAYERS.putIfAbsent(event.getPlayer().getUniqueId(), new PlayerObject(DEFAULT))
        );

        listen(InventoryPickupItemEvent.class, event -> {
            if (!(event.getInventory().getHolder() instanceof Player)) {
                return;
            }

            PlayerObject object = PLAYERS.get(((Player) event.getInventory().getHolder()).getUniqueId());
            Material material = event.getItem().getItemStack().getType();

            switch (object.getType()) {
                case FARMER:
                    //TODO: FARMER
                    return;
                case MINER:
                    //TODO: Miner
                    return;
                case LUMBERJACK:
                    if (material != LOG && material != LOG_2) {
                        return;
                    }
                    object.increment();
            }
        });

        listen(BlockPlaceEvent.class, event -> {
            PlayerObject object = PLAYERS.get(event.getPlayer().getUniqueId());

            if (object.getType() != BUILDER) {
                return;
            }

            object.increment();
        });

        listen(EntityTameEvent.class, event -> {
            if (!(event.getOwner() instanceof Player)) {
                return;
            }

            PlayerObject object = PLAYERS.get(event.getOwner().getUniqueId());

            if (object.getType() != STOCKMAN) {
                return;
            }

            object.increment();
        });

        listen(EntityDeathEvent.class, event -> {
            if (event.getEntity().getKiller() == null || !(event.getEntity() instanceof Animals)) {
                return;
            }

            PlayerObject object = PLAYERS.get(event.getEntity().getKiller().getUniqueId());

            if (object.getType() != BUTCHER) {
                return;
            }

            object.increment();
        });


    }
}
