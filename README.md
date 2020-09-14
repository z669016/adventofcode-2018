# Advent of code 2018

## Convenience methods and classes
I have a small library with some convenience methods used for other AoC exercises. Like the ```ResourceLines``` class 
to read a resource file and transform the content into a ```List<String>```, or the CSV class to read a resource 
file containing comma separated values and returning a List of these values, optionally after transformation from 
```String``` to ```Integer```.

Also uses the algorithms library, which contains generic classes for addressing classic compute problems (from the book 
**Classic Computer Science Problems In Java** (c) Manning.com - 2020) 

It was never my intention to create the shortest program possible. I did try to create clear and simple implementations.

## Day 1 
A simple one to get started. For part 2 you can use a ```Set<Integer>``` as it won;t accept doubles. As soon as adding
anothe r frequency fails, you found the answer.

## Day 2
Again, straight forward, sorry ... nothing special going on.

## Day 3
Created a Claim class and a Fabric class. The Fabric class creates a grid of ```int``` and set all claimed cells
to the id of the claim. When a cell is claimed for the second time, the value is set to -1.
Now for part 1, you only need to count the cells with value -1. For Part 2, check if all cells for a claim indeed
contain the claim.id, if not it has overlap.

## Day 4
A bit more work, especially to parse the input data. But from there it's relatively straight forward. It helps to create
an aggregated timeline of ints (``int[60]```) for each guard for every minute from 00:00 - 00:59, and collect in there 
the number of times the guard sleeps at each minute. From there findng the sleepiest guard, or the minute he sleeps 
most often is easy.  

