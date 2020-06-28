package com.ravensdot.twitchplaysmod.init;

import com.ravensdot.twitchplaysmod.TwitchPlays;
import com.ravensdot.twitchplaysmod.entities.TwitchSilverfishEntity;
import com.ravensdot.twitchplaysmod.entities.TwitchSpiderEntity;
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
                        .size(0.6f, 1.95f)
                        .build("twitch_zombie"));

    public static final RegistryObject<EntityType<TwitchSilverfishEntity>> TWITCH_SILVERFISH_ENTITY = ENTITY_TYPES
            .register("twitch_silverfish",
                    () -> EntityType.Builder
                            .create(TwitchSilverfishEntity::new, EntityClassification.MONSTER)
                            .size(0.4f, 0.3f)
                            .build("twitch_silverfish"));

    public static final RegistryObject<EntityType<TwitchSpiderEntity>> TWITCH_SPIDER_ENTITY = ENTITY_TYPES
            .register("twitch_spider",
                    () -> EntityType.Builder
                            .create(TwitchSpiderEntity::new, EntityClassification.MONSTER)
                            .size(1.4f, 0.9f)
                            .build("twitch_spider"));
}
