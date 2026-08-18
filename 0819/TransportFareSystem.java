public class TransportFareSystem {
    public static void main(String[] args) {
        Transport[] transports = {
            new Bus("藍15", 12),
            new Bus("307", 25),
            new Taxi("大都會", 3),
            new Taxi("台灣大車隊", 8)
        };

        System.out.println("=== 交通票價計算結果 ===");
        for (Transport transport : transports) {
            System.out.printf("路線名稱: %-10s | 票價: %4d 元\n", 
                    transport.getRouteName(), 
                    transport.calculateFare());
        }
    }
}

abstract class Transport {
    private String routeName;
    private int distance;

    public Transport(String routeName, int distance) {
        this.routeName = routeName;
        this.distance = distance;
    }

    public String getRouteName() {
        return routeName;
    }

    public int getDistance() {
        return distance;
    }

    public abstract int calculateFare(int distance);

    public int calculateFare() {
        return calculateFare(this.distance);
    }
}

class Bus extends Transport {
    public Bus(String routeName, int distance) {
        super(routeName, distance);
    }

    @Override
    public int calculateFare(int distance) {
        if (distance <= 0) {
            return 0;
        }
        if (distance <= 8) {
            return 15;
        }
        if (distance <= 16) {
            return 30;
        }
        return 45;
    }
}

class Taxi extends Transport {
    public Taxi(String routeName, int distance) {
        super(routeName, distance);
    }

    @Override
    public int calculateFare(int distance) {
        if (distance <= 0) {
            return 0;
        }
        int baseFare = 85;
        if (distance <= 2) {
            return baseFare;
        }
        return baseFare + (distance - 2) * 30;
    }
}