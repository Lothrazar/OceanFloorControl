package com.lothrazar.oceanfloor;

import com.lothrazar.library.config.ConfigTemplate;
import net.minecraftforge.common.ForgeConfigSpec;

public class ConfigOcean extends ConfigTemplate {

  private static final ForgeConfigSpec.Builder CFG = new ForgeConfigSpec.Builder();
  private static ForgeConfigSpec CONFIG;
  public static final String WALL = "####################################################################################";
  static {
    CFG.comment(WALL, "Configured Features moved to the data pack; see the /oceanfloor/worldgen/ folder", WALL).push(ModOcean.MODID);
    CFG.pop(); //ROOT
    CONFIG = CFG.build();
  }

  public ConfigOcean() {
    CONFIG.setConfig(setup(ModOcean.MODID));
  }
}
