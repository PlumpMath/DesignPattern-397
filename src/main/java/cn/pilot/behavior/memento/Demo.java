package cn.pilot.behavior.memento;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
//        List<Originator.Memento> savedStates = new ArrayList<>();
//
//        Originator originator = new Originator();
//
//        originator.set("State1");
//
//        originator.set("State2");
//        savedStates.add(originator.saveToMemento());
//
//        originator.set("State3");
//        savedStates.add(originator.saveToMemento());
//
//        originator.restoreFromMemento(savedStates.get(1));
//
//        originator.restoreFromMemento(savedStates.get(0));
        Originator me = new Originator();

        // when I was 17
        me.setAge(17);
        me.setSkills("none");
        me.setBadMemories("Dark street");

        // save!
        Caretaker whenIWas17 = new Caretaker(me.saveToMemento());

        // when I was 27
        me.setAge(27);
        me.setSkills("basic java");
        me.setBadMemories("Losing memories");

        // save!
        Caretaker whenIWas27 = new Caretaker(me.saveToMemento());

        // time travel
        me.restoreFromMemento(whenIWas17.getMemento());

        me.restoreFromMemento(whenIWas27.getMemento());
    }
}