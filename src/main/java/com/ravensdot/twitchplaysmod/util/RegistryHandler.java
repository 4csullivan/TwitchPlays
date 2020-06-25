package com.ravensdot.twitchplaysmod.util;

import com.ravensdot.twitchplaysmod.TwitchPlays;
import com.ravensdot.twitchplaysmod.init.EntityInit;
import com.ravensdot.twitchplaysmod.init.ItemInit;
import com.ravensdot.twitchplaysmod.items.TwitchConfigItem;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryHandler
{
	
	public static void init()
	{
		ItemInit.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		EntityInit.ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
	}

}
