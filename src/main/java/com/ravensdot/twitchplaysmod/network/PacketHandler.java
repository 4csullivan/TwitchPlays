package com.ravensdot.twitchplaysmod.network;

import com.ravensdot.twitchplaysmod.entities.MobTypes;
import com.ravensdot.twitchplaysmod.network.packets.EffectPacket;
import com.ravensdot.twitchplaysmod.network.packets.SummonPacket;

import javafx.scene.effect.Effect;
import net.minecraft.util.RandomObjectDescriptor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

import java.util.Random;

public final class PacketHandler
{
	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
		    new ResourceLocation("twitchplaysmod", "main"),
		    () -> PROTOCOL_VERSION,
		    PROTOCOL_VERSION::equals,
		    PROTOCOL_VERSION::equals
		);
	
	public static void sendSummon(int x, int y, int z, String title, String mobName, boolean isSub) {
		INSTANCE.sendToServer(new SummonPacket(x, y, z, title, mobName, isSub));
	}

	public static void sendEffect()
	{
		Random random = new Random();
		int duration = random.nextInt(120 + 1 - 30) + 30;
		int strength = random.nextInt(5);
		INSTANCE.sendToServer(new EffectPacket(duration, strength));
	}
	
	public static void register()
	{
		int discrim = 0;
		INSTANCE.registerMessage(discrim++, SummonPacket.class, SummonPacket::encode, SummonPacket::new, SummonPacket::handle);
		INSTANCE.registerMessage(discrim++, EffectPacket.class, EffectPacket::encode, EffectPacket::new, EffectPacket::handle);
	}
}
