package com.lothrazar.oceanfloor;

import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
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
    PlacedFeature P_CLAY = PlacementUtils.register("p_clay", WorldGenRegistry.CLAY_ON_GRAVEL.placed(RarityFilter.onAverageOnceEvery(ConfigOcean.CLAYSPREAD.get()), InSquarePlacement.spread(), BiomeFilter.biome()));
    PlacedFeature P_CLAY_ON_SAND = PlacementUtils.register("pcs", WorldGenRegistry.CLAY_ON_SAND.placed(RarityFilter.onAverageOnceEvery(ConfigOcean.CLAYSPREAD.get()), InSquarePlacement.spread(), BiomeFilter.biome()));
    PlacedFeature P_SAND = PlacementUtils.register("sand_placed", WorldGenRegistry.SAND_ON_GRAVEL.placed(RarityFilter.onAverageOnceEvery(ConfigOcean.SANDSPREAD.get()), InSquarePlacement.spread(), BiomeFilter.biome()));
    PlacedFeature P_DIRT = PlacementUtils.register("p_dirt", WorldGenRegistry.DIRT_ON_GRAVEL.placed(RarityFilter.onAverageOnceEvery(ConfigOcean.DIRTSPREAD.get()), InSquarePlacement.spread(), BiomeFilter.biome()));
    PlacedFeature P_CDIRT = PlacementUtils.register("pc_dirt", WorldGenRegistry.CDIRT_ON_STONE.placed(RarityFilter.onAverageOnceEvery(ConfigOcean.GRAVELSIZE.get()), InSquarePlacement.spread(), BiomeFilter.biome()));
    PlacedFeature P_CGRAVEL = PlacementUtils.register("pc_gravel", WorldGenRegistry.GRAVEL_ON_STONE.placed(RarityFilter.onAverageOnceEvery(ConfigOcean.GRAVELSPREAD.get()), InSquarePlacement.spread(), BiomeFilter.biome()));
    Decoration d = Decoration.UNDERGROUND_DECORATION; // underground ores?
    if (event.getCategory() == Biome.BiomeCategory.OCEAN) {
      if (ConfigOcean.CLAYSIZE.get() > 0) {
        r.addFeature(d, P_CLAY);
        r.addFeature(d, P_CLAY_ON_SAND);
      }
      if (ConfigOcean.SANDSIZE.get() > 0) {
        r.addFeature(d, P_SAND);
      }
      if (ConfigOcean.DIRTSIZE.get() > 0) {
        r.addFeature(d, P_DIRT);
      }
      if (ConfigOcean.GRAVELSIZE.get() > 0) {
        r.addFeature(d, P_CDIRT);
        r.addFeature(d, P_CGRAVEL);
      }
    }
  }
}
