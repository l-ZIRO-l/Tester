public class Main {
    static int binarySearch(int[] a, int target) {
        int low = 0, high = a.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (a[mid] == target) return mid;
            else if (a[mid] < target) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] a = {2, 5, 8, 12, 16, 23, 38};
        System.out.println(binarySearch(a, 23));
    }
}
