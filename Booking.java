import java.net.ServerSocket;
import java.util.*;

public class Booking implements  ITestable{
    private Date date;
    private Room room;
    private ArrayList<HotelService> services;
    private Reservation reservation;
    private Review review;


    public Booking(Date a_date, Room a_room){
        date = a_date;
        room = a_room;
        services = new ArrayList<HotelService>();
    }

    public void addService(HotelService s){
        services.add(s);
    }

    public void addReview(Review a_review) {review  = a_review; }

    public void addReservation(Reservation r){
        reservation = r;
    }

    public void assignRoom(Room room){
        this.room = room;
    }


    // getters

    public Date getDate() {
        return date;
    }

    public Room getRoom() {
        return room;
    }

    public ArrayList<HotelService> getServices() {
        return services;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public Review getReview() {
        return review;
    }



    @Override
    public boolean checkConstraints() {
//        if(!constraint_8()) return false;
//        if(!constraint_9()) return false;
//        if(!constraint_13()) return false;
//        return true;
        return constraint_8() && constraint_9() && constraint_13();
    }

    public static boolean checkAllIntancesConstraints(Model model){
        for(Object o: model.allObjects){
            if(o instanceof Booking){
                if(!((Booking) o).checkConstraints()) return false;
            }
        }
        return true;
    }


    /**
     * RoomCatagory type have to be in the same level or higher
     * @return
     */
    public boolean constraint_8(){
        RoomCategory.RoomType RoomTypeOfReservation = this.getReservation().getRoomCategory().getType();
        RoomCategory.RoomType RoomTypeOfRoom = this.getRoom().getRoomCategory().getType();
        boolean boolToReturn = true;
        if(RoomTypeOfReservation == RoomCategory.RoomType.VIP){
            if((RoomTypeOfRoom != RoomCategory.RoomType.VIP)){
                boolToReturn = false;
            }
        }
        if(RoomTypeOfReservation == RoomCategory.RoomType.SUITE){
            if((RoomTypeOfRoom != RoomCategory.RoomType.VIP || RoomTypeOfRoom == RoomCategory.RoomType.SUITE)){
                boolToReturn = false;
            }
        }
        return boolToReturn;
    }

    /**
     * Client that ordered a VIP service must have a review
     * @return
     */
    public boolean constraint_9(){
        for (HotelService hs: this.getServices()){
            if(hs.getService() instanceof VipService){
                if (this.review == null) return false;
            }
        }
        return true;
    }

    /**
     * All services given in the booking belong to the hosting hotel.
     * @return
     */
    public boolean constraint_13(){
        if( this.room.getHotel() != null) {
            HashMap<Service, HotelService> hotelServices = this.room.getHotel().getServices();
            for (HotelService service : this.services) {
                boolean found = false;
                for (HotelService hotelService : hotelServices.values()) {
                    if (service == hotelService) found = true;
                }
                if (!found) return false;
            }
        }
        return true;
    }
}
