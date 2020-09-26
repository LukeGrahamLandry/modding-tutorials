## Intro 

In this tutorial we make crafting table and furnace recipes.

## Folder 

In src/main/resources/data/mod_id make a new folder called recipes. 
This is where you'll put json files to define your recipes. 
The convention is to name these files after the output item but it doesn't matter. 

## Shaped 

In your recipes folder make a new json file.  

The key dictionary assigns a character to each item you use in the recipe. 
Then use these characters to make the shape of the recipe in the pattern list. 
The result tells it what item to output. It can also have a count field if you want it to give more than one. 

```json
{
    "type": "minecraft:crafting_shaped",
    "pattern": [
        "###",
        " / ",
        " / "
    ],
    "key": {
        "#": {
            "item": "firstmod:smile"
        },
        "/": {
            "item": "minecraft:stick"
        }
    },
    "result": {
        "item": "firstmod:pink_pickaxe"
    }
} 
```

## Shapeless

In your recipes folder make a new json file.  

Ingredients is a list of items to use to craft. The result is the item to output (it an have a count field). 
The arangement of items in the crafting table doesn't matter. 

```json
{
  "type": "minecraft:crafting_shapeless",
  "ingredients": [
    {
      "item": "minecraft:ender_pearl"
    },
    {
      "item": "firstmod:smile"
    }
  ],
  "result": {
    "item": "firstmod:teleport_staff"
  }
} 
```

## Furnace

In your recipes folder make a new json file.  

The ingredient item is the input and the result is the output. 
You can also set experience and cookingTime. 

```json
{
    "type": "minecraft:smelting",
    "ingredient": {
        "item": "firstmod:smile_block"
    },
    "result": "firstmod:smile",
    "experience": 1.0,
    "cookingtime": 100
} 
```

## Run the game

Then we can run the game and see that these recipes work.