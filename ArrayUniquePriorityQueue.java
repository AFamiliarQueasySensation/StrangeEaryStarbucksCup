
public class ArrayUniquePriorityQueue<T> {

	private T[] queue;
	private double[] priority;
	private int count;

	/**
	 * initializes the queue starting with an array of length 10, and then expands
	 * if needed.
	 */
	public ArrayUniquePriorityQueue() {
		this.queue = ((T[]) new Object[10]);
		this.priority = new double[10];
		this.count = 0;

	}

	/**
	 * Checks if data is already in the queue, returns nothing, otherwise adds it
	 * into the queue. count incremented
	 * 
	 * @param data
	 * @param prio
	 */
	public void add(T data, double prio) {
		// already in then return
		if (contains(data))
			return;
		// Resizes full array
		if (count == queue.length - 1) {

			T[] temp = (T[]) new Object[queue.length + 5];
			double[] tempPrio = new double[temp.length + 5];
			for (int i = 0; i < count; i++) {
				temp[i] = queue[i];
				tempPrio[i] = priority[i];
			}
			queue = temp;
			priority = tempPrio;

		}
		int i = 0;
		while (i < count && prio >= priority[i]) {
			i++;
		}
		if (i < count) {
			for (int j = count - 1; j >= i; j--) {
				queue[j + 1] = queue[j];
				priority[j + 1] = priority[j];
			}
		}
		queue[i] = data;
		priority[i] = prio;

		count++;
	}

	/**
	 * Returns whether the data is found in the array, and false otherwise
	 * 
	 * @param data the data you want to find in the queue.
	 * @return True if contains false otherwise
	 */
	public boolean contains(T data) {
		for (int i = 0; i < count; i++) {
			if (queue[i] != null && queue[i].equals(data))
				return true;
		}
		return false;
	}

	/**
	 * Returns the first element in the queue, if the queue is empty throws
	 * exception.
	 * 
	 * @return first priority in the queue
	 * @throws CollectionException if the priority queue is empty
	 */
	public T peek() throws CollectionException {
		if (count == 0)
			throw new CollectionException("PQ is empty");
		return queue[0];
	}

	/**
	 * Returns the smallest priority, and throws exception if the queue is empty.
	 * 
	 * @return the smallest priority
	 * @throws CollectionException if the priority queue is empty
	 */
	public T removeMin() throws CollectionException {
		if (queue[0].equals(null))
			throw new CollectionException("PQ is empty");
		T temp = queue[0];
		for (int i = 0; i < count - 1; i++) {
			queue[i] = queue[i + 1];
			priority[i] = priority[i + 1];
		}
		queue[count - 1] = null;
		priority[count - 1] = 0.0;
		count--;
		return temp;

	}

	/**
	 * Updates the priority by removing the priority, and then re-add it into the
	 * data set
	 * 
	 * 
	 * 
	 * @param data    Data that you want to reassign in the queue
	 * @param newPrio The new priority
	 * @throws CollectionException if data param is not found in the queue
	 */
	public void updatePriority(T data, double newPrio) throws CollectionException {
		boolean countain = true;
		int position = 0;
		for (int i = 0; i < count; i++) { // finds if it is in the list and its position
			if (queue[i].equals(data)) {
				countain = false;
				position = i;
			}
		}
		if (countain)
			throw new CollectionException("Item not found in PQ");

		for (int j = position-1; j >= 1; j--) { // removes and moves the thing
			queue[j] = queue[j + 1];
			priority[j] = priority[j + 1];
		}

		add(data, newPrio); // re adds with new priorirty

	}

	/**
	 * returns whether the queue has any elements in the array.
	 * 
	 * @return if empty true, else false
	 */
	public boolean isEmpty() {
		return count == 0;
	}

	/**
	 * Returns the amount of elements set in the array.
	 * 
	 * @return the count of how many things in array.
	 */
	public int size() {

		return count;
	}

	/**
	 * Returns the length of the queue Counter, if the priority counter is not the
	 * same as the queuCoutnter will print out Queue priority not being the same.
	 * 
	 * @return int length
	 */
	public int getlength() {
		return count;
	}

	/**
	 * returns a string representation of the priority queue in a specific way.
	 * 
	 * @return (queue) [priority],
	 */
	public String toString() {
		if (isEmpty())
			return "The PQ is empty";

		StringBuilder tmp = new StringBuilder("");

		for (int i = 0; i < count; i++) {
			tmp.append(queue[i]);
			tmp.append(" [");
			tmp.append(priority[i]);
			tmp.append("], ");
		}

		tmp.deleteCharAt(tmp.length() - 1);
		tmp.deleteCharAt(tmp.length() - 1);

		return tmp.toString();
	}

	public static void main(String[] args) {
		ArrayUniquePriorityQueue unique = new ArrayUniquePriorityQueue();

		unique.add("Red 1 ", 1);
		unique.add("Gree 4 ", 4);
		unique.add("Orange 0 ", 0);
		unique.add("Blue 3", 3);
		unique.add("Mango 6", 6);
		unique.add("Racist 14", 14);
		unique.add("Mangoo 66", 66);
		unique.add("Mangoiii 69", 69);
		unique.add("Mangggo 64", 64);
		unique.add("Madddngo 65", 65);
		unique.add("ssMango 62", 62);
		unique.add("Mangssso 61", 61);
		unique.updatePriority("Red 1 ", 2.0);
		unique.removeMin();
		System.out.println(unique.isEmpty());
		System.out.println(unique.count);
		System.out.println(unique.toString());
	}

}
