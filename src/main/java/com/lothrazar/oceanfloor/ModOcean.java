package com.lothrazar.oceanfloor;

import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ModOcean.MODID)
public class ModOcean {

  public static final String MODID = "oceanfloor";

  /**
   * https://minecraft.fandom.com/wiki/Custom_world_generation/rule_test
   * 
   * datapack /worldgen/ folder
   */
  public ModOcean() {
    ConfigOcean.setup();
    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    GenerationStep.Decoration.UNDERGROUND_ORES.getName();
  }

  private void setup(final FMLCommonSetupEvent event) {
    event.enqueueWork(() -> {
      //      WorldGenRegistry.init();
    });
  }
}
