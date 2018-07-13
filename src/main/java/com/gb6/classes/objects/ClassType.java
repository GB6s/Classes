package com.gb6.classes.objects;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;

import java.util.List;

/**
 * Created by Gijs on 23-3-2018.
 */

public enum ClassType {
    DEFAULT, FARMER, BUILDER, MERCHANT, FISHERMAN, MINER, LUMBERJACK, HUNTER, STOCKMAN, BUTCHER, BREWER, ASSASSIN;

    @Getter @Setter private List<Material> materials;
}

