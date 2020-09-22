## Intro
In this tutorial we will make a new item with a name and a texture. We will also make a new creative tab to put items in.

## New Item
Make a new package (in src/main/java/com/name/modname) called init and in that make a new class
class called ItemInit. This is where we will register all our items. I recommend doing this with deffered registers. 
So first we have to get the item register so we can tell the game about our items.
```java
public static final DeferredRegister<Item> ITEMS = 
            new DeferredRegister<>(ForgeRegistries.ITEMS, FirstMod.MOD_ID);
```
Make sure you import all the classes you need. In intellij unimported classes will be written in red and you can 
press option enter on them to import them. Important to import the version of Item from net.minecraft.  
  
Then you can register your first item. It's going to be both static and final, the convention is to name it in all uppercase. 
Call the register function and the first argument is the name which must be all lowercase (this can be used to give it to yourself 
in game with /give Dev modid:item_name) and the second is a supplier for a new Item which takes in a new Item.Properties. Later, if you want to access the item from your code you can do ItemInit.ITEM_NAME.get()  

```java
public static final RegistryObject<Item> SMILE = ITEMS.register("smile",
            () -> new Item(new Item.Properties()));
```

## Creative Tab
If you want to make a new tab in the creative menu for your item to show up in, you can make an inner class 
that extends ItemGroup. It can should the default constructer and a function called createIcon that returns an 
ItemStack to use as the icon in the GUI. 

```java
public static class ModItemGroup extends ItemGroup {
    private ModItemGroup(int index, String label) {
        super(index, label);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(SMILE.get());
    }
}
```

Then (still in the inner class), you can make a static instance of this class to actually use. 
The index is just its place in the list of item groups (so the current length of the list) 
and the name can be used later to set the text displayed when the logo is hovered over (all lowercase). 

```java
public static final ModItemGroup instance = new ModItemGroup(ItemGroup.GROUPS.length, "firstmod");
```

Then you can tell the Item.Properties of your item to use your group. If you want it to show up in a Vanilla 
creative tab, just use ItemGroup.whatever (your IDE should let you auto fill it)

```java
new Item.Properties().group(ModItemGroup.instance)
```

## Assets

In your project folder go to src/main/resources and make a new folder called assets, in that make one called your modid 
and in that ones called lang, models, and textures. In the textures folder make a folder called items and
in that put a png image to use for your item in game.  

In the assets folder make a new file called en_us.json This is where we tell it how to display the names for our item 
and item group in game. Make sure to change firstmod to your modid and smile to your item name. 

```json
{
    "itemGroup.firstmod": "First Mod Items",

    "item.firstmod.smile": "Smiley Face"
} 
```

Then in the models folder make a file called item_name.json (call it whatever string you passed into the Item constructer). 
In the layer0 item, make sure to change firstmod to your modid and smile to the name of the image file you want to use. 

```json
{
    "parent": "item/generated",
    "textures": {
        "layer0": "firstmod:items/smile"
    }
} 
```

Your file structure should look like this:

```
src/main
    - resources/assets/modid/
        - lang/
            - en_us.json
        - models/
            - item_name.json
        - textures/
            - items/
                - item_name.png
    - java/com/name/modname/
        - ModName.java
        - init/
            - ItemInit.java
```

## Run the game

Now run the game. It will take a few minutes to load but eventually you should be able to 
see that your item shows up in your creative tab.  

If you have any problems with this, don't hesitate to ask me on [discord](https://discord.gg/VbZVnRd).