package com.ravensdot.twitchplaysmod.network.packets;

import java.util.function.Supplier;

import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class CommandPacket
{
	private final String command;
	
	public CommandPacket(PacketBuffer buf) {
		this.command = buf.readString();
	}
	
	public CommandPacket(String command) {
		this.command = command;
	}
	
	public void encode(PacketBuffer buf) {
		buf.writeString(command);
	}
	
	public CommandPacket decode(PacketBuffer buf) {
		return new CommandPacket(buf.readString());
	}
	
	public void handle(Supplier<NetworkEvent.Context> ctx)
	{
		ctx.get().enqueueWork(()-> {
			Minecraft.getInstance().player.sendChatMessage(command);
		});
		ctx.get().setPacketHandled(true);
	}
}
