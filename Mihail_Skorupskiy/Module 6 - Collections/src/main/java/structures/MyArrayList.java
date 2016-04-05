package structures;

import java.util.Arrays;

public class MyArrayList <T>  {

    private T[] buffer = null;

    public MyArrayList(){
        //Nothing to see here, Sonar, move along.
    }

    public MyArrayList(T[] source){
        buffer = Arrays.copyOf(source, source.length+1);
    }

    public void add(T source, int position){
        if (position >= 0) {
            if (position >= buffer.length) {
                increaseCapacity();
            }
            buffer[position] = source;
        } else {
            throw new IndexOutOfBoundsException("Negative index.");
        }
    }

    public void addArray(T[] source){
        int bufferEnd = buffer.length-1;
        while (buffer.length < bufferEnd + source.length){
            increaseCapacity();
        }
        for (int i = 0; i < source.length; i++){
            buffer[bufferEnd+i] = source[i];
        }
    }

    public void remove(int position){
        if (position > 0 && position < buffer.length){
            buffer[position] = null;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public T get(int position){
        if (position > 0 && position < buffer.length){
            return buffer[position];
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public int size(){
        return buffer.length;
    }

    private void increaseCapacity(){
        buffer = Arrays.copyOf(buffer, (int)(buffer.length*1.5f));
    }

    public T[] toArray(){
        return buffer;
    }

    public void trim(){
        int nulls = 0;
        for (int i = buffer.length - 1; i > 0; i--){
            if (buffer[i] != null){
                break;
            }
            nulls++;
        }
        buffer = Arrays.copyOf(buffer, buffer.length - nulls);
    }

    public void clear(){
        buffer = null;
    }

    @Override
    public String toString(){
        return Arrays.toString(buffer);
    }
}
