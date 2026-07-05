package IPA02;
import java.util.*;

public class Sol {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Footwear[] foots = new Footwear[5];
        for(int i=0;i<5;i++){
            int id = sc.nextInt();sc.nextLine();
            String name = sc.nextLine();
            String type = sc.nextLine();
            int price = sc.nextInt();sc.nextLine();
            foots[i] = new Footwear(id, name, type, price);
        }

        String type = sc.nextLine();
        String name = sc.nextLine();

        sc.close();

        int res1 = getCountByType(foots, type);
        Footwear res2 = getsecondhighestpricebybrand(foots, name);

        if (res1 == 0) {
            System.out.println("Footwear not available");
        }else{
            System.out.println(res1);
        }

        if (res2 == null) {
            System.out.println("Brand not available");
        }else{
            System.out.println(res2.getid());
            System.out.println(res2.getname());
            System.out.println(res2.getprice());
        }
    }

    public static int getCountByType(Footwear[] foots, String footweartype) {

        int count=0;
        for(Footwear el: foots){
            if(el.gettype().equalsIgnoreCase(footweartype)) count++;
        }
        return count;
    }

    public static Footwear getsecondhighestpricebybrand(Footwear[] foots, String footwearname){
        Footwear high = new Footwear(-1, "", "", -1);
        Footwear secondhigh = new Footwear(-2, "", "", -2);
        //Footwear res = null;
        for(Footwear el: foots){
            boolean name_case = el.getname().equalsIgnoreCase(footwearname); 
            if( name_case && el.getprice() > high.getprice() ){
                secondhigh = high;
                high = el;
               
            }

            if(name_case && el.getprice() > secondhigh.getprice() && el.getprice() != high.getprice()){
                secondhigh = el;
            }
        }

        if (secondhigh.getid() == -2)
            return null;
        return secondhigh;

        //return secondhigh;
    } 
}

class Footwear {
    private final int footwearid;
    private int price;
    private final String footweartype;
    private final String footwearname;

    public Footwear(int id, String name, String type, int price) {
        footwearid = id;
        footwearname = name;
        footweartype = type;
        this.price = price;
    }

    public int getid() {
        return footwearid;
    }

    public int getprice() {
        return price;
    }

    public String gettype() {
        return footweartype;
    }

    public String getname() {
        return footwearname;
    }

    public void setprice(int price){
        this.price = price;
    }
}