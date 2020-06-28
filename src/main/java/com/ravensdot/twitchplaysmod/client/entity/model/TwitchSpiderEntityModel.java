package com.ravensdot.twitchplaysmod.client.entity.model;

// Made with Blockbench 3.5.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.ravensdot.twitchplaysmod.entities.TwitchSpiderEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class TwitchSpiderEntityModel<T extends TwitchSpiderEntity> extends EntityModel<T> {
    private final ModelRenderer head;
    private final ModelRenderer body0;
    private final ModelRenderer body1;
    private final ModelRenderer leg0;
    private final ModelRenderer leg1;
    private final ModelRenderer leg2;
    private final ModelRenderer leg3;
    private final ModelRenderer leg4;
    private final ModelRenderer leg5;
    private final ModelRenderer leg6;
    private final ModelRenderer leg7;

    public TwitchSpiderEntityModel() {
        textureWidth = 64;
        textureHeight = 32;

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 15.0F, -3.0F);
        head.setTextureOffset(32, 4).addBox(-4.0F, -4.0F, -8.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

        body0 = new ModelRenderer(this);
        body0.setRotationPoint(0.0F, 15.0F, 0.0F);
        body0.setTextureOffset(0, 0).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);

        body1 = new ModelRenderer(this);
        body1.setRotationPoint(0.0F, 15.0F, 9.0F);
        body1.setTextureOffset(0, 12).addBox(-5.0F, -4.0F, -6.0F, 10.0F, 8.0F, 12.0F, 0.0F, false);

        leg0 = new ModelRenderer(this);
        leg0.setRotationPoint(-4.0F, 15.0F, 2.0F);
        setRotationAngle(leg0, 0.0F, 0.7854F, -0.7854F);
        leg0.setTextureOffset(18, 0).addBox(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, 0.0F, false);

        leg1 = new ModelRenderer(this);
        leg1.setRotationPoint(4.0F, 15.0F, 2.0F);
        setRotationAngle(leg1, 0.0F, -0.7854F, 0.7854F);
        leg1.setTextureOffset(18, 0).addBox(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, 0.0F, false);

        leg2 = new ModelRenderer(this);
        leg2.setRotationPoint(-4.0F, 15.0F, 1.0F);
        setRotationAngle(leg2, 0.0F, 0.2618F, -0.6109F);
        leg2.setTextureOffset(18, 0).addBox(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, 0.0F, false);

        leg3 = new ModelRenderer(this);
        leg3.setRotationPoint(4.0F, 15.0F, 1.0F);
        setRotationAngle(leg3, 0.0F, -0.2618F, 0.6109F);
        leg3.setTextureOffset(18, 0).addBox(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, 0.0F, false);

        leg4 = new ModelRenderer(this);
        leg4.setRotationPoint(-4.0F, 15.0F, 0.0F);
        setRotationAngle(leg4, 0.0F, -0.2618F, -0.6109F);
        leg4.setTextureOffset(18, 0).addBox(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, 0.0F, false);

        leg5 = new ModelRenderer(this);
        leg5.setRotationPoint(4.0F, 15.0F, 0.0F);
        setRotationAngle(leg5, 0.0F, 0.2618F, 0.6109F);
        leg5.setTextureOffset(18, 0).addBox(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, 0.0F, false);

        leg6 = new ModelRenderer(this);
        leg6.setRotationPoint(-4.0F, 15.0F, -1.0F);
        setRotationAngle(leg6, 0.0F, -0.7854F, -0.7854F);
        leg6.setTextureOffset(18, 0).addBox(-15.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, 0.0F, false);

        leg7 = new ModelRenderer(this);
        leg7.setRotationPoint(4.0F, 15.0F, -1.0F);
        setRotationAngle(leg7, 0.0F, 0.7854F, 0.7854F);
        leg7.setTextureOffset(18, 0).addBox(-1.0F, -1.0F, -1.0F, 16.0F, 2.0F, 2.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
        this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
        this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
        float f = ((float)Math.PI / 4F);
        this.leg0.rotateAngleZ = (-(float)Math.PI / 4F);
        this.leg1.rotateAngleZ = ((float)Math.PI / 4F);
        this.leg2.rotateAngleZ = -0.58119464F;
        this.leg3.rotateAngleZ = 0.58119464F;
        this.leg4.rotateAngleZ = -0.58119464F;
        this.leg5.rotateAngleZ = 0.58119464F;
        this.leg6.rotateAngleZ = (-(float)Math.PI / 4F);
        this.leg7.rotateAngleZ = ((float)Math.PI / 4F);
        float f1 = -0.0F;
        float f2 = ((float)Math.PI / 8F);
        this.leg0.rotateAngleY = ((float)Math.PI / 4F);
        this.leg1.rotateAngleY = (-(float)Math.PI / 4F);
        this.leg2.rotateAngleY = ((float)Math.PI / 8F);
        this.leg3.rotateAngleY = (-(float)Math.PI / 8F);
        this.leg4.rotateAngleY = (-(float)Math.PI / 8F);
        this.leg5.rotateAngleY = ((float)Math.PI / 8F);
        this.leg6.rotateAngleY = (-(float)Math.PI / 4F);
        this.leg7.rotateAngleY = ((float)Math.PI / 4F);
        float f3 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + 0.0F) * 0.4F) * limbSwingAmount;
        float f4 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + (float)Math.PI) * 0.4F) * limbSwingAmount;
        float f5 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + ((float)Math.PI / 2F)) * 0.4F) * limbSwingAmount;
        float f6 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + ((float)Math.PI * 1.5F)) * 0.4F) * limbSwingAmount;
        float f7 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 0.0F) * 0.4F) * limbSwingAmount;
        float f8 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + (float)Math.PI) * 0.4F) * limbSwingAmount;
        float f9 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + ((float)Math.PI / 2F)) * 0.4F) * limbSwingAmount;
        float f10 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + ((float)Math.PI * 1.5F)) * 0.4F) * limbSwingAmount;
        this.leg0.rotateAngleY += f3;
        this.leg1.rotateAngleY += -f3;
        this.leg2.rotateAngleY += f4;
        this.leg3.rotateAngleY += -f4;
        this.leg4.rotateAngleY += f5;
        this.leg5.rotateAngleY += -f5;
        this.leg6.rotateAngleY += f6;
        this.leg7.rotateAngleY += -f6;
        this.leg0.rotateAngleZ += f7;
        this.leg1.rotateAngleZ += -f7;
        this.leg2.rotateAngleZ += f8;
        this.leg3.rotateAngleZ += -f8;
        this.leg4.rotateAngleZ += f9;
        this.leg5.rotateAngleZ += -f9;
        this.leg6.rotateAngleZ += f10;
        this.leg7.rotateAngleZ += -f10;
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        head.render(matrixStack, buffer, packedLight, packedOverlay);
        body0.render(matrixStack, buffer, packedLight, packedOverlay);
        body1.render(matrixStack, buffer, packedLight, packedOverlay);
        leg0.render(matrixStack, buffer, packedLight, packedOverlay);
        leg1.render(matrixStack, buffer, packedLight, packedOverlay);
        leg2.render(matrixStack, buffer, packedLight, packedOverlay);
        leg3.render(matrixStack, buffer, packedLight, packedOverlay);
        leg4.render(matrixStack, buffer, packedLight, packedOverlay);
        leg5.render(matrixStack, buffer, packedLight, packedOverlay);
        leg6.render(matrixStack, buffer, packedLight, packedOverlay);
        leg7.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}