import java.util.Scanner;
import java.util.ArrayList;
class Movie{
    int movieId;
    String movieName;
    String movieTime;
    int duration;
    String movieGenre;
String movieLanguage;

Movie(int movieId, String movieName,String movieTime,int duration,String movieGenre,String movieLanguage){
    this.movieId = movieId;
    this.movieName = movieName;
    this.movieTime = movieTime;
    this.duration = duration;
    this.movieGenre = movieGenre;
    this.movieLanguage = movieLanguage;
}
}

class Screen{
int screenId;
String screenName;
char seats[][];
String screenType;

Screen(int screenId,String screenName,String screenType){
    this.screenId = screenId;
    this.screenName = screenName;
    this.screenType = screenType;
    seats = new char[5][10];
    availableSeats();
}

public void availableSeats(){
    for(int i =0;i<5;i++){
        for(int j =0;j<10;j++){
            seats[i][j] = 'O';
        }
    }
}
public String getSeatCategory(int row)  {
    if(row<=1){
        return "Standard Seat";
    }
else if(row<=3){
    return "Premium Seat";
    }
else{
    return "Recliner Seat";
    }
}

public float calculateSeatAmount(int row , int qty){
    float price = 0;
    if(row<=1 ){
        price = 250.0f*qty;
    }
    else if(row<=3){
       price = 350.0f*qty;
    }
    else {
        price = 500.0f*qty;
    }
    float gst = price*0.18f;
    float finalAmount = price + gst;
    return finalAmount;
}

public void displaySeats(){
    System.out.println("Screen Seats: ");
    for(int i =0;i<5;i++){
        for(int j =0;j<10;j++){
            System.out.print(seats[i][j]+" ");
        }
        System.out.println();
    }
}
}
class Booking{
    static int counter = 1000;
    int row;
    int col;
Movie movie;
Screen screen;
float Amount;
int bookingId;
    Booking(int row, int col, Movie movie, Screen screen, float Amount){
        bookingId = counter++;
        this.row = row;
        this.col = col;
        this.movie = movie;
        this.screen = screen;
        this.Amount = Amount;
    }
}
public class MovieTicketBookingSystem {
    ArrayList<Movie> movies = new ArrayList<>();

    public void initMovies() {
        movies.add(new Movie(101, "Kalki", "9:00 A.M.", 180, "Sci-Fi Action", "Hindi"));
        movies.add(new Movie(102, "Dhurandhar", "10:00 A.M.", 360, "Crime Thriller", "Hindi"));
        movies.add(new Movie(103, "Dhurandhar The Revenge", "10:30 A.M.", 360, "Action Thriller", "Hindi"));
        movies.add(new Movie(104, "Ek Din", "12:00 P.M.", 200, "Drama", "Hindi"));
        movies.add(new Movie(105, "Ikkis", "1:30 P.M.", 176, "War Drama", "Hindi"));
        movies.add(new Movie(106, "Stree", "2:00 P.M.", 215, "Comedy Romance", "Hindi"));
        movies.add(new Movie(107, "Tere Ishk Mein", "6:00 P.M.", 190, "Romantic Drama", "Hindi"));
        movies.add(new Movie(108, "Sultan", "7:30 A.M.", 180, "Sports Drama", "Hindi"));
        movies.add(new Movie(109, "Stree 2", "10:00 P.M.", 360, "Action Crime", "Hindi"));
        movies.add(new Movie(110, "Mardaani 3", "12:00 P.M.", 215, "Crime Thriller", "Hindi"));
    }

    ArrayList<Booking> bookSeats = new ArrayList<>();

    public void showMovies() {
        for (Movie m : movies) {
            System.out.println(m.movieId + " || " + m.movieName + " || " + m.movieTime + " || " + m.movieGenre + " || " + m.movieLanguage);
        }
    }

    public void showCategory(){
        System.out.println("Seat Category ");
        System.out.println("Row 0-1 -> Standard Seat Row2-3 -> Premium Seat Row4 -> Recliner Seat");
        System.out.println("O -> Available Seats  X -> Booked Seats");
    }

    public void bookSeats(int movieId, Screen screen, int row, int col , int qty) {
        if (row < 0 || row >= 5 || col < 0 || col >= 10) {
            System.out.println("Invalid Seat Position! ");
            return;
        }
        Movie selectedMovie = null;

        for (Movie m : movies) {
            if (m.movieId == movieId) {
                selectedMovie = m;
                break;
            }
        }
        if(selectedMovie == null){
            System.out.println("Movie Not Found! ");
            return;
        }
        float amount = screen.calculateSeatAmount(row,qty);

            if (screen.seats[row][col] == 'O') {
                screen.seats[row][col] = 'X';
                Booking b = new Booking(row, col, selectedMovie, screen, amount);
                bookSeats.add(b);
                System.out.println("Seats Booked Successfully! ");
                System.out.println("Booking Id: " + b.bookingId);
            }
         else {
            System.out.println("Seats Already Booked!");
        }
    }

