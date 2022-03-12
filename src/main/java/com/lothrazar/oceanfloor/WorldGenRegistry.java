package com.lothrazar.oceanfloor;

import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
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
import java.util.List;

public class WorldGenRegistry {

  private static final RuleTest REPLACEGRAVEL = new BlockMatchTest(Blocks.GRAVEL);
  private static final RuleTest REPLACESAND = new BlockMatchTest(Blocks.SAND);
  private static final RuleTest REPLACESTONE = new BlockMatchTest(Blocks.STONE);

  private static ConfiguredFeature<OreConfiguration, ?> buildOreFeature(RuleTest rule, Block block, int size) {
    return Feature.ORE.configured(new OreConfiguration(rule, block.defaultBlockState(), size));
  }

  private static List<PlacementModifier> generatePlacementModifiers(int spread, int minHeight, int maxHeight) {
    return commonOrePlacement(spread, HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(minHeight), VerticalAnchor.absolute(maxHeight)));
  }

  private static List<PlacementModifier> orePlacement(PlacementModifier countModifier, PlacementModifier heightPlacement) {
    return List.of(countModifier, InSquarePlacement.spread(), heightPlacement, BiomeFilter.biome());
  }

  private static List<PlacementModifier> commonOrePlacement(int spread, PlacementModifier modifier) {
    return orePlacement(CountPlacement.of(spread), modifier);
  }

  public static final ConfiguredFeature<?, ?> CLAY_ON_GRAVEL = buildOreFeature(REPLACEGRAVEL, Blocks.CLAY, ConfigOcean.CLAYSIZE.get());
  public static final ConfiguredFeature<?, ?> CLAY_ON_SAND = buildOreFeature(REPLACESAND, Blocks.CLAY, ConfigOcean.CLAYSIZE.get());
  public static final ConfiguredFeature<?, ?> SAND_ON_GRAVEL = buildOreFeature(REPLACEGRAVEL, Blocks.SAND, ConfigOcean.SANDSIZE.get());
  public static final ConfiguredFeature<?, ?> DIRT_ON_GRAVEL = buildOreFeature(REPLACEGRAVEL, Blocks.DIRT, ConfigOcean.DIRTSIZE.get());
  public static final ConfiguredFeature<?, ?> DIRT_ON_STONE = buildOreFeature(REPLACESTONE, Blocks.COARSE_DIRT, ConfigOcean.GRAVELSIZE.get());
  public static final ConfiguredFeature<?, ?> GRAVEL_ON_STONE = buildOreFeature(REPLACESTONE, Blocks.GRAVEL, ConfigOcean.GRAVELSIZE.get());

  public static PlacedFeature CLAY_ON_GRAVEL_PLACED_FEATURE = CLAY_ON_GRAVEL.placed(generatePlacementModifiers(ConfigOcean.CLAYSPREAD.get(), 1, 64));
  public static PlacedFeature CLAY_ON_SAND_PLACED_FEATURE = CLAY_ON_SAND.placed(generatePlacementModifiers(ConfigOcean.CLAYSPREAD.get(), 1, 64));
  public static PlacedFeature SAND_ON_GRAVEL_PLACED_FEATURE = SAND_ON_GRAVEL.placed(generatePlacementModifiers(ConfigOcean.DIRTSPREAD.get(), 1, 64));
  public static PlacedFeature DIRT_ON_GRAVEL_PLACED_FEATURE = DIRT_ON_GRAVEL.placed(generatePlacementModifiers(ConfigOcean.DIRTSPREAD.get(), 1, 64));
  public static PlacedFeature DIRT_ON_STONE_PLACED_FEATURE = DIRT_ON_STONE.placed(generatePlacementModifiers(ConfigOcean.GRAVELSPREAD.get(), 1, 32));
  public static PlacedFeature GRAVEL_ON_STONE_PLACED_FEATURE = GRAVEL_ON_STONE.placed(generatePlacementModifiers(ConfigOcean.GRAVELSPREAD.get(), 1, 64));

  public static void init() {
    //warmest is clay/sand
    FeatureUtils.register(new ResourceLocation(ModOcean.MODID, "clay_on_sand").toString(), CLAY_ON_SAND);
    FeatureUtils.register(new ResourceLocation(ModOcean.MODID, "clay_on_gravel").toString(), CLAY_ON_GRAVEL);
    FeatureUtils.register(new ResourceLocation(ModOcean.MODID, "sand_on_gravel").toString(), SAND_ON_GRAVEL);
    FeatureUtils.register(new ResourceLocation(ModOcean.MODID, "dirt_on_gravel").toString(), DIRT_ON_GRAVEL);
    FeatureUtils.register(new ResourceLocation(ModOcean.MODID, "coarse_dirt_on_stone").toString(), DIRT_ON_STONE);
    FeatureUtils.register(new ResourceLocation(ModOcean.MODID, "gravel_on_stone").toString(), GRAVEL_ON_STONE);
    //coldest is dirt/grave/

    PlacementUtils.register(new ResourceLocation(ModOcean.MODID, "clay_on_sand").toString(), CLAY_ON_SAND_PLACED_FEATURE);
    PlacementUtils.register(new ResourceLocation(ModOcean.MODID, "clay_on_gravel").toString(), CLAY_ON_GRAVEL_PLACED_FEATURE);
    PlacementUtils.register(new ResourceLocation(ModOcean.MODID, "sand_on_gravel").toString(), SAND_ON_GRAVEL_PLACED_FEATURE);
    PlacementUtils.register(new ResourceLocation(ModOcean.MODID, "dirt_on_gravel").toString(), DIRT_ON_GRAVEL_PLACED_FEATURE);
    PlacementUtils.register(new ResourceLocation(ModOcean.MODID, "coarse_dirt_on_stone").toString(), DIRT_ON_STONE_PLACED_FEATURE);
    PlacementUtils.register(new ResourceLocation(ModOcean.MODID, "gravel_on_stone").toString(), GRAVEL_ON_STONE_PLACED_FEATURE);
  }
}
