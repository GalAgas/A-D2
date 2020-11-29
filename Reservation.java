import java.util.Date;
import java.util.ArrayList;

public class Reservation implements  ITestable {
    private int id;
    private RoomCategory roomCategory;
    private Date orderDate;
    private Date requestDate;
    private Booking booking;
    private ReservationSet reservationSet;


    public Reservation(Date ordDate, Date reqDate, int id) {
        this.id = id;
        orderDate = ordDate;
        requestDate = reqDate;
    }

    public void setReservationSet(ReservationSet reservationSet){
        this.reservationSet = reservationSet;
    }


    public void addRoomCategory(RoomCategory roomCategory) {
        this.roomCategory = roomCategory;
    }

    public void addBooking(Booking _booking) {
        booking = _booking;
    }


    public RoomCategory getRoomCategory() {
        return roomCategory;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public Booking getBookings() {
        return booking;
    }

    public int getId() {
        return id;
    }

    public ReservationSet getReservationSet(){return reservationSet;}

    @Override
    public boolean checkConstraints() {
        return constrinat_3();
    }

    public static boolean checkAllIntancesConstraints(Model model) {
        for(Object o: model.allObjects){
            if(o instanceof Reservation){
                if(!((Reservation) o).checkConstraints()) return false;
            }
        }
        return true;
    }


    /**
     * Reservation and booking for hotel room should be for the same hotel
     * @return
     */
    public boolean constrinat_3(){
        return this.getReservationSet().getHotel() == this.getBookings().getRoom().getHotel();
    }

}
    