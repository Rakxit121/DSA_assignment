package QNS;
//5b)
//Assume an electric vehicle must go from source city s to destination city d. You can locate many service centers along
// the journey that allow for the replacement of batteries; however, each service center provides batteries
// with a specific capacity. You are given a 2D array in which servicecenter[i]=[xi,yj] indicates that
// the ith service center is xi miles from the source city and offers yj miles after the automobile can travel after
// replacing batteries at specific service centers. Return the number of times the car's batteries need to be replaced
// before arriving at the destination.
//Input: serviceCenters = [{10,60},{20,30},{30,30},{60,40}], targetMiles= 100, startChargeCapacity = 10
//Output: 2
//Explanation: The car can go 10 miles on its initial capacity; after 10 miles, the car replaces batteries with a capacity of 60 miles; and after travelling 50 miles, at position 60 we change batteries with a capacity of 40 miles; and ultimately, we may arrive at our destination.

//wrong\\



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Q5_b {
    public int numBatteryReplacements(int[][] serviceCenters, int targetMiles, int startChargeCapacity) {
        int count = 0;
        int currentMiles = startChargeCapacity;
        ArrayList<Integer> distances = new ArrayList<>();
        ArrayList<Integer> capacities = new ArrayList<>();
        // Extracting the distances and capacities from the serviceCenters array and storing them in separate ArrayLists
        for (int[] serviceCenter : serviceCenters) {
            distances.add(serviceCenter[0]);
            capacities.add(serviceCenter[1]);
        }

        // Checking the distances between the service centers and determining the number of battery replacements required
        for (int i = 0; i < distances.size(); i++) {
            if (distances.get(i) > currentMiles) {
                currentMiles = capacities.get(i - 1);
                count++;
            }
        }

        // If the car has not reached the target miles and still has some charge left, a battery replacement is required
        if (currentMiles < targetMiles) {
            count++;
        }

        return count;
    }

    public static void main(String[] args) {
        int[][] serviceCenters = {{10, 60}, {20, 30}, {30, 30}, {60, 40}};
        Q5_b question = new Q5_b();
        int numReplacements = question.numBatteryReplacements(serviceCenters, 100, 10);
        System.out.println("Number of battery replacements required: " + numReplacements);
    }
}






