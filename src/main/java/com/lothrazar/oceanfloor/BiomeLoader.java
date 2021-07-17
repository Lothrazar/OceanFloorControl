package com.lothrazar.oceanfloor;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = ModOcean.MODID, bus = EventBusSubscriber.Bus.FORGE)
public class BiomeLoader {

  @SubscribeEvent(priority = EventPriority.HIGH)
  public static void onBiomeLoading(BiomeLoadingEvent event) {
    BiomeGenerationSettingsBuilder r = event.getGeneration();
    Decoration d = Decoration.UNDERGROUND_DECORATION; // underground ores?
    if (event.getCategory() == Biome.Category.OCEAN) {
      //go
      if (ConfigOcean.SANDSIZE.get() > 0) {
        r.withFeature(d, WorldGenRegistry.SAND);
      }
      if (ConfigOcean.CLAYSIZE.get() > 0) {
        r.withFeature(d, WorldGenRegistry.CLAY);
      }
      if (ConfigOcean.DIRTSIZE.get() > 0) {
        r.withFeature(d, WorldGenRegistry.DIRT);
      }
      if (ConfigOcean.CLAYSIZE.get() > 0) {
        r.withFeature(d, WorldGenRegistry.CLAY_ON_SAND);
      }
    }
  }
}
