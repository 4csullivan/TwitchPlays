package com.ravensdot.twitchplaysmod.config;

import com.ravensdot.twitchplaysmod.TwitchPlays;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.config.ModConfig;

@EventBusSubscriber(modid = TwitchPlays.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class TwitchConfig
{
	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final General GENERAL = new General(BUILDER);
	public static final ForgeConfigSpec SPEC = BUILDER.build();
	
	public static String CLIENT_ID, CLIENT_SECRET, CHANNEL, OAUTH_TOKEN;
	public static boolean IS_CONNECTED;
	public static float SPAWN_RATE_LIMIT;
	
	public static void bakeConfig()
	{
		CLIENT_ID = GENERAL.ClientID.get();
		CLIENT_SECRET = GENERAL.ClientSecret.get();
		CHANNEL = GENERAL.Channel.get();
		OAUTH_TOKEN = GENERAL.OauthToken.get();
		IS_CONNECTED = GENERAL.Connected.get();
		SPAWN_RATE_LIMIT = GENERAL.SpawnRateLimit.get();
	}
	
	@SubscribeEvent
	public static void onModConfigEvent(final ModConfig.ModConfigEvent event)
	{
		if (event.getConfig().getSpec() == TwitchConfig.SPEC) {
			bakeConfig();
		}
	}
	
	public static class General
	{
		public final ForgeConfigSpec.ConfigValue<String> ClientID;
		public final ForgeConfigSpec.ConfigValue<String> ClientSecret;
		public final ForgeConfigSpec.ConfigValue<String> Channel;
		public final ForgeConfigSpec.ConfigValue<String> OauthToken;
		public final ForgeConfigSpec.ConfigValue<Boolean> Connected;
		public final ForgeConfigSpec.ConfigValue<Float> SpawnRateLimit;

		public General(ForgeConfigSpec.Builder builder)
		{
			builder.push("General");
			
			ClientID = builder
					.comment("The client ID value")
					.define("clientID", "N/A");
			
			ClientSecret = builder
					.comment("The secret ID value")
					.define("clientSecret", "N/A");
			
			Channel = builder
					.comment("The name of the channel")
					.define("channel", "N/A");
			
			OauthToken = builder
					.comment("The generated OAuth value")
					.define("oauthToken", "N/A");
			
			Connected = builder
					.comment("Enable/Disable Twitch Integration [false/true|default:false]")
					.define("isConnected", false);

			SpawnRateLimit = builder
					.comment("Limit how often a user can spawn")
					.define("spawnRateLimit", 5f);

			builder.pop();
		}
	}
}
