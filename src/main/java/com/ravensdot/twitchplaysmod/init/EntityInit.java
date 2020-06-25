package com.ravensdot.twitchplaysmod.init;

import com.ravensdot.twitchplaysmod.TwitchPlays;
import com.ravensdot.twitchplaysmod.entities.TwitchZombieEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityInit {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES, TwitchPlays.MOD_ID);
    public static final RegistryObject<EntityType<TwitchZombieEntity>> TWITCH_ZOMBIE_ENTITY = ENTITY_TYPES
            .register("twitch_zombie",
                    () -> EntityType.Builder
                        .create(TwitchZombieEntity::new, EntityClassification.MONSTER)
                        .size(0.6f, 1.0f)
                        .build("twitch_zombie"));
}
