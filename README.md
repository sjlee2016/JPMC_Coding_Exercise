# JPMC Coding Exercise by Se Jin Lee 
(sl9339@nyu.edu)


## File Structure 
* Before, it was hard to implement new features to this project because of the lack of file structure. Therefore, I have divided the files into three packages, Object, Service and Utilities. 

### Object
  Object stores object class, which has a constructor and setters and getters. These classes don't have any complicated logic in them. They will simply store values and return values. 
  * Customer.java
  * Movie.java
  * Showing.java
  * Theater.java
  
### Service 
Service are the classes that has implementations of more complex features such as making reservation, printing out theater schedules.
* ReservationService.java : Service used to make movie reservations
* TheaterScheduleInfoService.java : Service used to convert theater schedules info to string or json format
  
### Utilities 
Utilities has no state but just static methods that outside class can use.
* TicketPriceUtils : Utility used to calculate ticket price 
  
## Test Class 
Similarly, previous tests were hard to understand exactly what they were testing. I believe tests should be made for each service or significant feature,
not for each class. So instead of having MovieTests.java and TheaterTests.java, I have created LocalProviderTest, ReservationServiceTest, TheaterScheduleInfoTest,and TicketPriceUtilsTest.

## Naming Convention
* There were several naming conventions issues with the previous code.
* every function name should start with a verb 
 ```code 
    public LocalDate currentDate() { // change to getCurrentDate
            return LocalDate.now();
    }
``` 
* use consistent variable names in different methods if they are the same thing. 
  For example, audienceCount and howManyTickets 
```code
    public Reservation reserve(Customer customer, int sequence, int howManyTickets) { // change to audienceCount
        Showing showing;
        try {
            showing = schedule.get(sequence - 1);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            throw new IllegalStateException("not able to find any showing for given sequence " + sequence);
        }
        return new Reservation(customer, showing, howManyTickets);
    }
```
* every test function name should start with "test" prefix. 
```code
  @Test
    void specialMovieWith50PercentDiscount() { // should be testSpecialMovie
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        assertEquals(10, spiderMan.calculateTicketPrice(showing));

        System.out.println(Duration.ofMinutes(90));
    }
```
### Avoid using raw numbers directly when calculating a value
Previously, the code calculated discount multiplied the ticket price by 0.2 directly. This isn't a good practice because the amount of special discount can later change. For that reason, I have defined static variables and used them to calculate the discounts. 
```code
 double specialDiscount = 0;
        if (MOVIE_CODE_SPECIAL == specialCode) {
            specialDiscount = ticketPrice * 0.2;  // This will be hard to make change, later.
        }
```
```code
    private static int SPECIAL_MOVIE_CODE = 1;
    private static double SPECIAL_DISCOUNT = 0.2;
    private static double TIME_DISCOUNT = 0.25;
    // so I used static variables in TicketPriceUtils
    .
    .
    .
  if(SPECIAL_MOVIE_CODE == showing.getMovie().getSpecialCode()){
            return showing.getMovie().getTicketPrice() * SPECIAL_DISCOUNT;
        }
```
### Check Edge Cases
* Ticket Price : I have modified ticket price calculation method in TicketPriceUtils to make sure no ticket price can be negative after discount. For instance, if $2 dollar ticket gets $3 dollar discount, it will be $0, not $-1 dollar.
* Reservation : I have implemented reservation method in ReservationService to make sure no reservation can be made if audienceCount <= 0 or sequenceNumber <= 0. 