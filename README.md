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
A simple one to get started. For part 2 you can use a ```Set<Integer>``` as it won't accept doubles. As soon as adding
another frequency fails, you found the answer.

## Day 2
Again, straight forward, sorry ... nothing special going on.

## Day 3
Created a Claim class and a Fabric class. The Fabric class creates a grid of ```int``` and set all claimed cells
to the id of the claim. When a cell is claimed for the second time, the value is set to -1.
Now for part 1, you only need to count the cells with value -1. For Part 2, check if all cells for a claim indeed
contain the claim id, if not it has overlap.

## Day 4
A bit more work, especially to parse the input data, but from there it's relatively straight forward. It helps to create
an aggregated timeline of integers (``int[60]```) for each guard for every minute from 00:00 - 00:59, and collect in 
there the number of times the guard sleeps at each minute. From there, finding the sleepiest guard, or the minute he 
sleeps most often is easy.

## Day 5
Quite simple today ... Use a StringBuffer and remove two consecutive units when similar and opposite polarity. 
When you have removed two units, and you're not at the beginning of the StringBuffer, move one position back (this will 
then remove the enclosing combination), if nothing was removed, move ahead to the next position.

For part two, get all the units, then get the length per reaction without each distinct unit, get the min value. When 
using streams it requires just a few lines of code.

## Day 6
Not sure how to solve this one yet. The input only contains positive coordinates, so I could just start with a 
grid size (maxX + 2, maxY + 2). Then place the letters, and set each position in the grid to the letter of its 
proximity. If no value can be determined set it to ".". Then take all letters, and remove the ones that touch the 
edge of the grid (for those could continue forever). Then determine the letter that occurs most often.

And that worked pretty well...

Part two is similar ... for every point in the grid, check the sum of distances to all points, and 'paint' the point
as '#' if the distance is less than 10.000. Finally, count all '#' in the grid. To be sure, I printed the grid, to 
ensure no # where at the edge (which would have meant the grid was to small) but that wasn't the case.

## Day 7      
That was fun! Initially I thought to create some kind of dependency tree of nodes to determine the correct order. Then 
I thought why bother? If I know which steps have been taken, I can also determine which steps could be next. All I need
to know is which steps are possible and for each step, which ones must be finished before this one can. So the next 
possible steps are those that can be taken given the set of finished steps. 

I created a ```Step``` class, to know whis\ch steps need to be finished before the step itself can finish. Based on 
a set of finished steps, the class can tell if it's available or not. A ```Steps``` class has the list of steps, and 
can provide an ```OrderedSet``` of options to go next, based on a set of already finished steps. When the finished steps 
set is still empty, of course you get the only step without required predecessors.   

When you select a step to take, remove it from the set that still needs to be done and move it to the set of finished 
steps. Repeat this while you still have steps to take. So the use of sets of steps, or ordered sets where applicable, 
makes it quite easy.

For part two, I used a simulation where a ```StepWalker``` class gets a step to take, and it requires multiple calls 
to the ```walk()``` operation before it returns the letter of the step. The ```ConcurrentStepWalker``` Use a ```List``` 
of ```StepWalker``` (with a max size of 5). As long as the list isn't full, for each option a ```StepWalker``` is 
created and added to the list. Repeat this while there are walker objects in the list. Meanwhile, the program gathers 
the letters that become available. Count the loops to measure the duration.

## Day 8
Some challenge of parsing the input into a tree of nodes. However, when you use an iterator to go through the list of 
integers, you can pass the iterator to a factory method that recursively creates nodes (while passing on the iterator 
to the recursive calls). Each Node builds a list of child notes, and a list of metadata values (integers).

Once you have the tree structure, its relatively simple to use streams to calculate the values. As I was worried about
speed I added memoization, that only calculates a value once and remembers it (```OptionalInt``` is very practical 
here). Although for this exercise not required, I cleared the memoized value when the node data changes (i.e. when 
metadata, or a child gets added).

## Day 9
I've created a ```Stock``` class, as provider of marbles which implements the Iterator interface. A ```Circle``` class
to maintain the circle and place new values. When a value is placed, a score gets returned (mostly 0, except when the 
marble can be divided by 23). The ```MarbleGame``` class plays the game given a ```Stock```, a number of players, and a 
```Circle``` implementation. 

I started with a Circle class based on a ```LinkedList``` (sorry, I don't know why I chose a LinkedList). That worked, 
although it was slow (over 2 seconds for part 1). For part 2, I changed the implementation to use an ```ArrayList````` 
which ran way faster but still too slow for part 2. So, I created an implementation which used nodes in a double linked 
list. That outperformed the other implementation, and had no issue what-so-ever with part 2.

## Day 10
This one was nasty ... I used a ```Message``` class to do the work on a list of ```MovingPoint``` objects. I 
implemented a grid method that created a grid to hold all the points and visualize the message. The grid
is sized as small as possible, so unused area around the cloud of points is not in the grid.

When playing the sample, the grid reduced in size an appeared to be at his smallest size when the message 'HI' appeared. 
So, apparently, the message can be read when the grid is at the smallest possible size.

I then implemented a decrypt-message method that would move all the points until the grid would be at its smallest
size, and then ```decrypt()``` would return that grid. As the initial grid was very large, the decrypt-method
would not create a grid until its size would be smaller than 80x40.

For part 2, I only had to count the calls to ```move()``` all the points until the smallest possible grid was found.

## Day 11
Nothing special on this one. Once the grid has been setup, it's a simple search. On part 2 a better performance can be 
made, by reusing the calculations of the previous searches (the values of the 4x4 grids can start with the values of 
the 3x3 grids), but I didn't take that effort. Part two ran within 30 seconds, which is slow but still acceptable.

## Day 12
I created a ```Pots``` class to handle the tough work, like generating a next generation and calculating the pot sum. 
To prevent testing on boundaries, I add max 5 dots before and after the initial string (and increase the index of pot 
zero accordingly, when dots are added to the front). 

Part one is pretty straight forward. Don't do any difficult translations with the rules. Just create a ```Set<String>``` 
with all the combinations that lead to a new plant, and ignore all other rules. Grow a plant in the pot if the 
combination is part of the set. No difficult rule matching is required.

This can solved it for part one very fast. For part 2 additional steps where required.

When I printed the pots as string after 1.000 generations, it contained a lot of dots in at the start of the string. 
So, I enhanced the ```Pots``` class, to remove dots from the start of the string (leave max 5) and update an offset 
accordingly. Now up to 1.000.000 generations were doable within 3,5 seconds. But that's still far from the required 
50.000.000.000 generations.

When I then printed the string of pots for every 1.000th generation, I saw the strings were all identical. What 
differed was only the offset. It appeared that after generation 86, no the string stays the same, but the offset 
increases with 1 for every generation, which means the location of pot zero doesn't change, and neither the sequence
of plants, but the sequence does move one position further away every generation. And with that info, I could calculate
the positions after 50.000.000.000 generations.

The ```bignumber()``` test case contains the investigation code.

## Day 13
Now this one was nasty (for me). The basic idea is simple, but the caveat was in collision detection. I thought to use 
a ```Set``` to detect collisions, and that's fine, but I should have remembered that collisions not only occur with 
moved carts, but also between a moving and a not-yet-moved cart. Next mistake, was on part 2, where I reused an object 
containing a ```Set<Cart>``` that had changed during part 1. Finally,while removing carts from the moved-set as wel as 
the to-be-moved set in a loop, the iterator of the loop of course didn't detect the removal of the cart of the 
to-be-moved set ...
While the navigation and the moving worked, I made 3 stupid errors on the collision part. Maybe not do this around 
at midnight after a long day of work...          

## Day 14
The problem is quite straight forward. I Solved the indexing for the circular list, by extending the ```ArrayList``` ond
overriding the ```get(int index)``` method. Generating a number of recipes also wasn't hard (at least for part one). For
part two, it was all about a smart method to check the last digits for equality with the provided input. And you need
to be aware that there can be one additional generated recipe, as regularly two recipes get created. In that case you 
might need to ignore the very last recipe.
  