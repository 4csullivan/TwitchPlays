package com.ravensdot.twitchplaysmod.network;

import com.ravensdot.twitchplaysmod.network.packets.CommandPacket;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public final class PacketHandler
{
	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
		    new ResourceLocation("twitchplaysmod", "main"),
		    () -> PROTOCOL_VERSION,
		    PROTOCOL_VERSION::equals,
		    PROTOCOL_VERSION::equals
		);
	
	public static void sendCommand(int x, int y, int z) {
		INSTANCE.sendToServer(new CommandPacket(x, y, z));
	}
	
	public static void register()
	{
		int discrim = 0;
		INSTANCE.registerMessage(discrim++, CommandPacket.class, CommandPacket::encode, CommandPacket::new, CommandPacket::handle);
	}
}
