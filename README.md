## My Personal Project

### A Bullethell Autoscroller Game



**What will the application do?**

My idea for the personal project is a 2-Dimensional "Bullethell" game where the objective is to shoot enemies
and avoid projectiles in an auto-scrolling environment for as long as possible. The player will control a spaceship 
that can move up, down, left, or right. They will use right click to shoot their own bullets. The player will also be
able to hit spacebar to temporarily be invincible to enemy projectiles. It will also feature a menu on startup where the 
user can change the appearance of their controlled ship-character before starting the game. Enemies will appear
periodically and shoot bullets in various patterns that the player must dodge. IF the player gets hit once, the game
ends.


**Who will use it?**

Anyone interested in 2D Bullethell-style games will be interested in this application. There are no real limitations on
the intended user.


**Why is this project of interest to you?**

I've always been interested in game design, and I saw this project as an opportunity to get my feet wet in actually
making a game. As for why this game in particular, I thought it offered a nice middle ground between a completely basic
and uninspiring game (like a pong copy for example), and a game that was too complex for the given timeframe. Though,
perhaps my scope is still too large for a summer project.

### User Stories

The following are some user stories that I plan to implement in phase 1 of the project.

* As a user, I want to be able to change the visual appearance of my ship.
* As a user, I want to be able to change the visual appearance of my bullets.
* As a user, I want to be able to change the sound of bullets firing.
* As a user, I want to be able to name my ship.
* As a user, I want to be able to turn background music off.
* As a user, I want to be able to change all the above settings back to the default.
* Let a defined set of ship name, ship appearance, and bullet appearance be known as a ship configuration.
  As a user, I want to be able to save multiple different ship configurations and load from saved configurations.
* As a user, I want to be able to save my player config and list of saved configs.
* As a user, I want to be able to load saved player configs and list of saved configs.

### Phase 4: Task 2

Implemented:

Robust class in the "Model" package: PlayerShip, SavedPlayerShipConfig.

Type hierarchy: GuiShip and GuiStart both extend GuiFrame.

### Phase 4: Task 3

I think my design has a lot of unnecessary coupling--especially with the PlayerShip and SavedPlayerShipConfig classes. 
It's a huge mess of lines since most classes hold some association with the two classes. To remedy this, a possible 
refactoring strategy is to create a UiMenu interface or abstract class that associates with PlayerShip and 
SavedPlayerShipConfig, and then have the console UI classes (StartMenu, ShipMenu, OptionsMenu) implement the parent 
class. There's also a confusing relationship between SavedPlayerShipConfig, ConfigSlot, and PlayerShip. Cleaning that up
would again help with the messy UML. I also think that I would benefit from using an Observer pattern for all my UI
classes. Ultimately, if I had more time, 