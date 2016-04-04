package firstTask;


import java.io.*;

public class TextWorker {


        public String reader(String fileName) {
            StringBuilder stringBuilder = new StringBuilder();
            try (
                 FileInputStream fileInputStream = new FileInputStream(fileName);
                 BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, "cp1251"))) {
                String output;
                while ((output = bufferedReader.readLine()) != null) {
                    stringBuilder.append(output);
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            return stringBuilder.toString();
        }


        public String replaceWords(String text) {

            StringBuilder stringBuilder = new StringBuilder();
            String[] sentence = text.split("[\\!|\\.|\\?]+\\s?");
            String[] sentenceResult = new String[sentence.length];

            for (int i = 0; i < sentence.length; i++) {
                sentenceResult[i] = sentence[i].trim().replaceAll("(?U)^(\\w+)(.*)(\\b\\w+)([.?!]?$)", "$3$2$1$4");
                stringBuilder.append(sentenceResult[i]+" ");
            }

            return stringBuilder.toString().trim();

        }


        public String deleteWords(String text) {
            String[] sentence = text.split("[«»\\-,;:.!?\\s]+");
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < sentence.length; i++) {
                char first = sentence[i].charAt(0);
                if (!((sentence[i].length() == 4) && ((first != 'a') && (first != 'о') && (first != 'э') && (first != 'и') &&
                        (first != 'у') && (first != 'е') && (first != 'ё') && (first != 'ю') && (first != 'я') && (first != 'ы'))))
                {
                    stringBuilder.append(sentence[i]+" ");
                }
            }

            return stringBuilder.toString();
        }

        public String deleteFirstLetters(String text) {
            String firstLetters = "";
            String[] sentence = text.split("[«»\\-,;:.!?\\s]+");
            StringBuilder stringBuilder = new StringBuilder();
            for (String  s:sentence) {

                firstLetters=s.substring(1);

                stringBuilder.append(firstLetters+" ");
            }
            return  stringBuilder.toString().trim();

        }

        public boolean writer(String fileName, String text, boolean append) throws FileNotFoundException {
            try {
                FileOutputStream fos = new FileOutputStream(fileName);
                OutputStreamWriter out = new OutputStreamWriter(fos, "UTF8");
                out.write(text);

                out.close();
                return true;
            }
            catch (IOException e) {
                e.printStackTrace();
            }return false;
        }
        public boolean writer(String fileName, String text) throws FileNotFoundException {
            return writer(fileName, text, true);
        }
    }

