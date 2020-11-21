import java.util.HashSet;

public class Group implements  ITestable{
    private int groupId;
    HashSet<Hotel> hotels;

    public Group(int id){
        hotels = new HashSet<Hotel>();
        groupId = id;
    }



    public void addHotelToGroup(Hotel hotel){
        hotels.add(hotel);
    }

    //getters

    public int getGroupId() {
        return groupId;
    }

    public HashSet<Hotel> getHotels() {
        return hotels;
    }

    public boolean constraint_1(){
        for (Hotel h1:this.getHotels()){
            for (Hotel h2:this.getHotels()) {
                if (h1 != h2 && h1.getCity().equals(h2.getCity())){
                    return false;
                }
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
