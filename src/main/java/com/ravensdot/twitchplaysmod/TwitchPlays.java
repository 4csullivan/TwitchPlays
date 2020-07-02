package com.ravensdot.twitchplaysmod;

import com.ravensdot.twitchplaysmod.entities.MobChoices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ravensdot.twitchplaysmod.config.TwitchConfig;
import com.ravensdot.twitchplaysmod.network.PacketHandler;
import com.ravensdot.twitchplaysmod.util.RegistryHandler;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("twitchplaysmod")
public class TwitchPlays
{

    private static final Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "twitchplaysmod";
    
    public TwitchPlays()
    {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        MinecraftForge.EVENT_BUS.register(this);
        RegistryHandler.init();
        
        PacketHandler.register();
        
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, TwitchConfig.SPEC);

        MobChoices.generateEntityList();
    }

    private void setup(final FMLCommonSetupEvent event) { }

    private void doClientStuff(final FMLClientSetupEvent event) { }
}
