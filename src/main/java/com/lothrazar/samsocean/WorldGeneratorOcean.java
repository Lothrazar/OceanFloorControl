package com.lothrazar.samsocean;

import java.util.Random;

import net.minecraft.block.state.pattern.BlockHelper;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGeneratorOcean implements IWorldGenerator
{
	//Thanks to ref :  http://bedrockminer.jimdo.com/modding-tutorials/basic-modding/world-generation/

	private WorldGenerator genClay;  
	private WorldGenerator genSand;  
	private WorldGenerator genDirt;  

	private final int MIN_HEIGHT = 20; 
	private final int MAX_HEIGHT = 128;
	public static final int CHUNK_SIZE = 16;
	public static final int OVERWORLD = 0;
 
	public WorldGeneratorOcean() 
	{   
	    this.genClay = new WorldGenMinable(Blocks.clay.getDefaultState(), ModOcean.cfg.clayNumBlocks,BlockHelper.forBlock(Blocks.gravel));
	    this.genSand = new WorldGenMinable(Blocks.dirt.getDefaultState(), ModOcean.cfg.dirtNumBlocks,BlockHelper.forBlock(Blocks.gravel));
	    this.genDirt = new WorldGenMinable(Blocks.sand.getDefaultState(), ModOcean.cfg.sandNumBlocks,BlockHelper.forBlock(Blocks.gravel));
	}
	 
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,IChunkProvider chunkGenerator, IChunkProvider chunkProvider) 
	{ 
		if(world.provider.getDimensionId() == OVERWORLD)//overworld 
		{ 
			this.run(this.genClay, world, random, chunkX * CHUNK_SIZE, chunkZ * CHUNK_SIZE, 
					 ModOcean.cfg.clayChance, MIN_HEIGHT, MAX_HEIGHT);
			this.run(this.genSand, world, random, chunkX * CHUNK_SIZE, chunkZ * CHUNK_SIZE, 
					 ModOcean.cfg.sandChance, MIN_HEIGHT, MAX_HEIGHT);
			this.run(this.genDirt, world, random, chunkX * CHUNK_SIZE, chunkZ * CHUNK_SIZE,
					 ModOcean.cfg.dirtChance, MIN_HEIGHT, MAX_HEIGHT);
		} 
		/*//TODO: maybe oneday?
		if(world.provider.getDimensionId() == Reference.Dimension.nether) 
		{ 
			this.run(this.genGold, world, random, chunkX * Reference.CHUNK_SIZE, chunkZ * Reference.CHUNK_SIZE, 
					ModMain.cfg.clayChance, MIN_HEIGHT, MAX_HEIGHT); 
		}*/
	}
	
	private void run(WorldGenerator generator, World world, Random rand, int chunk_X, int chunk_Z, int chancesToSpawn, int minHeight, int maxHeight) 
	{
	    if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
	        throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");
 
	    int heightDiff = maxHeight - minHeight;
 
	    BlockPos pos;
	    BiomeGenBase biome;
	    
	    for (int i = 0; i < chancesToSpawn; i ++) 
	    { 
	        int x = chunk_X + rand.nextInt(CHUNK_SIZE);
	        int y = minHeight + rand.nextInt(heightDiff);
	        int z = chunk_Z + rand.nextInt(CHUNK_SIZE);
	        
	        pos = new BlockPos(x, y, z);
	        biome = world.getBiomeGenForCoords(pos);
	         
	        if( biome == BiomeGenBase.ocean || 
	        	biome == BiomeGenBase.deepOcean 
	        	//||biome==BiomeGenBase.hell//TODO: a seperate way/class for the nether?
	        	)
	        {  
	        	generator.generate(world, rand, pos);  
	        }
	    }
	} 
}
