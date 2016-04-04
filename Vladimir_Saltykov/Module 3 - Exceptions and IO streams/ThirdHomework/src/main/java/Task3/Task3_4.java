package Task3;


import java.io.FileNotFoundException;

public class Task3_4 {

            private static final String FILE_NAME = "java_code.txt";
            private static final String FILE_OUTPUT ="test_file.txt";
            private static final String FILE_OUTPUT_BYTES ="test_file_b.txt";
            private static final String[] WORDS = { "abstract","continue","for","new","switch",
                    "assert","default","goto","package","synchronized",
                    "boolean","do","if","case","enum","instanceof","return"	,"transient",
                    "catch","extends","int","short","try",
                    "char","final","interface","static","void",
                    "class","long","strictfp","volatile",
                    "float" ,"native","	super","while","private","this",
                    "break","double","implements","protected","throw",
                    "byte","else","import","public","throws",
                    };


            public static void main(String[] args) throws FileNotFoundException {


                WordsSearcher wordsSearcher = new WordsSearcher();

                String text = wordsSearcher.search(FILE_NAME,WORDS);
                wordsSearcher.writeByBytes(FILE_OUTPUT_BYTES, text);
                wordsSearcher.write(FILE_OUTPUT, text);

                System.out.println(String.format("File reading:\n%s", wordsSearcher.read(FILE_OUTPUT)));


            }


        }

