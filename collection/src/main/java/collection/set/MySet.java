package collection.set;

public interface MySet<E> {

    /**
     * Set..
     *      원소의 중복을 허용하지 않음
     *      원소의 순서를 보장하지 않음
     */

    boolean add(E e);
    boolean remove(E e);
    boolean contains(E e); // search
}
