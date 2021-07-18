package com.lothrazar.oceanfloor;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;

public class WorldGenRegistry {

  private static final RuleTest REPLACEGRAVEL = new BlockMatchRuleTest(Blocks.GRAVEL);
  private static final RuleTest REPLACESAND = new BlockMatchRuleTest(Blocks.SAND);
  private static final RuleTest REPLACESTONE = new BlockMatchRuleTest(Blocks.STONE);

  private static ConfiguredFeature<?, ?> buildOreFeature(RuleTest rule, Block block, int size, int minHeight, int maxHeight, int spread) {
    return Feature.ORE.withConfiguration(new OreFeatureConfig(rule, block.getDefaultState(), size)).func_242731_b(spread).square().withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(minHeight, 0, maxHeight))); // 
  }

  public static final ConfiguredFeature<?, ?> CLAY = buildOreFeature(REPLACEGRAVEL, Blocks.CLAY,
      ConfigOcean.CLAYSIZE.get(), 1, 64, ConfigOcean.CLAYSPREAD.get());
  public static final ConfiguredFeature<?, ?> CLAY_ON_SAND = buildOreFeature(REPLACESAND, Blocks.CLAY,
      ConfigOcean.CLAYSIZE.get(), 1, 64, ConfigOcean.CLAYSPREAD.get());
  public static final ConfiguredFeature<?, ?> SAND = buildOreFeature(REPLACEGRAVEL, Blocks.SAND,
      ConfigOcean.SANDSIZE.get(), 1, 64, ConfigOcean.SANDSPREAD.get());
  public static final ConfiguredFeature<?, ?> DIRT = buildOreFeature(REPLACEGRAVEL, Blocks.DIRT,
      ConfigOcean.DIRTSIZE.get(), 1, 64, ConfigOcean.DIRTSPREAD.get());
  public static final ConfiguredFeature<?, ?> CDIRT = buildOreFeature(REPLACESTONE, Blocks.COARSE_DIRT,
      ConfigOcean.GRAVELSIZE.get(), 1, 32, ConfigOcean.GRAVELSIZE.get());
  public static final ConfiguredFeature<?, ?> GRAVEL = buildOreFeature(REPLACESTONE, Blocks.GRAVEL,
      ConfigOcean.GRAVELSIZE.get(), 1, 64, ConfigOcean.GRAVELSPREAD.get());

  public static void init() {
    Registry<ConfiguredFeature<?, ?>> r = WorldGenRegistries.CONFIGURED_FEATURE;
    //warmest is clay/sand
    Registry.register(r, new ResourceLocation(ModOcean.MODID, "clay_on_sand"), CLAY_ON_SAND);
    Registry.register(r, new ResourceLocation(ModOcean.MODID, "clay_on_gravel"), CLAY);
    Registry.register(r, new ResourceLocation(ModOcean.MODID, "sand_on_gravel"), SAND);
    Registry.register(r, new ResourceLocation(ModOcean.MODID, "dirt_on_gravel"), DIRT);
    Registry.register(r, new ResourceLocation(ModOcean.MODID, "coarse_dirt_on_stone"), CDIRT);
    Registry.register(r, new ResourceLocation(ModOcean.MODID, "gravel_on_stone"), GRAVEL);
    //coldest is dirt/grave/
  }
}
