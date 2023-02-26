package QNS;//Given an array of even numbers sorted in ascending order and an integer k,
//Find the k^th missing even number from provided array
//Input a[] ={0, 2, 6, 18, 22} k=6
//Input a[] ={0, 2, 6, 18, 22} k=6
//Output: 16 examples:
//Explanation: Missing even numbers on the list are 4, 8, 10, 12, 14, 16, 20 and so on
// and kth missing number is on 6th place of the list i.e. 16

//Wrong\\

//
//public class Q8_b {
//    public static int findKthMissingEven(int[] arr, int k) {
//        int low = 0, high = arr.length - 1;
//        int missingCount = arr[high] / 2 - arr[low] / 2 - (arr[0] % 2 == 0 ? 1 : 0);
//        if (missingCount < k)
//            return arr[high] + (k - missingCount) * 2;
//        while (low < high) {
//            int mid = (low + high) / 2;
//            int leftMissingCount = arr[mid] / 2 - arr[low] / 2 - (arr[low] % 2 == 0 ? 1 : 0);
//            if (leftMissingCount >= k)
//                high = mid;
//            else {
//                k -= leftMissingCount;
//                low = mid + 1;
//            }
//        }
//        return arr[low - 1] + (k - (arr[low - 1] / 2 - arr[0] / 2 - (arr[0] % 2 == 0 ? 1 : 0))) * 2;
//    }
//
//    public static void main(String[] args) {
//        int[] arr = {0, 2, 6, 18, 22};
//        int k = 6;
//        System.out.println("The k-th missing even number is: " + findKthMissingEven(arr, k));
//    }
//}


public class Q8_b {
    Q8_b(int[] a,int k,int n){
        int count=a[n-1]/2+1;
        System.out.println(count);
        System.out.println("value of n is "+n);
        int start = a[0];
        int even=0;
        int[] arr = new int[count];
        int missing=0;

        for(int i=start;i<count;i++){
            arr[i]=even;
            even=even+2;
            System.out.println(arr[i]);
        }
        int i=1;
        int j=1;
        while(i<=n){
            if(a[i]==arr[j]){
                i++;
                j++;
            }else{
                missing+=1;
                if(missing==k){
                    System.out.println("missing value is "+arr[j]);
                    break;
                }
                j++;
            }
        }
    }
    public static void main(String[] args) {
        int[] a={0,2,6,18,22};
        int k=6;
        int n=a.length;
        new Q8_b(a,k,n);
//        System.out.println("missed value is"+missingValue);
    }
}