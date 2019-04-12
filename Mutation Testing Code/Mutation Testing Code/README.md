#ABOUT THE PROJECT - ( Default readme.md)


# Sketch
A Java Paint Application

### Prerequisites

You Need
1.JDK 64 bit version.
2.Gradle Build tools
3.SBT Build Tools
4.IntelliJ IDEA

### Installing

1.Install IntelliJ Idea and open the project file.
2.Select the build option in the drop down window on the top left and then run to run the program.
3.The Run command should open the Sketch.


#DESCRIPTION OF THE IMPLEMENTATION

1. The original code was instrumented
2. The instrumentation generated a trace which was the inout of the mutation class
3. The mutation matrix was created by the mutation class
4. This mutation matrix was used to call the different mutation operations/classes
5. These mutation classes/operations created the mutated code
6. The method is reinvoked to create the trcae for the mutated code
7. The before and after traces of the mutated code can be seen and compared to conclude that the mutation is successfull

Instrumentation Code Implementation

We took the input Instrumentation from output of HW2

Tracing the Code

1. The result of the instrumentation is the execution trace 
2. This execution trace has been saved in execTrace.txt (src/execTrace.txt)

Mutation Matrix Creation (MutationMatrix.java)

1. First, we are calling the readTrace() function, which is reading the trace from execTrace.txt
2. Then, for each line in the trace, we determine which mutation operator (if any) are applicable
3. We store the statements and operators in the mutation matrix
4. This is how we create mutation matrix

Various Mutation Classes/Operations

1. TestCases.java present in Mutation Launcher Package is run which first calles MutationMatrix.java, which creates the mutation matrix
2. Then, TestCasesMutatewithAssist.java is called.
3. Inside this, the mutation matrix is read by using a loop and depending on the value read, the mutation operation classes are called.

How the mutation classes work

1. All the mutation classes are under the package 'MutationInjection' in src
2. Using javassist functions (getMethod(), setMethod(), Replace() ), the byte code is mutated
3. We create a new ClassPoll object and use it to call .get() to create an instance of the classs
4. Using this class instance, we get the required method using getMethod()
5. Using Replace() and setMethod(), we make changes to the method body
6. We then call writeFile() and toClass() to make the changes reflected in the original class file

Reinvoking the trace for Mutated Code

1. After step 6 of previous section, we call reflection functions (newInstance() -> getMethod() -> Invoke() )
2. Invoke() calls the mutated function.
3. The mutated trace is now generated and saved in Muataedexec.txt (src/Muataedexec.txt)

Comparing the trace before and after mutation

1. Below are the attached screenshots for the traces obtained before and after the mutation.
2. By comparing them, we can see that the output is different.

#*Compare The Execution Trace (src/execTrace.txt) with the Mutated Trace (src/Muataedexec.txt)

Before Mutation

![Alt text](http://i66.tinypic.com/2uqf1ts.png "Before Mutation")

After Mutation

![Alt text](http://i68.tinypic.com/2lrmsi.png "After Mutation")

3. Hence, we determine that the mutation is successful

#HOW TO COMPILE IT AND RUN IT

Using Gradle Tool

1. Open The terminal window in the bottem right of the IDE
2. input 'gradle build' to build the project.
3. input 'gradle run' to run the Mutation Launcher.

Using SBT Tool

(Limitation)
Unable to run on the sbt please use gradle or run directly via the IDE.