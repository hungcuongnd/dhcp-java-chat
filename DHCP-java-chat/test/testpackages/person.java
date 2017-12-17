/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testpackages;

/**
 *
 * @author Administrator
 */
class person {
    String name;
    int age;

    @Override
    public String toString() {
        return "person{" + "name=" + name + ", age=" + age + '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
}
