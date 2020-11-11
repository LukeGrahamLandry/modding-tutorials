package com.lukegraham.firstmod.client.render;

import com.lukegraham.firstmod.FirstMod;
import com.lukegraham.firstmod.client.model.KillerSnailModel;
import com.lukegraham.firstmod.entities.KillerSnailEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class KillerSnailRenderer extends MobRenderer<KillerSnailEntity, KillerSnailModel> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(FirstMod.MOD_ID, "textures/entity/killer_snail.png");

    public KillerSnailRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new KillerSnailModel(), 0.4F);
    }

    @Override
    public ResourceLocation getEntityTexture(KillerSnailEntity entity) {
        return TEXTURE;
    }
}
