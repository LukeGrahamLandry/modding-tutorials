## Description

In this tutorial we make new ores generate in the nether and the end

Ask Me Questions: https://discord.gg/VbZVnRd
The Code: https://github.com/LukeGrahamLandry/forge-modding-tutorial
Donate: https://www.patreon.com/LukeGrahamLandry

## Script

In your world package make a package called gen 
In that make a class called OreGen

Now just to avoid repeating a bunch of code 
Lets make a static function called generate
So this will add an ore to a biome as a feature 
So when chunks are generated in that biome it will replace some of the normal generation

So this will take in the biome to add the feature to, 
A filler block type which is what blocks it an replace so for example 
Iron ore can replace stone and nether quartz replaces nether rack 
The block for your ore, how common you want it to be, the minimum y level to generate it,
The max y level and the maximum size for a vain 

So first we make this configured placement which uses the commonness, min and max to decide where your ore can generate
This 0 is how much to offset the max level. 
I dont understand why it exists but we can just ignore it

Then we add a feature to the biome 
So this generation stage tells it when during the chunk generation to add it
Underground ores makes sure it waits for stone and stuff to happen but like before trees or flowers
Then we pass is a feature which uses the filler block type, the block you want to generate
More specifically the block state 
And how big to make it

Now we can make another function that loops through all the biomes and adds our ores to some of them
As an example I'll check if its in the nether and then call my generate function 
So for the filler block type I'll use nether rack but if you're doing it in the overworld to natural underscore stone
Then you need the block and if you're not sure what to make the other numbers, you can look at what vanilla sets them to
For different ores. This is done in the DefaultBiomeFeatures class 
Lapis does it slightly differently but the normal way is probably fine
So I'll make mine quite common so we can find it easily 

So if you want to make your ore generate in the end or your own custom dimensions not based on stone,
We have to make a new filler block type
I'll do endstone as an example 
So filler block type dot create 
This need a name and then another name for some reason 
These should be unique which is why I included my mod id
The second one might not like capitals im not sure
Finally give it a new block matcher based on whatever block you want your ore to generate in

Then I'll just add this to any biomes in the end with the same generate function
This time passing in the filler we just made 

Then in your main class call the add ores to biomes function 
I do this on the setup event but you could also do it in the biome register event 
Doesn't really matter 

If we run the game we can see a bunch of smiles generate in the nether
And some diamond blocks generate in the end