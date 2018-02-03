package ua.lazareva.reflection;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedList;


public class Test {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, InvocationTargetException {
        Reflection reflection = new Reflection();
        LinkedList name = new LinkedList();
        //String name = "Vova";
        Class clazz = name.getClass();
        TestClass testClass = new TestClass();

        //Object obj = Reflection.getInstance(clazz);
        //Reflection.runAllMethodsWithEmptyParam(name);
        // Reflection.returnFinalMethods(name);
        // Reflection.returnNonPublicMethods(clazz);
        //reflection.printAllParentClassesOrInterfaces(clazz);
        //reflection.changeValuesOfPrivateFields(testClass);
        //reflection.invokeRunAnnotatedMetods(testClass);
        reflection.initializeInjectedFields(testClass);
    }
}


