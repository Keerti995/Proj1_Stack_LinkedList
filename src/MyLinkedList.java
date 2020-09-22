import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * LinkedList class implements a doubly-linked list.
 */
public class MyLinkedList<AnyType> implements Iterable<AnyType>
{
    /**
     * Construct an empty LinkedList.
     */
    public MyLinkedList( )
    {
        doClear( );
    }

    private void clear( )
    {
        doClear( );
    }

    /**
     * Change the size of this collection to zero.
     */
    public void doClear( )
    {
        beginMarker = new Node<>( null, null, null );
        endMarker = new Node<>( null, beginMarker, null );
        beginMarker.next = endMarker;

        theSize = 0;
        modCount++;
    }

    /**
     * Returns the number of items in this collection.
     * @return the number of items in this collection.
     */
    public int size( )
    {
        return theSize;
    }

    public boolean isEmpty( )
    {
        return size( ) == 0;
    }

    /**
     * Adds an item to this collection, at the end.
     * @param x any object.
     * @return true.
     */
    public boolean add( AnyType x )
    {
        add( size( ), x );
        return true;
    }

    /**
     * Adds an item to this collection, at specified position.
     * Items at or after that position are slid one position higher.
     * @param x any object.
     * @param idx position to add at.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
     */
    public void add( int idx, AnyType x )
    {
        addBefore( getNode( idx, 0, size( ) ), x );
    }

    /**
     * Adds an item to this collection, at specified position p.
     * Items at or after that position are slid one position higher.
     * @param p Node to add before.
     * @param x any object.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
     */
    private void addBefore( Node<AnyType> p, AnyType x )
    {
        Node<AnyType> newNode = new Node<>( x, p.prev, p );
        newNode.prev.next = newNode;
        p.prev = newNode;
        theSize++;
        modCount++;
    }


    /**
     * Returns the item at position idx.
     * @param idx the index to search in.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType get( int idx )
    {
        return getNode( idx ).data;
    }

    /**
     * Changes the item at position idx.
     * @param idx the index to change.
     * @param newVal the new value.
     * @return the old value.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType set( int idx, AnyType newVal )
    {
        Node<AnyType> p = getNode( idx );
        AnyType oldVal = p.data;
        p.data = newVal;
        return oldVal;
    }

    /**
     *
     * @param idx1
     * @param idx2
     */
    public void swap(int idx1,int idx2){
        if(idx1 >= size() || idx2 >= size() ||(idx1 == idx2)){
            System.out.println("Please enter the correct indices to swap");
            return;
        }
        Node<AnyType> node1 = getNode(idx1);
        Node<AnyType> node2 = getNode(idx2);
        Node<AnyType> nextNode1 = node1.next;
        Node<AnyType> prevNode1= node1.prev;
        Node<AnyType> nextNode2 = node2.next;
        Node<AnyType>  prevNode2 =  node2.prev;
        node1.next =nextNode2;
        nextNode2.prev= node1;
        if(prevNode2 !=  node1)
            node1.prev = prevNode2;
        else
            node1.prev = node2;
        if(prevNode2 != node1)
            prevNode2.next = node1;
        if(nextNode1 != node2)
            node2.next = nextNode1;
        else
            node2.next = node1;
        node2.prev = prevNode1;

        if(nextNode1 != node2)
            nextNode1.prev = node2;
        if(prevNode1 != null)
            prevNode1.next= node2;
        modCount++;
        return;
    }

    /**
     *
     */
    public void shift(int shiftNumber){
        Node<AnyType> cur = beginMarker;
        Node<AnyType> head = beginMarker.next;
        shiftNumber = shiftNumber%size();
        if(shiftNumber == 0 )
            return;
        int shiftIndex = 0;
        if(shiftNumber < 0) {
            shiftNumber = size() + shiftNumber;
        }
        shiftIndex = size() - shiftNumber;
        for(int i = 0 ; i < shiftIndex;i++){
            cur =cur.next;
        }
        System.out.println("cur"+cur.data);
        Node<AnyType> temp =cur.next;
        Node<AnyType> tail = endMarker.prev;
        cur.next = endMarker;
        endMarker.prev = cur;
        Node<AnyType>  newHead = temp;
        newHead.prev = beginMarker;
        temp = tail;
        temp.next = head;
        head.prev = temp;
        beginMarker.next = newHead;
        return;
    }

    /**
     * Gets the Node at position idx, which must range from 0 to size( ) - 1.
     * @param idx index to search at.
     * @return internal node corresponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size( ) - 1, inclusive.
     */
    private Node<AnyType> getNode( int idx )
    {
        return getNode( idx, 0, size( ) - 1 );
    }

    /**
     * Gets the Node at position idx, which must range from lower to upper.
     * @param idx index to search at.
     * @param lower lowest valid index.
     * @param upper highest valid index.
     * @return internal node corresponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between lower and upper, inclusive.
     */
    private Node<AnyType> getNode( int idx, int lower, int upper )
    {
        Node<AnyType> p;

        if( idx < lower || idx > upper )
            throw new IndexOutOfBoundsException( "getNode index: " + idx + "; size: " + size( ) );

        if( idx < size( ) / 2 )
        {
            p = beginMarker.next;
            for( int i = 0; i < idx; i++ )
                p = p.next;
        }
        else
        {
            p = endMarker;
            for( int i = size( ); i > idx; i-- )
                p = p.prev;
        }

        return p;
    }
    /**
     *
     */

