package it.unibo.inner.impl;

import java.util.Iterator;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T>{

    private final T[] items;

    

    public IterableWithPolicyImpl(T[] items) {
        this.items = items;
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {

    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator(items);
    }

    private class MyIterator implements Iterator<T> {

        private final T[] items;
        private int index;

        public MyIterator(T[] items) {
            this.items = items;
            this.index = 0;
        }

        public boolean hasNext() {
            if (this.index < items.length) {
                return true;
            }
            return false;
        }

        @Override
        public T next() {
            if (this.hasNext()) {
                this.index = this.index +1;
                return items[index];
            } else {
                return null;
            }
        }
        
    }
    
}
