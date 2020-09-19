## Description

In this tutorial we make an event that fires when a zombie spawns and gives it gold boots

## Script

Make a package called events and in that make a new class
Make that class an event bus subscriber with this big line of code 
Then make a static function 
doesn't matter what you name it
The type of the parameter determines when the function to fire
So I'll do EntityJoinWorldEvent
And get the entity that's spawning
I'll check if its a zombie 
And if it is, give it golden boots
Also dont forget to give this function the subscribeEvent annotation 

There are a whole bunch of event types that you can use
Which all extend the event class so you can use your IDE to see all of them
We'll use a few of them in future tutorials 

Anyway, if we run the game and spawn a zombie we can see it has golden boots