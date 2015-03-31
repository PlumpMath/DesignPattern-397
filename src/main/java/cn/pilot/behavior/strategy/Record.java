package cn.pilot.behavior.strategy;

public class Record {
    private SortingAlgorithm sort;

    public Record(SortingAlgorithm sort) {
        this.sort = sort;
    }

    public void sortRecord() {
        sort.sort();
    }
}