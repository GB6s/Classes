package com.gb6.classes.commands;

import com.gb6.classes.objects.ClassType;
import com.gb6.classes.objects.PlayerObject;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

import static com.gb6.classes.utils.Constants.PLAYERS;
import static org.apache.commons.lang.StringUtils.capitalize;

public class ClassCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can only be used by players.");
            return false;
        }

        if (!sender.hasPermission("classes.command")) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use this command");
            return true;
        }

        Player player = (Player) sender;
        PlayerObject object = PLAYERS.get(player.getUniqueId());

        if (args.length == 0) {
            sender.sendMessage(ChatColor.GRAY + "Current class: " + capitalize(object.getType().toString()));
            sender.sendMessage(ChatColor.GRAY + "Current amount: " + capitalize("" + object.getAmount()));
            return true;
        } else if (args.length == 1) {
            if (Arrays.stream(ClassType.values()).noneMatch(s -> s.toString().equalsIgnoreCase(args[0]))) {
                sender.sendMessage(ChatColor.RED + "Unknown class: " + args[0]);
                return true;
            }

            ClassType type = ClassType.valueOf(args[0].toUpperCase());

            if (type == object.getType()) {
                sender.sendMessage(ChatColor.GRAY + "Class already picked: " + capitalize(type.toString()));
                return true;
            }

            object.setType(type);
            object.setAmount(0);

            sender.sendMessage(ChatColor.GRAY + "Class set: " + capitalize(type.toString()));
            return true;
        } else {
            sender.sendMessage(ChatColor.RED + "Invalid amount of arguments.");
            return true;
        }
    }
}
