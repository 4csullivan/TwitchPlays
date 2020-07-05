package com.ravensdot.twitchplaysmod.items;

import com.ravensdot.twitchplaysmod.client.gui.TwitchConfigScreen;
import com.ravensdot.twitchplaysmod.config.TwitchConfig;
import com.ravensdot.twitchplaysmod.twitch.TwitchHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

public class  TwitchConfigItem extends Item
{
	private TwitchHandler twitch;
	private TwitchConfigScreen configScreen;

	public TwitchConfigItem()
	{
		super(new Item.Properties().group(ItemGroup.MISC));
		configScreen = new TwitchConfigScreen(new StringTextComponent("title"));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
	{
		if (!worldIn.isRemote) {
			Minecraft.getInstance().displayGuiScreen(configScreen);
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	

}
