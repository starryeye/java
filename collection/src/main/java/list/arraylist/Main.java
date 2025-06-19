package list.arraylist;

public class Main {

    public static void main(String[] args) {

        MyArrayList<String> arrayList = new MyArrayList<>(1);
        System.out.println(arrayList);

        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        System.out.println(arrayList);

        arrayList.add(3, "addLast");
        System.out.println(arrayList);

        arrayList.add(0, "addFirst");
        System.out.println(arrayList);

        String removed1 = arrayList.remove(4);
        System.out.println("removed1 = " + removed1);
        System.out.println(arrayList);

        String removed2 = arrayList.remove(0);
        System.out.println("removed2 = " + removed2);
        System.out.println(arrayList);
    }
}
