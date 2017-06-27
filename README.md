# Robot Code
Troy FRC 3952 team's robot code. 
 
## Getting Started
Unfortunately, the installation can only be done on windows computers. 
1. Follow the direction to install Eclipse. Be sure to follow the **Java**
set up instructions and not the C++ ones. [Instructions](https://wpilib.screenstepslive.com/s/4485/m/13809/l/599681-installing-eclipse-c-java)
2. Install the [WPI Suite](https://wpilib.screenstepslive.com/s/4485/m/13809/l/599671-installing-the-frc-2017-update-suite-all-languages)
3. Clone code from [GitHub](https://github.com/TroyFRC/RobotCode/tree/master).

		$ cd where/ever/you/want/to/put/teh/codez
		$ git clone https://github.com/TroyFRC/RobotCode/tree/master
		
4. On the Driver station be sure to set the correct team number in the Set up tab on the left! Our team number is **3952** (not just a team but a family too :smile:)
5. Turn on Robot and connect to **3952** wifi. It usually takes about a minute to show up so don't worry if you don't see it immediately. If its not showing up, your router might need to be configured. 
6. Click on the *Run* Menu Item, go into the *Run As* sub item and click *WPIlib Java Deploy*. 
7. Go to Driver station and click enable. 

## Architecture
Our code revolves around subsystem classes and the robot class. The subsystem classes represent each part of the robot which is treated as a whole. For example, we have one subsystem class for the drive train and another for the shooter. These subsystem classes contain many methods which allows them to perform tasks. The Robot class initializes these subsystem classes in the `robotInit` method and uses them in the autonomous and teleop methods. This makes the Robot code extremely easy to read and fairly short.
This architecture may change in the future when more robust code is needed. However, as of now it works just fine.

## Contributing
Basically anyone can contribute to this code base. 
### Prerequistes:
0. Windows Computer
1. Java.
2. Git Basics
3. Command Line basics
4. Github Basics

### Things to get/install
1. [Eclipse](https://wpilib.screenstepslive.com/s/4485/m/13809/l/599681-installing-eclipse-c-java)
2. Github account
3. [Git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git).
4. [WPI Suite](https://wpilib.screenstepslive.com/s/4485/m/13809/l/599671-installing-the-frc-2017-update-suite-all-languages)

Most of these, you can just learn as you go. However, if you are not familiar with git or command line, please see tutorials. It'll make life easier. Here is an adorable one for [git](https://try.github.io/levels/1/challenges/1), heres an overkill one for [command line](https://www.codecademy.com/en/learn/learn-the-command-line).

### Setting up
1. Fork the repository. In order to do so, go to [the repo](https://github.com/TroyFRC/RobotCode/tree/master) and hit the fork button at the top 
2. Clone the forked repo **NOT** the original

		$ git clone [url of your forked repo]
	
	If you do not understand `cd` **PLEASE** see the tutorials and make your life easier. 
3. Make a new branch for your changes. This can be done many ways but heres my preference

	$ git checkout -b name-of-your-awesome-branch
	
4. Open the project in eclipse and make any changes one wants. 
5. Add, commit and push your changes. If you are familiar with git, you may place your changes in several commits and then push

	```
	$ git add -A
	$ git commit -m "[descriptive message about your changes]"
	$ git push origin name-of-your-awesome-branch	
	```
	
6. Go to your forked repository on github and file a pull request. If you are uncertain as to how to do that follow these [directions](https://help.github.com/articles/creating-a-pull-request-from-a-fork/)
7. Wait for the code review :smile: . If the you need to change anything, just repeat steps 4 and 5.

## License

Troy FRC's Robot Code is released under an [Eclipse Public License](https://github.com/TroyFRC/RobotCode/blob/master/LICENSE)

