package interfaces;

public interface SimpleCollection<E> extends Iterable<E> {
    void add(E element);
    int size();
    int capacity();
    E get(int index);
    E removeAt(int index);
    boolean isEmpty();
}
