package cn.pilot.behavior.memento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Memento {
    private int age;
    private String skill;
}