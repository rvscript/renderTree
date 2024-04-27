Attempt to add views of proportional sizes based on an array input.
The idea is to create a recursive function that will split the array in half and continue to do so until there are 2 or less items in the sub array.
Then render the items to a display. The array example {1,2,   3,4,   5,5} = 20 , So the first subarray = {1,2}. This sub array adds up to 3. 
3: 20 is the area of the rectangle to draw. 
with in the area, there is a 1 and a 2. 
The drawn item will be further divided by the 2 elements {1, 2} , so 1:3 and 2: 3.

This is to be drawn to display a grid of items with proportioned areas.

My initial attempt was using a layout and populating with more linear layouts programatically. This did not seem to work.
I tried to subdivide and create text views. This seems to be better and I will continue working on it. 

I've moved to draw rectangles for image of rectangles. This is better than drawing text views. Drawing on a canvas separates the action from the activities layouts. 

Thank you.
