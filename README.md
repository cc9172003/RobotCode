# Robot Code
Troy FRC 3952 team's robot code. 
 
## Getting Started
Unfortunately, the installation can only be done on windows computers. 
1. Follow the direction to install Eclipse. Be sure to follow the **Java**
set up instructions and not the C++ ones. [Instructions](https://wpilib.screenstepslive.com/s/4485/m/13809/l/599681-installing-eclipse-c-java)
2. Install the [WPI Suite](https://wpilib.screenstepslive.com/s/4485/m/13809/l/599671-installing-the-frc-2017-update-suite-all-languages)
3. Install git command line tool. Follow the Windows installation directions [here](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)
4. Clone code from [GitHub](https://github.com/TroyFRC/RobotCode/tree/master).

		$ cd where\ever\you\want\to\put\teh\codez
		$ git clone https://github.com/TroyFRC/RobotCode/tree/master
	Note: if you do not understand `cd` command, please see this [command line cheat sheet](http://www.cs.princeton.edu/courses/archive/spr05/cos126/cmd-prompt.html)
4. Open FRC Driver Station. On the Driver station be sure to set the correct team number in the Set up tab on the left! Our team number is **3952** (not just a team but a family too :smile:)
5. Turn on Robot and connect to **3952** wifi. It usually takes about a minute to show up so don't worry if you don't see it immediately. If its not showing up, your router might need to be configured. 
6. If the Driver Station says "No Robot Code," click on the *Run* Menu Item, go into the *Run As* sub item and click *WPIlib Java Deploy*. 
7. Go to Driver station and click enable. 
8. If any the code on Github changes and you want the updates, you will have to open command prompt and run the following command

		$ cd where\ever\you\putz\to\put\teh\codez
		$ git pull
		

## Architecture
Our code revolves around subsystem classes and the robot class. The subsystem classes represent each part of the robot which is treated as a whole. For example, we have one subsystem class for the drive train and another for the shooter. These subsystem classes contain many methods which allows them to perform tasks. The Robot class initializes these subsystem classes in the `robotInit` method and uses them in the the Task classes. TODO: finish the rest of this.

## Contributing
Basically anyone can contribute to this code base. 
#### Prerequistes:
0. Windows Computer
1. Java.
2. Knowledge of Git Basics
3. Knowledge of Command Line basics
4. Knowledge of Github Basics
5. [Eclipse](https://wpilib.screenstepslive.com/s/4485/m/13809/l/599681-installing-eclipse-c-java)
6. Github account
7. [Git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git).
8. [WPI Suite](https://wpilib.screenstepslive.com/s/4485/m/13809/l/599671-installing-the-frc-2017-update-suite-all-languages)

Most of these, you can just learn as you go. However, if you are not familiar with git or command line, please see tutorials. It'll make life easier. Here is an adorable one for [git](https://try.github.io/levels/1/challenges/1), heres an overkill one for [command line](https://www.codecademy.com/en/learn/learn-the-command-line), and a [command line cheat sheet](http://www.cs.princeton.edu/courses/archive/spr05/cos126/cmd-prompt.html)

#### Setting up for Troy FRC Team members
1. Talk to the programming head and request them to add you as a contributor.
2. Clone the original repo

		$ cd where\you\want\it
		$ git clone https://github.com/TroyFRC/RobotCode
	
	If you do not understand `cd` **PLEASE** see the tutorials and make your life easier. 
3. Make a new branch for your changes. This can be done many ways but heres my preference

		$ git checkout -b name-of-your-awesome-branch
	
4. Open the project in eclipse and make any changes one wants. Be sure to test them. 
5. Add, commit and push your changes. If you are familiar with git, you may place your changes in several commits and then push

	
		$ git add -A
		$ git commit -m "[descriptive message about your changes]"
		$ git push origin name-of-your-awesome-branch	
	
	
6. Go to the [repo](https://github.com/TroyFRC/RobotCode) and file a pull request to merge your branch with master. If you are uncertain as to how to do that follow these [directions](https://help.github.com/articles/creating-a-pull-request-from-a-fork/)
7. Wait for the code review :smile: . If the you need to change anything, just repeat steps 4 and 5.

#### Setting up for non Troy FRC peoples.
1. Fork the repository. In order to do so, go to [the repo](https://github.com/TroyFRC/RobotCode/tree/master) and hit the fork button at the top 
2. Clone the forked repo **NOT** the original

		$ cd where/you/want/it
		$ git clone [url of your forked repo]
	
	If you do not understand `cd` **PLEASE** see the tutorials and make your life easier. 
3. Make a new branch for your changes. This can be done many ways but heres my preference

		$ git checkout -b name-of-your-awesome-branch
	
4. Open the project in eclipse and make any changes one wants. 
5. Add, commit and push your changes. If you are familiar with git, you may place your changes in several commits and then push

	
		$ git add -A
		$ git commit -m "[descriptive message about your changes]"
		$ git push origin name-of-your-awesome-branch	
	
	
6. Go to your forked repository on github and file a pull request. If you are uncertain as to how to do that follow these [directions](https://help.github.com/articles/creating-a-pull-request-from-a-fork/)
7. Wait for the code review :smile: . If the you need to change anything, just repeat steps 4 and 5.

## License

Troy FRC's Robot Code is released under an [Eclipse Public License](https://github.com/TroyFRC/RobotCode/blob/master/LICENSE)

