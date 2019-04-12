#**ABOUT THE PROJECT - ( Default readme.md)**

# Java-Simple-Hotel-Management

This was an experimental project built for a demo. It has almost all the features roughly required to manage a hotel.

**Disclaimer**

The complete project was built when I was a novice student of Java. You are strongly discouraged to use it as it now.
There is only one purpose of releasing it publicly. It is to get the idea of how different components of Java Swing, other UI Library & also SQLite work together. I know the codebase is somewhere messy & somewhere organized. That's because it was an experiment with no supervision.

But Newbies & students are encouraged explore the codebase. Specially when you are assigned to do some management system by your faculty with no guidelines. Tried to write comment where i felt necesary back then. If you can understand properly and make some improvements, Kudos :)

There are also some test files which are called in no where. so ignore those parts.

The project was created using Java 7.** and IDE used was Netbeans 7. The Visual parts were done through Netbeans drag & drop tools.


*Project Overview*
You need to create `user/guest/visitor and least one room class & a room` first from the dashboard before you see it in action. After that Search the employee name. ( you need to write at least 3 characters before the suggestion appears). Click room and make the booking.

And from the `Booking Dairy` Menu you'll be able to search by the room name and add different orders associated with that booking/visitor.

`Checkout&Payment` also works in similar fashion. search by room name, explicitly check out them by clicking it and then generate payment & print. ( the most undone part is this section). you'll see the printing pdf has merely anything but the table. It's still a good idea to see the code to understand how to initate this things.


![Alt text](https://s32.postimg.org/4hbw178qt/hotel_management.png "Optional title")


#**HOW TO RUN THE CODE**
The code is run by using a jar file.

1. Add Jar Application file configuration.

2. In the file path, browse and select the file located at : 'ava-Simple-Hotel-Management-master\dist\I3_HotelManagement.jar'

3. Name it as I3_HotelManagement.jar

![Alt text](https://ibb.co/czYwab "Optional title")



#**AST PARSER USED**

Instead of adding the parser code as main class to my project in gradle or sbt, I've added the code in my 'src' folder as a separate application.

It is located at Java-Simple-Hotel-Management-master/src/ASTparserTreeVariables

**To run the parser**

1. Add run configuration

2. Add a new configuration of type 'Application' and add as Main Class as 'ASTparserTreeVariables' and classpath of module as 'Java-Simple-Hotel-Management-master'

![Alt text](https://preview.ibb.co/cW6zvb/Capture.png "Optional title")


**How parser runs**

1. First, we'll specify out file directory and root directory using File method. This will enable to go to consecutive files in the specified directory one my one.

2. We'll convert the file to be read (in the specified directory path) to String format and store it in the buffer.

3. This String format file is then passed to the parse function where we'll use ASTparser to parse the input String.

4. In this function, we'll create a new Compilation Unit and use the following functions to get the name and node values required: 'getLineNumber(name.getStartPosition())' and 'getLineNumber(node.getStartPosition())'

5. This is the shown as the output in file. 


**output**

Booking

![Alt text](https://ibb.co/kzdq1G "Booking")

Extra Orders

![Alt text](https://ibb.co/ci8q1G "Optional title")

Food

![Alt text](http://i67.tinypic.com/2cgk1fp.png "Optional title")

Item

![Alt text](https://ibb.co/ivkbab "Optional title")

Order
![Alt text](http://i67.tinypic.com/120jzbb.png "Optional title")

Payment
![Alt text](https://ibb.co/hLF9vb "Optional title")

Room
![Alt text](https://ibb.co/hzo7gG "Optional title")

RoomFare
![Alt text](https://ibb.co/f2fL1G "Optional title")

UserInfo
![Alt text](https://ibb.co/eJrA1G "Optional title")


#**TEST CASES**

All Test Cases are in a different file called 'Test Cases.docx'

#**How to build gradle**
1.	Add the build.gradle and settings.gradle to the root directory of the java project
2.	Close the project and IntelliJ
3.	Restart IntelliJ. Now we can either restart the same project as a gradle build or delete existing project and rebuild the project as gradle
4.	To restart the project as gradle build, when we try to open the project from intelliJ, it’ll give the option of ‘import as gradle project’
5.	To delete the existing project and rebuild as gradle, simply goto File -> Open... and select the newly created build.gradle. Then, IntelliJ will ask you whether you want to ‘Delete the Existing Project and Import’. Select that option.

#**How to build sbt**
**First, add sbt as framework**
1. Right click on root directory.
2.	Select Add Framework option. Select Scala in the right hand side technologies list. 
3.	In left hand side menu, provide a path to the scala sdk installed.
4.	Here, select pre-installed scala library.
5.	If not installed, goto browse and click the download button on the lower right hand side of the panel. This will download the latest version of scala available to IntelliJ.


**Then, add sbt config files**
1.	Add the build.sbt to the root directory of the java project
2.	Close the project and IntelliJ
3.	To restart the project as sbt build, when we try to open the project from intelliJ, it’ll give the option of ‘import sbt project’



