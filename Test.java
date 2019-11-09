假设按照升序排序的数组在预先未知的某个点上进行了旋转。

( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。

搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。

你可以假设数组中不存在重复的元素。
你的算法时间复杂度必须是 O(log n) 级别。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    public int search(int[] nums, int target) {
        int l=0;
        int r=nums.length-1;
        while(l<=r){
            int mid=(r+l)>>>1;
             if(target==nums[mid]){
                    return mid;
             }else if(nums[mid]>=nums[l]){
                 if(target<nums[mid]&&target>=nums[l]){
                    r=mid-1;
                }else{
                     l=mid+1;
                 }
            }else{
                 if(target>nums[mid]&&target<=nums[r]){
                     l=mid+1;
                 }else{
                     r=mid-1;
                 }
             }
        }
        return -1;
    }
}

//
给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始
位置和结束位置。

你的算法时间复杂度必须是 O(log n) 级别。

如果数组中不存在目标值，返回 [-1, -1]。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] res={-1,-1};
        int left=0;
        int right=nums.length-1;
        int mid=0;
        while(left<=right){
            mid=(left+right)/2;
            if(nums[mid]>target){
                right=mid-1;
                if(nums[left]<target){
                    left++;
                }
            }else if(nums[mid]<target){
                left=mid+1;
                if(nums[right]>target){
                    right--;
                }
            }else{
                if(nums[left]<target){
                    left++;
                }if(nums[right]>target){
                    right--;
                }
            }
            if(left>=nums.length||right<0){
                break;
            }if(nums[left]==target&&nums[right]==target){
                res[0]=left;
                res[1]=right;
                return res;
            }
        }
        return res;
    }
}


//实现 pow(x, n) ，即计算 x 的 n 次幂函数。
class Solution {
    public double myPow(double x, int n) {
       double pow=1.0;
        for(int i=n;i!=0;i/=2){
            if(i%2!=0){
                pow*=x;
            }
            x*=x;
        }
        return n>0?pow:1/pow;
    }
}

//
假设按照升序排序的数组在预先未知的某个点上进行了旋转。

( 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] )。

编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    public boolean search(int[] nums, int target) {
        int l=0;
        int r=nums.length-1;
        while(l<=r){
            int mid=(l+r)>>>1;
            if(nums[mid]==target){
                return true;
            }if(nums[mid]==nums[l]){
                l++;
                continue;
            }
            if(nums[mid]>nums[l]){
                if(target<nums[mid]&&target>=nums[l]){
                    r=mid-1;
                }else{
                    l=mid+1;
                }
            }else{
                if(target>nums[mid]&&target<=nums[r]){
                    l=mid+1;
                }else{
                    r=mid-1;
                }
            }
        }
        return false;
    }
}