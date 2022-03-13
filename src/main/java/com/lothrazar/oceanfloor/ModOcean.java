package com.lothrazar.oceanfloor;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ModOcean.MODID)
public class ModOcean {

  public static final String MODID = "oceanfloor";

  public ModOcean() {
    ConfigOcean.setup();
    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
  }

  private void setup(final FMLCommonSetupEvent event) {
    event.enqueueWork(() -> {
      WorldGenRegistry.init();
    });
  }
}
