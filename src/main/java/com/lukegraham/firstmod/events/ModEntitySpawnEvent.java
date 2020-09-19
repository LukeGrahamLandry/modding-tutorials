package com.lukegraham.firstmod.events;

import com.lukegraham.firstmod.FirstMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=FirstMod.MOD_ID, bus=Mod.EventBusSubscriber.Bus.FORGE)
public class ModEntitySpawnEvent {
    // fires when an entity is added to the world
    @SubscribeEvent
    public static void onSpawn(EntityJoinWorldEvent event){
        // get the entity being added
        Entity mob = event.getEntity();
        // use event.getWorld(); to get the world if you want it for something

        // if its a zombie, give it gold boots
        if (mob instanceof ZombieEntity){
            mob.setItemStackToSlot(EquipmentSlotType.FEET, new ItemStack(Items.GOLDEN_BOOTS));
        }
    }
}
