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
    CFG.comment(WALL, " Dirt settings", WALL)
        .push("dirt");
    DIRTSIZE = CFG.comment("Patch size").defineInRange("size", 16, 0, 64);
    DIRTSPREAD = CFG.comment("Spread area").defineInRange("spread", 15, 1, 100);
    CFG.pop(); // dirt
    CFG.comment(WALL, " Clay settings", WALL)
        .push("clay");
    CLAYSIZE = CFG.comment("Patch size").defineInRange("size", 32, 0, 64);
    CLAYSPREAD = CFG.comment("Spread area").defineInRange("spread", 75, 1, 100);
    CFG.pop(); // clay
    CFG.comment(WALL, " Sand settings", WALL)
        .push("sand");
    SANDSIZE = CFG.comment("Patch size").defineInRange("size", 22, 0, 64);
    SANDSPREAD = CFG.comment("Spread area").defineInRange("spread", 45, 1, 100);
    CFG.pop(); // sand
    CFG.pop(); //ROOT
    COMMON_CONFIG = CFG.build();
  }
  //    clayNumBlocks = instance.getInt("clay_size", category, 32, 0, 64, "Number of blocks");
  //    clayChance = instance.getInt("clay_chance", category, 65, 0, 100, "Chance of spawning");
  //    sandNumBlocks = instance.getInt("sand_size", category, 22, 0, 64, "Number of blocks");
  //    sandChance = instance.getInt("sand_chance", category, 45, 0, 100, "Chance of spawning");
  //    dirtNumBlocks = instance.getInt("dirt_size", category, 18, 0, 64, "Number of blocks");
  //    dirtChance = instance.getInt("dirt_chance", category, 30, 0, 100, "Chance of spawning");
}
