package com.ravensdot.twitchplaysmod.client.entity.render;

import com.ravensdot.twitchplaysmod.TwitchPlays;
import com.ravensdot.twitchplaysmod.client.entity.model.TwitchSilverfishEntityModel;
import com.ravensdot.twitchplaysmod.entities.TwitchSilverfishEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.entity.monster.SilverfishEntity;
import net.minecraft.util.ResourceLocation;

public class TwitchSilverfishEntityRender extends MobRenderer<TwitchSilverfishEntity, TwitchSilverfishEntityModel<TwitchSilverfishEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(TwitchPlays.MOD_ID, "textures/entity/twitch_silverfish_entity.png");

    public TwitchSilverfishEntityRender(EntityRendererManager rendererManagerIn)
    {
        super (rendererManagerIn, new TwitchSilverfishEntityModel(), 0.4f);
    }

    @Override
    public ResourceLocation getEntityTexture(TwitchSilverfishEntity entity) {
        return TEXTURE;
    }
}
