package cn.pilot.behavior.strategy;

public class Demo {
    public static void main(String[] args) {
        new Record(new MergeSort()).sortRecord();

        new Record(new QuickSort()).sortRecord();
    }
}