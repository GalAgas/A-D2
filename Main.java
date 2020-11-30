import java.util.Date;

public class Main {
    public static void main(String[] args) {
        check1();
        check3();
        check7();
        check10();
        check4();
        check8();
        check9();
        check11();
    }


    public static void check11(){
        Model m = new Model();
        Hotel h = new Hotel("beer sheva", "beer sheva", 4);
        Service s = new RegularService("Tomer");
        Service s1 = new RegularService("Tomerk");
        HotelService hs = new HotelService(12,12);
        HotelService hs1 = new HotelService(12,12);


        m.addObjectToModel(h);
        m.addObjectToModel(s);
        m.addObjectToModel(s1);
        m.addObjectToModel(hs);
        m.addObjectToModel(hs1);

        h.addService(s,hs);
        h.addService(s1,hs1);

        System.out.println(h.constraint_11());

    }



    public static void check9() {
        Model m = new Model();
        Room r = new Room(1);
        Date d = new Date();
        Booking b = new Booking(d,r);
        HotelService hs = new HotelService(12,12);
        Review re = new Review(8,"to",d);
        Service vs = new VipService("ttto");

        m.addObjectToModel(b);
        m.addObjectToModel(r);
        m.addObjectToModel(hs);
        m.addObjectToModel(re);
        m.addObjectToModel(vs);

        hs.assignService(vs);
        b.addService(hs);
        b.addReview(re);

        System.out.println(b.constraint_9());

    }


    public static void check8() {
        Model m = new Model();
        Room r = new Room(1);
        Date d = new Date();
        Booking b = new Booking(d,r);
        Reservation rs = new Reservation(new Date(),new Date(), 23);
        RoomCategory rc = new RoomCategory(100, RoomCategory.RoomType.VIP);
        RoomCategory rc2 = new RoomCategory(100, RoomCategory.RoomType.VIP);
        RoomCategory rc3 = new RoomCategory(100, RoomCategory.RoomType.BASIC);

        m.addObjectToModel(b);
        m.addObjectToModel(rs);
        m.addObjectToModel(rc);
        m.addObjectToModel(rc2);
        m.addObjectToModel(r);

        rs.addRoomCategory(rc3);
        b.addReservation(rs);

        //rc.addRoom(r);
        r.asignRoomCategory(rc2);
        r.addBooking(b, new Date());

        System.out.println(b.constraint_8());


    }

    public static void check4(){
        Model m = new Model();
        Group g = new Group(1);
        Hotel h1 = new Hotel("Tel Aviv", "Dan Pano", 3);
        Hotel h2 = new Hotel("Tel Aviv", "Dan k", 4);
        m.addObjectToModel(g);
        m.addObjectToModel(h1);
        m.addObjectToModel(h2);
        g.addHotelToGroup(h1);
        g.addHotelToGroup(h2);

        Service s1 = new RegularService("reg");
        Service s2 = new RegularService("reg2");
        HotelService hs1 = new HotelService(12,12);
        HotelService hs2 = new HotelService(13,13);

        m.addObjectToModel(s1);
        m.addObjectToModel(s2);
        m.addObjectToModel(hs1);
        m.addObjectToModel(hs2);
        h1.addService(s1,hs1);
        h2.addService(s1,hs1);
        h2.addService(s2,hs2);
        h1.addService(s2,hs2);
        System.out.println(g.constraint_4());
    }


    public static void check1(){
        Model m = new Model();
        Group g = new Group(1);
        Hotel h1 = new Hotel("Tel Aviv", "Dan Pano", 1);
        Hotel h2 = new Hotel("Beer Sheva", "Dan k", 4);

        m.addObjectToModel(g);
        m.addObjectToModel(h1);
        m.addObjectToModel(h2);

        m.create_link_group_hotel(h1, g);
        m.create_link_group_hotel(h2, g);
        System.out.println(m.checkModelConstraints());
    }

