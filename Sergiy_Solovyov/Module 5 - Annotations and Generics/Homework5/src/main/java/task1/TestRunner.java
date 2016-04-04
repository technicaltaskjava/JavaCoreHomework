package task1;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 25.03.2016
 */
public class TestRunner {

    public static void runTest(String packageName){

        System.out.println("Start testing MyJunit\n");
        try {
            Iterable<Class> classes = TestRunner.getClasses(packageName);

            for(Class clazz: classes){
                test(clazz);
            }

        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void test(Class test){

        for (Method method : test.getMethods()) {

            if (method.isAnnotationPresent(Test.class)) {

                Annotation annotation = method.getAnnotation(Test.class);
                Test annTest = (Test) annotation;
                Class expected = annTest.expected();
                if (!annTest.ignore()) {

                    try {
                            System.out.printf("Test '%s', result: ", method.getName());
                            method.invoke(test.newInstance());

                    }
                    catch (IllegalAccessException|InvocationTargetException|InstantiationException ex) {

                        if(ex.getCause().getClass() == expected){

                            System.out.printf(" - passed: %s %n", ex.getCause());
                        }
                        else
                            System.out.printf(" - failed: %s %n", ex.getCause());
                    }

                } else {
                    System.out.printf("Test '%s' - ignored%n", method.getName());
                }

            }
        }
    }
    private static Iterable<Class> getClasses(String packageName) throws ClassNotFoundException, IOException
    {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<File>();
        while (resources.hasMoreElements())
        {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        List<Class> classes = new ArrayList<Class>();
        for (File directory : dirs)
        {
            classes.addAll(findClasses(directory, packageName));
        }

        return classes;
    }
    private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException
    {
        List<Class> classes = new ArrayList<Class>();
        if (!directory.exists())
        {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files)
        {
            if (file.isDirectory())
            {
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            }
            else if (file.getName().endsWith(".class"))
            {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }
}
