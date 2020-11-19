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

    @Override
    public boolean checkConstraints() {
        if(!constraint_4()) return false;
        return true;
    }
    public static boolean checkAllIntancesConstraints(Model model){
        for(Object o: model.allObjects){
            if(o instanceof Group){
                if(!((Group) o).checkConstraints()) return false;
            }
        }
        return true;
    }

    /**
     * same services to all the hotels in the same group
     * @return
     */
    public boolean constraint_4(){
        HashSet<Hotel> list_of_hotels = this.hotels;
        boolean boolToReturn = true;
        for (Hotel h1:list_of_hotels) {
            for (Hotel h2:list_of_hotels) {
                if(h1 != h2){
                    if(!(h1.getServices().keySet().equals(h2.getServices().keySet()))){
                        boolToReturn = false;
                    }
                }
            }
        }
        return boolToReturn;
    }
}
