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

  private static final RuleTest RULEGRAVEL = new BlockMatchRuleTest(Blocks.GRAVEL);
  private static final RuleTest RULESAND = new BlockMatchRuleTest(Blocks.SAND);
  public static final ConfiguredFeature<?, ?> DIRT = buildOreFeature(RULEGRAVEL, Blocks.DIRT,
      ConfigOcean.DIRTSIZE.get(), 1, 64, ConfigOcean.DIRTSPREAD.get());
  public static final ConfiguredFeature<?, ?> CLAY = buildOreFeature(RULEGRAVEL, Blocks.CLAY,
      ConfigOcean.CLAYSIZE.get(), 1, 64, ConfigOcean.CLAYSPREAD.get());
  public static final ConfiguredFeature<?, ?> SAND = buildOreFeature(RULEGRAVEL, Blocks.SAND,
      ConfigOcean.SANDSIZE.get(), 1, 64, ConfigOcean.SANDSPREAD.get());
  //sand to clan
  public static final ConfiguredFeature<?, ?> CLAY_ON_SAND = buildOreFeature(RULESAND, Blocks.CLAY,
      ConfigOcean.CLAYSIZE.get(), 1, 64, ConfigOcean.CLAYSPREAD.get());

  public static ConfiguredFeature<?, ?> buildOreFeature(RuleTest rule, Block block, int size, int minHeight, int maxHeight, int spread) {
    return Feature.ORE.withConfiguration(new OreFeatureConfig(rule, block.getDefaultState(), size)).func_242731_b(spread).square()
        .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(minHeight, 0, maxHeight))); // 
  }

  public static void init() {
    Registry<ConfiguredFeature<?, ?>> r = WorldGenRegistries.CONFIGURED_FEATURE;
    //most oceans, normal and cold, have a gravel floor
    Registry.register(r, new ResourceLocation(ModOcean.MODID, "clay_on_sand"), CLAY_ON_SAND);
    Registry.register(r, new ResourceLocation(ModOcean.MODID, "clay"), CLAY);
    Registry.register(r, new ResourceLocation(ModOcean.MODID, "sand"), CLAY);
    Registry.register(r, new ResourceLocation(ModOcean.MODID, "dirt"), DIRT);
    // WARM oceans can have a sand floor
  }
}
