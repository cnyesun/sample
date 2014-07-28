package com.yesun.sample.list;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyList<E> implements List<E> {
	
	public static void main(String[] args){
		
		MyList<String> list = new MyList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add(2,"333");
		
		for(int i=0;i<list.size();i++) {
			System.out.println(">> " + list.get(i));
		}
		
	}
	
	
	
	private Object[] objects;
	private final int size = 16;
	private int currentSize = 0;
	
	public MyList(){
		objects = new Object[size];
	}
	

	@Override
	public boolean add(E e) {
		add(currentSize, e);
		return true;
	}

	@Override
	public void add(int index, E element) {
		if(index == currentSize){
			//表示默认追加
			objects[currentSize] = element;
			currentSize++;
			return;
		}
		//更好的性能
		System.arraycopy(objects, index, objects, index+1, currentSize-index);
		objects[index] = element;
		
//		if(index < currentSize){
//			for(int i=currentSize;i>=index;i--){
//				objects[i] = objects[i-1];
//			}
//			objects[index] = element;
//		}
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		
		for(int i=0;i < this.size();i++){
			objects[i] = null;
		}
		
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E get(int index) {
		return (E)objects[index];
	}

	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator<E> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E set(int index, E element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		return objects.length;
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

}
