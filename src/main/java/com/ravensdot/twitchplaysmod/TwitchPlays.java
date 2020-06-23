package com.ravensdot.twitchplaysmod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ravensdot.twitchplaysmod.network.PacketHandler;
import com.ravensdot.twitchplaysmod.twitch.TwitchHandler;
import com.ravensdot.twitchplaysmod.util.RegistryHandler;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
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
        
        RegistryHandler.init();

        MinecraftForge.EVENT_BUS.register(this);
        
        PacketHandler.register();
    }

    private void setup(final FMLCommonSetupEvent event) { }

    private void doClientStuff(final FMLClientSetupEvent event) { }
}
