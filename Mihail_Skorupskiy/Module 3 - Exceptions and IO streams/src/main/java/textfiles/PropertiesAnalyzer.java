package textfiles;

import exceptions.NoKeyFoundException;

public class PropertiesAnalyzer {
    private static String[] properties;

    public static void fill(String fileName) throws Exception{
        reset();
        try {
            properties = IOMethods.readStringsFromFile(fileName);
        } catch (Exception e){
            throw e;
        }
        for (int i = 0; i < properties.length; i++){
            if (!properties[i].matches("[ :=]")){
                throw new NoKeyFoundException(i+1);
            }
        }
    }

    public static String[] getValues(){
        return properties;
    }

    private static void reset(){
        properties = null;
    }
}
