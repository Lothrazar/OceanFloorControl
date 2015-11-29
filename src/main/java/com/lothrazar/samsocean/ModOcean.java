package com.lothrazar.samsocean;

import org.apache.logging.log4j.Logger;     
 
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
  
@Mod(modid = ModOcean.MODID, useMetadata = true )  
public class ModOcean
{	
	public static final String MODID = "samsocean";
	public static final String TEXTURE_LOCATION = MODID + ":";

	@Instance(value = MODID)
	public static ModOcean instance;	

	public static Logger logger; 
	public static ConfigOcean cfg;

	@EventHandler
	public void onPreInit(FMLPreInitializationEvent event)
	{ 
		logger = event.getModLog();  
		
		cfg = new ConfigOcean(new Configuration(event.getSuggestedConfigurationFile()));
	  
		MinecraftForge.EVENT_BUS.register(instance); 
		MinecraftForge.TERRAIN_GEN_BUS.register(instance);
		MinecraftForge.ORE_GEN_BUS.register(instance); 
	}
	
	@EventHandler
	public void onInit(FMLInitializationEvent event)
	{       
		int weight = 0;
		GameRegistry.registerWorldGenerator(new WorldGeneratorOcean(), weight);
	}
}
