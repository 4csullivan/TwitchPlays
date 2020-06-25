package com.ravensdot.twitchplaysmod.twitch;

import com.github.philippheuer.events4j.simple.domain.EventSubscriber;
import com.github.twitch4j.chat.events.CommandEvent;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import com.github.twitch4j.common.enums.CommandPermission;
import com.github.twitch4j.pubsub.events.RewardRedeemedEvent;
import com.ravensdot.twitchplaysmod.network.PacketHandler;

import net.minecraft.client.Minecraft;

import java.util.Set;

public class SpawnMobOnCommand
{
	/*public SpawnMobOnCommand(SimpleEventHandler eventHandler)
	{
		eventHandler.onEvent(CommandEvent.class, event -> onChannelMessage(event));
	}*/
	
	@EventSubscriber
	public void onChannelCommand(CommandEvent event) {
		String command = event.getCommand();
		String username = event.getUser().getName();
		Set<CommandPermission> permissions = event.getPermissions();
		switch(command)
		{
		case "zombie":
			if (checkRemote()) {
				PacketHandler.sendCommand(String.format("/execute at @p run summon minecraft:area_effect_cloud ~-2 ~1 ~2 {Passengers:[" +
						"{id:\"twitchplaysmod:twitch_zombie\",CustomName:\"\\\"%s\\\"\",CustomNameVisible:1,ArmorItems:[{},{},{},{id:\"minecraft:leather_helmet\",tag:{display:{color:7286944}},Count:1}]}" +
						"]}", username));
			}
			break;
		}
	}

	/*@EventSubscriber
	public void onRedeem(RewardRedeemedEvent event) {
		String eventID = event.getRedemption().getReward().getId();
	}*/

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
