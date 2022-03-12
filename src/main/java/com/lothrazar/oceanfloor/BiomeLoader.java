package com.lothrazar.oceanfloor;

import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
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
    if (event.getCategory() == Biome.BiomeCategory.OCEAN) {
      if (ConfigOcean.CLAYSIZE.get() > 0) {
        r.addFeature(d, WorldGenRegistry.CLAY_ON_GRAVEL_PLACED_FEATURE);
        r.addFeature(d, WorldGenRegistry.CLAY_ON_SAND_PLACED_FEATURE);
      }
      if (ConfigOcean.SANDSIZE.get() > 0) {
        r.addFeature(d, WorldGenRegistry.SAND_ON_GRAVEL_PLACED_FEATURE);
      }
      if (ConfigOcean.DIRTSIZE.get() > 0) {
        r.addFeature(d, WorldGenRegistry.DIRT_ON_GRAVEL_PLACED_FEATURE);
      }
      if (ConfigOcean.GRAVELSIZE.get() > 0) {
        r.addFeature(d, WorldGenRegistry.DIRT_ON_STONE_PLACED_FEATURE);
        r.addFeature(d, WorldGenRegistry.GRAVEL_ON_STONE_PLACED_FEATURE);
      }
    }
  }
}
