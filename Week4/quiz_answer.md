# Week 4 Quiz

1.What is the name of the method that starts a Java program?
**Ans:** main

2.What argument type should the method that starts a Java program take?
**Ans:** an Array of String

3.What effect comes from declaring a field "static"?
**Ans:** There is only one copy of that field for the entire class, not one per instance.

4.What effect comes from declaring a field "static"?
**Ans:** To make an exception occur when the program detects a problematic circumstance that it cannot directly handle

5.Creating a new socket, as with: 
Socket s = new Socket(addr,port); 
can throw an IOException according to the documentation of the java.net.Socket class.
Which of the following structures is the best way to create a socket while handling the exception?
**Ans:**
try {
    Socket s = new Socket(addr, port);
    //code that uses s     
} 
catch(IOException ioe) {
    //code to handle the exception    
}

6.In Java, how is the keyword "finally" used?
**Ans:**In exception handling to specify code that should be executed regardless of whether an exception happened or not

7.If you wanted to read the contents of a file without using the edu.duke package, you might call Files.newBufferedReader.
What would you pass into Files.newBufferedReader?
**Ans:** Path

8.If you need to read data from a website without using the edu.duke package, you would probably want to use classes found in which package?
**Ans:** Java.net

9.You saw that a BufferedReader can be used to read data from a file on the local computer, as well as from a website. You could also use it with other sources of data, as long as you have an appropriate Reader class to access the data.
These capabilities are a great example of the benefits of which programming principles? Select the two best options.
**Ans:**
- Open/Closed Principle: the BufferedReader class is designed such that it can have its functionality expanded (to read from new data sources) without having to modify its code

-Abstraction: the BufferedReader class can work with any class that conforms to a specific interface, and does not need to know the details of how/where it reads data.
