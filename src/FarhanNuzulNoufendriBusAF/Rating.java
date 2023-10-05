package farhanNuzulNJBusAF;


public class Rating{
    private long total;
    private long count;
    
    public Rating(){
        this.total = 0;
        this.count = 0;
    }
    
    public void insert (int rating){
        total += rating;
        count++;
    }
    
    public double getAverage(){
        if (count == 0){
            return 0;
        }
        double total_d = (double) total;
        double count_d = (double) count;
        return (total_d / count_d) ;
    }
    
    public long getCount(){
        return count;
    }
    
    public long getTotal(){
        return total;
    }
    
    public String toString(){
        return "total: " + this.total + " count: " + this.count;
    }
}
