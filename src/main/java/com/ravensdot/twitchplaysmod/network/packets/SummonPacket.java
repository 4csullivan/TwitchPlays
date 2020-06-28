package com.ravensdot.twitchplaysmod.network.packets;

import java.util.function.Supplier;

import com.ravensdot.twitchplaysmod.entities.TwitchZombieEntity;
import com.ravensdot.twitchplaysmod.init.EntityInit;
import net.minecraft.entity.SpawnReason;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;

public class SummonPacket
{
	//private final String command;
	private final int x, y, z;
	private final String title;
	private final boolean isSub;

	public SummonPacket(PacketBuffer buf) {
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
		this.title = buf.readString();
		this.isSub = buf.readBoolean();
	}
	
	public SummonPacket(int x, int y, int z, String title, boolean isSub) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.title = title;
		this.isSub = isSub;
	}
	
	public void encode(PacketBuffer buf) {
		buf.writeInt(this.x);
		buf.writeInt(this.y);
		buf.writeInt(this.z);
		buf.writeString(this.title);
		buf.writeBoolean(this.isSub);
	}
	
	public SummonPacket decode(PacketBuffer buf) {
		return new SummonPacket(buf.readInt(), buf.readInt(), buf.readInt(), buf.readString(), buf.readBoolean());
	}
	
	public void handle(Supplier<NetworkEvent.Context> ctx)
	{
		System.out.println("handling...");
		StringTextComponent component = new StringTextComponent(title);
		if (isSub) {
			component.applyTextStyle(TextFormatting.GOLD);
		}
		ctx.get().enqueueWork(()-> {
			//Minecraft.getInstance().player.sendChatMessage(command);
			World world = ctx.get().getSender().world;
			if (!world.isRemote) {
				//TwitchZombieEntity twitchZombieEntity = new TwitchZombieEntity(EntityInit.TWITCH_ZOMBIE_ENTITY.get(), world);
				//twitchZombieEntity.setPosition(this.x, this.y, this.z);
				final BlockPos pos = new BlockPos(this.x, this.y, this.z);
				TwitchZombieEntity twitchZombieEntity = EntityInit.TWITCH_ZOMBIE_ENTITY.get().spawn(world, null, component, world.getClosestPlayer(this.x,this.y, this.z), pos, SpawnReason.SPAWN_EGG,true, false);
				assert twitchZombieEntity != null;
				twitchZombieEntity.setCustomNameVisible(true);
			}
		});
		ctx.get().setPacketHandled(true);
	}

}