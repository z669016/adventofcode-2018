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
That was fun! Initially I thought to create some kind of dependency tree of nodes to determine the correct order. Then 
I thought why bother? If I know which steps have been taken, I can also determine which step could be next. All I need
to know is which steps do I have and for each step, which ones must be finished before this one can. So the next 
possible steps are those that can be taken now based o the set that's already finished. 

I created a ```Step``` class, to know whish steps need to be finised before the step itself can finish. Based on a set
of finished steps, the class can tell it's available or not. A ```Steps``` class has the list of steps, and can provide 
an ```OrderedSet``` of options to go next based on a set of already finished steps. When the finished steps set is 
still empty, of course you get the only step without required predecessors.   

When you select a step to take, remove it from the set that still needs to be done and move it to the set of finished steps. 
repeat this while you still have steps to take. So the use of sets of steps, or ordered sets where applicable, makes 
it quite easy.

For part two, I used a simulation where a ```StepWalker``` class gets a step to take, and it requires multiple calls 
to the ```walk()``` operation before it returns the letter of the step. The ```ConcurrentStepWalker``` Use a ```List``` 
of ```StepWalker``` (with a max size of 5). As long as the list isn't full, for each option a ```StepWalker``` is 
created and added to the list. Repeat this while there are walker objects in the list. Meanwhile gather the 
letters that become available. Count the loops to measure the duration.

