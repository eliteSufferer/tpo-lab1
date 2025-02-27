package org.example;

import java.util.ArrayList;
import java.util.List;

public class ShellSort {
    private List<String> events = new ArrayList<>();

    public void sort(int[] array) {
        events.clear();
        events.add("START_SORTING");

        int n = array.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            events.add("GAP_SELECTED:" + gap);

            for (int i = gap; i < n; i++) {
                events.add("PROCESS_ELEMENT:" + i + " (gap=" + gap + ")");
                int temp = array[i];
                int j;

                for (j = i; j >= gap && array[j - gap] > temp; j -= gap) {
                    events.add("COMPARE:" + (j - gap) + " AND " + j);
                    array[j] = array[j - gap];
                    events.add("SWAP:" + (j - gap) + " AND " + j);
                }
                array[j] = temp;

                if (j != i) {
                    events.add("INSERT:" + temp + " TO_POS:" + j);
                }
            }
        }
        events.add("END_SORTING");
    }

    public List<String> getEvents() {
        return new ArrayList<>(events);
    }
}
