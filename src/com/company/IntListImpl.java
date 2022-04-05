package com.company;

import com.company.exceptions.CheckIndexException;
import com.company.exceptions.ElementIsNullException;
import com.company.exceptions.ElementNotFoundException;

public class IntListImpl implements IntList {
    int[] myIntList = new int[10];
    int size = 0;

    @Override
    public int add(int item) {
        myIntList[size] = item;
        size++;
        increaseList();
        return item;
    }

    @Override
    public int add(int index, int item) {
        checkIndex(index);
        increaseList();
        for (int i = size - 1; i >= index; i--) {
            myIntList[i + 1] = myIntList[i];
        }
        myIntList[index] = item;
        size++;
        return item;
    }

    @Override
    public int set(int index, int item) {
        checkIndex(index);
        myIntList[index] = item;
        return item;
    }

    @Override
    public int removeByIndex(int index) {
        checkIndex(index);
        int result = myIntList[index];
        for (int i = index; i < size; i++) {
            myIntList[i] = myIntList[i + 1];
        }
        size--;
        return result;
    }

    @Override
    public int removeByValue(int item) {
        int a = -1;
        for (int i = 0; i < size; i++) {
            if (myIntList[i] == item) {
                a = i;
                break;
            }
        }
        if (a != -1) {
            removeByIndex(a);
        } else {
            throw new ElementNotFoundException("Такое число отсутсвует в списке");
        }
        return item;
    }

    @Override
    public boolean contains(int item) {
        sortList(myIntList);
        int min = 0;
        int max = this.size - 1;
        while (min <= max) {
            int middle = (min + max) / 2;
            if (item == this.get(middle)) {
                return true;
            }
            if (item < this.get(middle)) {
                max = middle - 1;
            } else {
                min = middle + 1;
            }
        }
        return false;
    }

    @Override
    public int indexOf(int item) {
        int result = -1;
        for (int i = 0; i < size; i++) {
            if (myIntList[i] == item) {
                result = i;
                break;
            }
        }
        return result;
    }

    @Override
    public int lastIndexOf(int item) {
        int result = -1;
        for (int i = size - 1; i >= 0; i--) {
            if (myIntList[i] == item) {
                result = i;
                break;
            }
        }
        return result;
    }

    @Override
    public int get(int index) {
        checkIndex(index);
        return myIntList[index];
    }

    @Override
    public boolean equals(IntList otherList) {
        if (otherList == null) {
            throw new ElementIsNullException("В качестве параметра передан пустой элемент");
        }
        boolean result = true;
        if (this.size != otherList.size()) {
            result = false;
        } else {
            for (int i = 0; i < size; i++) {
                if (this.get(i) != otherList.get(i)) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        boolean result = true;
        if (this.size != 0) {
            result = false;
        }
        return result;
    }

    @Override
    public void clear() {
        myIntList = new int[10];
        size = 0;

    }

    @Override
    public int[] toArray() {
        int[] newIntList = new int[this.size];
        for (int i = 0; i < newIntList.length; i++) {
            newIntList[i] = this.get(i);
        }
        return newIntList;
    }

    private void increaseList() {
        if (size == myIntList.length) {
            int[] newList = new int[myIntList.length * 2];
            for (int i = 0; i < myIntList.length; i++) {
                newList[i] = myIntList[i];
            }
            myIntList = newList;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index > myIntList.length - 1) {
            throw new CheckIndexException("Индекс выходит за пределы листа");
        }
        if (index > size - 1) {
            throw new CheckIndexException("Элемент по указанному индексу отсуствует");
        }
    }

    private void sortList(int[] array) {
        for (int i = 1; i < this.size; i++) {
            int temp = array[i];
            int j = i;
            while (j > 0 && array[j - 1] >= temp) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = temp;
        }
    }
}
