package com.lothrazar.oceanfloor;

import java.util.List;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

public class WorldGenRegistry {

  private static final RuleTest REPLACEGRAVEL = new BlockMatchTest(Blocks.GRAVEL);
  private static final RuleTest REPLACESAND = new BlockMatchTest(Blocks.SAND);
  private static final RuleTest REPLACESTONE = new BlockMatchTest(Blocks.STONE);

  private static List<PlacementModifier> generatePlacementModifiers(int spread) {
    return commonOrePlacement(spread, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top()));
  }

  private static List<PlacementModifier> orePlacement(PlacementModifier countModifier, PlacementModifier heightPlacement) {
    return List.of(countModifier, InSquarePlacement.spread(), heightPlacement, BiomeFilter.biome());
  }

  private static List<PlacementModifier> commonOrePlacement(int spread, PlacementModifier modifier) {
    return orePlacement(CountPlacement.of(spread), modifier);
  }

  public static final Holder<ConfiguredFeature<OreConfiguration, ?>> CLAY_ON_GRAVEL = FeatureUtils.register(new ResourceLocation(ModOcean.MODID, "clay_on_sand").toString(),
      Feature.ORE, new OreConfiguration(REPLACEGRAVEL, Blocks.CLAY.defaultBlockState(), ConfigOcean.CLAYSIZE.get()));
  public static final Holder<ConfiguredFeature<OreConfiguration, ?>> CLAY_ON_SAND = FeatureUtils.register(new ResourceLocation(ModOcean.MODID, "clay_on_gravel").toString(),
      Feature.ORE, new OreConfiguration(REPLACESAND, Blocks.CLAY.defaultBlockState(), ConfigOcean.CLAYSIZE.get()));
  public static final Holder<ConfiguredFeature<OreConfiguration, ?>> SAND_ON_GRAVEL = FeatureUtils.register(new ResourceLocation(ModOcean.MODID, "sand_on_gravel").toString(),
      Feature.ORE, new OreConfiguration(REPLACEGRAVEL, Blocks.SAND.defaultBlockState(), ConfigOcean.SANDSIZE.get()));
  public static final Holder<ConfiguredFeature<OreConfiguration, ?>> DIRT_ON_GRAVEL = FeatureUtils.register(new ResourceLocation(ModOcean.MODID, "dirt_on_gravel").toString(),
      Feature.ORE, new OreConfiguration(REPLACEGRAVEL, Blocks.DIRT.defaultBlockState(), ConfigOcean.DIRTSIZE.get()));
  public static final Holder<ConfiguredFeature<OreConfiguration, ?>> DIRT_ON_STONE = FeatureUtils.register(new ResourceLocation(ModOcean.MODID, "coarse_dirt_on_stone").toString(),
      Feature.ORE, new OreConfiguration(REPLACESTONE, Blocks.COARSE_DIRT.defaultBlockState(), ConfigOcean.GRAVELSIZE.get()));
  public static final Holder<ConfiguredFeature<OreConfiguration, ?>> GRAVEL_ON_STONE = FeatureUtils.register(new ResourceLocation(ModOcean.MODID, "gravel_on_stone").toString(),
      Feature.ORE, new OreConfiguration(REPLACESTONE, Blocks.GRAVEL.defaultBlockState(), ConfigOcean.GRAVELSIZE.get()));

  public static Holder<PlacedFeature> CLAY_ON_GRAVEL_PLACED_FEATURE = PlacementUtils.register(new ResourceLocation(ModOcean.MODID, "clay_on_sand").toString(),
      CLAY_ON_GRAVEL, generatePlacementModifiers(ConfigOcean.CLAYSPREAD.get()));
  public static Holder<PlacedFeature> CLAY_ON_SAND_PLACED_FEATURE = PlacementUtils.register(new ResourceLocation(ModOcean.MODID, "clay_on_gravel").toString(),
      CLAY_ON_SAND, generatePlacementModifiers(ConfigOcean.CLAYSPREAD.get()));
  public static Holder<PlacedFeature> SAND_ON_GRAVEL_PLACED_FEATURE = PlacementUtils.register(new ResourceLocation(ModOcean.MODID, "sand_on_gravel").toString(),
      SAND_ON_GRAVEL, generatePlacementModifiers(ConfigOcean.DIRTSPREAD.get()));
  public static Holder<PlacedFeature> DIRT_ON_GRAVEL_PLACED_FEATURE = PlacementUtils.register(new ResourceLocation(ModOcean.MODID, "dirt_on_gravel").toString(),
      DIRT_ON_GRAVEL, generatePlacementModifiers(ConfigOcean.DIRTSPREAD.get()));
  public static Holder<PlacedFeature> DIRT_ON_STONE_PLACED_FEATURE = PlacementUtils.register(new ResourceLocation(ModOcean.MODID, "coarse_dirt_on_stone").toString(),
      DIRT_ON_STONE, generatePlacementModifiers(ConfigOcean.GRAVELSPREAD.get()));
  public static Holder<PlacedFeature> GRAVEL_ON_STONE_PLACED_FEATURE = PlacementUtils.register(new ResourceLocation(ModOcean.MODID, "gravel_on_stone").toString(),
      GRAVEL_ON_STONE, generatePlacementModifiers(ConfigOcean.GRAVELSPREAD.get()));

  public static void init() {
    //Just here to load the class and let the features be initialized and registered
  }
}
