package cn.pilot.behavior.memento;

import lombok.Setter;

@Setter
public class Originator {
    private int age;
    private String skills;
    private String badMemories;

    public Memento saveToMemento() {
        System.out.printf("Originator: Saving to Memento with age:%d, skills:%s\n", this.age, this.skills);
        return new Memento(this.age, this.skills);
    }

    public void restoreFromMemento(Memento memento) {
        this.age = memento.getAge();
        this.skills = memento.getSkill();
        System.out.printf("Originator: State after restoring from Memento with age:%d, skills:%s, bad memories:%s", this.age, this.skills, this.badMemories);
    }
}