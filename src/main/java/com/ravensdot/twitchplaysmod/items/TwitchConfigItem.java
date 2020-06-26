package com.ravensdot.twitchplaysmod.items;

import com.ravensdot.twitchplaysmod.twitch.TwitchHandler;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class  TwitchConfigItem extends Item
{
	private TwitchHandler twitch;
	public TwitchConfigItem()
	{
		super(new Item.Properties().group(ItemGroup.MISC));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
	{
		if (!worldIn.isRemote) {
			if (twitch != null) {
				twitch.stop();
			}
			twitch = new TwitchHandler();
			twitch.register();
			twitch.start();
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	
}
