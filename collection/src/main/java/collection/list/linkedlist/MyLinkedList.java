package collection.list.linkedlist;

import collection.list.MyList;

public class MyLinkedList<E> implements MyList<E> {

    private Node<E> head;
    private Node<E> tail;

    private int size;

    @Override
    public void add(E element) {
        addLast(element);
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            addFirst(element);
            return;
        }

        if (index == size) {
            addLast(element);
            return;
        }

        Node<E> current = getNode(index);
        Node<E> newNode = new Node<>(element);
        Node<E> prev = current.prev;

        newNode.next = current;
        newNode.prev = prev;

        prev.next = newNode;
        current.prev = newNode;

        size++;
    }

    public void addFirst(E element) {

        Node<E> newNode = new Node<>(element);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    public void addLast(E element) {

        Node<E> newNode = new Node<>(element);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    @Override
    public E set(int index, E element) {
        Node<E> target = getNode(index);
        E oldValue = target.item;
        target.item = element;
        return oldValue;
    }

    @Override
    public E remove(int index) {
        Node<E> node = getNode(index);

        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }

        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }

        size--;
        return node.item;
    }

    public E removeFirst() {

        if (head == null) {
            throw new IndexOutOfBoundsException("List is empty");
        }

        E removed = head.item;
        head = head.next;

        if (head != null) {
            head.prev = null;
        } else {
            tail = null; // 리스트가 비게 되면 tail 도 null
        }
        size--;

        return removed;
    }

    public E removeLast() {

        if (tail == null) {
            throw new IndexOutOfBoundsException("List is empty");
        }

        E removed = tail.item;
        tail = tail.prev;

        if (tail != null) {
            tail.next = null;
        } else {
            head = null; // 리스트가 비게 되면 head 도 null
        }
        size--;

        return removed;
    }

    public void clear() {
        head = tail = null;
        size = 0;
    }

    @Override
    public E get(int index) {
        return getNode(index).item;
    }

    public E getFirst() {
        if (head == null) {
            throw new IndexOutOfBoundsException("List is empty");
        }
        return head.item;
    }

    public E getLast() {
        if (tail == null) {
            throw new IndexOutOfBoundsException("List is empty");
        }
        return tail.item;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int indexOf(E element) {
        int index = 0;
        if (element == null) {
            for (Node<E> current = head; current != null; current = current.next) {
                if (current.item == null)
                    return index;
                index++;
            }
        } else {
            for (Node<E> current = head; current != null; current = current.next) {
                if (element.equals(current.item))
                    return index;
                index++;
            }
        }
        return -1;
    }

    private Node<E> getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
    }

    @Override
    public String toString() {
        return "MyLinkedList{" +
                "head=" + head +
                ", tail=" + tail +
                ", size=" + size +
                '}';
    }

    private static class Node<E> {
        E item;
        Node<E> prev;
        Node<E> next;

        public Node(E item) {
            this.item = item;
        }

        @Override
        public String toString() {

            Node<E> temp = this;

            StringBuilder sb = new StringBuilder();
            sb.append("[");

            while (temp != null) {
                sb.append(temp.item);
                if (temp.next != null) {
                    sb.append(" ⇄ ");
                }
                temp = temp.next;
            }

            sb.append("]");

            return sb.toString();
        }
    }
}
