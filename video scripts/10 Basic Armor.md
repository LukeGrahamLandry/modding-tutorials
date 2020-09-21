 ## Description

In this tutorial we make a set of armour

## Script

In your util package make a new enum called ModArmorMaterial which implements IArmorMaterial
Similar to how we did item tier, you can open Vanilla's ArmorMaterial class 
and copy most of it over
Then make an instance of this enum
The first argument is your mod id colon then a name for the material (all lowercase, no spaces)
Then for all these numbers you can compare it to the Vanilla ones in ArmorMaterial 
First is the durability factor which is multiplied by the values in the max damage array to calculate the durability of each piece
Then there's an array of integers which represent how much protection each piece gives
First is helmet then leggings then chest plate then boots 
Then you have the enchantability 
Then the sound effect to play when you equip it
Then the toughness
Then an ingredient that tells it what items can repair it in an anvil

Then in your item init class start the same as a normal item 
Change the names and the class in the supplier to an armour item
Then in addition to the item properties you need the armour material we just made
And which armour slot type it goes in
So for helmet that's equipment slot type dot head
Then the other pieces are the same just change the names and the equipment slot type to chest, legs or feet

In your assets textures folder make a new folder called models and in that one called armor (spelled the American way)
In that you put net of how to render it on the player 
So there are two images for this. There's armour name underscore layer underscore 1 and armour name underscore layer underscore 2
I'll put templates for these in the description cause they're kinda weird 

Then you need your item textures as normal 
The item model json are the same as normal and so is the lang file

Then if we run the game 
We can put on the armour as expected and it gives protection 
