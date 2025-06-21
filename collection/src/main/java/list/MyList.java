package list;

public interface MyList<E> {

    /**
     * List
     * 순서(index)가 존재한다.
     * element 중복 적재를 허용한다.
     * 크기가 동적으로 변할 수 있다.
     */

    void add(E e);

    void add(int index, E e);

    E set(int index, E e);

    E remove(int index);

    E get(int index);

    int size();

    int indexOf(E e);
}
