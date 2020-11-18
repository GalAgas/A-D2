public class VipService extends Service{

    public VipService(String serviceName) {
        super(serviceName);
    }

    public static boolean checkAllIntancesConstraints(Model model){
        for(Object o: model.allObjects){
            if(o instanceof VipService){
                if(!((VipService) o).checkConstraints()) return false;
            }
        }
        return true;
    }

}
