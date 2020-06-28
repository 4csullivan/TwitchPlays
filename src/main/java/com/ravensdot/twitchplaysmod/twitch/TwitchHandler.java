package com.ravensdot.twitchplaysmod.twitch;

import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.ravensdot.twitchplaysmod.config.TwitchConfig;

import java.util.Collections;

public class TwitchHandler
{
	private TwitchClient twitchClient;
	
	public TwitchHandler()
	{
		OAuth2Credential oauth = new OAuth2Credential(TwitchConfig.CHANNEL, TwitchConfig.OAUTH_TOKEN);
		
		twitchClient = TwitchClientBuilder.builder()
				 	.withClientId(TwitchConfig.CLIENT_ID)
				 	.withClientSecret(TwitchConfig.CLIENT_SECRET)
					.withEnableHelix(true)
					.withEnableChat(true)
					.withEnablePubSub(true)
					.withChatAccount(oauth)
					.withCommandTrigger("!")
					.build();

		String channelID = twitchClient
				.getHelix()
				.getUsers(oauth.getAccessToken(), null, Collections.singletonList(TwitchConfig.CHANNEL))
				.execute()
				.getUsers().get(0).getId();

		twitchClient.getPubSub().listenForChannelPointsRedemptionEvents(oauth, channelID);
	}
	
	public void register()
	{
		PlayerInteraction playerInteraction = new PlayerInteraction();
		twitchClient.getEventManager().getEventHandler(SimpleEventHandler.class).registerListener(playerInteraction);
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
