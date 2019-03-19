import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Trucks {
    public static void main(String[] args) {
        int answer = solution(100, 100, new int[]{10,10,10,10,10,10,10,10,10,10});
        System.out.println(answer);
    }

    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 1;
        Queue<Integer> waitTrucks = new LinkedList<>();
        Queue<Truck> passTrucks = new LinkedList<>();
        Arrays.stream(truck_weights).forEach(x -> waitTrucks.offer(x));

        while(!waitTrucks.isEmpty()) {
            if((passTrucks.stream().mapToInt(Truck::getWeight).sum() + waitTrucks.peek().intValue()) <= weight) {
                Truck truck = new Truck(waitTrucks.poll());
                passTrucks.offer(truck);
            }
            passTruck(passTrucks, bridge_length);
            answer++;
        }

        while(!passTrucks.isEmpty()) {
            passTruck(passTrucks, bridge_length);
            answer++;
        }

        return answer;
    }

    public static void passTruck(Queue<Truck> passTrucks, int bridge_length) {
        if (passTrucks.peek().getLocation() >= bridge_length) { passTrucks.poll(); }
        for (Truck truck : passTrucks) { truck.setLocation(truck.getLocation()+1); }
    }
}

class Truck {
    int location;
    int weight;

    public Truck(int weight) {
        this.location = 1;
        this.weight = weight;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
