package com.gb6.classes;

import com.gb6.classes.objects.PlayerObject;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

import static com.gb6.classes.utils.Constants.CLASSES;
import static com.gb6.classes.utils.Constants.PLAYERS;
import static org.apache.commons.lang.StringUtils.capitalize;

public class ClassPlaceHolderHook extends PlaceholderExpansion {


    /*
    The identifier, shouldn't contain any _ or %
    */
    public String getIdentifier() {
        return "classes";
    }

    public String getPlugin() {
        return "Classes";
    }


    /*
     The author of the Placeholder
     This cannot be null
     */
    public String getAuthor() {
        return "GB6";
    }

    /*
     Same with #getAuthor() but for version
     This cannot be null
     */

    public String getVersion() {
        return "1.0";
    }

    /*
    Use this method to setup placeholders
    This is somewhat similar to EZPlaceholderhook
     */
    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        if (player == null) {
            return "";
        }

        PlayerObject object = PLAYERS.get(player.getUniqueId());

        if (identifier.equalsIgnoreCase("type")) {
            return capitalize(object.getType().toString().toLowerCase());
        } else if (identifier.equalsIgnoreCase("amount")) {
            int lvl = CLASSES.get(object.getType()).getLevels().floorKey(object.getAmount());
            return "" + lvl;
        }
        return "";
    }
}
