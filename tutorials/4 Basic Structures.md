## Description

In this tutorial we make a structure that generates in the world (with a randomly filled chest).

Ask Me Questions: https://discord.gg/VbZVnRd
The Code: https://github.com/LukeGrahamLandry/forge-modding-tutorial
Donate: https://www.patreon.com/LukeGrahamLandry

Structure code based on this repository: https://github.com/TelepathicGrunt/StructureTutorialMod

## Script

Go into a world and slash give yourself a structure block
Then build the structure
You can have chest and just put items in it or add a structure block set to data
And do the items from code. Remember the tag you set here
If you dont want it to overwrite blocks when it spawns, 
Slash give yourself a structure void and place them where ever you want to allow default chunk generation

Put down a structure block in one corner set it to corner mode and name it something
Then put one in the opposite corner on save mode and name it the same thing
Click detect to show the outline
You can also make it show invisible blocks. Blue for air and red for void that will let itself be overwritten
Then go back to the structure block, click save and exit the world

Go to your main folder, run / saves / world name / generated / structures 
And find the nbt file called whatever you named the structure this stores all the information about it
Move that into src / main / resources / data / mod id / structures you might have to make some of those folders

In your mod package make a new package called world dot structures
In that package make a new class called something structure 
The code for this is on my GitHub in the description but I'll try to explain it

This function decides which chunk the structure will spawn in 
It uses vanilla's default algorithm based on min and max distance
The structures can't be closer than min chunks or farther than max chunks

This seed modifier makes sure that even if two structures have the same spawn location algorithm, 
they wont be in the same place. Make it a unique large number

This function does all the checking to see if the structure can spawn 
It calls the previous get position function and checks the biome

This init function decides where in the church it will spawn and calls the start method from the Pieces class we're about to make

Next make a new class called structure name Pieces 
Again the code's linked in the description 
This first field is just the location of the structure's nbt file 
If you want you can have multiple of these and connect them when you spawn the structure
This is a map of how all the different pieces are offset from where the structure starts spawning
For example if you have one piece that is the walls and one that's the roof, 
you might have the walls one up from the ground and the roof 5 up to be above the walls
I tend to just do the heights here and do x z offsets in the next function

In the start function we assemble the pieces of the structure
You can offset things here aswell
So I have my piece added twice with the second time being shifted on the x axis
But if you have multiple pieces this is where you'd put them together

This handleDataMArker function lets you do something where ever you put a data structure block in your structure
So first we check what you called it 
And if it was chest (as ours was) we replace the structure block with air
And set the chest beinith it to have a loot table 
This lets us make the loot different for each structure but if you want you could set the items manually
Of course you can have structure blocks with different data tags that behave differently
 
So if we go to data src mod id and make a new folder called loot_tables and one in that called chest
We can add a json file for our loot table. 
I'm sure there are other tutorials on how to make loot tables or look at the vanilla ones

In your init package make a new class called FeatureInit
Again, code in the description
First we make static instances of our structure and piece classes for ease of use
Then we wait for the feature register event and register our feature
Then in this add to biomes class we add the structure and feature to all biomes.
You can add it to only some by putting those two lines in an if statement 
Like if biome equals Biomes dot forest add them
Then in your main mod class 
Call the add to biomes function from the setup function

Finally, run the game, make a new world
And use the locate command to find your structure 




