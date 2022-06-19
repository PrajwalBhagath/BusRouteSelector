package HashSetWithChaining;

import java.util.*;

public class HashSetWithChaining<E> implements Set<E> {

    Node<E>[] table;
    protected int size;
    private int numberOfItems = 0;

    public HashSetWithChaining(){
        size = 6;
        table = new Node[size];
        for(int i = 0; i < size; i++){
            Node<E> newNullNode = new Node<E>(null);
            table[i] = newNullNode;
        }
    }

    @Override
    public int size() {
        return numberOfItems;
    }

    @Override
    public boolean isEmpty() {
        if(numberOfItems == 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(Object o) {
        int index = o.hashCode()%size;

        if(index < 0){
            index *= -1;
        }

        if(table[index].element == null){
            return false;
        }

        if(table[index].element.equals(o)){
            return true;
        }else {
            Node<E> current = table[index];
            if(table[index].next != null){
                do{
                    if(current.element.equals(o)){
                        return true;
                    }else if(current.next != null) {
                        current = current.next;
                    }
                }while(current != null);
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new HashSetWithChainingIterator<E>(table);
    }

    //iterate through and chuck it into the array
    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    //chuck it into the array given
    @Override
    public <T> T[] toArray(T[] a) {
        int count = 0;
        for (Node<E> n: table){
            a[count] = (T) n;
            count++;
        }
        return a;
    }


    @Override
    public boolean add(E e) {
        double percentage = ((numberOfItems*100)/size);
        if(percentage>75){
            expandCapacity();
        }
        if(contains(e)){
            return false;
        }

        else {
           int index = e.hashCode()%size;
           if(index<0){
               index *= -1;
           }
           Node<E> current = table[index];
           Node<E> element = new Node<E>(e);

           if(table[index].element == null){
               table[index] = element;
               numberOfItems++;
               return true;
           }

           while (current.next != null){
               current = current.next;
           }

           current.next = element;
           element.prev = current;
           numberOfItems++;
           return true;
        }
    }

    public void expandCapacity(){
        System.out.println("Expanding capacity");

        Node<E>[] tempTable = table;

        clear();
        size = size*2;
        numberOfItems = 0;
        table = new Node[size];
        for(int i = 0; i < size; i++){
            Node<E> newNullNode = new Node<E>(null);
            table[i] = newNullNode;
        }

        Node<E> iteratorNode;
        for(Node<E> e: tempTable){
            if(e.element == null){
                continue;
            }
            else {
                iteratorNode = e;
                add(e.element);
                while (iteratorNode.next != null) {
                    add(e.element);
                    iteratorNode = iteratorNode.next;
                }
            }
        }

    }

    @Override
    public boolean remove(Object o) {
        if(contains(o)){
            int index = o.hashCode()%size;
            boolean s = true;

            Node<E> currentNode = table[index];

            while (s){
                if(currentNode.element == o){
                    if(currentNode.next == null){
                        Node<E> newNull = new Node<E>(null);
                        table[index] = newNull;
                        return true;
                    }else {
                    currentNode.prev.next = currentNode.next;
                    currentNode.next.prev = currentNode.prev;
                    return true;
                    }
                }
                currentNode = currentNode.next;
            }

        }
        return false;
    }

    //Java doc for set interface decried this method as optional
    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    //Java doc for set interface decried this method as optional
    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    //Java doc for set interface decried this method as optional
    @Override
    public boolean retainAll(Collection<?> c) {

        return false;
    }

    //Java doc for set interface decried this method as optional
    @Override
    public boolean removeAll(Collection<?> c) {
        c.removeAll(c);
        return true;
    }


    @Override
    public void clear() {
    Node<E>[] temp = new Node[size];
    table = temp;
    numberOfItems = 0;
    }

    @Override
    public String toString() {
        String s = "";
        Node<E> iteratorNode;
        for(int i = 0;i<size; i++){
            s+="Row "+(i+1)+": ";
            if(table[i].element == null){
                s+= "[null]\n";
            }
            else {
                iteratorNode = table[i];
                s += table[i].element;
                while (iteratorNode.next != null) {
                    s+="->"+iteratorNode.next.element;
                    iteratorNode = iteratorNode.next;
                }
                s += "\n";
            }
        }
        return s;
    }

    public class Node<E>{
        E element;
        public Node<E> next;
        public Node<E> prev;

        public Node(E e){
            this.element = e;
            next = null;
            prev = null;
        }

    }

    private class HashSetWithChainingIterator<E> implements Iterator<E>{

        Node<E> firstNode;
        Node<E> nextNode;
        int index;


        public HashSetWithChainingIterator(Node[] n){
            for(int i = 0; i < n.length; i++){
                if(n [i] != null){
                    index = i;
                    firstNode = (Node<E>) table[i];
                    break;
                }
            }


        }
        @Override
        public boolean hasNext() {
            if(firstNode.next != null){
                nextNode = firstNode.next;
                return true;
            }
            else {
                for(int i = index; i < numberOfItems; i++){
                    if(table[i] != null){
                        nextNode = (Node<E>) table[i];
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        public E next() {
            return nextNode.element;
        }
    }
}
