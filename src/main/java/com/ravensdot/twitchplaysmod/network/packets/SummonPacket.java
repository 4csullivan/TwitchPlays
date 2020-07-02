package com.ravensdot.twitchplaysmod.network.packets;

import java.util.Random;
import java.util.function.Supplier;

import com.ravensdot.twitchplaysmod.entities.MobChoices;
import com.ravensdot.twitchplaysmod.entities.MobTypes;
import com.ravensdot.twitchplaysmod.twitch.PlayerInteraction;
import net.minecraft.entity.EntityType;
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
	private final String mobName;
	private final boolean isSub;

	public SummonPacket(PacketBuffer buf) {
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
		this.title = buf.readString();
		this.mobName = buf.readString();
		this.isSub = buf.readBoolean();
	}
	
	public SummonPacket(int x, int y, int z, String title, String mobName, boolean isSub) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.title = title;
		this.mobName = mobName;
		this.isSub = isSub;
	}
	
	public void encode(PacketBuffer buf) {
		buf.writeInt(this.x);
		buf.writeInt(this.y);
		buf.writeInt(this.z);
		buf.writeString(this.title);
		buf.writeString(this.mobName);
		buf.writeBoolean(this.isSub);
	}
	
	public SummonPacket decode(PacketBuffer buf) {
		return new SummonPacket(buf.readInt(), buf.readInt(), buf.readInt(), buf.readString(), buf.readString(), buf.readBoolean());
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
				final BlockPos pos = new BlockPos(this.x, this.y, this.z);

				EntityType<?> entity = null;
				if (mobName.equals(PlayerInteraction.RANDOM_MOB_NAME)) {
					Random rand = new Random();
					int choice = rand.nextInt(MobChoices.ENTITY_TYPES.size());
					entity = MobChoices.ENTITY_TYPES.get(choice);
				} else {
					for (EntityType<?> e : MobChoices.ENTITY_TYPES) {
						if (e.getName().getUnformattedComponentText().toLowerCase().equals(mobName)) {
							entity = e;
							break;
						}
					}
					if (entity == null) {
						return;
					}
				}

				entity.spawn(world, null, component, world.getClosestPlayer(this.x, this.y, this.z), pos, SpawnReason.SPAWN_EGG, true, false)
							.setCustomNameVisible(true);
				ctx.get().getSender().sendMessage(new StringTextComponent(String.format("%s has spawned %s!", title, entity.getName().getUnformattedComponentText())));
				/*switch(this.type)
				{
					case ZOMBIE:
						TwitchZombieEntity twitchZombieEntity = EntityInit.TWITCH_ZOMBIE_ENTITY.get().spawn(world, null, component, world.getClosestPlayer(this.x,this.y, this.z), pos, SpawnReason.SPAWN_EGG,true, false);
						assert twitchZombieEntity != null;
						twitchZombieEntity.setCustomNameVisible(true);
						twitchZombieEntity.setChild(false);
						break;
					case SPIDER:
						TwitchSpiderEntity spiderEntity = EntityInit.TWITCH_SPIDER_ENTITY.get().spawn(world, null, component, world.getClosestPlayer(this.x, this.y, this.z), pos, SpawnReason.SPAWN_EGG, true, false);
						assert spiderEntity != null;
						spiderEntity.setAggroed(true);
						spiderEntity.setCustomNameVisible(true);
						break;
					case SILVERFISH:
						SilverfishEntity silverfishEntity = EntityInit.TWITCH_SILVERFISH_ENTITY.get().spawn(world, null, component, world.getClosestPlayer(this.x,this.y, this.z), pos, SpawnReason.SPAWN_EGG,true, false);
						assert silverfishEntity != null;
						silverfishEntity.setCustomNameVisible(true);
						break;
					case
				}*/


			}
		});
		ctx.get().setPacketHandled(true);
	}

}
