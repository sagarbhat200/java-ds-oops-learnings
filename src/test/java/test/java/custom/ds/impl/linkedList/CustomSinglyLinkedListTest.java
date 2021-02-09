package test.java.custom.ds.impl.linkedList;
import custom.ds.impl.linkedList.CustomSinglyLinkedList;
import org.junit.Assert;
import org.junit.Test;

public class CustomSinglyLinkedListTest {

    @Test
    public void testAddFirst(){
        CustomSinglyLinkedList<Integer> list = new CustomSinglyLinkedList();
        Assert.assertEquals(list.getSize(),0);
        list.addFirst(1);
        Assert.assertEquals(list.getSize(),1);
        list.addFirst(2);
        Assert.assertTrue(list.getFirst()==2);
    }

    @Test
    public void testAddLast(){
        CustomSinglyLinkedList<Integer> list = new CustomSinglyLinkedList();
        Assert.assertEquals(list.getSize(),0);
        list.addLast(1);
        Assert.assertEquals(list.getSize(),1);
        list.addLast(2);
        Assert.assertTrue(list.getFirst()==1);
        Assert.assertTrue(list.get(list.getSize()-1)==2);
    }

    @Test
    public void testGetFirst(){
        CustomSinglyLinkedList<Integer> list = new CustomSinglyLinkedList();
        list.addFirst(1);
        Assert.assertTrue(list.getFirst()==1);
        list.addFirst(2);
        Assert.assertTrue(list.getFirst()==2);
    }

   @Test
    public void testGet(){
        CustomSinglyLinkedList<Integer> list = new CustomSinglyLinkedList();
        list.addFirst(1);
        list.addLast(2);
        list.addLast(3);
        Assert.assertTrue(list.get(0)==1);
        Assert.assertTrue(list.get(1)==2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetThrowsException(){
        CustomSinglyLinkedList<Integer> list = new CustomSinglyLinkedList();
        list.addFirst(1);
        list.addLast(2);
        list.addLast(3);
        list.get(4);
    }

    @Test
    public void testRemoveFirst(){
        CustomSinglyLinkedList<Integer> list = new CustomSinglyLinkedList();
        list.addFirst(3);
        list.addFirst(2);
        list.addFirst(1);
        Assert.assertTrue(list.getFirst()==1);
        list.removeFirst();
        Assert.assertTrue(list.getFirst()==2);
    }

    @Test
    public void testRemove(){
        CustomSinglyLinkedList<Integer> list = new CustomSinglyLinkedList();
        list.addFirst(3);
        list.addFirst(2);
        list.addFirst(1);
        Assert.assertTrue(list.getFirst()==1);
        list.remove(0);
        Assert.assertTrue(list.getFirst()==2);
        list.remove(1);
        Assert.assertTrue(list.getSize()==1);
    }


}
