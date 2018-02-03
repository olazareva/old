package ua.lazareva.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;


public class Reflection {

    /**
     * Метод принимает object и вызывает у него все методы без параметров
     */
    public static void invokeAllMethodsWithEmptyParam(Object obj) throws InvocationTargetException, IllegalAccessException {
        for (Method method : obj.getClass().getMethods()) {
            //noinspection Since15
            if (method.getParameterCount() == 0) {
                method.setAccessible(true);
                method.invoke(obj);
                method.setAccessible(false);
            }
        }
    }

    /**
     * Метод принимает класс и возвращает созданный объект этого класса
     */
    public Object getInstance(Class className)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return className.newInstance();
    }

    /**
     * Метод принимает object и выводит на экран все сигнатуры методов в который есть final
     */
    public void printFinalMethods(Object obj) {
        for (Method method : obj.getClass().getMethods()) {
            if (Modifier.isFinal(method.getModifiers())) {
                System.out.println(method);
            }
        }
    }

    /**
     * Метод принимает Class и выводит все не публичные методы этого класса
     */
    public void printNonPublicMethods(Class className) {
        for (Method method : className.getDeclaredMethods()) {
            if (!Modifier.isPublic(method.getModifiers())) {
                System.out.println(method);
            }
        }
    }

    /**
     * Метод принимает Class и выводит всех предков класса и все интерфейсы которое класс имплементирует
     */
    public void printAllParentClassesOrInterfaces(Class className) {
        Class classIterator = className;

        while (classIterator.getSuperclass() != null) {
            System.out.println(classIterator.getSuperclass());
            classIterator = classIterator.getSuperclass();
        }
        printAllParentInterfaces(className);
    }

    private void printAllParentInterfaces(Class<?> className) {
        for (Class iface : className.getInterfaces()) {
            System.out.println(iface);
            printAllParentInterfaces(iface);
        }
    }

    /**
     * Метод принимает объект и меняет всего его приватные поля на их нулевые значение (null, 0, false etc)+
     */
    public void changeValuesOfPrivateFields(Object obj) throws IllegalAccessException {
        for (Field field : obj.getClass().getDeclaredFields()) {
            if (Modifier.isPrivate(field.getModifiers())) {
                field.setAccessible(true);
                Class fieldType = field.getType();
                if (fieldType.equals(Boolean.class) || fieldType.equals(boolean.class)) {
                    field.set(obj, false);
                } else if (fieldType.equals(Integer.class) || fieldType.equals(int.class)) {
                    field.set(obj, 0);
                } else {
                    field.set(obj, null);
                }
                field.setAccessible(false);
            }
        }
    }

    /**
     * Принимает объект и вызывает методы проанотированные аннотацией @Run (аннотация Ваша, написать самим)
     */
    public void invokeRunAnnotatedMetods(Object obj) throws InvocationTargetException, IllegalAccessException {
        for (Method method : obj.getClass().getMethods()) {

            if (method.isAnnotationPresent(Run.class)) {
                method.invoke(obj);
            }
        }
    }

    /**
     * Принимает объект. Поля проаннотиваные аннотацией @Inject заполняет объектом того класса который находиться
     * в поле аннотации Class clazz(). Если поле аннотации содержит ссылку на Void.class.
     * Создает пустой экзепляр класса, базируясь на типе поля (аннотация Ваша, написать самим)
     */
    public void initializeInjectedFields(Object obj) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        Inject annotation;
        for (Field field : obj.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Inject.class)) {

                annotation = field.getAnnotation(Inject.class);
                if (annotation.equals(Void.class)) {
                    obj = null;
                } else {
                    obj = annotation.className().newInstance();
                }
            }
        }
    }

}
