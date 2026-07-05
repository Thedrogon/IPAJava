package IPA03;

import java.util.Scanner;

public class Sol {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Student[] arr = new Student[4];
        for(int i=0;i<4;i++){
            int roll = sc.nextInt();sc.nextLine();
            String name = sc.nextLine();
            String branch = sc.nextLine();
            double score = sc.nextDouble();
            boolean dayscholar = sc.nextBoolean();
            arr[i] = new Student(roll, name, branch, score, dayscholar);
        }
        sc.close();

        int res1 = findCountofdayscholarstudents(arr);
        Student res2 = findstudentwithsecondhighestscore(arr);

        if(res1 == 0){
            System.out.println("There are no such dayscholar student");
        }else{
            System.out.println(res1);
        }

        if(res2 != null){
            System.out.println(res2.getroll() + "#" + res2.getname() + "#"+ res2.getscore());
        }else{
            System.out.println("There are no student from non day scholar");
        }
    }

    public static int findCountofdayscholarstudents(Student[] studs){
        int count = 0;
        for(Student el: studs){
            if(el.getdayscholar() && el.getscore() > 80){
                count++;
            }
        }

        return count;
    }

    public static Student findstudentwithsecondhighestscore(Student[] studs){
        Student high = new Student(-1, "", "", -1, true), 
                secondhigh = new Student(-2, "","",-2, true);
        high.setscore(-1); secondhigh.setscore(-2);

        for(Student el: studs){
            if(!el.getdayscholar() && el.getscore() > high.getscore()){
                secondhigh = high;
                high = el;
            }

            if(!el.getdayscholar() && el.getscore() > secondhigh.getscore() && el.getscore() < high.getscore()){
                secondhigh = el;
            }
        }

        if(secondhigh.getscore() == -2 || secondhigh.getscore() == -1){
            return null;
        }

        return secondhigh;
    }


}

class Student{
    private int rollno;
    private double score;
    private String name, branch;
    private boolean dayscholar;
   
    public Student(int roll, String name, String branch, double score, boolean  dayscholar){
        this.rollno = roll;
        this.name = name;
        this.branch = branch;
        this.score = score;
        this.dayscholar = dayscholar;
    }

    public int getroll(){
        return rollno;
    }

    public double getscore(){
        return score;
    }

    public String getname(){
        return name;
    }

    public String getbranch(){
        return branch;
    }

    public boolean getdayscholar(){
        return dayscholar;
    }

    public void setscore(int n){
        score = n;
    }
    
}