    public void  erase(int index , int numberOfElements){

        if(index>=size() || numberOfElements > size()-index){
            System.out.println("Please enter the correct indices");
            return;}
        Node<AnyType>  cur = null;
        if(index == 0 )
            cur = beginMarker;
        else
            cur = getNode(index-1);
        Node<AnyType> temp = cur;
        for(int i = 0 ; i < numberOfElements;i++){
            cur = cur.next;
        }
        temp.next = cur.next;
        cur.next.prev = temp;
        theSize -= numberOfElements;
    }

    /**
     *
     * @param copyList
     * @param index
     */
    public void insertList(MyLinkedList copyList, int index){
        if(index <0 || index >= size()){
            System.out.println("Please enter the correct index");
            return;}
        int i  = 0 ;
        Node<AnyType> cur = copyList.beginMarker;
        Node<AnyType>  cur2 = null;
        if(index == 0)
            cur2 = beginMarker;
        else {
            cur2 =getNode(index - 1);
        }

        Node<AnyType> temp = cur2.next;
        cur2.next = cur.next;
        cur.prev=cur2;
        copyList.endMarker.prev.next=temp;
        temp.prev = copyList.endMarker.prev;
        System.out.println(copyList.theSize);
        theSize += copyList.theSize;
    }
    /**
     * Removes an item from this collection.
     * @param idx the index of the object.
     * @return the item was removed from the collection.
     */
    public AnyType remove( int idx )
    {
        return remove( getNode( idx ) );
    }

    /**
     * Removes the object contained in Node p.
     * @param p the Node containing the object.
     * @return the item was removed from the collection.
     */
    private AnyType remove( Node<AnyType> p )
    {
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize--;
        modCount++;

        return p.data;
    }

    /**
     * Returns a String representation of this collection.
     */
    public String toString( )
    {
        StringBuilder sb = new StringBuilder( "[ " );

        for( AnyType x : this ) {
            //System.out.println(x);
            sb.append(x + " ");
        }
        sb.append( "]" );

        return new String( sb );
    }

    /**
     * Obtains an Iterator object used to traverse the collection.
     * @return an iterator positioned prior to the first element.
     */
    public java.util.Iterator<AnyType> iterator( )
    {
        return new LinkedListIterator( );
    }

    /**
     * This is the implementation of the LinkedListIterator.
     * It maintains a notion of a current position and of
     * course the implicit reference to the MyLinkedList.
     */
    private class LinkedListIterator implements java.util.Iterator<AnyType>
    {
        private Node<AnyType> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;

        public boolean hasNext( )
        {
            return current != endMarker;
        }

        public AnyType next( )
        {
            if( modCount != expectedModCount )
                throw new java.util.ConcurrentModificationException( );
            if( !hasNext( ) )
                throw new java.util.NoSuchElementException( );

            AnyType nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }

        public void remove( )
        {
            if( modCount != expectedModCount )
                throw new java.util.ConcurrentModificationException( );
            if( !okToRemove )
                throw new IllegalStateException( );

            MyLinkedList.this.remove( current.prev );
            expectedModCount++;
            okToRemove = false;
        }
    }

    /**
     * This is the doubly-linked list node.
     */
    private static class Node<AnyType>
    {
        public Node( AnyType d, Node<AnyType> p, Node<AnyType> n )
        {
            data = d; prev = p; next = n;
        }

        public AnyType data;
        public Node<AnyType>   prev;
        public Node<AnyType>   next;
    }

    private int theSize;
    private int modCount = 0;
    private Node<AnyType> beginMarker;
    private Node<AnyType> endMarker;
}

class TestLinkedList
{
    public static void main( String [ ] args )
    {
        MyLinkedList<Integer> lst = new MyLinkedList<>( );
        /**
         * lst is intially populated with 0 to 9 elements. if not required please remove
         */
        for( int i = 0; i < 10; i++ )
            lst.add( i );
        System.out.println("Current List is");
        System.out.println(lst);

        int  option ='a';
        Scanner scan = new Scanner(System.in);

        while(option != 0) {
            System.out.println("Enter  1-Swap   2-Shift   3-Erase   4-copyList   0-quit");
            option = scan.nextInt();
            switch (option) {
                case 1:
                    System.out.println("    Enter 2 indices to swap");
                    lst.swap(scan.nextInt(),scan.nextInt());
                    //System.out.println("came out "+lst.size());
                    System.out.println(lst);
                    break;
                case 2:
                    System.out.println("    Enter number to shift");
                    lst.shift(scan.nextInt());
                    System.out.println(lst);
                    break;
                case 3:
                    System.out.println("    Enter index and number of elements to erase");
                    lst.erase(scan.nextInt(), scan.nextInt());
                    System.out.println(lst);
                    break;
                case 4:
                    MyLinkedList<Integer> copylist = new MyLinkedList<>();
                    System.out.println("    Enter size of the copylist and elements to be added into the copylist");
                    int size = scan.nextInt();
                    for(int i=0;i<size;i++)
                        copylist.add(scan.nextInt());
                    System.out.println("    Enter index from where to copy the new list");
                    lst.insertList(copylist, scan.nextInt());
                    System.out.println(lst);
                    break;
                default:
                    System.out.println(lst);
            }
        }
    }
}