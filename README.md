# Troy2016Code


Outline:

Robot.robotInit() is the entry point. All set up is done there.

Task: represents some sort of function that the robot does. Can be human or antomous. 

telopPeriod is run periodically during driving. Concurrent tasks are run in order of importance.
Special tasks are run by hitting buttons. They can turn off certain concurrent tasks.


anonymous:
a queue stores tasks which are then each done to completition and then removed. Then next task is done.