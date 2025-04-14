import java.util.ArrayList;
import java.util.List;

public class SkipList {
    private static final float P = 0.5f;
    private static final int MAX_LEVEL = 16;

    private int level;
    private SkipListNode header;

    public SkipList() {
        this.level = 0;
        header = new SkipListNode(0, MAX_LEVEL);
    }

    private int randomLevel(){
        int lvl=0;
        while(lvl<MAX_LEVEL && Math.random()<P){
            lvl++;
        }
        return lvl;
    }

    boolean search(int value){
        SkipListNode cur = header;
        //从最高层开始查找
        for(int i=level; i>=0; i--){
            //遍历当前层
            while(cur.next[i]!=null && cur.next[i].value<value){
                //移动cur到当前层小于value的下一个节点
                cur = cur.next[i];
            }
        }
        //到达最底层
        cur = cur.next[0];
        return cur!=null && cur.value==value;
    }


    public void insert(int value){
        SkipListNode[] update = new SkipListNode[MAX_LEVEL+1];
        SkipListNode cur = header;

        for(int i=level; i>=0; i--){
            while(cur.next[i]!=null && cur.next[i].value<value){
                cur = cur.next[i];
            }
            update[i] = cur;
        }
        cur = cur.next[0];

        if(cur==null || cur.value!=value){
            int lvl = randomLevel();
            if(lvl>level){
                for(int i=level+1; i<=lvl; i++){
                    update[i] = header;
                }
                level = lvl;
            }
            SkipListNode newNode = new SkipListNode(value, lvl);

            for(int i=0; i<=lvl; i++){
                newNode.next[i] = update[i].next[i];
                update[i].next[i] = newNode;
            }

        }

    }


    public void delete(int value) {
        SkipListNode[] update = new SkipListNode[MAX_LEVEL + 1];
        SkipListNode current = header;

        // 从最高层开始查找要删除的节点
        for (int i = level; i >= 0; i--) {
            while (current.next[i] != null && current.next[i].value < value) {
                current = current.next[i];
            }
            update[i] = current;
        }

        current = current.next[0];

        // 如果找到要删除的节点
        if (current != null && current.value == value) {
            // 更新各层指针
            for (int i = 0; i <= level; i++) {
                if (update[i].next[i] != current) {
                    break;
                }
                update[i].next[i] = current.next[i];
            }

            // 如果删除的是最高层的唯一节点，降低跳表层数
            while (level > 0 && header.next[level] == null) {
                level--;
            }
        }
    }

    public List<Integer> queryRange(int start, int end){
        List<Integer> result = new ArrayList<>();
        SkipListNode cur = header;

        for(int i=level; i>=0; i--){
            while(cur.next[i]!=null && cur.next[i].value<start){
                cur = cur.next[i];
            }
        }
        cur = cur.next[0];

        while(cur!=null && cur.value<=end){
            result.add(cur.value);
            cur = cur.next[0];
        }
        return result;
    }

    public void display() {
        System.out.println("SkipList: ");
        for (int i = 0; i <= level; i++) {
            System.out.print("Level " + i + ": ");
            SkipListNode node = header.next[i];
            while (node != null) {
                System.out.print(node.value + " ");
                node = node.next[i];
            }
            System.out.println();
        }
    }
}

class SkipListNode{
    int value;
    SkipListNode[] next;

    public SkipListNode(int value, int level){
        this.value = value;
        this.next = new SkipListNode[level+1];
    }
}
