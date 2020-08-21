## Description

In this tutorial we download java 8, forge and IntelliJ. 
We also rename our package and class and update the mods.toml file. 

Java 8 JDK: https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html
Oracle Logins: http://bugmenot.com/view/oracle.com
Java 8 JRE: oracle.com/java/technologies/javase-jre8-downloads.html
Forge 1.15.2: https://files.minecraftforge.net/maven/net/minecraftforge/forge/index_1.15.2.html
Intellij IDEA: https://www.jetbrains.com/idea/download

Ask Me Questions: https://discord.gg/VbZVnRd  
The Code: https://github.com/LukeGrahamLandry/forge-modding-tutorial  
Donate: https://www.patreon.com/LukeGrahamLandry  

## Script

Step one of modding is downloading a bunch of things

First thing to get is the java development kit 
pick your operating system and then its gonna ask you to make an account 
but you can just borrow someone else's 
Just copy and past a set of credentials from this website
If it doesn't work just try the next one

Next you need the java runtime environemnt
So just pick your operating system, accept the terms and click download

Next you need forge
Get the recommended version cause its the most likely to work 
And download the MDK
It will send you to a page with ads. Very important not to click any of them
Just wait a few seconds and click the skip button in the top right

Then you need an IDE to write your code 
I'll use Intellij cause I like it but eclipse is fine too
Get the community addition cause free things are good

Now that you've got everything you can start setting up things 
Double click the JDK and just click yes to everything
It might need your password. And takes a while to install 

Then double click the JRE
Similar deal, takes a while, might need a password

Then unzip the forge MDK 
And rename the folder to the name of your mod
We dont need this license, readme, credits junk

Then launch intellijj 
You probably want dark theme but you can skip the rest of the settings, defaults are fine
Then you want to Open a project
Select the renamed forge folder
Give it a while to do the indexing 

Then go to the project explorer on the left 
Go to src, main and right click the com.example.examplemod and choose refactor: rename
Call it com dot your name or website or whatever dot your mod id
So whatever you want to call your mod. 
You dont want any spaces or capital letters or any junk like that
You can get rid of this example folder

Then open example mod
At the top, right click the class name to refactor:rename 
It to the name of your mod. Capitals are fine this time
Above that change the examplemod to your mod id

Then you can get rid of this ensue and process IMC and the functions down here
Also dont need the LOGGER stuff cluttering up your code
And we dont need the RegistryEvents class at the bottom 

Next go into resources, meta inf, mods.toml
I'm just gonna space these out a bit so its easier to read
You can change most of these but its important to set your mod id 
to whatever it was in the other file
Then lower down you can set the credits, author and description to whatever you want
The other ones are cool if you know what you're doing but dont really matter

Then you want to got to build.gradle
This is the file that tells java to get your dependancies like Minecraft and forge to run your mod 
Set the line that says group to whatever you named your package before
You can click this little elephant button top right ish to tell IntelliJ about the changes you've made

Then go to the terminal. You can just click it on the bottom 
And type ./gradlew GenIntellijRuns on windows use the command prompt and you dont need the ./
This lets you run Minecraft with the little green play button in the top right
That will probably take a while to run

If you exit IntelliJ and come back it should let you run the game with the play button
And you can see that your mod shows up on the mods list

Thanks for watching