    public void showBookedSeats() {
        for (Booking b : bookSeats) {
            System.out.println(
                    b.bookingId + " || "
                            + b.movie.movieName + " || "
                            + b.screen.screenName + " || "
                            + b.row + " || "
                            + b.col + " || "
                            + b.Amount
            );
        }
    }

    ArrayList<Screen> screens = new ArrayList<>();

    public void initScreens() {
        Screen s1 = new Screen(101, "Screen 1", "Acoustically Transparent");
        s1.availableSeats();
        Screen s2 = new Screen(102, "Screen 2", "Acoustically Transparent");
        s2.availableSeats();
        Screen s3 = new Screen(103, "Screen 3", "Acoustically Transparent");
        s3.availableSeats();

        screens.add(s1);
        screens.add(s2);
        screens.add(s3);
    }

    public void showScreens() {
        for (Screen screen : screens) {
            System.out.println(screen.screenId + " || " + screen.screenName);
        }
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        MovieTicketBookingSystem movie = new MovieTicketBookingSystem();
//        Screen screen = new Screen();
        movie.initMovies();
        movie.initScreens();
        while (true) {
            System.out.println("----- Welcome to Movie Ticket Booking System -----");
            System.out.println("1.Show Movies 2.Display Seats 3.Book Seats 4.Cancel Seats 5.Show Booked Seats 6.Exit");
            System.out.println("Enter your choice: ");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("--- Showing Movies ----");
                    movie.showMovies();
                    break;

                case 2: movie.showCategory();
                    movie.showScreens();
                    System.out.println("Please Select Screen Id First: ");
                    System.out.println("Enter Screen Id: ");
                    int screenId = input.nextInt();
                    input.nextLine();
                    for(Screen s : movie.screens){
                        if(s.screenId == screenId){
                            s.displaySeats();
                            break;
                        }
                    }
                    break;

                case 3: float totalAmount = 0;
                    Screen screenSelected = null;
                    System.out.println("--- Book Seats ---");
                    System.out.println("Enter Screen Id: ");
                    int screenIds = input.nextInt();
                    System.out.println("Enter Movie Id: ");
                    int movieId = input.nextInt();
                    System.out.println("Enter Ticket Quantity:");
                    int ticketQuantity = input.nextInt();

                    for(Screen s : movie.screens){
                        if(s.screenId == screenIds){
                            screenSelected = s;
                            break;
                        }
                    }
                    if(screenSelected == null){
                        System.out.println("Screen Not Found!");
                        break;
                    }

                    for(int i =0;i<ticketQuantity;i++) {
                        System.out.println("Enter Row: ");
                        int row = input.nextInt();
                        if (row < 0 || row>=5) {
                            System.out.println("Invalid Row Number! ");
                            break;
                        }
                        System.out.println("Enter Seat Number:");
                        int seatNumber = input.nextInt();
                        input.nextLine();
                        if (seatNumber < 0 || seatNumber >=10) {
                            System.out.println("Invalid Seat Number! ");
                            break;
                        }
                        movie.bookSeats(movieId,screenSelected,row,seatNumber,1);
                        System.out.println("Seat (" + row + "," + seatNumber + ") -> " + screenSelected.getSeatCategory(row));
                        totalAmount +=screenSelected.calculateSeatAmount(row,1);
                    }
                    System.out.println("Final Amount For " + ticketQuantity + " Tickets = "+ totalAmount);
break;
                case 4:
                    System.out.println("--- Cancel Seats ---");
                    System.out.println("Enter Booking ID: ");
                    int bookingId = input.nextInt();
                    input.nextLine();
                    boolean found = false;
                   for(int i =0;i<movie.bookSeats.size();i++){
                       Booking b = movie.bookSeats.get(i);
if(b.bookingId == bookingId){
    b.screen.seats[b.row][b.col]='O';
    movie.bookSeats.remove(i);
    System.out.println("Seat Cancelled Successfully! ");
    found = true;
    break;
}
 }
                    if(!found){
                        System.out.println("Invalid Booking ID! ");
                        break;
                   }
break;
                case 5: if(movie.bookSeats.isEmpty()){
                        System.out.println("No Booking Found!");
                        break;
                    }
                    System.out.println("--- Showing Booked Seats ---");
                    movie.showBookedSeats();
                    break;
                case 6:
                    System.out.println("Thank You For Visiting!");
                    System.out.println("Enjoy Your Day!!");
                    return;
                default:
                    System.out.println("Invalid Choice! Please Enter Between Range(1-6)");
                    break;
            }
        }
    }
}