## Description

In this tutorial we make a bow that uses torches as ammo. It also places torches where the arrow hits. 

## Script


Since minecraft’s bow code is hard to read, I’m just going to copy it and refactor it to make more sense 

In your items package make a new class called modBow
This is going to extend BowItem with the default constructer 
I’m going to go into the BowItem class and copy this onPlayerStoppedUsing method. 
Forge autogenerates useless variable names so I’ll rename a bunch of them to be more descriptive 
Then I’ll move the logic for setting the damage into its own function 
And I’ll do the same for setting the knock back based on punch
So when we want to make a bow that extends this class its easy to just overrides these functions to set a custom damage and knock back 
I’ll do the same thing for where it finds arrows from your inventory so we can make it use other items as ammo 
And I’ll do the same for creating the arrow entity. This will let us shoot our own custom arrow entity if we want 
I’ll add a bool for wether its a tipped arrow 
Then I’ll rewrite the logic for wether the arrow can be picked up from the ground. So if they have infinity they can’t pick up the arrow if they miss 
Then the logic for consuming an arrow. If they dont have infinity or they’re using a tipped arrow we consume an item from the ammo item stack 
I forgot to make these functions protected so we can override them when we extend this class
And instead of using the find ammo function, I’ll use this get ammo predicate function so the logic for letting you start to fire the bow works properly.  Currently this checks if the item is in the arrow tag but if you want to shoot a different item you can check for that when you extend the class
And I forgot to change this j to powerLevel when I calculate damage 
And I’m going to change the create arrow function so it doesn’t take an arrow item as a parameter so you can use other items as ammo in a way that makes sense 

Now we’re done this lets make a new class that extends ModBow 
I’ll override the get arrow damage function to make it deal less damage than normal 
The I’ll make the ammo predicate check if its a torch so it shoots those instead of arrows 

The make a new package entities dot projectiles and in that a class called torch arrow entity which extends arrow entity with the constructer that takes a world and a shooter 
We can override the onHit function to do something when the arrow hits something. Make sure you call the super method 

If you override arrowHit you can do something when it hits an entity. So call the super method and I’ll set it on fire and give it the glowing effect 
Then back in the onHit function, if it hit a block so if the type of the retrace result is block, I’ll call a method that I haven’t made yet to place a torch where it hit 
I’ll cast the ray trace result to the clock version and pass it into that function 
So in that function we can get the face it hit and the position 
Then if we hit the bottom of a block or somewhere that isn’t air so like inside a sign or another torch, I’ll just drop a torch item on the ground. So make an item entity and add it to the world 

Then if it hit the top of a block we just set the position to a torch 
If it hit the side of a block its a bit more complicated we have to orient it the right way.  So I stole this from the normal placing logic we can get the correct state of wall torch with the block face we hit and set the position to that 
In the hit function I’ll remove the arrow entity so the arrow doesn’t stick in the ground 
And in the set to torch function I forgot to offset the position with the face because we dont want to set the block we hit to a torch we want to set where the arrow is to a torch 

Then back in the torch bow class we can override the create arrow function to make a new instance of the torch arrow entity we just made 

In ItemInit we make the item like a normal advanced item just make sure to set the max damage to something so durability works 
Then I’ll look at the Minecraft assets on GitHub, link in the description and download the 4 textures you need for the animation when you shoot the bow. Bow and then pulling 0 1 and 2, I’ll also copy the model json for the bow and use it for my torch bow. Just changing the textures it points to. So instead of using mine crafts bow pulling it will use my mod id item slash bow name pulling whatever 
Then I have to make the model files for those 3 stages of pulling which are the same as normal. They just point to a texture
I’ll put the images into a pixel art editor and quickly change the colours then download them and put them in my textures/items folder. Make sure the names match what you reference in your model files 

The lang file is the same as normal 

Then if I run the game we can see we can shoot the bow and it places torches 
If we shoot at a torch it just drops one on the ground 
It lights monsters on fire 
If we’re in survival it takes torches from our inventory and if we have infinity it doesn’t consume torches 

#code/moding