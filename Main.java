public class Main {
    public static void main(String[] args) {
        check_constraint_1();
    }

    public static void check_constraint_1(){
        Model model = new Model();

        Group g = new Group(1);
        Hotel h1 = new Hotel("Beer-Sheva", "coral", 4);
        Hotel h2 = new Hotel("Beer-Sheva", "dan", 4);

        model.addObjectToModel(g);
        model.addObjectToModel(h1);
        model.addObjectToModel(h2);

        model.create_link_group_hotel(h1,g);
        model.create_link_group_hotel(h2,g);

        System.out.println(model.checkModelConstraints());
    }

    public static void check_constraint_2(){
        Model model = new Model();

        Group g = new Group(1);
        Hotel h1 = new Hotel("Beer-Sheva", "coral", 4);
        Hotel h2 = new Hotel("Beer-Sheva", "dan", 4);

        model.addObjectToModel(g);
        model.addObjectToModel(h1);
        model.addObjectToModel(h2);

        model.create_link_group_hotel(h1,g);
        model.create_link_group_hotel(h2,g);

        System.out.println(model.checkModelConstraints());
    }
}
