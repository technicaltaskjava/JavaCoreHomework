package task1.taskA;

public class TaskA
    {
        StringBuffer text;

        public TaskA(StringBuffer text)
            {
                this.text = text;
            }


        public StringBuffer reversEndStart()
            {
                StringBuffer buffer = new StringBuffer();
                String[] sentence = text.toString().split("[\\.]\\s?");
                String[] sentenceResult = new String[sentence.length];

                for (int i = 0; i < sentence.length; i++)
                    {
                     sentenceResult[i] = sentence[i].trim().replaceAll("(?U)^(\\w+)(.*)(\\b\\w+)([.?!]?$)", "$3$2$1$4");
                    }

                for (String texts : sentenceResult)
                    {
                        buffer.append(texts + " ");
                    }
                return buffer;
            }
    }
