# Novel Custom MinStack Implementation at O(1) Time Complexity

Operation | Time Complexity
------------ | -------------
push(E elem) | O(1)
pop() | O(1)
peek() | O(1)
isEmpty() | O(1)
getMin() | O(1)



<h2>Why?</h2>
The getMin() operation of the stack will always be O(1) in all cases, both best or worst. The minimum value, even being popped, will always be retrieved in constant time, with no reinitialization of the most minimum value in the stack. This is done using an auxiliary helper structure, whose purpose is to always contain the minimum value of the stack at either its head or tail.


<h2>TODO</h2>
 - Make MinStack Generic
