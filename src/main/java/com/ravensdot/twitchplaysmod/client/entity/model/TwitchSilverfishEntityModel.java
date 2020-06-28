package com.ravensdot.twitchplaysmod.client.entity.model;

// Made with Blockbench 3.5.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.ravensdot.twitchplaysmod.entities.TwitchSilverfishEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.monster.SilverfishEntity;
import net.minecraft.util.math.MathHelper;

public class TwitchSilverfishEntityModel<T extends TwitchSilverfishEntity> extends EntityModel<T> {
    private final ModelRenderer bodyPart_2;
    private final ModelRenderer bodyPart_0;
    private final ModelRenderer bodyPart_1;
    private final ModelRenderer bodyLayer_2;
    private final ModelRenderer bodyPart_3;
    private final ModelRenderer bodyPart_4;
    private final ModelRenderer bodyLayer_1;
    private final ModelRenderer bodyPart_5;
    private final ModelRenderer bodyPart_6;
    private final ModelRenderer bodyLayer_0;

    public TwitchSilverfishEntityModel() {
        textureWidth = 64;
        textureHeight = 32;

        bodyPart_2 = new ModelRenderer(this);
        bodyPart_2.setRotationPoint(0.0F, 20.0F, 1.0F);
        bodyPart_2.setTextureOffset(0, 9).addBox(-3.0F, 0.0F, -1.5F, 6.0F, 4.0F, 3.0F, 0.0F, false);

        bodyPart_0 = new ModelRenderer(this);
        bodyPart_0.setRotationPoint(0.0F, 2.0F, -4.5F);
        bodyPart_2.addChild(bodyPart_0);
        bodyPart_0.setTextureOffset(0, 0).addBox(-1.5F, 0.0F, -1.0F, 3.0F, 2.0F, 2.0F, 0.0F, false);

        bodyPart_1 = new ModelRenderer(this);
        bodyPart_1.setRotationPoint(0.0F, 1.0F, -2.5F);
        bodyPart_2.addChild(bodyPart_1);
        bodyPart_1.setTextureOffset(0, 4).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 3.0F, 2.0F, 0.0F, false);

        bodyLayer_2 = new ModelRenderer(this);
        bodyLayer_2.setRotationPoint(0.0F, -2.0F, 0.0F);
        bodyPart_1.addChild(bodyLayer_2);
        bodyLayer_2.setTextureOffset(20, 18).addBox(-3.0F, 0.0F, -1.5F, 6.0F, 5.0F, 2.0F, 0.0F, false);

        bodyPart_3 = new ModelRenderer(this);
        bodyPart_3.setRotationPoint(0.0F, 1.0F, 3.0F);
        bodyPart_2.addChild(bodyPart_3);
        bodyPart_3.setTextureOffset(0, 16).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);

        bodyPart_4 = new ModelRenderer(this);
        bodyPart_4.setRotationPoint(0.0F, 2.0F, 6.0F);
        bodyPart_2.addChild(bodyPart_4);
        bodyPart_4.setTextureOffset(0, 22).addBox(-1.0F, 0.0F, -1.5F, 2.0F, 2.0F, 3.0F, 0.0F, false);

        bodyLayer_1 = new ModelRenderer(this);
        bodyLayer_1.setRotationPoint(0.0F, -2.0F, 0.0F);
        bodyPart_4.addChild(bodyLayer_1);
        bodyLayer_1.setTextureOffset(20, 11).addBox(-3.0F, 0.0F, -1.5F, 6.0F, 4.0F, 3.0F, 0.0F, false);

        bodyPart_5 = new ModelRenderer(this);
        bodyPart_5.setRotationPoint(0.0F, 3.0F, 8.5F);
        bodyPart_2.addChild(bodyPart_5);
        bodyPart_5.setTextureOffset(11, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

        bodyPart_6 = new ModelRenderer(this);
        bodyPart_6.setRotationPoint(0.0F, 3.0F, 10.5F);
        bodyPart_2.addChild(bodyPart_6);
        bodyPart_6.setTextureOffset(13, 4).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);

        bodyLayer_0 = new ModelRenderer(this);
        bodyLayer_0.setRotationPoint(0.0F, -4.0F, 0.0F);
        bodyPart_2.addChild(bodyLayer_0);
        bodyLayer_0.setTextureOffset(20, 0).addBox(-5.0F, 0.0F, -1.5F, 10.0F, 8.0F, 3.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
        bodyPart_0.rotateAngleY = MathHelper.cos(ageInTicks * 0.9F + (float)0 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (float)(1 + Math.abs(0 - 2));
        bodyPart_0.rotateAngleX = MathHelper.cos(ageInTicks * 0.9F + (float)0 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (float)(Math.abs(0 - 2));
        bodyPart_1.rotateAngleY = MathHelper.cos(ageInTicks * 0.9F + (float)1 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (float)(1 + Math.abs(1 - 2));
        bodyPart_1.rotateAngleX = MathHelper.cos(ageInTicks * 0.9F + (float)1 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (float)(Math.abs(1 - 2));
        bodyPart_2.rotateAngleY = MathHelper.cos(ageInTicks * 0.9F + (float)2 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (float)(1 + Math.abs(2 - 2));
        bodyPart_2.rotateAngleX = MathHelper.cos(ageInTicks * 0.9F + (float)2 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (float)(Math.abs(2 - 2));
        bodyPart_3.rotateAngleY = MathHelper.cos(ageInTicks * 0.9F + (float)3 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (float)(1 + Math.abs(3 - 2));
        bodyPart_3.rotateAngleX = MathHelper.cos(ageInTicks * 0.9F + (float)3 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (float)(Math.abs(3 - 2));
        bodyPart_4.rotateAngleY = MathHelper.cos(ageInTicks * 0.9F + (float)4 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (float)(1 + Math.abs(4 - 2));
        bodyPart_4.rotateAngleX = MathHelper.cos(ageInTicks * 0.9F + (float)4 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (float)(Math.abs(4 - 2));
        bodyPart_5.rotateAngleY = MathHelper.cos(ageInTicks * 0.9F + (float)5 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (float)(1 + Math.abs(5 - 2));
        bodyPart_5.rotateAngleX = MathHelper.cos(ageInTicks * 0.9F + (float)5 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (float)(Math.abs(5 - 2));
        bodyPart_6.rotateAngleY = MathHelper.cos(ageInTicks * 0.9F + (float)6 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (float)(1 + Math.abs(6 - 2));
        bodyPart_6.rotateAngleX = MathHelper.cos(ageInTicks * 0.9F + (float)6 * 0.15F * (float)Math.PI) * (float)Math.PI * 0.05F * (float)(Math.abs(6 - 2));

        this.bodyLayer_0.rotateAngleY = this.bodyPart_2.rotateAngleY;
        this.bodyLayer_1.rotateAngleY = this.bodyPart_4.rotateAngleY;
        this.bodyLayer_1.rotationPointX = this.bodyPart_4.rotationPointX;
        this.bodyLayer_2.rotateAngleY = this.bodyPart_1.rotateAngleY;
        this.bodyLayer_2.rotationPointX = this.bodyPart_1.rotationPointX;

    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        bodyPart_2.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}