package it.unibo.inner.impl;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T>{
    private final List<T> items;
    private Predicate<T> filter;

    public IterableWithPolicyImpl(T[] items) {
        this(items, new Predicate<T>() {
            public boolean test(T elem) {
                return true;
            }     
        });
    }
    
    public IterableWithPolicyImpl(T[] items, Predicate<T> filter) {
        this.items = List.of(items);
        this.filter = filter;
    }



    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        this.filter = filter;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator(items);
    }

    private class MyIterator implements Iterator<T> {

        private final List<T> items;
        private int index;

        public MyIterator(List<T> items) {
            this.items = items;
            this.index = 0;
            this.nextSuitable();
        }

        public boolean hasNext() {
            if (this.index < items.size()) {
                index++;
                return true;      
            }
            return false;
        }

        private void nextSuitable(){
            while (this.index < items.size() && !filter.test(items.get(index))) {
                index++;
            }
        }

        @Override
        public T next() {
            if (this.hasNext()) {
                T itemToReturn;
                itemToReturn =  items.get(index);
                index++;
                this.nextSuitable();
                return itemToReturn;
            } else {
                throw new NoSuchElementException();
            }
        }
    }
    
}
