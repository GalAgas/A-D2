public class RegularService extends Service {


    public RegularService(String serviceName) {
        super(serviceName);
    }

    public static boolean checkAllIntancesConstraints(Model model){
        for(Object o: model.allObjects){
            if(o instanceof RegularService){
                if(!((RegularService) o).checkConstraints()) return false;
            }
        }
        return true;
    }
}
