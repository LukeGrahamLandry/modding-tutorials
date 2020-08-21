## Description

In this tutorial we make a simple item with deferred registers, 
give it a texture, and put it in a custom creative tab.

Ask Me Questions: https://discord.gg/VbZVnRd
The Code: https://github.com/LukeGrahamLandry/forge-modding-tutorial
Donate: https://www.patreon.com/LukeGrahamLandry

Online Pixel Art Editor: https://www.piskelapp.com/p/create  
Minecraft Assets: https://github.com/LukeGrahamLandry/minecraft-assets  
^ Useful if you want to base your textures on Vanilla ones  

## Script

It a good idea to set a constant for your mod id cause you're gonna write it out a lot

Then in the same package as your main class make a new package called init 
And then a class called ItemInit 
I'm gonna use deferred registers cause I think that's the best way
So this first line sets up the register that lets you tell the game about your items
You have to import these classes in red. IntelliJ lets you click them then hit option enter

Next we actually register the item 
You need name for it. This string here can't have capitals or spaces
And then a supplier for a new instance of the item class
If you want to reference the item from code later its just ItemInit dot the name of the field so SMILE dot get
Then if we want we can make a new tab in the creative menu for all the items from our mod
So we make a new class that extends ItemGroup
Use this constructor. I think index will be its place in the list in game and label lets you set what its called in the lang file which we'll get to later
Then you need this createIcon function to pick what will show up in the game
You can use an item from Vanilla instead just have Items. Something instead of the smile dot get
Then make an instance of your item group that we can use

So back in out item we can do dot group and pass in ModItemGroup dot instance
If you want it to show up in a vanilla creative tab just do ItemGroup dot and pick from the list it gives you

Now go to your the folder for this project 
Go to src main resources and make a new folder called assets
In that a new folder called your mod id then one called lang, models and textures
In models make one called item and in textures one called items
In this folder put a png of what you want the item to look like in game
Minecraft textures are 16 by 16 but you could do 32 or 64 or whatever

Then go back to IntelliJ and make a new file called en underscore us do json in the lang folder
In that we make some squiggly brackets and then we can set how the name of our item group and item shows up in game
Make sure to change the item dot first mod to item dot your mod id
Important not to forget this comma

Next make a file called smile or whatever you called your item dot json in the models/item folder
This file tells the game how to render your item
Just use this json and the only thing you need to change is the mod id and the path to your image file

Now we're done and we can run the game
It'll take a few minutes to load
If there's any errors you can't figure out, there's a link in the description to ask me on discord

In the game we can see our tab shows up
And our item acts as you'd expect

Thanks for watching