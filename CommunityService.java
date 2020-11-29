public class CommunityService extends Service{

    public CommunityService(String serviceName) {
        super(serviceName);
    }

    public static boolean checkAllIntancesConstraints(Model model){
        for(Object o: model.allObjects){
            if(o instanceof CommunityService){
                if(!((CommunityService) o).checkConstraints()) return false;
            }
        }
        return true;
    }
}
