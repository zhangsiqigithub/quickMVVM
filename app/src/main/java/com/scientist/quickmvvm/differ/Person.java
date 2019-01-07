package com.scientist.quickmvvm.differ;

import android.util.Log;

import com.scientist.lib.Differ;
import com.scientist.lib.DifferManager;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * Author: zhangsiqi
 * Email: zsq901021@sina.com
 * Date: 2019/1/4
 * Time: 11:02 AM
 * Desc:
 */
public class Person {



    static Differ<Person> differ = new Differ<Person>() {
        @Override
        public boolean areItemsTheSame(Person t1, Person t2) {
            return t1.getId().equals(t2.getId());
        }

        @Override
        public boolean areContentsTheSame(Person t1, Person t2) {
            boolean name = t1.getName().equals(t2.getName());
            boolean age = t1.getAge() == t2.getAge();
            boolean man = t1.isMan() == t2.isMan();
            return name && age && man;
        }

        @Override
        public HashMap<String, Object> getChangePayload(Person t1, Person t2) {
            HashMap<String, Object> map = new HashMap<>();
            try {
                Field[] fields = t1.getClass().getDeclaredFields();
                for (Field f : fields) {
                    if (!f.isAccessible()) {
                        f.setAccessible(true);
                    }
                    Object v1 = f.get(t1);
                    Object v2 = f.get(t2);
                    if (v1 != null && v2 != null && !v1.equals(v2)) {
                        map.put(f.getName(), v2);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return map;
        }
    };

    static {
        DifferManager.getInstance().put(Person.class.getName(), differ);
    }

    private String name;
    private boolean isMan;
    private int age;
    private String id;

    public Person(String name, boolean isMan, int age, String id) {
        this.name = name;
        this.isMan = isMan;
        this.age = age;
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMan() {
        return isMan;
    }

    public void setMan(boolean man) {
        isMan = man;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
