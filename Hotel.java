import java.util.ArrayList;
import java.util.HashMap;

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
        if(!constraint_6()) return false;
        if(!constraint_11()) return false;
        return true;
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
     * the name of service in hotel is unique
     * @return
     */
    public boolean constraint_11(){
        HashMap<Service, HotelService> list_of_Services = this.getServices();
        boolean boolToReturn = true;
        for (Service s1: list_of_Services.keySet()) {
            for (Service s2: list_of_Services.keySet()) {
                if(s1 != s2){
                    if(!(s1.getServiceName().equals(s2.getServiceName()))){
                        boolToReturn = false;
                    }
                }
            }
        }
        return boolToReturn;
    }
}
