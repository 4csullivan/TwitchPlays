package com.ravensdot.twitchplaysmod.network.packets;

import java.io.DataOutput;
import java.util.function.Supplier;

import com.ravensdot.twitchplaysmod.entities.TwitchZombieEntity;
import com.ravensdot.twitchplaysmod.init.EntityInit;
import com.sun.org.apache.xpath.internal.operations.Bool;
import net.minecraft.client.Minecraft;
import net.minecraft.command.arguments.NBTCompoundTagArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;

public class CommandPacket
{
	//private final String command;
	private final int x, y, z;
	private final String title;

	public CommandPacket(PacketBuffer buf) {
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
		this.title = buf.readString();
	}
	
	public CommandPacket(int x, int y, int z, String title) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.title = title;
	}
	
	public void encode(PacketBuffer buf) {
		buf.writeInt(this.x);
		buf.writeInt(this.y);
		buf.writeInt(this.z);
		buf.writeString(this.title);
	}
	
	public CommandPacket decode(PacketBuffer buf) {
		return new CommandPacket(buf.readInt(), buf.readInt(), buf.readInt(), buf.readString());
	}
	
	public void handle(Supplier<NetworkEvent.Context> ctx)
	{
		System.out.println("handling...");
		StringTextComponent component = new StringTextComponent(title);
		component.applyTextStyle(TextFormatting.GOLD);

		ctx.get().enqueueWork(()-> {
			//Minecraft.getInstance().player.sendChatMessage(command);
			World world = ctx.get().getSender().world;
			if (!world.isRemote) {
				//TwitchZombieEntity twitchZombieEntity = new TwitchZombieEntity(EntityInit.TWITCH_ZOMBIE_ENTITY.get(), world);
				//twitchZombieEntity.setPosition(this.x, this.y, this.z);
				final BlockPos pos = new BlockPos(this.x, this.y, this.z);
				EntityInit.TWITCH_ZOMBIE_ENTITY.get().spawn(world, null, component, world.getClosestPlayer(this.x,this.y, this.z), pos, SpawnReason.SPAWN_EGG,true, false);
			}
		});
		ctx.get().setPacketHandled(true);
	}

}
