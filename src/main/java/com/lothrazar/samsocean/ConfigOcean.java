package com.lothrazar.samsocean;

import net.minecraftforge.common.config.Configuration;

public class ConfigOcean {

  private Configuration instance;
  private String category = ModOcean.MODID;
  int clayChance;
  int clayNumBlocks;
  int dirtChance;
  int dirtNumBlocks;
  int sandChance;
  int sandNumBlocks;

  public Configuration instance() {
    return instance;
  }

  public ConfigOcean(Configuration c) {
    instance = c;
    instance.load();
    clayNumBlocks = instance.getInt("clay_size", category, 32, 0, 64, "Number of blocks");
    clayChance = instance.getInt("clay_chance", category, 65, 0, 100, "Chance of spawning");
    sandNumBlocks = instance.getInt("sand_size", category, 22, 0, 64, "Number of blocks");
    sandChance = instance.getInt("sand_chance", category, 45, 0, 100, "Chance of spawning");
    dirtNumBlocks = instance.getInt("dirt_size", category, 18, 0, 64, "Number of blocks");
    dirtChance = instance.getInt("dirt_chance", category, 30, 0, 100, "Chance of spawning");
    if (instance.hasChanged()) {
      instance.save();
    }
  }
}
