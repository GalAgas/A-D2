import java.util.ArrayList;

public class ReservationSet implements  ITestable{
    private Client client;
    private Hotel hotel;
    private ArrayList<Reservation> reservations;

    public ReservationSet(){
        reservations = new ArrayList<Reservation>();
    }

    public void setClient(Client client){
        this.client = client;
    }

    public void setHotel(Hotel hotel){
        this.hotel = hotel;
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }


    public Client getClient() {
        return client;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public ArrayList<Reservation> getReservations() {
        return reservations;
    }

    @Override
    public boolean checkConstraints() {
        if(constraint_2()) return true;
        return false;
    }

    public static boolean checkAllIntancesConstraints(Model model){
        for(Object o: model.allObjects){
            if(o instanceof ReservationSet){
                if(!((ReservationSet) o).checkConstraints()) return false;
            }
        }
        return true;
    }

    /**
     * If a customer has at least 5 different reservations, one of them should be with a VIP room.
     * @return
     */
    public boolean constraint_2(){
        if(this.reservations.size() > 4){
            for(Reservation reservation: this.reservations){
                if(reservation.getRoomCategory().getType() == RoomCategory.RoomType.VIP) return true;
            }
            return false;
        }
        return true;
    }
}
