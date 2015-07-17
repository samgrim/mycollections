import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;


public class MyListTester {

	public static void main(String[] args) {
		MyList<Integer> list = new MyList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.addFirst(0);
		list.addFirst(-1);
		list.addFirst(-2);
	/*	for(int i = 0; i < 5; i++) {
			System.out.println("List length =" + list.length());
			System.out.println(list);
			list.remove();
			System.out.println(list);
			list.add(5);
			System.out.println("Fifth element = " +list.getElementAt(5));
			System.out.println("Second element = " +list.getElementAt(2));
			list.add(2, 80);
			System.out.println(list);
			list.add(0, 80);
			System.out.println(list);
			list.removeFirst();
			System.out.println(list);
			list.add(9, 80);
			System.out.println(list);
			list.removeElementAt(9);
			System.out.println(list);
			System.out.println("Index of 3 =" + list.indexOf(3));
			list.remove(80);
			System.out.println(list);
		}
		
		System.out.println("For Each test");
		for(Integer i:list){
			System.out.println(i);
		}
		
		Iterator<Integer> it = list.iterator();
		while(it.hasNext()) {
			System.out.println("Removing " + it.next());
			it.remove();
			System.out.println(list);
		}*/
		
		ListIterator<Integer> li = list.listIterator();
		System.out.println(list);
		li.next();
		li.next();
		li.remove();
		System.out.println(list);
		li.add(7);
		System.out.println(list);
		li.previous();
		System.out.println("list["+li.previousIndex() + "] = " + li.previous());
		
	}
}
