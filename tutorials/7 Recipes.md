## Description

In this tutorial we make crafting table and furnace recipes

Ask Me Questions: https://discord.gg/VbZVnRd
The Code: https://github.com/LukeGrahamLandry/forge-modding-tutorial
Donate: https://www.patreon.com/LukeGrahamLandry

## Script

In src main resources data mod id make a new folder called recipes 
Then make a new json file. The convention is to name it after the output item

So you can fill out this key dictionary 
Mapping each item you need in the recipe to a character that you'll use to show the pattern
So then in this pattern list you can make the shape of your recipe with those characters 

The result tells it what to output
You can also add a count field to this dictionary if you want it to output more than one

We can make another json file for a furnace recipe 
Again the result is the name of the item to output
And this ingredient item is the input 
You can also set how long it takes in ticks and how much xp it gives 

Then we can run the game and see that it works  