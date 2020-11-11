package com.lukegraham.firstmod.util;

import com.lukegraham.firstmod.FirstMod;
import com.lukegraham.firstmod.client.render.KillerSnailRenderer;
import com.lukegraham.firstmod.init.EntityInit;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid= FirstMod.MOD_ID, bus=Mod.EventBusSubscriber.Bus.MOD, value=Dist.CLIENT)
public class EntitySetup {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event){
        FirstMod.LOGGER.debug("FMLClientSetupEvent");
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.KILLER_SNAIL.get(), KillerSnailRenderer::new);
    }
}
