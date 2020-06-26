package com.ravensdot.twitchplaysmod.twitch;

import com.github.philippheuer.events4j.simple.domain.EventSubscriber;
import com.github.twitch4j.chat.events.CommandEvent;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import com.github.twitch4j.common.enums.CommandPermission;
import com.github.twitch4j.pubsub.events.ChannelPointsRedemptionEvent;
import com.github.twitch4j.pubsub.events.RewardRedeemedEvent;
import com.ravensdot.twitchplaysmod.config.TwitchConfig;
import com.ravensdot.twitchplaysmod.network.PacketHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.scoreboard.Scoreboard;

import java.util.Map;
import java.util.Set;

enum MobTypes
{
	ZOMBIE,
	SKELETON,
	CREEPER,

}

public class SpawnMobOnCommand
{
	/*public SpawnMobOnCommand(SimpleEventHandler eventHandler)
	{
		eventHandler.onEvent(CommandEvent.class, event -> onChannelMessage(event));
	}*/

	private long startTimer = System.currentTimeMillis();
	private Map<String, Long> userTimers;

	@EventSubscriber
	public void onChannelCommand(CommandEvent event) {
		String command = event.getCommand();
		String username = event.getUser().getName();
		Set<CommandPermission> permissions = event.getPermissions();


		switch(command)
		{
		case "zombie":
			long seconds = (System.currentTimeMillis() - startTimer)/1000;
			if (seconds < TwitchConfig.SPAWN_RATE_LIMIT){
				event.getTwitchChat().sendMessage(TwitchConfig.CHANNEL, String.format("Spawn cooldown. Please wait: %.1f seconds.", (float)seconds));
				return;
			} else {
				startTimer = System.currentTimeMillis();
			}
			if (!teamExists()) {
				PacketHandler.sendCommand("/team add twitch");
				PacketHandler.sendCommand("/team modify twitch color gold");
			}
			if (checkRemote()) {
				String addTeam = "";
				if (isSub(permissions))
					addTeam = ",Team:twitch";
				PacketHandler.sendCommand(String.format("/execute at @p run summon minecraft:area_effect_cloud ~2 ~1 ~2 {Passengers:[" +
						"{id:\"twitchplaysmod:twitch_zombie\",CustomName:\"\\\"%s\\\"\",CustomNameVisible:1%s}" +
						"]}", username,addTeam));
			}
			break;
		}
	}

	private boolean isSub(Set<CommandPermission> permissions) {
		return permissions.contains(CommandPermission.SUBSCRIBER);
	}

	private boolean teamExists() {
		assert Minecraft.getInstance().world != null;
		return Minecraft.getInstance().world.getScoreboard().getTeamNames().contains("twitch");
	}

	@EventSubscriber
	public void onRewardRedeem(RewardRedeemedEvent event)
	{
		String title = event.getRedemption().getReward().getTitle();
		switch(title)
		{
			case "Spawn Mob":
				spawnMob(event.getRedemption().getUserInput());
				break;
			case "Apply Random Effect":
				applyEffect();
				break;
			default:
				break;
		}
	}

	private void spawnMob(String userInput)
	{

	}

	private void applyEffect()
	{

	}

	private boolean checkRemote() {
		boolean isRemote = false;
		if (Minecraft.getInstance().world != null) {
			isRemote = Minecraft.getInstance().world.isRemote;
		}
		return isRemote;
	}
	
	/*@EventSubscriber
	public void onMessage(ChannelMessageEvent event) {
		System.out.print("message recieved! " + event.getMessage());
	}*/
}
