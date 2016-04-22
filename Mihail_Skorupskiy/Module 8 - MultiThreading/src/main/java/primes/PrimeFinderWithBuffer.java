package primes;

import java.util.Collection;
import java.util.HashSet;

public class PrimeFinderWithBuffer extends PrimeFinder{

    private Collection<Integer> buffer;

    public PrimeFinderWithBuffer(int start, int end){
        super(start, end);
        buffer = new HashSet<>();
    }

    @Override
    protected void findPrimes(){
        for (int i = start; i < end; i++){
            int temp = checkPrime(i);
            if (temp != 0){
                this.buffer.add(temp);
            }
        }
        Primes.results.addAll(buffer);
    }
}
