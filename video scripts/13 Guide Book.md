## Description

In this tutorial we make an in game guide book (like Botania's lexicon)

## Script

To make an in game guide book for our mod we can depend on a mod called patchouli. This will let us define the content of our book without dealing with the code. 

So from patchouli's GitHub we can get the repository we have to add to our build dot gradle file and the dependancy. WE just need the runtime one. And we can set the version to 1.15.2 dash 1.2 dash 32 point 168

In our mods.toml file we can add it as a dependancy. You can copy the example one they give and just change the modID and version range. With mandatory false people can still play your mod without patchouli but the guide book won't appear in game. If you want to force them to have it, set mandatory to true 

We can look at the getting started page on patchouli's wiki and make the books, book name, en_us folder and in that entries, categories and templates

We can make books dot json in our book name directory and set the name to display in game and the text to show on the landing page. We can also use a custom model so it doesn't look the same as any other mods using patchouli. So just make a textue and model file like normal. I called mine help underscore book so I'll set the model field to my modid colon help book. Then we can put it in a creative tab, this doesn't need the mod id just the name of the tab. 

In the categories folder I'll make a weapons dot json file where I'll define a category my book. OF course you can have as many of these as you want. We just set a display name a description and an icon which is just an item 

In entries, I'll make a folder for all my weapons and in that a json file for my torch bow. I'll set the name and icon and make it appear in my weapons category. 
I'll make a page that just has text. But there are whole bunch of pre-made page types you can see on the wiki. 
Let's make a spot light one to highlight our item. I'll also just do a smelting recipe as an example despite it not relating to the bow. Both of these can also have text and a title. It has a field called recipe which has the recipe id which I think means your mod id then the name of the json file where you declare your recipe 

I'll copy this file to make another entry. This time for my teleport staff. 
Let's make a page that displays the recipe. It can have a title and text like normal. And a recipe field that just has the id of the item you want to show. Make sure you spell recipe right. Turns our the type should be crafting not recipe.  

Then if we run the game we can find the book in our item tab. Go into the weapons category and see the pages for our different items. And see that the different page types nicely render our recipes. 

