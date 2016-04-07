package structures;

import java.util.Arrays;

public class NumberList <T extends Number & Comparable> {

    private T[] buffer;

    public NumberList(T[] source){
        buffer = Arrays.copyOf(source, source.length);
    }

    public void add(T source, int position){
        if (position >= 0 && position < buffer.length) {
            buffer[position] = source;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public void removeByPosition(int position){
        if (position > 0 && position < buffer.length){
            buffer[position] = null;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public void increaseSize(int addedSize){
        buffer = Arrays.copyOf(buffer, buffer.length+addedSize);
    }

    public int size(){
        return buffer.length;
    }

    public T searchByValue(T value){
        int targetIndex = 0;
        double parameter = value.doubleValue();
        double prevResult = parameter;
        for (int i = 0; i < buffer.length; i++){
            if (Math.abs(parameter - buffer[i].doubleValue()) < prevResult){
                prevResult = parameter - buffer[i].doubleValue();
                targetIndex = i;
            }
        }
        return buffer[targetIndex];
    }

    public void removeByValue(T value){
        for (int i = 0; i < buffer.length; i++){
            if (buffer[i] != null && buffer[i].equals(value)){
                buffer[i] = null;
            }
        }
    }

    public void compress(){
        for (int i = 0; i < buffer.length; i++){
            if (buffer[i] == null){
                compressIteration(i);
            }
        }
        trim();
    }

    private void compressIteration(int index){
        for (int i = index; i < buffer.length; i++){
            if (buffer[i] != null){
                buffer[index] = buffer[i];
                buffer[i] = null;
                break;
            }
        }
    }

    private void trim(){
        int nulls = 0;
        for (int i = buffer.length - 1; i > 0; i--){
            if (buffer[i] != null){
                break;
            }
            nulls++;
        }
        buffer = Arrays.copyOf(buffer, buffer.length - nulls);
    }

    @Override
    public String toString(){
        return Arrays.toString(buffer);
    }
}
