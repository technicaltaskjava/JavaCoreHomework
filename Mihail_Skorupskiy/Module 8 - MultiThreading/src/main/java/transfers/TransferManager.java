package transfers;

import transfers.exceptions.TransferException;
import transfers.utility.Transfer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

public class TransferManager {

    private ArrayBlockingQueue<Transfer> transfers = null;
    private ArrayList<Transfer> delayed = new ArrayList<>();

    public TransferManager(){
        readTransfers();
    }

    //This assumes every 3n+1-th line to be source account number,
    //every 3n+2-th line to be destination account number,
    //and every 3n-th line to be the amount of units to be transferred.
    private void readTransfers(){
        ArrayList<Transfer> buffer = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/transfers.txt"))) {
            String currentInput;
            while ((currentInput = reader.readLine()) != null) {
                long currentSource = Long.parseLong(currentInput);
                currentInput = reader.readLine();
                long currentDestination = Long.parseLong(currentInput);
                currentInput = reader.readLine();
                long currentAmount = Long.parseLong(currentInput);
                Transfer temp = createTransfer(currentSource, currentDestination, currentAmount);
                if (temp != null) {
                    buffer.add(temp);
                    System.out.println("Transfer is created.");
                }
            }
            transfers = new ArrayBlockingQueue<>(buffer.size());
            transfers.addAll(buffer);
            System.out.println("\nTotal read transfers: " + transfers.size() + "\n");
        } catch (IOException e){
            System.out.println("Read error." + e);
        }
    }

    private Transfer createTransfer(long source, long destination, long amount){
        try {
            return new Transfer(AccountManager.getAccount(source),
                                        AccountManager.getAccount(destination),
                                        amount);
        } catch (TransferException te) {
            System.out.println("Transfer was not created." + te);
            return null;
        }
    }

    public synchronized void addDelayed(Transfer transfer){
        delayed.add(transfer);
    }

    public Transfer getTask() throws InterruptedException{
        return transfers.take();
    }

    public synchronized int getSize(){
        return transfers.size();
    }

    public int getDelayedSize(){
        return delayed.size();
    }
}
