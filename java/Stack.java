package bx.basic;

/**
 * 实现一个栈，满足min() pop() push()方法的时间复杂度都为O(1).(min()返回栈中最小元素)
 * 
 * @author kxt
 */
public class Stack {
    
    private Item[] items;
    private int count;
    private int size;
    
    private static final int DEFAULT_SIZE = 100;
    
    class Item {
        int data;
        int min;
        public Item(int data) {
            this.data = data;
        }
    }
    
    public Stack() {
        this.size = DEFAULT_SIZE;
        items = new Item[size];
    }
    
    public Stack(int size) {
        this.size = size;
        items = new Item[size];
    }
    
    public boolean push(Item item) {
        if (count > size - 1) {
            return false;
        }
        // set min value
        item.min = count == 0 ? item.data : item.data < min() ? item.data : min();
 
        // push
        items[count++] = item;
        return true;
    }
    
    public Item pop() {
        if (count == 0) {
            return null;
        }
        // pop
        return items[--count];
    }
    
    public int min() {
        return count != 0 ? items[count-1].min : 0; 
    }
    
    public int size() {
        return count;
    }
    
}
