import org.example.ShellSort;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

@DisplayName("ShellSort Event Sequence Tests")
class ShellSortTest {

    @DisplayName("Empty Array: remains empty and records only START and END events")
    @Tag("edge-case")
    @Test
    void testEmptyArray() {
        ShellSort shellSort = new ShellSort();
        int[] arr = {};
        shellSort.sort(arr);
        assertArrayEquals(new int[]{}, arr);

        List<String> events = shellSort.getEvents();
        assertSequence(events,
                "START_SORTING",
                "END_SORTING"
        );
    }

    @DisplayName("Already Sorted Array: no swap events during sorting")
    @Tag("sorted")
    @Test
    void testAlreadySorted() {
        ShellSort shellSort = new ShellSort();
        int[] arr = {1, 2, 3, 4, 5};
        shellSort.sort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);

        List<String> events = shellSort.getEvents();
        assertTrue(events.contains("GAP_SELECTED:2"));
        assertTrue(events.contains("GAP_SELECTED:1"));
        assertFalse(events.stream().anyMatch(e -> e.startsWith("SWAP")));
    }

    @DisplayName("Reverse Sorted Array: checks event order and swap occurrence")
    @Tag("reverse")
    @Test
    void testReverseSorted() {
        ShellSort shellSort = new ShellSort();
        int[] arr = {5, 4, 3, 2, 1};
        shellSort.sort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);

        List<String> events = shellSort.getEvents();
        assertTrue(events.indexOf("GAP_SELECTED:2") < events.indexOf("GAP_SELECTED:1"));
        assertTrue(events.contains("SWAP:0 AND 2"));
    }

    @DisplayName("Specific Scenario: verifies partial sequence of events")
    @Tag("scenario")
    @Test
    void testSpecificScenario() {
        ShellSort shellSort = new ShellSort();
        int[] arr = {5, 3, 8, 4, 2};
        shellSort.sort(arr);
        assertArrayEquals(new int[]{2, 3, 4, 5, 8}, arr);

        List<String> events = shellSort.getEvents();
        assertSequencePartial(events,
                "GAP_SELECTED:2",
                "PROCESS_ELEMENT:2 (gap=2)",
                "PROCESS_ELEMENT:3 (gap=2)",
                "PROCESS_ELEMENT:4 (gap=2)",
                "COMPARE:2 AND 4",
                "SWAP:2 AND 4",
                "COMPARE:0 AND 2",
                "SWAP:0 AND 2",
                "GAP_SELECTED:1",
                "PROCESS_ELEMENT:3 (gap=1)",
                "COMPARE:2 AND 3",
                "SWAP:2 AND 3"
        );
    }

    private void assertSequence(List<String> actual, String... expected) {
        assertEquals(expected.length, actual.size(), "Event count mismatch");
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], actual.get(i), "Event mismatch at index " + i);
        }
    }

    private void assertSequencePartial(List<String> actual, String... expectedOrder) {
        int currentIndex = 0;
        for (String expectedEvent : expectedOrder) {
            boolean found = false;
            while (currentIndex < actual.size()) {
                if (actual.get(currentIndex).equals(expectedEvent)) {
                    found = true;
                    currentIndex++;
                    break;
                }
                currentIndex++;
            }
            assertTrue(found, "Missing event: " + expectedEvent);
        }
    }
}