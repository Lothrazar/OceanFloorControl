package com.lothrazar.oceanfloor;


import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

@Mod(ModOcean.MODID)
public class ModOcean {

  public static final String MODID = "oceanfloor";

  /**
   * https://minecraft.fandom.com/wiki/Custom_world_generation/rule_test
   * 
   * datapack /worldgen/ folder
   */
  public ModOcean(IEventBus bus, ModContainer modContainer) {

    modContainer.registerConfig(ModConfig.Type.COMMON, ConfigOcean.CONFIG);
  }
}
