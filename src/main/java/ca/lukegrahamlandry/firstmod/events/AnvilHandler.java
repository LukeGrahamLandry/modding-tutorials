package ca.lukegrahamlandry.firstmod.events;

import ca.lukegrahamlandry.firstmod.init.ItemInit;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AnvilHandler {
    static class RepairData {
        protected Item repairableItem;
        protected Item materialItem;
        protected int amountToRepair;
        protected int levelCost;
        protected int materialCost;
        protected RepairData(Item repairableItemIn, Item materialItemIn, int amountToRepairIn, int levelCostIn, int materialCostIn){
            this.repairableItem = repairableItemIn;
            this.amountToRepair = amountToRepairIn;
            this.levelCost = levelCostIn;
            this.materialCost = materialCostIn;
            this.materialItem = materialItemIn;
        }
    }

    static class CombineData {
        public final Item left;
        public final Item right;
        public final ItemStack out;
        public final int levelCost;
        protected CombineData(Item left, Item right, ItemStack out, int levelCost){
            this.left = left;
            this.right = right;
            this.out = out;
            this.levelCost = levelCost;
        }
    }

    private static ArrayList<RepairData> repairRecipes = new ArrayList<>();
    private static ArrayList<CombineData> combineRecipes = new ArrayList<>();

    public static void initAnvilRecipes(){
        repairRecipes.add(new RepairData(ItemInit.TELEPORT_STAFF.get(), ItemInit.SMILE.get(), 20, 5, 1));

        combineRecipes.add(new CombineData(Items.BEEF, Items.COAL, new ItemStack(Items.COOKED_BEEF, 3), 10));
    }

    @SubscribeEvent
    public static void handleRepair(AnvilUpdateEvent event){
        repairRecipes.forEach((data) -> {
            if (event.getLeft().getItem() == data.repairableItem && event.getRight().getItem() == data.materialItem){
                ItemStack out = event.getLeft().copy();
                out.setDamageValue(out.getDamageValue() - data.amountToRepair);
                event.setOutput(out);
                event.setCost(data.levelCost);
                event.setMaterialCost(data.materialCost);
            }
        });

        combineRecipes.forEach((data) -> {
            if (event.getLeft().getItem() == data.left && event.getRight().getItem() == data.right){
                event.setOutput(data.out.copy());
                event.setCost(data.levelCost);
                event.setMaterialCost(1);
            }
        });
    }

}
