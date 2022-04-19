# Tower Defense Game

This Game is written by java 8. It consists of a small graphical Tower Defense game. You can find the **javadoc documentation and class UML diagram in docs folder** . Following design patterns are used in the project

- Strategy 
- Factory
- State
- Decorator
- Singleton

The objective of this assignment is to learn the basics of object oriented design principles, Unified Modeling
Language (UML) and design patterns. You will build a small graphical Tower Defense game. You will
write javadoc for your implementation and create a UML class diagram.

# Details
In this game, we will have four main game entity classes. Monster class for simulating the monsters and
TowerRegular, TowerIce, and TowerPoison classes for defense towers you will place. You will use
these towers to destroy waves of enemies until you lose all three of your lives. The monsters will circle the
tower and upon reaching their spawn point again, the player will lose a life.

- Game: Singleton class that contains the main method. It is the responsible class for managing the
simulation, and storing the required entities.
- Display: Singleton class that represents the display on which animation entities, the remaining
lives, current gold, total kills and the current wave number are repeatedly drawn.
- IPaintable: Interface for paintable game objects.
- Entity: Represents the abstract base class for the animation entities. Towers and Monster are the
subclasses of the base class.
- Monster: The concrete class for Monster entities in the game. Hold all of their information.
- Tower: Abstract class for different Tower classes. Holds basic information.
- TowerRegular, TowerPoison, TowerIce: The concrete classes that extend the Tower class.
- TowerDecorator: An abstract base class for TowerDecoratorGrade1, TowerDecoratorGrade2,
TowerDecoratorGrade3.
- TowerDecoratorGrade1, TowerDecoratorGrade2, TowerDecoratorGrade3: The concrete
decorator classes that extend the TowerDecorator class that are responsible to draw the symbols
indicating kill counts of the towers.
- ITowerFactory: An interface for different tower factories.
- TowerRegularFactory, TowerPoisonFactory, TowerIceFactory: The concrete factory classes
that implements the ITowerFactory interface.
- IMonsterStrategy: An interface for the strategy pattern to implement different monster movement
strategies.
- MonsterZigZagStrategy, MonsterCircularStrategy: The concrete strategy classes that im-
plements the IMonsterStrategy interface.
- MonsterState: The abstract class representing state pattern for monster upon receiving special
damage.
- MonsterPoisonState, MonsterIceState: The concrete state classes extend the MonsterState
class.


# Run (You need java to be installed)
git clone 
cd Source
make all
make run
