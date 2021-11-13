package ca.lukegrahamlandry.firstmod.client.render;

import ca.lukegrahamlandry.firstmod.FirstModMain;
import ca.lukegrahamlandry.firstmod.entity.ExplosiveArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class ExplosiveArrowRenderer extends ArrowRenderer<ExplosiveArrowEntity> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(FirstModMain.MOD_ID, "textures/entity/explosive_arrow.png");

    public ExplosiveArrowRenderer(EntityRendererProvider.Context manager) {
        super(manager);
    }

    public ResourceLocation getTextureLocation(ExplosiveArrowEntity arrow) {
        return TEXTURE;
    }
}