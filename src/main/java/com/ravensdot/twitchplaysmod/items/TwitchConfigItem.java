package com.ravensdot.twitchplaysmod.items;

import com.ravensdot.twitchplaysmod.twitch.TwitchHandler;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class TwitchConfigItem extends Item
{
	private TwitchHandler twitch;
	public TwitchConfigItem()
	{
		super(new Item.Properties().group(ItemGroup.MATERIALS));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
	{
		if (twitch != null) {
			twitch.stop();
		}
		twitch = new TwitchHandler();
		twitch.register();
		twitch.start();
		if (!worldIn.isRemote()) {
			
			//PacketHandler.sendCommand("/execute at @p run summon minecraft:area_effect_cloud ~2 ~1 ~2 {Passengers:[{id:\"minecraft:zombie\",CustomName:\"\\\"Zombie\\\"\",CustomNameVisible:1}]}");
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	
}
