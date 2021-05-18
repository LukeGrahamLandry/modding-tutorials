package ca.lukegrahamlandry.firstmod.events;

import ca.lukegrahamlandry.firstmod.FirstModMain;
import ca.lukegrahamlandry.firstmod.util.IDamageHandlingArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ArmorHandlers {
    @SubscribeEvent
    public static void armorAttackHandler(LivingDamageEvent event){
        for (ItemStack armor : event.getEntityLiving().getArmorSlots()){
            if (armor.getItem() instanceof IDamageHandlingArmor){
                float newDamage = ((IDamageHandlingArmor)armor.getItem()).onDamaged(event.getEntityLiving(), armor.getEquipmentSlot(), event.getSource(), event.getAmount());
                event.setAmount(newDamage);
            }
        }
    }
}
