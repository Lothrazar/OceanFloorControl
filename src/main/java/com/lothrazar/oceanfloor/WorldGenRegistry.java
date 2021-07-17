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

  private static final int SPREAD = 50;
  private static final RuleTest RULEGRAVEL = new BlockMatchRuleTest(Blocks.GRAVEL);
  public static final ConfiguredFeature<?, ?> DIRT = buildOreFeature(RULEGRAVEL, Blocks.DIAMOND_BLOCK,
      ConfigOcean.DIRTSIZE.get(), 1, 64, SPREAD);
  public static final ConfiguredFeature<?, ?> CLAY = buildOreFeature(RULEGRAVEL, Blocks.GRAY_WOOL,
      ConfigOcean.DIRTSIZE.get(), 1, 64, SPREAD);
  public static final ConfiguredFeature<?, ?> SAND = buildOreFeature(RULEGRAVEL, Blocks.YELLOW_CONCRETE,
      ConfigOcean.DIRTSIZE.get(), 1, 64, SPREAD);

  public static ConfiguredFeature<?, ?> buildOreFeature(RuleTest rule, Block block, int size, int minHeight, int maxHeight, int spread) {
    return Feature.ORE.withConfiguration(new OreFeatureConfig(rule, block.getDefaultState(), size)).func_242731_b(spread).square()
        .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(minHeight, 0, maxHeight))); // 
  }

  public static void init() {
    Registry<ConfiguredFeature<?, ?>> r = WorldGenRegistries.CONFIGURED_FEATURE;
    Registry.register(r, new ResourceLocation(ModOcean.MODID, "clay"), CLAY);
    Registry.register(r, new ResourceLocation(ModOcean.MODID, "sand"), CLAY);
    Registry.register(r, new ResourceLocation(ModOcean.MODID, "dirt"), DIRT);
  }
}
