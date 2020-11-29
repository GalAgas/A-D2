import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Hotel implements  ITestable{
    private String name;
    private HashMap<Client, ReservationSet> allReservation;
    private HashMap<Service, HotelService> services;
    private HashMap<Integer,Room> rooms;
    private String city;
    private Group group;
    private int rate;



    public Hotel(String city, String name,int rate){
        this.city = city;
        this.name = name;
        this.rate = rate;
        rooms = new HashMap<Integer,Room>();
        allReservation = new HashMap<Client, ReservationSet>();
        services = new HashMap<Service, HotelService>();

    }

    public void addReservationSet(Client client,ReservationSet reservationSet){
        allReservation.put(client,reservationSet);
    }

    public void addService(Service service, HotelService hotelService){
        services.put(service,hotelService);
    }

    public void addRoom(int roomNumber, Room room){
        rooms.put(roomNumber,room);
    }


    public void setGroup(Group group) {
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public HashMap<Client, ReservationSet> getAllReservation(){return allReservation;}

    public HashMap<Service, HotelService> getServices(){return services;}

    public int getRate(){return rate;}


    @Override
    public boolean checkConstraints() {
//        if(!constraint_6()) return false;
//        if(!constraint_7()) return false;
//        if(!constraint_10()) return false;
//        if(!constraint_11()) return false;
//        return true;
        return constraint_6() && constraint_7() && constraint_10() && constraint_11();
    }

    public static boolean checkAllIntancesConstraints(Model model){
        for(Object o: model.allObjects){
            if(o instanceof Hotel){
                if(!((Hotel) o).checkConstraints()) return false;
            }
        }
        return true;
    }

    /**
     * Amount of VIP rooms in a specific hotel would not exceed more than 10% of all hotel's rooms.
     * @return
     */
    public boolean constraint_6(){
        int numOfVip = 0;
        for(Room room: rooms.values()){
            if(room.getRoomCategory().getType() == RoomCategory.RoomType.VIP) numOfVip++;
        }
        return numOfVip <= (0.1 * rooms.size());
    }


    /**
     * The guest registered on the reservation of hotels in LAS VEGAS must be 21 and over
     * @return
     */
    public boolean constraint_7(){
        if (this.getCity().equals("LAS VEGAS")){
            for (Client c: this.getAllReservation().keySet()) {
                if (c.getAge() < 21){
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * Average rating of reviews for 5 stars hotels must be greater than 5.7
     * @return
     */
    public boolean constraint_10(){
        if (this.getRate() == 5){
            HashSet<Review> allReviews = new HashSet<>();
            for (ReservationSet rs: this.getAllReservation().values()) {
                for (Reservation r: rs.getReservations()){
                    if (r.getBookings() != null && r.getBookings().getReview() != null){
                        allReviews.add(r.getBookings().getReview());
                    }
                }
            }
            double sumOfRanks = 0;
            for (Review r:allReviews) {
                sumOfRanks += r.getRank();
            }
            return sumOfRanks/allReviews.size() > 7.5;
        }
        return true;
    }

    /**
     * the name of service in hotel is unique
     * @return
     */
    public boolean constraint_11(){
        HashMap<Service, HotelService> list_of_Services = this.getServices();
        boolean boolToReturn = true;
        for (Service s1: list_of_Services.keySet()) {
            for (Service s2: list_of_Services.keySet()) {
                if(s1 != s2){
                    if((s1.getServiceName().equals(s2.getServiceName()))){
                        boolToReturn = false;
                    }
                }
            }
        }
        return boolToReturn;
    }
}
