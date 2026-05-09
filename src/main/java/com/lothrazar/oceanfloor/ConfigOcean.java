package com.lothrazar.oceanfloor;


import net.neoforged.neoforge.common.ModConfigSpec;

public class ConfigOcean  {

  private static final ModConfigSpec.Builder CFG = new ModConfigSpec.Builder();
  static ModConfigSpec CONFIG;
  public static final String WALL = "####################################################################################";
  static {
    CFG.comment(WALL, "Configured Features are in the data pack; see the /data/oceanfloor/ folder", WALL).push(ModOcean.MODID);
    CFG.pop(); //ROOT
    CONFIG = CFG.build();
  }

}
