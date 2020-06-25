package com.ravensdot.twitchplaysmod.client.entity.render;

import com.ravensdot.twitchplaysmod.TwitchPlays;
import com.ravensdot.twitchplaysmod.client.entity.model.TwitchZombieEntityModel;
import com.ravensdot.twitchplaysmod.entities.TwitchZombieEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class TwitchZombieEntityRender extends MobRenderer<TwitchZombieEntity, TwitchZombieEntityModel<TwitchZombieEntity>> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(TwitchPlays.MOD_ID,
            "textures/entity/twitch_zombie_entity.png");

    public TwitchZombieEntityRender(EntityRendererManager rendererManagerIn)
    {
        super(rendererManagerIn, new TwitchZombieEntityModel(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(TwitchZombieEntity entity) {
        return TEXTURE;
    }
}
