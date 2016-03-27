package task1;

import task1.reader.FilesReader;
import task1.taskA.TaskA;
import task1.taskB.TaskB;
import task1.taskC.TaskC;
import task1.taskD.TaskD;

import java.io.*;


public class Main
    {
        public static void main(String[] args)
            {
                File file = new File("src\\main\\java\\task1\\texts.txt");
                String text;
                FilesReader fileReader = new FilesReader(file);
                text = fileReader.reader();
                System.out.println(text);
                Shifts shifts = new Shifts(text);
                System.out.println("NEW TXT");
                System.out.println(shifts.shiftMath().toString());


                System.out.println("Task1 A");
                TaskA taskA = new TaskA(shifts.shiftMath());
                System.out.println(taskA.reversEndStart().toString());



                System.out.println("Task1 B");
                TaskB taskB = new TaskB();
                taskB.shiftMathEnter(shifts.shiftMathToString().toString());


                System.out.println("Task1 C");
                System.out.println("TEXT");
                System.out.println(shifts.shiftMath().toString());
                System.out.println("NEW TEXT");
                TaskC taskC =new TaskC(shifts.shiftMathToString().toString(), 6);
                taskC.getSerchWords();


                System.out.println("Task1 D");
                System.out.println("TEXT");
                System.out.println(shifts.shiftMath().toString());
                System.out.println("NEW TEXT");
                TaskD taskD =new TaskD(shifts.shiftMathToString().toString());
                taskD.getTextRevers();


            }


    }