    public static void check3(){
        Model m = new Model();
        Hotel h1 = new Hotel("Tel Aviv", "Dan Pano", 1);
        Room room1 = new Room(1);
        m.addObjectToModel(h1);
        m.addObjectToModel(room1);
        m.create_link_hotel_room(room1, h1);

        Hotel h2 = new Hotel("Tel Aviv", "Dan k", 4);
        Room room2 = new Room(2);
        m.addObjectToModel(h2);
        m.addObjectToModel(room2);
        m.create_link_hotel_room(room2, h2);



        RoomCategory rcat = new RoomCategory(50, RoomCategory.RoomType.VIP);
        m.addObjectToModel(rcat);
        m.create_link_room_roomCategory(room1, rcat);
        m.create_link_room_roomCategory(room2, rcat);

        Date today = new Date();
        Booking b = new Booking(today,room1);
        m.addObjectToModel(b);
        m.create_link_room_Booking(room1, b);



        ReservationSet rSet = new ReservationSet();
        m.addObjectToModel(rSet);
        Client c = new Client(123,30, "Dan", "BS");
        m.addObjectToModel(c);
        m.create_link_client_hotel_reservationSet(c, h2, rSet);


        Reservation res = new Reservation(today,today, 123);
        m.addObjectToModel(res);
        m.create_link_reservationSet_reservation(rSet, res);

        m.create_link_reservation_roomCategory(res, rcat);
        m.create_link_reservation_booking(b, res);

        boolean t = res.checkConstraints();
        System.out.println(t);

    }

    public static void check7() {
        Model m = new Model();

        Hotel h1 = new Hotel("LAS VEGAS", "Dan Pano", 1);
        m.addObjectToModel(h1);

        ReservationSet resset1 = new ReservationSet();
        m.addObjectToModel(resset1);

        ReservationSet resset2 = new ReservationSet();
        m.addObjectToModel(resset2);

        Client c1 = new Client(123,30, "Dan", "BS");
        Client c2 = new Client(1456,18, "gan", "BS");
        m.addObjectToModel(c1);
        m.addObjectToModel(c2);

        m.create_link_client_hotel_reservationSet(c1, h1, resset1);
        m.create_link_client_hotel_reservationSet(c2, h1, resset2);

        System.out.println(h1.checkConstraints());
    }

    public static void check10() {
        Model m = new Model();

        Hotel h1 = new Hotel("LAS VEGAS", "Dan Pano", 5);
        m.addObjectToModel(h1);

        ReservationSet resset1 = new ReservationSet();
        m.addObjectToModel(resset1);

        ReservationSet resset2 = new ReservationSet();
        m.addObjectToModel(resset2);

        ReservationSet resset3 = new ReservationSet();
        m.addObjectToModel(resset3);

        Client c1 = new Client(123,30, "Dan", "BS");
        Client c2 = new Client(1456,18, "gan", "BS");
        Client c3 = new Client(1457,18, "gan", "BS");
        m.addObjectToModel(c1);
        m.addObjectToModel(c2);
        m.addObjectToModel(c3);

        m.create_link_client_hotel_reservationSet(c1, h1, resset1);
        m.create_link_client_hotel_reservationSet(c2, h1, resset2);
        m.create_link_client_hotel_reservationSet(c3, h1, resset3);

        Date d1 = new Date();
        Reservation res1 = new Reservation(d1, d1, 1);
        Reservation res2 = new Reservation(d1, d1, 2);
        Reservation res3 = new Reservation(d1, d1, 3);
        m.addObjectToModel(res1);
        m.addObjectToModel(res2);
        m.addObjectToModel(res3);

        m.create_link_reservationSet_reservation(resset1, res1);
        m.create_link_reservationSet_reservation(resset2, res2);
        m.create_link_reservationSet_reservation(resset3, res3);

        Room r1 = new Room(1);
        Booking b1 = new Booking(d1,r1);
        m.addObjectToModel(r1);
        m.addObjectToModel(b1);
        m.create_link_room_Booking(r1, b1);

        Room r2 = new Room(2);
        Booking b2 = new Booking(d1,r2);
        m.addObjectToModel(r2);
        m.addObjectToModel(b2);
        m.create_link_room_Booking(r2, b2);

        Room r3 = new Room(3);
        Booking b3 = new Booking(d1,r3);
        m.addObjectToModel(r3);
        m.addObjectToModel(b3);
        m.create_link_room_Booking(r3, b3);

        m.create_link_reservation_booking(b1, res1);
        m.create_link_reservation_booking(b2, res2);
        m.create_link_reservation_booking(b3, res3);


        Review rev1 = new Review(4, "dfghh",d1);
        m.addObjectToModel(res1);
        m.create_link_booking_review(b1, rev1);

        Review rev2 = new Review(10, "dfghh",d1);
        m.addObjectToModel(res2);
        m.create_link_booking_review(b2, rev2);

        boolean t = h1.checkConstraints();

        System.out.println(t);
    }
}
