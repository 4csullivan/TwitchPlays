package com.ravensdot.twitchplaysmod.twitch;

import com.github.philippheuer.events4j.simple.domain.EventSubscriber;
import com.github.twitch4j.chat.events.CommandEvent;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import com.github.twitch4j.chat.events.channel.IRCMessageEvent;
import com.github.twitch4j.common.enums.CommandPermission;
import com.github.twitch4j.pubsub.events.ChannelPointsRedemptionEvent;
import com.github.twitch4j.pubsub.events.RewardRedeemedEvent;
import com.ravensdot.twitchplaysmod.entities.MobTypes;
import com.ravensdot.twitchplaysmod.network.PacketHandler;

import com.ravensdot.twitchplaysmod.util.RegistryHandler;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.*;

public class PlayerInteraction
{
	public static final int SPAWN_RADIUS = 6;
	public static final int SPAWN_MIN_RADIUS = 2;
	public static final int POTION_CHANCE = 30;
	public static final int NUM_OF_MOBS = MobTypes.values().length;
	private BlockPos playerPos;
	private final Minecraft mc = Minecraft.getInstance();
	private Set<CommandPermission> permissions;
	private static final MobTypes[] types = MobTypes.values();

	private long startTimer = System.currentTimeMillis();
	private Map<String, Long> userTimers;

	@EventSubscriber
	public void onChannelCommand(CommandEvent event) {
		String command = event.getCommand();
		String username = event.getUser().getName();
		permissions = event.getPermissions();
		String[] split = command.split("\\s+");
		assert mc.player != null;
		playerPos = new BlockPos(mc.player);

		switch(split[0])
		{
		case "spawn":
			MobTypes type = null;
			if (split.length > 1) {
				String mobName = split[1].toUpperCase();

				try {
					type = MobTypes.valueOf(mobName);
				} catch (Exception e) {
					e.printStackTrace();
					event.respondToUser("that mob doesn't exist :/");
					return;
				}
			} else {
				Random rand = new Random();

				type = types[rand.nextInt(MobTypes.values().length)];
			}
			String mobName = type.toString().toLowerCase();
			mc.player.sendMessage(new StringTextComponent(String.format("%s has spawned %s!", username, mobName)));

			BlockPos spawnLoc = findSpawnLoc();
			PacketHandler.sendSummon(spawnLoc.getX(), spawnLoc.getY(), spawnLoc.getZ(), username, type, isSub(permissions));
			break;
		case "potion":
			mc.player.sendMessage(new StringTextComponent(String.format("%s applied an effect!", username)));
			PacketHandler.sendEffect();
			break;
		default:
			break;
		}
	}

	@EventSubscriber
	public void onRewardRedeem(ChannelPointsRedemptionEvent event)
	{
		String username = event.getRedemption().getUser().getDisplayName();
		String title = event.getRedemption().getReward().getTitle().toLowerCase();
		playerPos = new BlockPos(mc.player);

		int numOfEvents = 0;
		String regex = "^random event( *)";
		if (title.matches(regex + "(\\d*)$")){
			String[] split = title.split(regex,2);
			numOfEvents = Integer.parseInt(split[1]);
		}

		mc.player.sendMessage(new StringTextComponent(String.format("%s has redeemed %d random events!",username,numOfEvents)));
		Random rand = new Random();
		for (int i = 0; i < numOfEvents; i++) {
			int choice = (int)(rand.nextFloat() * 100);
			if (choice < POTION_CHANCE) {
				PacketHandler.sendEffect();
			} else {
				BlockPos spawnLoc = findSpawnLoc();
				MobTypes type = MobTypes.ZOMBIE;
				int mobChances = (100 - POTION_CHANCE)/NUM_OF_MOBS;
				if (choice < mobChances + POTION_CHANCE) {
					type = MobTypes.ZOMBIE;
				} else if (choice < mobChances * 2 + POTION_CHANCE) {
					type = MobTypes.SPIDER;
				} else {
					type = MobTypes.SILVERFISH;
				}
				PacketHandler.sendSummon(spawnLoc.getX(), spawnLoc.getY(), spawnLoc.getZ(), username, type, false);
			}

		}
	}

	private BlockPos findSpawnLoc() {

		List<BlockPos> possibleLocs = new ArrayList<>();

		for (int y = -SPAWN_RADIUS; y < SPAWN_RADIUS; y++) {
			for (int x = -SPAWN_RADIUS; x < SPAWN_RADIUS; x++) {
				for (int z = -SPAWN_RADIUS; z < SPAWN_RADIUS; z++) {
					if (Math.abs(x) < SPAWN_MIN_RADIUS && Math.abs(z) < SPAWN_MIN_RADIUS)
						continue;

					final BlockPos pos = playerPos.add(x, 0f, z);
					final BlockPos posDown = playerPos.add(x, -1f, z);
					assert mc.world != null;
					final Block block = mc.world.getBlockState(pos).getBlock();
					final Block blockDown = mc.world.getBlockState(posDown).getBlock();

					if (block == Blocks.AIR || block == Blocks.CAVE_AIR) {
						possibleLocs.add(pos);
					}
				}
			}
		}

		if (possibleLocs.size() > 0) {
			Random random = new Random();
			int choice = random.nextInt(possibleLocs.size());
			return possibleLocs.get(choice);
		} else {
			return null;
		}
	}

	private boolean isValidBlock(Block block, Block blockDown) {
		return (block == Blocks.AIR && blockDown != Blocks.AIR) || (block == Blocks.CAVE_AIR && blockDown != Blocks.CAVE_AIR);
	}

	private boolean isSub(Set<CommandPermission> permissions) {
		return permissions.contains(CommandPermission.SUBSCRIBER);
	}

	private boolean checkRemote() {
		boolean isRemote = false;
		if (Minecraft.getInstance().world != null) {
			isRemote = Minecraft.getInstance().world.isRemote;
		}
		return isRemote;
	}
}
