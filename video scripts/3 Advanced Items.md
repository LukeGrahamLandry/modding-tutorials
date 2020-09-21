## Description

In this tutorial we make a furnace fuel, 
a food that grants a potion effect 
and an item that teleports you when right clicked  

## Script

First lets make a food so go into ItemInit and just copy the code for a basic item
Make sure you change the name of the field and the string you pass in 
Then after the group function you can do dot food which takes in a new Food.Builder
So after that you can do dot hunger and give it an integer which is how much food it gives you
Then you can do dot saturation, same idea 
Now you can do dot build and that would be a basic food
But if you want it to give a potion effect like a golden apple does before the build 
You can do dot effect this takes in a supplier for a new EffectInstance which takes Effects dot whatever effect you want 
The time it lasts in ticks so 20 is one second then the amplifier 
The amplifier is one less than the level of the potion so here we get fire res one
Then outside the bracket we have a float that's how likely you are to get the effect
1 is always 0 is never. Rotten flesh uses this to only give you hunger sometimes 

Next we'll make a fuel for a furnace
Same thing as before copy paste an item, change the names
But this time instead of a new item we're going to make our own class
I'll call it FuelItem but the name doesn't matter 
Then we can make a new package called items and create that class
This will extend item, make sure you import the one from net dot Minecraft 
and we can have intellij add the default constructer 
Then we can add the get burn time function
It takes in the item stack used as fuel and returns how long the fuel will burn in ticks
So 32 hundred is twice a coal so it should do 1/4 a stack
Then we can go back to ItemInit and import the FuelItem class

So now an actually advanced item, lets make one that teleports you where you're looking
So same thing copy paste, rename and make a new class in the items package
Extend item, add the default constructor and then we can override this onItemRightClick function
So this is fired when ever you right click with the item in your hand
It takes in the world which lets you effect blocks and stuff, the player that used it and whether it was main or offhand
So we can use an function built into the item class to do a raycast in the direction the player is looking 
and save the first thing it hits in a variable
Then we can set the player's position to that position

If we want to give the players some help, we can add a tool tip when they hover over the item in their inventory 
So override addInformation and add to the tooltip 
This StringTextComponent class is just a string that Minecraft can render
Another thing you can do is only show this when they're holding shift
So make a new package called util and a class called KeyboardHelper
Everything here will be static, we can get the game window so we can get user input 
Then just add these functions to check if they're holding shift, space, or control respectively 
Of course for shift and control we're checking both the left and right one
Then import all the classes and we van go back to the Item we're making 
So just check if they're holding shift before adding the tool tip

As it is the range of the teleport is just the players mining range
To fix that we can make our own version of this raycast method
So just copy that, paste it into our class, and its a bunch of confusing math
But if you just look at it a bit, you can see its getting the position of the player's eyes, 
Then the direction you're looking and multiplying that by this d0 float
So that's the range which can be changed to whatever you want
Then just make sure you're using this new function instead of the default one

If you want to limit how often the item can be used you can add this cool down line
The second argument is the time they have to wait before using it again in twentieth's of a second
You can make teleporting reset how far you've fallen so you can use the item to escape fall damage
You can also make it use durability 
First get the item stack which is what stores the unique information about each item like how much durability is left
Then set how damaged it is to one more than it was before 
It stores how much damage its taken not how much durability is remaining 
Note that setting the damage doesn't make it break when it hits 0
So we have to check if its zero and if so set the size of the stack to 0 which sets it to empty
Then you can go back to ItemInit. Dont forget to import your special item class
If you did the durability thing you have to set the maxDamage to something
You can also do set the maxStackSize but giving it durability automatically makes that one

Now we're done, you can setup the textures, models and lang file exactly the same as for basic items
Just remember to change the names of the file and any time you use the name of the item 

Then we can run the game and test it out
Our items show up in our creative tab and have textures 
We can see I can eat this cherry and sometimes get fire resistance
This blue coal can be used in a furnace and smelt 16 items 
And this staff will teleport me and lose durability

Thanks for watching
