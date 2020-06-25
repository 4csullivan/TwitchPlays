package com.ravensdot.twitchplaysmod.entities;

import com.ravensdot.twitchplaysmod.util.RegistryHandler;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class TwitchZombieEntity extends ZombieEntity
{
	public TwitchZombieEntity(EntityType<? extends ZombieEntity> type, World worldIn)
	{
		super(type, worldIn);
	}

	/*@Override
	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
			ILivingEntityData spawnDataIn, CompoundNBT dataTag)
	{
		// TODO Auto-generated method stub
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	@Override
	public AgeableEntity createChild(AgeableEntity ageable)
	{
		TwitchZombieEntity entity = new TwitchZombieEntity(RegistryHandler.TWITCH_ZOMBIE_ENTITY.get(), this.world);
		entity.onInitialSpawn(this.world, this.world.getDifficultyForLocation(new BlockPos(entity)), SpawnReason.BREEDING)
	}*/
}
