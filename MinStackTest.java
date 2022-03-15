import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test Area ensuring proper operations of MinStack
 * @author ivansy
 * @version 03/14/22
 */
class MinStackTest {

    private MinStack mStack;

    @BeforeEach
    void setUp() {
        mStack = new MinStack();
        mStack.push(3);
        mStack.push(5);
        mStack.push(6);
        mStack.push(1);
        mStack.push(2);
    }

    /**
     * Test method for {@link MinStack#pop()}.
     */
    @Test
    void test_IsEmpty() {
        // CHECK NOT EMPTY
        assertEquals(false, mStack.isEmpty());
        // CHECK mStack now empty
        mStack = new MinStack();
        assertEquals(true, mStack.isEmpty());

    }

    /**
     * Test method for {@link MinStack#isEmpty()}.
     */
    @Test
    void test_IsEmptyAfterDeletion() {
        // CHECK NOT EMPTY
        assertEquals(false, mStack.isEmpty());
        // CHECK mStack now empty after popping off all elements
        mStack.pop();
        mStack.pop();
        mStack.pop();
        mStack.pop();
        mStack.pop();
        assertEquals(true, mStack.isEmpty());
    }

    /**
     * Test method for {@link MinStack#push(int)}.
     */
    @Test
    void test_Push() {
        final String contents = new String("2, 1, 6, 5, 3");
        // CHECK string content
        assertEquals(contents, mStack.toString());
        // CHECK AFTER PUSHING
        mStack.push(-5);
        assertEquals("-5, 2, 1, 6, 5, 3", mStack.toString());
    }

    /**
     * Test method for {@link MinStack#push(int)}.
     */
    @Test
    void test_PushAfterPop() {
        mStack.pop();
        final String contents = new String("1, 6, 5, 3");
        // CHECK string content after POP
        assertEquals(contents, mStack.toString());
        // CHECK AFTER PUSHING
        mStack.push(1);
        assertEquals("1, 1, 6, 5, 3", mStack.toString());
    }

    /**
     * Test method for {@link MinStack#pop()}.
     */
    @Test
    void test_Pop() {
        int test_Value = mStack.pop();
        assertEquals(test_Value, 2);
        mStack.pop();
        mStack.pop();
        // CHECK string content after 3x pop
        final String contents = new String("5, 3");
        assertEquals(contents, mStack.toString());
        // CHECK string content after another pop
        mStack.pop();
        assertEquals("3", mStack.toString());
    }

    /**
     * Test method for {@link MinStack#peek()}.
     */
    @Test
    void peek() {
        // initial check to the top of stack before popping
        int test_Value = mStack.peek();
        assertEquals(test_Value, 2);
        final String contents = new String("2, 1, 6, 5, 3");
        assertEquals(contents, mStack.toString());
        // check after popping two values
        mStack.pop();
        mStack.pop();
        assertEquals("6, 5, 3", mStack.toString());
    }

    /**
     * Test method for {@link MinStack#getMin()}.
     */
    @Test
    void getMin() {
        // CHECK to see if the min is retrieved at all times
        assertEquals(1, mStack.getMin());
        mStack.push(7);
        // CHECK if min is still valid after pushing
        assertEquals(1, mStack.getMin());
        mStack.push(-2);
        assertEquals(-2, mStack.getMin());
        mStack.pop(); // pops -2
        assertEquals(1, mStack.getMin());
        mStack.pop(); // pops 7
        mStack.pop(); // pops 2
        mStack.pop(); // pops 1
        // CHECK IF NEW MIN
        assertEquals(3, mStack.getMin());
        final String contents = new String("6, 5, 3");
        assertEquals(contents, mStack.toString());
    }
}