package com.lothrazar.oceanfloor;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import java.nio.file.Path;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.fml.loading.FMLPaths;

public class ConfigOcean {

  private static final ForgeConfigSpec.Builder CFG = new ForgeConfigSpec.Builder();
  private static ForgeConfigSpec COMMON_CONFIG;
  public static IntValue DIRTSIZE;
  public static IntValue DIRTSPREAD;
  public static IntValue CLAYSPREAD;
  public static IntValue CLAYSIZE;
  public static IntValue SANDSIZE;
  public static IntValue SANDSPREAD;
  public static IntValue GRAVELSIZE;
  public static IntValue GRAVELSPREAD;
  public static final String WALL = "####################################################################################";
  static {
    initConfig();
  }

  public static void setup() {
    Path path = FMLPaths.CONFIGDIR.get().resolve(ModOcean.MODID + ".toml");
    final CommentedFileConfig configData = CommentedFileConfig.builder(path)
        .sync()
        .autosave()
        .writingMode(WritingMode.REPLACE)
        .build();
    configData.load();
    COMMON_CONFIG.setConfig(configData);
  }

  private static void initConfig() {
    CFG.comment(WALL, "Features", WALL).push(ModOcean.MODID);
    CFG.comment(WALL, " Dirt spawns in spand an gravel floors of ocean-type biomes", WALL)
        .push("dirt");
    DIRTSIZE = CFG.comment("Patch size").defineInRange("size", 16, 0, 64);
    DIRTSPREAD = CFG.comment("Spread").defineInRange("spread", 15, 1, 100);
    CFG.pop(); // dirt
    CFG.comment(WALL, " Clay spawns in sand and gravel floors of ocean-type biomes", WALL)
        .push("clay");
    CLAYSIZE = CFG.comment("Patch size").defineInRange("size", 32, 0, 64);
    CLAYSPREAD = CFG.comment("Spread").defineInRange("spread", 75, 1, 100);
    CFG.pop(); // clay
    CFG.comment(WALL, " Sand spawns in gravel floors of ocean-type biomes", WALL)
        .push("sand");
    SANDSIZE = CFG.comment("Patch size").defineInRange("size", 22, 0, 64);
    SANDSPREAD = CFG.comment("Spread").defineInRange("spread", 45, 1, 100);
    CFG.pop(); // sand
    CFG.comment(WALL, " Gravel + Coarse Dirt spawn in the stone caves below ocean-type biomes", WALL)
        .push("gravel");
    GRAVELSIZE = CFG.comment("Patch size").defineInRange("size", 16, 0, 64);
    GRAVELSPREAD = CFG.comment("Spread").defineInRange("spread", 16, 1, 100);
    CFG.pop(); // gravel
    CFG.pop(); //ROOT
    COMMON_CONFIG = CFG.build();
  }
}
