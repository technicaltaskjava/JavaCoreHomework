package task1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnalyzTest<T>
    {
        public void analyz(T clazz)
            {
                Method[] methods = clazz.getClass().getMethods();


                for (Method method : methods)
                    {
                        if (method.isAnnotationPresent(MyTest.class))
                            {
                                MyTest test = method.getAnnotation(MyTest.class);
                                if (!test.Ignore())
                                    {
                                        System.out.println(method.getName());
                                        Class expected = test.expected();
                                        try
                                            {
                                                method.invoke(clazz);
                                            }


                                        catch (Exception e)
                                            {

                                                if (e.getCause().toString()!= expected.getName())
                                                    {
                                                        System.out.println("Catch don't exp Exeption" + e.getCause());

                                                    }
                                                else
                                                    {
                                                        System.out.println("Catch exp Exeption " + e.getCause());

                                                    }
                                            }

                                    }
                            }
                    }
            }


    }
