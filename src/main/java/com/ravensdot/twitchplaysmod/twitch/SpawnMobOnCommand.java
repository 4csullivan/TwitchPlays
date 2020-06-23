package com.ravensdot.twitchplaysmod.twitch;

import com.github.philippheuer.events4j.simple.domain.EventSubscriber;
import com.github.twitch4j.chat.events.CommandEvent;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import com.ravensdot.twitchplaysmod.network.PacketHandler;

import net.minecraft.client.Minecraft;

public class SpawnMobOnCommand
{
	/*public SpawnMobOnCommand(SimpleEventHandler eventHandler)
	{
		eventHandler.onEvent(CommandEvent.class, event -> onChannelMessage(event));
	}*/
	
	@EventSubscriber
	public void onChannelMessage(CommandEvent event) {
		String command = event.getCommand();
		String username = event.getUser().getName();
		switch(command)
		{
		case "zombie":
			if (!Minecraft.getInstance().world.isRemote) {
				PacketHandler.sendCommand(String.format("/execute at @p run summon minecraft:area_effect_cloud ~-2 ~1 ~2 {Passengers:[{id:\"minecraft:zombie\",CustomName:\"\\\"%s\\\"\",CustomNameVisible:1}]}", username));
			}
			break;
		}
	}
	
	@EventSubscriber
	public void onMessage(ChannelMessageEvent event) {
		System.out.print("message recieved! " + event.getMessage());
	}
}
