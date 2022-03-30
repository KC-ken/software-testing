//import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.stream.Stream;

public class PriorityQueueTest {
    static Stream<Arguments> streamProvider() {
        return Stream.of(
                Arguments.of(new int[] {3, 0, 9, 5, 1}, new int[] {0, 1, 3, 5, 9}),
                Arguments.of(new int[] {1, 0, 7, 5}, new int[] {0, 1, 5, 7}),
                Arguments.of(new int[] {9, 5, 2, 7}, new int[] {2, 5, 7, 9}),
                Arguments.of(new int[] {3, 0, -9, -5, 1}, new int[] {-9, -5, 0, 1, 3}),
                Arguments.of(new int[] {-9, 5, 2, -7}, new int[] {-9, -7, 2, 5})
        );
    }

    @ParameterizedTest(name="#{index} - Test with Argument={0}, {1}")
    @MethodSource("streamProvider")
    public void PriorityQueue_RunTest(int[] random_array, int[] correct_array) {
        PriorityQueue<Integer> test = new PriorityQueue<Integer>();
//        int index = 0;
//        Integer s;
        int[] result = new int[random_array.length];

        // random_array add to PriorityQueue
        for (int randNum : random_array)
            test.add(randNum);

        // get PriorityQueue result
        for (int i = 0; i < random_array.length; ++i)
            result[i] = test.poll();

        assertArrayEquals(correct_array, result);
    }

    @Test
    public void whenExeptionThrown_thenInitialCapacityLessThanOne() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            PriorityQueue<Integer> testpq = new PriorityQueue<>(0);
        });
    }

    @Test
    public void whenExeptionThrown_thenAddNullElement() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            PriorityQueue<Integer> testpq = new PriorityQueue<>();
            testpq.add(null);
        });
    }

    @Test
    public void whenExeptionThrown_thenToArrayToADifferentTypeArray() {
        Exception exception = assertThrows(ArrayStoreException.class, () -> {
            PriorityQueue<Integer> testpq = new PriorityQueue<>();
            testpq.add(10);
            testpq.add(0);
            testpq.add(120);

            String[] s = new String[testpq.size()];
            String[] testS = testpq.toArray(s);
        });
    }

}
