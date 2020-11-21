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

    @Override
    public boolean checkConstraints() {
        return true;
    }

    public static boolean checkAllIntancesConstraints(Model model){
        return true;
    }
}
