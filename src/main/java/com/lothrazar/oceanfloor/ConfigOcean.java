package com.lothrazar.oceanfloor;

import java.nio.file.Path;
import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.lothrazar.library.config.ConfigTemplate;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.fml.loading.FMLPaths;

public class ConfigOcean extends ConfigTemplate {

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
    CFG.comment(WALL, "Features moved to the data pack", WALL).push(ModOcean.MODID);
    CFG.pop(); //ROOT
    COMMON_CONFIG = CFG.build();
  }
}
