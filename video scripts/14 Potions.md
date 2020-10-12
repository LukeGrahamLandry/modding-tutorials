## Description

In this tutorial we make an effect and potions with recipes in the brewing stand 

## Script

Make a new package called effects
In that make a class called explosive effect which extends effect with the default constructer 
Change type in to effect type dot harmful and the liquid colour is a decimal colour to tint the potion bottle. So you can mess with these colours on mathisfun dot com, I'll make mine brown and just copy over this number. 
Then you can remove the parameters for the constructor 

Override preform effect. This is where your potion will do things. So I'll make it add an explosion where the entity is. So get the world, create explosion, pass in null, position,  explosion radius, which I'll base on the level of the potion, and then the explosion type


Then override isReady which tells it when to call preform effect. So it gives you the amplifier and the duration remaining in ticks. So if we want to explode every 5 seconds I'll check if duration modulo 100 is 0. 

Let's go back and make the explosion radius higher to make it more dramatic
Also make sure the constructor is public 

Then in your init package make a new class called potion init. This will be similar to your item init. You set up a deferred register in the same way but this time with the effect type and the Potions registry 

Then copy it and change Effect to potion and The registry to potion types 

Now we can register our effect so make an effect registry object of your explosive effect class 

We can make a new registry object of type potion. This will have a supplier for a new potion which takes a new effect instance of our effect. So this is the effect instance the potion we brew will give us so set whatever duration and amplifier your want. (Amplifier is the level of the potion minus one)

Then if you want to be able to improve your potions with flowstone and red stone like vanilla ones, you can copy this potion to make a long version with a higher duration and a strong version with a higher amplifier but a lower duration. Dont forget to change the name string you pass in 

Make an inner class that implements IBrewingRecipe 
Give it private final instance variables of type potion, item and ItemStack

Make a constructer where you take in a potion for the potion in the input bottles. For example the vanilla potion recipes make this an awkward potion. 
Then an item which is the item that goes in the slot where nether wart would go 
Then you take the potion to output which will be one of the ones we delighted above 

So set the bottleInput to that first potion 
Set the item input to the item 
For the output item stack we'll use the potion utils class and call add potion to item stack. Pass in a new item stack of the potion item and the output potion that was passed in 

Implement the other methods this needs. For is input we check if the potion on the item stack is the same as the bottle input we expect 
For is ingredient we check if the item stacks's item is the same as the input item expect 

For get output we call is input and is ingredient and if both are true we return. A copy of our item output stack otherwise item stack dot empty
Its important that we copy it because item stacks are mutable, it you dont to that things will break 

Then make a public static void function called add potion recipes 
Here we call brewing recipe registry dot add recipe and pass in a new instance of the better brewing recipe class we just make 
So I'll say we can brew an awkward potion an a int together to get by basic explosion potion. Then I'll copy this and make it so I can improve my explosive potion with glow stone and red stone like vanilla ones 
And I made the name of the function capital by accident dont do that 
And it should be glowstone dust 

In your main mod class register your effects and potions like you to for items 
Then in the setup function call the add potion recipes function 

The lang file is a bit of a pain 
Fist set effect dot mod id dot name s for me explosive to something 
Then you have to do it for all the items 
So normal potion, splash potion, lingering potion, and tipped arrows for your normal potion as well as the long version and the strong version

In your texture folder make a new folder called mob underscore effect 
I want the icon for the potion to be a int so I'll go download an image and put it in that folder. I also have to edit out the background cause it should be transparent 

Now we're done but I just want to demonstrate that you can use your effect from code 
So if you have an entity you can call add potion effect like normal and just pass in an effect instance based on your effect 

If you want something to behave differently if they have the effect, 
You can get the active effect instance of your effect
And if it isn't null, they have the effect 

If I run the game we can see that I can brew explosive potions with awkward potions and int in. Normal brewing stand and that if I splash myself with a potion, it explode every once and a while. In survival mode I'll die daily quickly but they won't hurt the train cause of the explosion mod I used 
