package implementations;

import interfaces.Deque;

import java.util.Iterator;

public class ArrayDeque<E> implements Deque<E> {
    private final static int INITIAL_CAPACITY = 7;

    private Object[] elements;
    private int capacity;
    private int first;
    private int last;
    private int size;

    public ArrayDeque(){
        this.elements = new Object[INITIAL_CAPACITY];
        this.capacity = INITIAL_CAPACITY;
        this.first = this.last = INITIAL_CAPACITY / 2;
        this.size = 0;
    }

    @Override
    public void add(E element) {
        this.addLast(element);
    }

    @Override
    public void offer(E element) {
        this.addLast(element);
    }

    private void grow(boolean growOnTheLeft) {
        Object[] newElements = new Object[this.capacity *= 2];

        // ----- grow on the left/right -------------------------------------------

        int newFirst = this.first + (growOnTheLeft ? this.capacity/2 : 0);
        int newLast  = this.last  + (growOnTheLeft ? this.capacity/2 : 0);

        // this.first = 0   this.last = 6
        //   0    1    2    3    4    5    6
        // [10] [11] [12] [13] [14] [15] [16]
        // ----------------------------------------------------------------------
        // old capacity = 7  ===>  new capacity = 14
        // ----------------------------------------------------------------------
        // newFirst = 7   newLast = 13 (growOnTheLeft = true)
        //   0    1    2    3    4    5    6    7    8    9   10   11   12   13
        // [**] [**] [**] [**] [**] [**] [**] [10] [11] [12] [13] [14] [15] [16]

        // newFirst = 0   newLast = 6  (growOnTheLeft = false)
        //   0    1    2    3    4    5    6    7    8    9   10   11   12   13
        // [10] [11] [12] [13] [14] [15] [16] [**] [**] [**] [**] [**] [**] [**]

        // ----- end grow on the left/right ---------------------------------------


//        // ----- grow in the middle -----------------------------------------------
//
//        int newFirst = (this.capacity - this.size)/2 ;
//        int newLast = newFirst + this.size - 1;
//
//        // ----- end grow in the middle -------------------------------------------


        for (int i = 0; i < this.size; i++)
            newElements[i + newFirst] = this.elements[i + this.first];

        this.first = newFirst;
        this.last = newLast;

        this.elements = newElements;

    }

    @Override
    public void addFirst(E element) {
        if (this.first == 0)
            this.grow(true);

        this.elements[isEmpty() ? this.first : --this.first] = element;
        this.size++;
    }

    @Override
    public void addLast(E element) {
        if (this.last == this.capacity - 1)
            this.grow(false);

        this.elements[isEmpty() ? this.last : ++this.last] = element;
        this.size++;
    }

    @Override
    public void push(E element) {
        this.addFirst(element);
    }

    @Override
    public void insert(int index, E element) {
        index += this.first;
        this.checkRealIndex(index);

        if (this.last == this.capacity - 1)
            this.grow(false);

        for (int i = this.last; i >= index; i--)
            this.elements[i+1] = this.elements[i];

        this.elements[index] = element;
        this.last++;

        this.size++;
    }

    @Override
    public void set(int index, E element) {
        index += this.first;
        this.checkRealIndex(index);

        this.elements[index] = element;
    }

    @Override
    public E peek() {
        return this.getFromRealIndex(this.first);
    }

    @Override
    public E poll() {
        return this.removeFirst();
    }

    @Override
    public E pop() {
        return this.removeFirst();
    }

    @Override
    public E get(int index) {
        index += this.first;
        this.checkRealIndex(index);

        return this.getFromRealIndex(index);
    }

    @Override
    public E get(Object object) {
        if (this.isEmpty())
            return null;

        for (int i = this.first; i <= this.last; i++){
            if (this.elements[i].equals(object))
                return this.getFromRealIndex(i);
        }
        return null;

//        for (Object element : this.elements) {
//            if (element.equals(object))
//                return (E) element;
//        }
//        return null;

//        return (E) Arrays.stream(this.elements)
//                .filter(e -> e.equals(object))
//                .findFirst()
//                .orElse(null);
    }

    @Override
    public E remove(int index) {
        index += this.first;
        this.checkRealIndex(index);

        E toRemove = this.getFromRealIndex(index);

        for (int i = index; i < this.last; i++)
            this.elements[i] = this.elements[i + 1];

        this.elements[this.size == 1 ? this.last : this.last--] = null;

        this.size--;

        return toRemove;
    }

    @SuppressWarnings("unchecked")
    private E getFromRealIndex(int index){
        if (this.isEmpty())
            return null;

        return (E) this.elements[index];
    }

    private void checkRealIndex(int index) {
        if (index < this.first || index > this.last)
            throw new IndexOutOfBoundsException("The index " + (index - this.first) + " is out of bound.");
    }

    @Override
    public E remove(Object object) {
        if (this.isEmpty())
            return null;

        E toRemove = null;

        for (int i = this.first; i <= this.last; i++) {
            if (this.elements[i].equals(object))
                toRemove = this.remove(i - this.first);
        }
        return toRemove;
    }

    @Override
    public E removeFirst() {
        if (this.isEmpty())
            return null;

        E toRemove = this.getFromRealIndex(this.first);
        this.elements[this.size == 1 ? this.first : this.first++] = null;

        this.size--;

        return toRemove;

    }

    @Override
    public E removeLast() {
        if (this.isEmpty())
            return null;

        E toRemove = this.getFromRealIndex(this.last);
        this.elements[this.size == 1 ? this.last : this.last--] = null;

        this.size--;

        return toRemove;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int capacity() {
        return this.capacity;
    }

    @Override
    public void trimToSize() {
        Object[] newElements = new Object[this.size];
        this.capacity = this.size;

        for (int i = 0; i < this.size; i++)
            newElements[i] = this.elements[i + this.first];

        this.last -= this.first;
        this.first = 0;

        this.elements = newElements;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int iterIndex = first;

            @Override
            public boolean hasNext() {
                return iterIndex <= last;
            }

            @Override
            public E next() {
                return getFromRealIndex(iterIndex++);
            }
        };
    }
}
