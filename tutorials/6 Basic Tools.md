## Description

In this tutorial we make a basic set of tools

## Script

In your util package make a new enum called ModItemTier
Which extends IItemTier 
You can steal most of the code from the Vanilla ItemTier class
In Intellij press shift twice, check the most for include non-project items 
And search 

So make a new instance on this enum 
Which is where you set the stats for your tools 
Name it something clever and set the base 
harvest level, durability, mining speed, damage 
and enchantablity (the higher this is, the better enchantments you get)
Finally give it a supplier for a ingredient that tells it which items can repair your tools in an anvil

If you want to have more tiers, just put a comma after this and make another one 
With a semicolon after only the last one

In ItemInit you can actually make the items 
So its similar to normal but the supplier is for a new SwordItem
In addition to the item properties, this takes in the item tier,
an attack damage which is added to the one in your item tier 
And an attack speed which controls how fast you can swing the tool 

The rest of the set is the same 
Just change the SwordItem to PickaxeItem, ShovelItem or AxeItem
And for the hoe you don't give it an attack damage

The item models are the same as normal
Except the parent is item slash handheld instead of generated 
Make sure you've got textures in the right folder
And do the lang file exactly the same as the rest of your items 

Now we can run the game and see that they mine the things they should 
And the right click effects on hoe, shovel, and axe work properly
