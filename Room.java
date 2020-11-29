import java.util.Date;
import java.util.HashMap;

public class Room implements  ITestable{
    private RoomCategory roomCategory;
    private HashMap<Date,Booking> bookings;
    private int number;
    private Hotel hotel;


    public Room(int number){
        this.number = number;
        bookings = new HashMap<Date,Booking>();
    }

    public void setHotel(Hotel h){ hotel = h; }

    public void asignRoomCategory(RoomCategory roomCategory){
        this.roomCategory = roomCategory;
    }

    public void addBooking(Booking booking, Date date) {
        bookings.put(date, booking);
    }

    public RoomCategory getRoomCategory() {
        return roomCategory;
    }

    public HashMap<Date, Booking> getBookings() {
        return bookings;
    }

    public int getNumber() {
        return number;
    }

    public Hotel getHotel(){ return hotel; }

    public boolean constraint_5(){
        if (this.getRoomCategory().getType().equals(RoomCategory.RoomType.VIP)){
            for (Booking b : this.getBookings().values()) {
                for (HotelService hs : b.getServices()) {
                    if (!(hs.getService() instanceof VipService)){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public boolean checkConstraints() {
        return constraint_5();
    }

    public static boolean checkAllIntancesConstraints(Model model){
        for(Object o: model.allObjects){
            if(o instanceof Room){
                if(!((Room) o).checkConstraints()) return false;
            }
        }
        return true;
    }
}
