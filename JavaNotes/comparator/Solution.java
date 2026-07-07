package comparator;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Device[] arr;
        String inp1;
        String inp2;
        try (Scanner sc = new Scanner(System.in)) {
            arr = new Device[4];
            for(int i = 0;i< 4;i++){
                int id = sc.nextInt();sc.nextLine();
                String name = sc.nextLine();
                String brand = sc.nextLine();
                double price = sc.nextDouble();
                double rating = sc.nextDouble();sc.nextLine();
                arr[i] = new Device(id, price, rating, name , brand);
            }   inp1 = sc.nextLine();
            inp2 = sc.nextLine();
        }

        double res1 = findAveragePriceByBrand(arr, inp1);
        Device[] res2 = getDevicesSortedByRatingThenPrice(arr, inp2);

        if(res1 == 0){
            System.out.println("No devices found for the given brand");
        }else{
            System.out.println(res1);
        }

        if(res2 == null){
            System.out.println("No devices found");
        }else{
            for (Device d : res2) {
                System.out.println(d.getdeviceName());
            }
        }
    }

    public static double findAveragePriceByBrand(Device[] dev, String brand){
        double  sum = 0.0, count = 0.0;
        for(Device el: dev){
            if(el.getbrand().equalsIgnoreCase(brand)){
                sum += el.getprice();
                count++;
            }
        }
        return sum/count;
    }

    public static Device[] getDevicesSortedByRatingThenPrice(Device[] dev, String brand){
        ArrayList<Device> res = new ArrayList<>();

        //filter
        for(Device el: dev){
            if(el.getbrand().equalsIgnoreCase(brand)){
                res.add(el);
            }
        }
        res.sort(Comparator.comparingDouble(Device::getrating).reversed().thenComparing(Device::getprice));

        return res.toArray(Device[]::new);
        
    }
}

class Device{
    private final int deviceId;
    private final double price, rating;
    private final String deviceName, brand;


    public Device(int id, double price, double rating, String deviceName, String brand){
        deviceId = id;
        this.price = price;
        this.rating = rating;
        this.deviceName = deviceName;
        this.brand = brand;
    }

    public int getdeviceid(){
        return deviceId;
    }

    public double getprice(){
        return price;
    }

    public double getrating(){
        return rating;
    }

    public String getdeviceName(){
        return deviceName;
    }

    public String getbrand(){
        return brand;
    }


}
