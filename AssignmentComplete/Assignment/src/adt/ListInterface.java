/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt;

/**
 *
 * @author shuej
 */
public interface ListInterface<T> {

    public boolean add(T newEntry);

    public boolean add(int newPosition, T newEntry);

    public boolean remove(T newEntry);

    public T remove(int givenPosition);

    public void clear();

    public boolean replace(int givenPosition, T newEntry);

    public T getEntry(int givenPosition);

    public boolean contains(T anEntry);

    public int getNumberOfEntries();

    public boolean isEmpty();

    public boolean isFull();

    public int size();

    public T get(int index);

    public void bubbleSort();
}
