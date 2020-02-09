package com.java.linkedlist;
class CustomLinkedList<E> {

	Node<E> head;

	static class Node<E> {
		E data;
		Node<E> next;

		Node(E data) {
			this.data = data;
		}
	}

	public void addNodeAtLast(E inputData) {
		Node<E> newNode = new Node<>(inputData);
		Node<E> node = head;

		if (head == null) {
			head = newNode;
		} else {
			while (node.next != null) {
				node = node.next;
			}
			node.next = newNode;
		}
	}

	public void addNodeAtAfterGivenNode(E givenNodeData, E inputData) {

		Node<E> newNode = new Node<>(inputData);
		if (head == null) {
			System.out.println("No data found:  List is Empty");
			return;
		}

		Node<E> tempNode = head;
		while (tempNode != null) {
			if (tempNode.data == givenNodeData) {
				Node<E> currentNode = tempNode.next;
				tempNode.next = newNode;
				newNode.next = currentNode;

				return;
			}
			tempNode = tempNode.next;
		}
		System.out.println("Given Node not Found");
	}

	public void addNodeAtFirst(E inputData) {

		Node<E> newNode = new Node<E>(inputData);
		if (head == null) {
			head = newNode;
			return;
		}

		newNode.next = head;
		head = newNode;
	}

	/*
	 * public void deleteNode(int inputData) {
	 * 
	 * if (head != null && head.data == inputData) { head = head.next; return; }
	 * 
	 * Node tempNode = head; Node prevNode = null;
	 * 
	 * while (tempNode != null && tempNode.data != inputData) { prevNode = tempNode;
	 * tempNode = tempNode.next; }
	 * 
	 * if (tempNode == null) { System.out.println("Data Not Found!"); return; }
	 * prevNode.next = tempNode.next; }
	 */

	public void deleteNode(E data) {

		if (head == null) {
			System.out.println("no Node found:");
			return;
		}

		if (head.data == data) {
			head = head.next;
		}
		Node<E> focusNode = head;
		Node<E> prevNode = null;
		while (focusNode != null) {

			if (focusNode.data == data) {
				prevNode.next = focusNode.next;
				return;
			} else {
				prevNode = focusNode;
				focusNode = focusNode.next;
			}
		}

		System.out.println("No Node Found!");
	}

	public void searchData(E inputData) {

		if (head == null) {
			System.out.println("List is Empty: Node not Found!");
			return;
		}

		Node<E> tempNode = head;
		while (tempNode != null) {
			if (tempNode.data == inputData) {
				System.out.println("Node Found!");
				return;
			}
			tempNode = tempNode.next;
		}
		System.out.println("Node not Found");
	}

	public void traverse() {
		if (head == null) {
			System.out.println("Linked List is Empty");
			return;
		}

		Node<E> tempNode = head;
		while (tempNode != null) {
			System.out.println("Node data: " + tempNode.data);
			tempNode = tempNode.next;
		}
	}

	public void reverseLinkedList() {

		if (head == null) {
			System.out.println("List is Empty!");
			return;
		}

		Node<E> tempNode = head;
		Node<E> next, prev = null;

		while (tempNode != null) {

			next = tempNode.next;
			tempNode.next = prev;
			prev = tempNode;
			tempNode = next;
		}

		head = prev;
		// traverse();
	}

	public void findMiddleNode() {

		Node<E> pointer1 = head, pointer2 = head;
		int count = 1;
		while (pointer1 != null) {
			pointer1 = pointer1.next;
			if (count % 2 == 0) {
				pointer2 = pointer2.next;
			}
			count++;
		}
		System.out.println("Middle Element:" + pointer2.data);
	}

	public void NthNodeFromLast(int Nth) {

		Node<E> pointer1 = head, pointer2 = head;
		int count = 1;
		while (count <= Nth && pointer1 != null) {
			pointer1 = pointer1.next;
			count++;
		}
		if (count <= Nth) {
			System.out.println("Length is less in LinkedList");
			return;
		}

		while (pointer1 != null) {
			pointer1 = pointer1.next;
			pointer2 = pointer2.next;
		}
		System.out.println("Nth Node is:" + pointer2.data);
	}

	public int sizeOfLinkedListRecursive(Node<E> tempNode) {
		if (tempNode == null) {
			return 0;
		}
		return 1 + sizeOfLinkedListRecursive(tempNode.next);
	}

	public void removeNthNodeFromLast(int Nth) {

		Node<E> pointer1 = head, pointer2 = head;
		int count = 1;

		while (count <= Nth && pointer1 != null) {

			pointer1 = pointer1.next;
			count++;
		}
		if (count <= Nth) {
			System.out.println("Length is less in LinkedList");
			return;
		}

		Node<E> prev = null;
		while (pointer1 != null) {
			prev = pointer2;
			pointer1 = pointer1.next;
			pointer2 = pointer2.next;
		}
		prev.next = pointer2.next;
		System.out.println("Nth Node is:" + pointer2.data);
	}
}

class CustomPriorityQueue<E> {

	Object[] queue;

	public CustomPriorityQueue(int initialCapacity) {
		this.queue = new Object[initialCapacity];
	}

	private int size = 0;

	public void addElement(E data) {

		int noOfE = size;
		if (noOfE == 0)
			queue[0] = data;
		else
			siftUp(noOfE, data);
		size++;
	}

	private void siftUp(int noOfE, E data) {

		while (noOfE > 0) {
			int parent = noOfE - 1 >>> 1;

			Object e = queue[parent];
			if (((Integer) data).intValue() - ((Integer) e).intValue() < 1 ? true : false)
				break;

			queue[noOfE] = e;
			noOfE = parent;
		}
		queue[noOfE] = data;
	}

	public void removeFrontElement(E x) {
		siftDown(1, x);
	}
	private void siftDown(int k, E x) {
		int half = size >>> 1; // loop while a non-leaf
		while (k < half) {
			int child = (k << 1) + 1; // assume left child is least
			Object c = queue[child];
			int right = child + 1;

			if (right < size && ((Integer) c).compareTo((Integer) queue[right]) > 0)
				c = queue[child = right];
			if (((Integer) x).compareTo((Integer) c) <= 0)
				break;
			queue[k] = c;
			k = child;
		}
		queue[k] = x;
	}
}