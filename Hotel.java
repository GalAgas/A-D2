import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

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

    public boolean constraint_12(){
        // find all bookings that have hotel services
        HashSet<Booking> bookings = new HashSet<>();
        for (HotelService hs : this.getServices().values()) {
            bookings.addAll(hs.getGivenServices());
        }

        // collect and separate all hotel services per year
        HashMap<Integer,ArrayList<HotelService>> hsPerYear = new HashMap<>();
        for (Booking b : bookings) {
            if (!hsPerYear.containsKey(b.getDate().getYear())){
                hsPerYear.put(b.getDate().getYear(), b.getServices());
            }
            else {
                Integer year = b.getDate().getYear();
                ArrayList<HotelService> cur = hsPerYear.get(year);
                cur.addAll(b.getServices());

                hsPerYear.put(year, cur);
            }
        }

        // calculate revenue per year
        HashMap<Integer,Double> revenuesPerYear = new HashMap<Integer, Double>();
        for (Integer year : hsPerYear.keySet()) {
            double sum = 0;
            ArrayList<HotelService> hotelServices = hsPerYear.get(year);
            for (HotelService hs : hotelServices) {
                sum += hs.getPrice();
            }
            revenuesPerYear.put(year,sum);
        }

        // sort year in ascending order
        ArrayList<Integer> yearInOrder = new ArrayList<>(revenuesPerYear.keySet());
        Collections.sort(yearInOrder);

        // check increasing revenue per consecutive years
        for (Integer i=0; i<yearInOrder.size()-1 ;i++){
            if (revenuesPerYear.get(i) > revenuesPerYear.get(i+1)){
                return false;
            }
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
