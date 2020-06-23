package com.ravensdot.twitchplaysmod.twitch;

import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.ravensdot.twitchplaysmod.config.TwitchConfig;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class TwitchHandler
{
	private TwitchClient twitchClient;
	
	public TwitchHandler()
	{
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, TwitchConfig.SPEC);
		OAuth2Credential oauth = new OAuth2Credential(TwitchConfig.CHANNEL, TwitchConfig.OAUTH_TOKEN);
		
		twitchClient = TwitchClientBuilder.builder()
				 	.withClientId(TwitchConfig.CLIENT_ID)
				 	.withClientSecret(TwitchConfig.CLIENT_SECRET)
					.withEnableHelix(true)
					.withEnableChat(true)
					.withChatAccount(oauth)
					.withCommandTrigger("!")
					.build();
	}
	
	public void register()
	{
		SpawnMobOnCommand spawnMobEvent = new SpawnMobOnCommand();
		twitchClient.getEventManager().getEventHandler(SimpleEventHandler.class).registerListener(spawnMobEvent);
	}
	
	public void start()
	{
		twitchClient.getChat().joinChannel(TwitchConfig.CHANNEL);
	}
	
	public void stop()
	{
		twitchClient.getChat().leaveChannel(TwitchConfig.CHANNEL);
	}
}
