#*Random Java Program Generator*
Project By *Sreetama_Banerjee* *Adarsh_JanapareddyVenkata* *Charvi_Virani*


### Prerequisites

You Need

1.JDK 64 bit version.

2.Gradle Build tools

3.IntelliJ IDEA.


#*DESCRIPTION OF THE IMPLEMENTATION*

1. We created a Production_rule.properties file which provides the rule number as key and its corresponding production rule as value.
2. The values are loaded into a variable using Properties class method getproperty().
3. Each production rule expands to different production rules.
4. Each production rule is stored in a HashMap with its value storing range of numbers which are the range index numbers of expanded production rules stored in a Vector List.
5. We set the root node and get its value by randomly generating an index value using random number generator. 
6. The getAllLeafNodes() gets all the leaf nodes (nodes having children property as null). This function is called at the end to get the finished class structure.
7. The class tree is then printed
8. Used Apache Commons to dynamically change values for existing keys in .properties file according to user input in config.txt(We have implemented this for "NoOfPaametersperMethod" and "AllowedTypes" inputs.

#*HOW TO COMPILE IT AND RUN IT*

*Using Gradle Tool*

1. Open The terminal window in the bottem right of the IDE
2. input 'gradle build' to build the project.
3. input 'gradle run' to run the Mutation Launcher.

*Using SBT Tool*

(Limitation)
Unable to run on the sbt please use gradle or run directly via the IDE.
