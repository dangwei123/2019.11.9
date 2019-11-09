假设按照升序排序的数组在预先未知的某个点上进行了旋转。

( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。

请找出其中最小的元素。

你可以假设数组中不存在重复元素。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    public int findMin(int[] nums) {
        int left=0;
        int right=nums.length-1;
        int mid=0;
        while(left<right){
            mid=left+(right-left)/2;
            if(nums[mid]<nums[right]){
                right=mid;
            }else{
                left=mid+1;
            }
        }
        return nums[left];
    }
}

//
峰值元素是指其值大于左右相邻值的元素。

给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。

数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/find-peak-element
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    public int findPeakElement(int[] nums) {
        int left=0;
        int right=nums.length-1;
        int mid=0;
        while(left<right){
            mid=left+(right-left)/2;
            if(nums[mid]>nums[mid+1]){
                right=mid;
            }else{
                left=mid+1;
            }
        }
        return left;
    }
}

//
给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续
子数组。如果不存在符合条件的连续子数组，返回 0。
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int l=0;
        int r=0;
        int sum=0;
        int count=nums.length+1;
        while(r<nums.length){
            sum+=nums[r];
            while(sum>=s&&l<=r){
                count=Math.min(count,r-l+1);
                sum-=nums[l];
                 l++;
            }
            r++;
        }
        if(count==nums.length+1){
            return 0;
        }
        return count;
    }
}

//双向链表
class ListNode{
    public int data;
    public ListNode pre;
    public ListNode next;
    public ListNode(int data){
        this.data=data;
    }
}
public class DoubleList {
    public ListNode head;
    public ListNode last;
    //头插
    public void addFirst(int data){
        ListNode node=new ListNode(data);
        if(this.head==null){
            this.head=node;
            this.last=node;
        }else{
            this.head.pre=node;
            node.next=this.head;
            this.head=node;
        }
    }
    //尾插
    public void addLast(int data){
        ListNode node=new ListNode(data);
        if(this.head==null){
            this.head=node;
            this.last=node;
        }else{
            this.last.next=node;
            node.pre=last;
            this.last=last.next;
        }
    }
    //任意位置插入,第一个数据节点为0号下标
    public void addIndex(int index,int data){
        if(this.head==null||index<0||index>getLen()){
            System.out.println("下标不合法");
            return ;
        }
        ListNode node=new ListNode(data);
        ListNode cur=this.head;
        if(index==0){
            addFirst(data);
        }else if(index==getLen()){
            addLast(data);
        }else{
            while(index!=0){
                cur=cur.next;
                index--;
            }
            node.pre=cur.pre;
            cur.pre.next=node;
            node.next=cur;
            cur.pre=node;
        }
    }
    //查找是否包含关键字key是否在单链表当中
    public boolean contains(int key){
        if(this.head==null){
            return false;
        }
        ListNode cur=this.head;
        while(cur!=null){
            if(cur.data==key){
                return true;
            }
            cur=cur.next;
        }
        return false;
    }
    //删除第一次出现关键字为key的节点
    public int remove(int key){
        if(this.head==null){
            return -1;
        }
        if(this.head.data==key){
            int oldData=this.head.data;
            this.head=head.next;
            this.head.pre=null;
            return oldData;
        }
        ListNode cur=this.head;
        while(cur.next!=null){
            if(cur.pre!=null&&cur.data==key){
                int oldData=cur.data;
                cur.pre.next=cur.next;
                cur.next.pre=cur.pre;
                return oldData;
            }
            cur=cur.next;
        }
        if(this.last.data==key){
            int oldData=this.last.data;
            this.last=last.pre;
            this.last.next=null;
            return oldData;
        }
        return -1;
    }
    //删除所有值为key的节点
    public void removeAllKey(int key){
        if(this.head==null){
            return ;
        }
        ListNode pre=this.head;
        ListNode cur=pre.next;
        while(cur!=null){
            if(cur.data==key){
                pre.next=cur.next;
                cur=cur.next;
            }else{
                pre=cur;
                cur=cur.next;
            }
        }
        if(this.head.data==key){
            this.head=head.next;
            this.head.pre=null;
        }
    }
    //清空链表
    public void clear(){
        while(this.head!=null){
            ListNode cur=this.head.next;
            this.head.pre=null;
            this.head.next=null;
            this.head=cur;
        }
        this.last=null;
    }
    //得到链表长度
    public int getLen(){
        ListNode cur=this.head;
        int len=0;
        while(cur!=null){
            cur=cur.next;
            len++;
        }
        return len;
    }
    //打印
    public void disPlay(){
        ListNode cur=this.head;
        while(cur!=null){
            System.out.print(cur.data+" ");
            cur=cur.next;
        }
        System.out.println();
    }


    public static void main(String[] args) {
        DoubleList doubleList=new DoubleList();
        doubleList.addFirst(3);
        doubleList.addFirst(2);
        doubleList.addFirst(1);
        doubleList.addLast(4);
        doubleList.addLast(5);
        doubleList.disPlay();
        System.out.println(doubleList.getLen());
        doubleList.addIndex(0,8);
        doubleList.addIndex(6,8);
        doubleList.disPlay();
    }
}
