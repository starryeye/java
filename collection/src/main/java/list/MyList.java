package list;

public interface MyList<E> {

    void add(E e);

    void add(int index, E e);

    E set(int index, E e);

    E remove(int index);

    E get(int index);

    int size();

    int indexOf(E e);
}
