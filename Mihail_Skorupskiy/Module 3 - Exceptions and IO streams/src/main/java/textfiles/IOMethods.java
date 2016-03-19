package textfiles;

import exceptions.IncorrectFileFormatException;

import java.io.*;

public class IOMethods {

    public static void writeStringsToFile(String[] data, String fileName) throws Exception{
        if (fileName.toLowerCase().endsWith(".txt")) {
            if (data != null) {
                PrintWriter writer = null;
                try {
                    writer = new PrintWriter(new FileWriter(fileName, true));
                    printToFile(writer, data);
                } catch (IOException e) {
                    throw e;
                } finally {
                    if (writer != null) {
                        writer.close();
                    }
                }
            } else{
                throw new NullPointerException("Empty input.");
            }
        } else{
            throw new IncorrectFileFormatException("Not a .txt file.");
        }
    }

    public static void rewriteStringsToFile(String[] data, String fileName) throws Exception{
        if(fileName.toLowerCase().endsWith(".txt")) {
            if (data != null) {
                File file = new File(fileName);
                boolean isDeleted = false;
                if (file.exists() && file.length() > 0) {
                    try {
                        isDeleted = file.delete();
                    } catch (SecurityException e) {
                        throw e;
                    }
                }
                if (isDeleted) {
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        throw e;
                    }
                }
                if (file.exists()) {
                    PrintWriter writer = null;
                    try {
                        writer = new PrintWriter(new FileWriter(fileName));
                        writer.flush();
                        printToFile(writer, data);
                    } catch (IOException e) {
                        throw e;
                    } finally {
                        if (writer != null) {
                            writer.close();
                        }
                    }
                }
            } else{
                throw new NullPointerException("Empty input.");
            }
        } else{
            throw new IncorrectFileFormatException("Not a .txt file.");
        }
    }

    //This method is used only by writeStringsToFile and rewriteStringsToFile
    private static void printToFile(PrintWriter writer, String[] data){
        for (int i = 0; i < data.length; i++) {
            try {
                writer.println(data[i]);
            } catch (NullPointerException e) {
                throw new NullPointerException("Incorrect input.");
            }
        }
    }

    public static String[] readStringsFromFile(String fileName) throws Exception{
        if (fileName.toLowerCase().endsWith(".txt") ||
            fileName.toLowerCase().endsWith(".java") ||
            fileName.toLowerCase().endsWith(".properties")) {
            BufferedReader reader;
            int lineNumber = 0;
            try {
                reader = new BufferedReader(new FileReader(fileName));
                try {
                    while (reader.readLine() != null){
                        lineNumber++;
                    }
                } catch (NullPointerException e) {
                    throw e;
                } catch (IOException e) {
                    throw e;
                } finally {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        throw e;
                    }
                }
            } catch (FileNotFoundException e) {
                throw e;
            }
            String[] data = new String[lineNumber];
            try{
                reader = new BufferedReader(new FileReader(fileName));
                for (int i = 0; i < lineNumber; i++){
                    data[i] = reader.readLine();
                }
            } catch(IOException e){
                throw e;
            }
            return data;
        } else {
            throw new IncorrectFileFormatException("Not a .txt or .java file.");
        }
    }

    //This method is an alternative to readBytesFromFile, and in conjunction with CodeAnalyzer.analyzeString()
    //can be used to analyze java code using only character streams, as specified in the task.
    public static String readStringFromFile(String fileName) throws Exception{
        if (fileName.toLowerCase().endsWith(".txt") || fileName.toLowerCase().endsWith(".java")) {
            BufferedReader reader;
            String data = new String();
            int LineNumber = 0;
            try {
                reader = new BufferedReader(new FileReader(fileName));
                try {
                    while (reader.readLine() != null){
                        LineNumber++;
                    }
                } catch (NullPointerException e) {
                    throw e;
                } catch (IOException e) {
                    throw e;
                } finally {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        throw e;
                    }
                }
            } catch (FileNotFoundException e) {
                throw e;
            }
            try{
                reader = new BufferedReader(new FileReader(fileName));
                for (int i = 0; i < LineNumber; i++){
                    data += reader.readLine();
                }
            } catch(IOException e){
                throw e;
            }
            return data;
        } else {
            throw new IncorrectFileFormatException("Not a .txt or .java file.");
        }
    }

    public static int[] readBytesFromFile(String fileName) throws Exception{
        if (fileName.toLowerCase().endsWith(".txt") || fileName.toLowerCase().endsWith(".java")) {
            BufferedInputStream reader;
            File file = new File(fileName);
            int[] data = new int[(int)file.length()];
            try {
                reader = new BufferedInputStream(new FileInputStream(fileName));
                try {
                    for (int i = 0; i < data.length; i++) {
                        data[i] = reader.read();
                    }
                } catch (NullPointerException e) {
                    throw e;
                } catch (IOException e) {
                    throw e;
                } finally {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        throw e;
                    }
                }
            } catch (FileNotFoundException e) {
                throw e;
            }
            return data;
        } else {
            throw new IncorrectFileFormatException("Not a .txt or .java file.");
        }
    }

    public static void stringArrayPrint(String[] array){
        if(array != null) {
            for (int i = 0; i < array.length; i++) {
                System.out.println(array[i]);
            }
        }
    }

    public static String validatedInput() throws IOException{
        String buffer;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            buffer = reader.readLine();
        } catch(IOException e){
            throw e;
        }
        return buffer;
    }

    public static int intInput() throws IOException {
        String input;
        try {
            input = validatedInput();
        } catch(IOException e){
            throw e;
        }
        int buffer = -1;
        try {
            buffer = Integer.valueOf(input);
        } catch (Throwable NumberFormatException) {
            System.out.println("Input is not a number.");
        }
        return buffer;
    }


    //This is an unused, but fully functional method for writing binary data into a text file using byte stream.

    /*public static void writeBytesToFile(int[] data, String fileName) throws Exception{
        if (fileName.toLowerCase().endsWith(".txt")) {
            FileOutputStream writer = null;
            try {
                writer = new FileOutputStream(fileName);
                if (data != null) {
                    for (int i = 0; i < data.length; i++) {
                        try {
                            writer.write(data[i]);
                        } catch (NullPointerException e) {
                            throw e;
                        }
                    }
                } else{
                    throw new NullPointerException("Empty input.");
                }
            } catch (IOException e) {
                throw e;
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException e){
                        throw e;
                    }
                }
            }
        } else{
            throw new IncorrectFileFormatException("Not a .txt file.");
        }
    }*/

}
