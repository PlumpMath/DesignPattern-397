package cn.pilot.behavior.memento;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * MementoHolder
 */
@AllArgsConstructor
@Getter
public class Caretaker {
    private Memento memento;
}