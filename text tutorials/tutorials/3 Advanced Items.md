## Intro
In this tutorial we will make a food that gives a potion effect, a furnace fuel and an item that teleports you
when right clicked.  

## Food

In your ItemInit class copy the code for a basic item and change the names. 

```java
public static final RegistryObject<Item> FRUIT = ITEMS.register("fruit",
            () -> new Item(new Item.Properties().group(ModItemGroup.instance)));
```

Then on the Item.Properties, call the food function. This takes in a Food created with a Food.Builder. 
The Food.Builder has a varitey of functions that let you set different properties of the food. For example, hunger and saturation 
let you set how much your food restores. When you're done call build to create the Food object.  

```java
new Item.Properties().group(ModItemGroup.instance)
                    .food(new Food.Builder().hunger(4).saturation(2).build()
```

To make your food grant a potion effect when eaten, call the effect function of your Food.Builder (before you call build). 
This takes a supplier for an EffectInstance which takes the effect you want to give, the duration (in tickes so 20 is one seconds), 
and the amplifier (0 is level I). The effect method also takes the likelyhood your efect will be applied (1.0F is always and 0F is never).
So this code will give fire resistance I for 10 seconds half the time. 

```java
.effect(() -> new EffectInstance(Effects.FIRE_RESISTANCE, 200, 0), 0.5F)
```

## Fuel

Again, start by copying the basic item code and change the names. This time change the supplier to from an Item to a class you will create.

```java
public static final RegistryObject<Item> FUEL = ITEMS.register("fuel",
            () -> new FuelItem(new Item.Properties().group(ModItemGroup.instance)));
```

