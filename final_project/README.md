## Bus Tickets selector

### Project Description

The project implements the functionality of ordering bus tickets 
on the client side, on the administrator side it allows you to manage buses, 
the routes they travel on, trips, possible discounts on trips, as well as users and orders.
***
Both a registered user and not can order tickets, the difference is that when placing an order, a registered user does not need to enter personal data.
***
Also, a registered user can view/update their data, view their orders.
The administrator can create/delete/update/view information about buses, discounts, routes, routes, flights, orders; view information / ban and unban users
### Database Information
Database consists 6 tables:
<li>users(information about admins and register clients)</li>
<li>buses(information about buses: name, image, number of seats)</li>
<li>promotions(information about sales: image, name, start/end date, percent sale)</li>
<li>routes(information about routes: departure town, arrival town)</li>
<li>buses(information about trips: arrival and departure, bus, promotion, route, price for 1 ticket)</li>
<li>orders(information about orders: name, surname , phone, client(optional), trip, number od children and adults and final price of order)</li>

### Getting start

After starting the application, the database and the necessary tables will be generated. Also, in the main class in the event listener, the client and admin will be added to the database, after which this method can be removed (the client was added to fill the order table with the sql script)
***
In the resources in the static / sql folder is the data.sql file that will fill all the tables with different values.
***
Then just go to http://localhost:8080/ and you will be taken to the main ticket selection page.
