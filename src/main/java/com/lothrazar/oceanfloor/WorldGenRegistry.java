package com.lothrazar.oceanfloor;

import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

public class WorldGenRegistry {

  private static final RuleTest REPLACEGRAVEL = new BlockMatchTest(Blocks.GRAVEL);
  private static final RuleTest REPLACESAND = new BlockMatchTest(Blocks.SAND);
  private static final RuleTest REPLACESTONE = new BlockMatchTest(Blocks.STONE);

  private static ConfiguredFeature<?, ?> buildOreFeature(RuleTest rule, Block block, int size) {
    return Feature.ORE.configured(new OreConfiguration(rule, block.defaultBlockState(), size));
    //    .count(spread).squared().decorated(
    //        FeatureDecorator.RANGE.configured(new RangeConfiguration(
    //            UniformHeight.of(VerticalAnchor.aboveBottom(minHeight), VerticalAnchor.absolute(maxHeight))))
  }

  public static final ConfiguredFeature<?, ?> CLAY_ON_GRAVEL = buildOreFeature(REPLACEGRAVEL, Blocks.CLAY, ConfigOcean.CLAYSIZE.get());
  public static final ConfiguredFeature<?, ?> CLAY_ON_SAND = buildOreFeature(REPLACESAND, Blocks.CLAY, ConfigOcean.CLAYSIZE.get());
  public static final ConfiguredFeature<?, ?> SAND_ON_GRAVEL = buildOreFeature(REPLACEGRAVEL, Blocks.SAND, ConfigOcean.SANDSIZE.get());
  public static final ConfiguredFeature<?, ?> DIRT_ON_GRAVEL = buildOreFeature(REPLACEGRAVEL, Blocks.DIRT, ConfigOcean.DIRTSIZE.get());
  public static final ConfiguredFeature<?, ?> CDIRT_ON_STONE = buildOreFeature(REPLACESTONE, Blocks.COARSE_DIRT, ConfigOcean.GRAVELSIZE.get());
  public static final ConfiguredFeature<?, ?> GRAVEL_ON_STONE = buildOreFeature(REPLACESTONE, Blocks.GRAVEL, ConfigOcean.GRAVELSIZE.get());

  public static void init() {
    Registry<ConfiguredFeature<?, ?>> r = BuiltinRegistries.CONFIGURED_FEATURE;
    //warmest is clay/sand
    Registry.register(r, new ResourceLocation(ModOcean.MODID, "clay_on_sand"), CLAY_ON_SAND);
    Registry.register(r, new ResourceLocation(ModOcean.MODID, "clay_on_gravel"), CLAY_ON_GRAVEL);
    Registry.register(r, new ResourceLocation(ModOcean.MODID, "sand_on_gravel"), SAND_ON_GRAVEL);
    Registry.register(r, new ResourceLocation(ModOcean.MODID, "dirt_on_gravel"), DIRT_ON_GRAVEL);
    Registry.register(r, new ResourceLocation(ModOcean.MODID, "coarse_dirt_on_stone"), CDIRT_ON_STONE);
    Registry.register(r, new ResourceLocation(ModOcean.MODID, "gravel_on_stone"), GRAVEL_ON_STONE);
    //coldest is dirt/grave/
  }
}