Make a new package called items and in that create the class you refrenced when making the item (for me that's FuelItem). 
It should extend Item and use the default constructer.

```java
public class FuelItem extends Item {
    public FuelItem(Properties properties) {
        super(properties);
    }
}
```

Then override the getBurnTime method. This takes the itemStack being used as fuel and 
returns the burn time in ticks (items generally take 100 ticks to smelt). 

```java
@Override
public int getBurnTime(ItemStack itemStack) {
    return 3200;
}
```

Don't forget to go back to ItemInit and import this class.

## Right click behaviour 

Start the same way as for a fuel. In ItemInit, copy paste basic item, rename and change the class. Make a new class 
in your items package that extends item and uses the default constructer. 

```java
// ItemInit.java

public static final RegistryObject<Item> TELEPORT_STAFF = ITEMS.register("teleport_staff",
            () -> new TeleportStaff(new Item.Properties().group(ModItemGroup.instance)));

// items/TeleportStaff.java

public class TeleportStaff extends Item {
    public TeleportStaff(Properties properties) {
        super(properties);
    }
}
```

To do something when it's right clicked, override onItemRightClick. It takes in the world which lets you effect blocks and stuff, the player that used it and whether it was main or offhand. 

```java
@Override
public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
    return super.onItemRightClick(worldIn, playerIn, handIn);
}
```

So we can use an function built into the item class to do a raycast in the direction the player is looking and save the first thing it hits in a variable. Then we can set the player's position to that position. That will teleport the player forward.

```java
Vec3d lookPos = rayTrace(worldIn, playerIn, RayTraceContext.FluidMode.NONE).getHitVec();
playerIn.setPosition(lookPos.x, lookPos.y, lookPos.z);
```

If we want to give the players some help, we can add a tool tip when they hover over the item in their inventory.
So override addInformation and add to the tooltip. The StringTextComponent class is just a string that Minecraft can render.

```java
@Override
public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
    tooltip.add(new StringTextComponent("teleports you where you're looking"));

    super.addInformation(stack, worldIn, tooltip, flagIn);
}
```

If you want less clutter for the player, you can only show this when they're holding shift. So make a new package called util and a class called KeyboardHelper. Everything here will be static, we can get the game window so we can get user input. Then make 
some functions to check if they're holding shift, space, or control respectively. For shift and control we're checking both 
the left and right one. 

```java
public class KeyboardHelper {
    private static final long WINDOW = Minecraft.getInstance().getMainWindow().getHandle();

    @OnlyIn(Dist.CLIENT)
    public static boolean isHoldingShift() {
        return InputMappings.isKeyDown(WINDOW, GLFW.GLFW_KEY_LEFT_SHIFT) || InputMappings.isKeyDown(WINDOW, GLFW.GLFW_KEY_RIGHT_SHIFT);
    }

    @OnlyIn(Dist.CLIENT)
    public static boolean isHoldingControl() {
        return InputMappings.isKeyDown(WINDOW, GLFW.GLFW_KEY_LEFT_CONTROL) || InputMappings.isKeyDown(WINDOW, GLFW.GLFW_KEY_RIGHT_CONTROL);
    }

    @OnlyIn(Dist.CLIENT)
    public static boolean isHoldingSpace() {
        return InputMappings.isKeyDown(WINDOW, GLFW.GLFW_KEY_SPACE);
    }
}
```

Then you can change your addInformation method to check if the player is holding shift.  

```java
if (KeyboardHelper.isHoldingShift()){
    tooltip.add(new StringTextComponent("teleports you where you're looking"));
}
```

Currently the range of the teleport is just the players mining range. To fix that, we can make our own version 
of this rayTrace method. Copy it from the Item class (you can open a class by contorl clicking) and paste it into your class. 
Its a bunch of confusing math. But if you look at it, you can see its getting the position of the player's eyes, 
then the direction you're looking and multiplying that by this a float called d0. So if you change that, 
you change the length of the vector and thus the range. Then just make sure you're using this new function instead of the default one

```java
protected static RayTraceResult rayTrace(World worldIn, PlayerEntity player, RayTraceContext.FluidMode fluidMode) {
    double range = 10; // player.getAttribute(PlayerEntity.REACH_DISTANCE).getValue();;

    float f = player.rotationPitch;
    float f1 = player.rotationYaw;
    Vec3d vec3d = player.getEyePosition(1.0F);
    float f2 = MathHelper.cos(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
    float f3 = MathHelper.sin(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
    float f4 = -MathHelper.cos(-f * ((float)Math.PI / 180F));
    float f5 = MathHelper.sin(-f * ((float)Math.PI / 180F));
    float f6 = f3 * f4;
    float f7 = f2 * f4;
    Vec3d vec3d1 = vec3d.add((double)f6 * range, (double)f5 * range, (double)f7 * range);
    return worldIn.rayTraceBlocks(new RayTraceContext(vec3d, vec3d1, RayTraceContext.BlockMode.OUTLINE, fluidMode, player));
}
```

If you want to limit how often the item can be used you can add a cool down. The second argument 
is the time they have to wait before using it again in ticks (1/20 of a second). 
You can also make teleporting reset how far you've fallen so you can use the item to escape fall damage

```java
playerIn.getCooldownTracker().setCooldown(this, 60);

playerIn.fallDistance = 0F;
```

If you want it to use durability you can get the item stack being used, increase how damaged it is 
(becuase its stored as how much damage has been taken not how much durability remains). Note that setting the damage 
doesn't make it break when it hits 0. So we have to check if its zero and if so make the stack empty. 

```java
ItemStack stack = playerIn.getHeldItem(handIn);
stack.setDamage(stack.getDamage() + 1);
if (stack.getDamage() == 0) stack.setCount(0);
```

Then you can go back to ItemInit. Dont forget to import your special item class. If you did the durability thing you have to set the maxDamage to something in the Item.Properties. You can also do set the maxStackSize but giving it durability automatically makes that one.

```java
new Item.Properties().group(ModItemGroup.instance).maxDamage(50)
```

## Assets

You can setup the textures, models and lang file exactly the same as for basic items. Just remember to change the names of the file and any time you use the name of the item.

## Run the game

If you run the game the items show up in the creative tab and have textures. You can eat the food and sometimes get fire resistance. My fuel coal can be used in a furnace and smelt 16 items. Finaly the staff will teleport you and lose durability.