package com.epam.modul7.xmlParser.person;


public class Person
    {
        private String name;
        private int sumLines;
        private int wordsSize;

        public int getWordsSize()
            {
                return wordsSize;
            }

        public void setWordsSize(int wordsSize)
            {
                this.wordsSize = wordsSize;
            }

        public String getName()
            {
                return name;
            }

        public void setName(String name)
            {
                this.name = name;
            }

        public int getSumLines()
            {
                return sumLines;
            }

        public void setSumLines(int sumLines)
            {
                this.sumLines = sumLines;
            }


        private int averageWordsInLine()
            {
                return  wordsSize / sumLines;

            }




        @Override
        public String toString() {
            return "Actor{" +
                    " name ='" + name + '\'' +
                    ", Sum replica ='" + sumLines + '\'' +
                    ", Average words in ='" + averageWordsInLine() + '\'' +
                    '}';
        }




    }
