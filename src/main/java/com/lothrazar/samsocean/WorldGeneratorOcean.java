package com.lothrazar.samsocean;
import java.util.Random;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGeneratorOcean implements IWorldGenerator {
  // http://bedrockminer.jimdo.com/modding-tutorials/basic-modding/world-generation/
  private WorldGenerator genClay;
  private WorldGenerator genSand;
  private WorldGenerator genDirt;
  private final int MIN_HEIGHT = 20;
  private final int MAX_HEIGHT = 128;
  public static final int CHUNK_SIZE = 16;
  public static final int OVERWORLD = 0;
  public WorldGeneratorOcean() {
    this.genClay = new WorldGenMinable(Blocks.CLAY.getDefaultState(), ModOcean.cfg.clayNumBlocks, BlockMatcher.forBlock(Blocks.GRAVEL));
    this.genSand = new WorldGenMinable(Blocks.DIRT.getDefaultState(), ModOcean.cfg.dirtNumBlocks, BlockMatcher.forBlock(Blocks.GRAVEL));
    this.genDirt = new WorldGenMinable(Blocks.SAND.getDefaultState(), ModOcean.cfg.sandNumBlocks, BlockMatcher.forBlock(Blocks.GRAVEL));
  }
  @Override
  public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
    if (world.provider.getDimension() == OVERWORLD) {
      this.run(this.genClay, world, random, chunkX * CHUNK_SIZE, chunkZ * CHUNK_SIZE, ModOcean.cfg.clayChance, MIN_HEIGHT, MAX_HEIGHT);
      this.run(this.genSand, world, random, chunkX * CHUNK_SIZE, chunkZ * CHUNK_SIZE, ModOcean.cfg.sandChance, MIN_HEIGHT, MAX_HEIGHT);
      this.run(this.genDirt, world, random, chunkX * CHUNK_SIZE, chunkZ * CHUNK_SIZE, ModOcean.cfg.dirtChance, MIN_HEIGHT, MAX_HEIGHT);
    }
  }
  private void run(WorldGenerator generator, World world, Random rand, int chunk_X, int chunk_Z, int chancesToSpawn, int minHeight, int maxHeight) {
    if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
      throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");
    int heightDiff = maxHeight - minHeight;
    BlockPos pos;
    Biome biome;
    for (int i = 0; i < chancesToSpawn; i++) {
      int x = chunk_X + rand.nextInt(CHUNK_SIZE);
      int y = minHeight + rand.nextInt(heightDiff);
      int z = chunk_Z + rand.nextInt(CHUNK_SIZE);
      pos = new BlockPos(x, y, z);
      biome = world.getBiome(pos);
      if (biome == Biomes.OCEAN || biome == Biomes.DEEP_OCEAN || biome == Biomes.FROZEN_OCEAN) {
        generator.generate(world, rand, pos);
      }
    }
  }
}
