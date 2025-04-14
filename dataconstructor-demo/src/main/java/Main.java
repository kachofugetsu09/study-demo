public class Main {
    public static void main(String[] args) {
        SkipList skipList = new SkipList();
        skipList.insert(3);
        skipList.insert(6);
        skipList.insert(7);
        skipList.insert(9);
        skipList.insert(12);
        skipList.insert(17);
        skipList.insert(19);
        skipList.insert(21);
        skipList.insert(25);
        skipList.insert(26);

        skipList.display();

        // 测试范围查询
        System.out.println("Range [7, 21]: " + skipList.queryRange(7, 21));
        System.out.println("Range [10, 20]: " + skipList.queryRange(10, 20));
    }
}