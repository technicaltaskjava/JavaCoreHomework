package primes;

public class PrimeFinder implements Runnable{

    int start;
    int end;

    public PrimeFinder(int start, int end){
        this.start = start;
        this.end = end;
    }

    @Override
    public void run(){
        findPrimes();
    }

    protected int checkPrime(int input){
        int buffer = input;
        for (int j = 2; j < (input/2)+1; j++){
            if (input%j == 0){
                buffer = 0;
                break;
            }
        }
        return buffer;
    }

    protected void findPrimes(){
        for (int i = start; i < end; i++){
            int temp = checkPrime(i);
            if (temp != 0){
                Primes.write(temp);
            }
        }
    }
}
