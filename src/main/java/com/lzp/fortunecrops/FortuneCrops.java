package com.lzp.fortunecrops;

import com.mojang.logging.LogUtils;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

// Data-only mod: the actual change lives in the loot table overrides under
// src/main/resources/data/minecraft/loot_table/blocks/ (wheat, beetroots).
// The @Mod entry point exists only so the javafml loader accepts the jar;
// no registration or event handling is needed.
@Mod(FortuneCrops.MODID)
public class FortuneCrops {
    public static final String MODID = "fortunecrops";
    public static final Logger LOGGER = LogUtils.getLogger();

    public FortuneCrops() {
        LOGGER.info("Fortune Crops: wheat & beetroot produce are now affected by Fortune");
    }
}
