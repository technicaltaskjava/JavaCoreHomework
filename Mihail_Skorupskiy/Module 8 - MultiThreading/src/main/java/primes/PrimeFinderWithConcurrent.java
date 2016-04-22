package primes;

public class PrimeFinderWithConcurrent extends PrimeFinder {

    public PrimeFinderWithConcurrent(int start, int end){
        super(start, end);
    }

    @Override
    protected void findPrimes(){
        for (int i = start; i < end; i++){
            int temp = checkPrime(i);
            if (temp != 0){
                Primes.resultsConcurrent.add(temp);
            }
        }
    }
}
