package ua.lazareva.reflection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.LinkedList;

public class TestClass {
    public int publicField = 18;
    private int size = 8;
    private boolean isEmpty = true;
    @Inject(className = String.class)
    private String privateString = "private";
    @Inject(className = LinkedList.class)
    private Object fieldInjectedWithSomeClass;
    @Inject
    private Object fieldInjectedWithDefaultClass;





    public Object getFieldInjectedWithSomeClass() {
        return fieldInjectedWithSomeClass;
    }

    public void setFieldInjectedWithSomeClass(Object fieldInjectedWithSomeClass) {
        this.fieldInjectedWithSomeClass = fieldInjectedWithSomeClass;
    }

    public Object getFieldInjectedWithDefaultClass() {
        return fieldInjectedWithDefaultClass;
    }

    public void setFieldInjectedWithDefaultClass(Object fieldInjectedWithDefaultClass) {
        this.fieldInjectedWithDefaultClass = fieldInjectedWithDefaultClass;
    }

    public void setPrivateString(String string) {
        this.privateString = string;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }


    @Run
    public void printMessageForString() {
        System.out.println("String variable has access level " + privateString);
    }

    @Run
    public void printMessageForSize() {
        System.out.println("Size is " + size);
    }

}