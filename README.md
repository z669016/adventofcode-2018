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

## Day 5
Quite simple today ... Use a StringBuffer and remove two consecutive characters similar and opposite polarity. When you 
have removed two units and you're not at the beginning of the StringBuffer, move one position back (this will then 
remove the the enclosing combination), if nothing was removed, move ahead to the next position.
For part two, get all the units, get the length per reaction without each distinct unit, get the min value. WHen 
using streams just a few lines of code.

## Day 6
Not sure how to solve this yet. The input only contains positive coordinates, So i could just start with a 
grid size (maxX + 2, maxY + 2). Then place the letters, and set each position in the grid to the letter of its 
proximity. If no value can be determined set it to ".". Then take all letters, and remove the ones that touch the 
edge of the grid (for those could continue forever). Then determine the letter that occurs most often.

And that worked pretty well...

Part two is similar ... for every point in the grid, check the sum of distances to all points, and 'paint' the point
as '#' if the distance is less than 10.000. Finally, count all '#' in the grid. To be sure, I printed the grid, to 
ensure no # where at the edge (which would have meant the grid was to small) but that wasn't the case.

## Day 7      

