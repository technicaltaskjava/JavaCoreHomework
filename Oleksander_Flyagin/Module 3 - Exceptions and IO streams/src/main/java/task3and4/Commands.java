package task3and4;


public class Commands
    {

        private String splitWords[];
        private String words[] = null;


        public String line(String texts)
            {
                splitWords = splitMath(texts);
                for (String word : splitWords)
                    {
                        if (Words.getWords(word))
                            {
                                addWords(word);
                            }
                    }
                return serch(words);
            }


        private void addWords(String word)
            {
                int size;
                if (words == null)
                    {
                        size = 0;
                        words = new String[1];
                        words[0] = word;
                    }
                else
                    {
                        size = words.length;
                    }
                String[] temp = new String[size + 1];
                for (int stap = 0; stap < size; stap++)
                    {
                        temp[stap] = words[stap];
                    }
                temp[size] = word;
                words = temp;
            }


        private String serch(String[] words)
            {
                int count =0;
                String word = "";
                for (String addword : words)
                    {
                        word += addword + " ";
                    }
                String[] array = new String[0];

                int start = 0;
                int end = 0;
                while (true)
                    {
                        end = word.indexOf(" ", start);
                        if (end == -1) break;
                        String s = word.substring(start, end);
                        start = end + 1;
                        if (!isPresent(array, s))
                            {
                                array = newStringArray(array, s);
                            }
                    }
                String rezult = "";
                for (String s : array)
                    {
                        rezult += s + " \n";
                        count++;
                    }


                return rezult.trim() + "\nWords are : " + count ;
            }

        private String[] newStringArray(String[] array, String str)
            {
                String[] newArray = new String[array.length + 1];
                for (int i = 0; i < array.length; i++)
                    {
                        newArray[i] = array[i];
                    }
                newArray[newArray.length - 1] = str;
                return newArray;
            }

        private boolean isPresent(String[] words, String str)
            {
                for (String word : words)
                    {
                        if (word.equals(str))
                            {
                                return true;
                            }
                    }
                return false;

            }


        private String[] splitMath(String line)
            {

                String[] words = null;
                String[] temp;
                String cash = "";

                temp = line.split(";");
                for (String word : temp)
                    {
                        cash += word + " ";
                    }
                words = cash.split(" ");
                return words;
            }
    }
