package com.ravensdot.twitchplaysmod.items;

import com.ravensdot.twitchplaysmod.network.PacketHandler;
import com.ravensdot.twitchplaysmod.network.packets.CommandPacket;
import com.ravensdot.twitchplaysmod.twitch.SpawnMobOnCommand;
import com.ravensdot.twitchplaysmod.twitch.TwitchHandler;

import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.world.World;

public class TwitchConfigItem extends Item
{
	private TwitchHandler twitch;
	public TwitchConfigItem()
	{
		super(new Item.Properties().group(ItemGroup.MATERIALS));
		twitch = new TwitchHandler();
		twitch.start();
		twitch.register();
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
	{
		if (!worldIn.isRemote()) {
			twitch.stop();
			//PacketHandler.sendCommand("/execute at @p run summon minecraft:area_effect_cloud ~2 ~1 ~2 {Passengers:[{id:\"minecraft:zombie\",CustomName:\"\\\"Zombie\\\"\",CustomNameVisible:1}]}");
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	
}
