package com.lothrazar.oceanfloor;

import net.minecraftforge.fml.common.Mod;

@Mod(ModOcean.MODID)
public class ModOcean {

  public static final String MODID = "oceanfloor";

  /**
   * https://minecraft.fandom.com/wiki/Custom_world_generation/rule_test
   * 
   * datapack /worldgen/ folder
   */
  public ModOcean() {
    new ConfigOcean();
  }
}
