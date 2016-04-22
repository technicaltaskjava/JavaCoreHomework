package task2;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Oleg on 20.04.2016.
 */
public class FileProcessConcurrent implements Runnable{

        private int startLine;
        private int endLine;
        private Account account1;
        private Account account2;
        private List<String> accountOperationsData;

        public FileProcessConcurrent(Account account1, Account account2, String fileName, int startLine, int endLine){
            this.account1 = account1;
            this.account2 = account2;
            this.startLine = startLine;
            this.endLine = endLine;
            try {
                accountOperationsData = AccountFileReader.getLines(fileName);
            } catch (FileNotFoundException e) {
                System.out.println("File not found!");
            }
        }

        public void run(){
            processLines();
        }

        public void processLines(){

            for (int i = startLine; i <= endLine; i++){
                String[] s = accountOperationsData.get(i-1).split("\\s");
                performOperation(s[0], Integer.valueOf(s[2]));
            }
        }



        private void performOperation(String account, int summ){
            Lock lock = new ReentrantLock();

                    if ("account1".equals(account)) {
                        lock.lock();
                        account1.withdraw(summ);
                        account2.deposit(summ);
                        lock.unlock();
                    }else {
                        lock.lock();
                        account2.withdraw(summ);
                        account1.deposit(summ);
                        lock.unlock();
                    }


            }

        }

