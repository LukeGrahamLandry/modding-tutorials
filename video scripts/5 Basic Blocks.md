## Description

In this tutorial we make a simple block

## Script

In the init package make a new class called BlockInit 
Then copy the code for making a basic Item
Change everywhere it says item to block
Important that when you import the block class you get the one from net.minecraft

Change the argument for this supplier to Block.Properties.create
This takes in a Material. which controls a few things like whether its flammable
Just pick which ever is most like what your block is supposed to be

Do dot hardness and resistance
The first number is how long it takes to mine
And the second is how resistance to explosions it is 
12 hundred is the same as obsidian

You can set the harvest level you need for it to drop something 0 is wood, 3 is diamond 
And you can set the tool you need

You can set how much light to give off if you want it to be a light source
There's a bunch more you can do like slipperiness, speed factor, jump factor 

Make this class an event bus subscriber
And make a new function with the Subscribe event anotation that fires when items are registered 
This is where we'll make the items for the blocks 
You can do this manually but this is easier 

This line gets the item registry 
Then we can go through all the blocks we've made
We want to put them through the get function so we can use the block instead of the registry object 
Then for each of them we make a new Item Properties object that puts them in our creative tab
Create a new block item with the same registry name as our block
And register it 

Finally call the block init class from your main class same as for items 

Now lets give it a texture

So go to src/main/resources/assets/mod id
In models, make a new folder called block
In textures one called blocks
And one called block states in your mod id folder

Then go out to 
resources/data/mod id/loot_tables (if you dont have that folder, make it)
And make a folder called blocks 

Then in block states make a file called whatever you called your block dot json
Since this is a simple block we just need one variant  

The in models/block make block name dot json
Which partners off block/cube all
And references one texture 
This is the file you'd change if you want different sides to look different like a grass block

In your lang file you can add a line giving this a name in game
Same as item but block dot mod id dot name

In models/item make a new json file that just parents off your block model

Then in loot tables / blocks we can create block name dot json
This will make it drop itself but you could make it drop any item

In src/maini/resources.assets/mod id/textures/blocks put an image for your block
Make sure its called the same thing as you put in your block model

Now we can run the game
The block shows up in our creative tab
We can place it and break it with an iron pickaxe
