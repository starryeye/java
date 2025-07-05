package collection.set.hashset;

import java.util.Objects;

public class Main {

    public static void main(String[] args) {

        System.out.println("\n=== String 원소 HashSet ===");
        MyHashSet<String> set = new MyHashSet<>(10);

        // add
        set.add("A");
        set.add("B");
        set.add("C");
        System.out.println(set);

        // search
        String searchValue = "A";
        boolean result1 = set.contains(searchValue);
        System.out.println("contains = " + result1);


        System.out.println("\n=== Member 원소 HashSet ===");
        MyHashSet<Member> memberSet = new MyHashSet<>(10);

        // add
        Member member1 = new Member("AAA", 10);
        Member member2 = new Member("AAA", 20);
        Member member3 = new Member("BBB", 30);
        Member member4 = new Member("AAA", 10); // member1 과 중복 원소
        System.out.println("member1.hashcode() = " + member1.hashCode());
        System.out.println("member2.hashcode() = " + member2.hashCode());
        System.out.println("member3.hashcode() = " + member3.hashCode());
        System.out.println("member4.hashcode() = " + member4.hashCode());
        memberSet.add(member1);
        memberSet.add(member2); // 해시코드는 다르나.. 해시 인덱스가 동일하여 해시 충돌됨.
        memberSet.add(member3);
        memberSet.add(member4); // 중복 원소 add
        System.out.println(memberSet);

        // search
        Member searchMember = new Member("AAA", 10);
        boolean result2 = memberSet.contains(searchMember);
        System.out.println("contains = " + result2);
    }

    private static class Member {

        /**
         * 객체의 동등성 비교와 해싱이 올바르게 동작하도록 equals(), hashCode() 는 반드시 오버라이딩하여 구현해야한다.
         */

        String name;
        int age;

        public Member(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public boolean equals(Object o) { // 해시 인덱스를 구할때는 hashcode 만 있어도 되지만, bucket 이 특정되고 난 이후에는 원소의 equals 비교로 같은 원소임을 판단한다.
            if (o == null || getClass() != o.getClass()) return false;
            Member member = (Member) o;
            return age == member.age && Objects.equals(name, member.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age); // Objects.hash() : 주어진 필드의 값으로 해시 코드를 생성하는 메서드이다.
        }

        @Override
        public String toString() {
            return "Member{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
