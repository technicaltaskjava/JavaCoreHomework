package transfers;

import transfers.exceptions.NegativeBalance;
import transfers.utility.Transfer;

public class RunnableTransfer implements Runnable {

    private TransferManager manager;

    public RunnableTransfer(TransferManager manager){
        this.manager = manager;
    }

    @Override
    public void run(){
        while (manager.getSize() > 0){
            try {
                makeTransfer(manager.getTask());
            } catch (InterruptedException e){
                System.out.println("Something went terribly wrong.");
                Thread.currentThread().interrupt();
            }
        }
    }

    private void makeTransfer(Transfer transfer){
        try {
            transfer.commit();
            System.out.println(transfer + " is completed.");
        } catch (NegativeBalance e){
            manager.addDelayed(transfer);
            System.out.println(transfer + " is delayed.");
            System.out.println("Reason: " + e);
        }
    }
}
