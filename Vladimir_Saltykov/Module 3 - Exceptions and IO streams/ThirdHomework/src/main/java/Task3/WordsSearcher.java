package Task3;

import java.io.*;

public class WordsSearcher {

    public String search (String fileName, String[] found) {
        StringBuilder builder = new StringBuilder();

        BufferedReader bufferReader = null;
        try {
            bufferReader = new BufferedReader(new FileReader("java_code.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("something wrong here");
            e.printStackTrace();
        }
            String output;
            int count=0;
        try {
            while ((output = bufferReader.readLine()) != null) {
                for (int i = 0; i < found.length; i++) {
                    if (output.contains(found[i])) {

                        builder.append(found[i] + " ");

                        count++;
                        File file = new File(fileName);
                        if (!file.exists()) {
                            file.createNewFile();
                        }

                    }
                }
            }
        }catch (IOException e) {
            System.out.println("something wrong");
        }

            builder.append("\namount of words = "+count);
        return builder.toString();
    }


    public boolean writeByBytes(String fileName, String text) throws FileNotFoundException {
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            OutputStreamWriter out = new OutputStreamWriter(fos, "UTF8");
            out.write(String.valueOf(text.getBytes()));

            out.close();
            return true;
        }
        catch (IOException e) {
            e.printStackTrace();
        }return false;
    }
    public boolean write(String fileName, String text) throws FileNotFoundException {
        return write(fileName, text);
    }

    public String read(String fileName) {
        StringBuilder builder = new StringBuilder();
        try {
            FileInputStream reader = new FileInputStream(fileName);
            BufferedInputStream bufferedReader = new BufferedInputStream(reader);
                int output;

                while ((output = bufferedReader.read()) != -1) {
                    builder.append((char) output);
                }
                reader.close();
            } catch (FileNotFoundException e) {
            e.printStackTrace();

    }catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return builder.toString();
    }

}

