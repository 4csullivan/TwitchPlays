package com.ravensdot.twitchplaysmod.twitch;

import com.github.philippheuer.events4j.simple.domain.EventSubscriber;
import com.github.twitch4j.chat.events.CommandEvent;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import com.github.twitch4j.common.enums.CommandPermission;
import com.github.twitch4j.pubsub.events.ChannelPointsRedemptionEvent;
import com.github.twitch4j.pubsub.events.RewardRedeemedEvent;
import com.ravensdot.twitchplaysmod.config.TwitchConfig;
import com.ravensdot.twitchplaysmod.entities.TwitchZombieEntity;
import com.ravensdot.twitchplaysmod.init.EntityInit;
import com.ravensdot.twitchplaysmod.network.PacketHandler;

import com.ravensdot.twitchplaysmod.util.RegistryHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.*;

enum MobTypes
{
	ZOMBIE,
	SKELETON,
	CREEPER,

}

public class SpawnMobOnCommand
{
	public static final int SPAWN_RADIUS = 6;
	public static final int SPAWN_MIN_RADIUS = 3;
	private BlockPos playerPos;
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
		Minecraft mc = Minecraft.getInstance();
		assert mc.player != null;
		playerPos = new BlockPos(mc.player);

		switch(command)
		{
		case "zombie":
			BlockPos spawnLoc = findSpawnLoc(mc);
			System.out.println("sending packet...");
			PacketHandler.sendCommand(spawnLoc.getX(), spawnLoc.getY(), spawnLoc.getZ());

			//AxisAlignedBB aabb = mc.player.getBoundingBox().expand(SPAWN_RADIUS, SPAWN_RADIUS, SPAWN_RADIUS);
			//mc.world.findBlockstateInArea(aabb, Blocks.AIR);

			/*if (mc.player.getEntityWorld().isRemote) {
				TwitchZombieEntity zombieEntity = new TwitchZombieEntity(EntityInit.TWITCH_ZOMBIE_ENTITY.get(), mc.player.getEntityWorld());

				if (spawnLoc != null) {
					zombieEntity.setLocationAndAngles(spawnLoc.getX(), spawnLoc.getY(), spawnLoc.getZ(), 0, 0);
					System.out.println(String.format("spawn: %f, %f, %f",  zombieEntity.getPosX(), zombieEntity.getPosY(), zombieEntity.getPosZ()));
					assert mc.world != null;
					mc.player.getEntityWorld().addEntity(zombieEntity);
				}
			}*/

			/*long seconds = (System.currentTimeMillis() - startTimer)/1000;
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
			}*/
			break;
		}
	}

	private BlockPos findSpawnLoc(Minecraft mc) {

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

					if (isValidBlock(block, blockDown)) {
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
