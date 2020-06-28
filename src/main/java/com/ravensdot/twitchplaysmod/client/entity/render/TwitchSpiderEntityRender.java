package com.ravensdot.twitchplaysmod.client.entity.render;

import com.ravensdot.twitchplaysmod.TwitchPlays;
import com.ravensdot.twitchplaysmod.client.entity.model.TwitchSpiderEntityModel;
import com.ravensdot.twitchplaysmod.entities.TwitchSpiderEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class TwitchSpiderEntityRender extends MobRenderer<TwitchSpiderEntity, TwitchSpiderEntityModel<TwitchSpiderEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(TwitchPlays.MOD_ID,
            "textures/entity/twitch_spider_entity.png");

    public TwitchSpiderEntityRender(EntityRendererManager rendererManagerIn)
    {
        super(rendererManagerIn, new TwitchSpiderEntityModel(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(TwitchSpiderEntity entity) {
        return TEXTURE;
    }
}
