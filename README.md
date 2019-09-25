# drone-delivery-app
Express shipping with unmanned aerial vehicles ("drones")
--------------------------------------------------------


Trading company is looking into express shipping with unmanned aerial vehicles ("drones").

There is a fleet of drones in two locations available:
Metrostrasse 12, 40235 Düsseldorf
Am Albertussee 1, 40549 Düsseldorf

Currently four test customers participate
C1 : Kronprinzenstraße 88, 40217 Düsseldorf
C2 : Kaiserstraße 2, 40479 Düsseldorf
C3 : Wildenbruchstraße 2, 40545 Düsseldorf
C4 : Schlesische Straße 5, 40231 Düsseldorf

Traiding company can supply goods out of these following locations:

- Schiessstraße 31, 40549 Düsseldorf
- Friedrichstraße 152, 40217 Düsseldorf
- Breslauer Str. 2, 41460 Neuss
- Bataverstraße 93, 41462 Neuss
- Am Sandbach 30, 40878 Ratingen

The process of delivery works like this:
- The orders are pre-picked and can be picked up without delay from the stores above
- The drone will always begin from one of the two drone depots mentioned above
- After picking up the goods the drone will fly to the customers in the shortest distance

Develop highly re-usable Java software that will calculate the fastest delivery from depot to store to customer.
Your software should tell which depot and store shall be chosen for a given customer and calculate the total delivery time in minutes and seconds.

Assumtions: You can pre- calculate the GPS coordinates and use these in your software.
The drone has a velocity of 60 km/h. No time for pick-up and hand-over is required.

Work with programming paradigms that you find appropriate and suitable.

--------------------

Applied technology:
-------------------

Back-End:

* Spring Boot 2.1.3
* Java 1.8
* Database: local H2

Front-End:

* Bootrastrap 4.3.1
* JQuery 3.3.1
* AngularJs 1.5.8
* Maps JavaScript API

Applied algorithms
------------------

* Dijkstra's shortest path first reversed algorithm (based on https://www.baeldung.com/java-dijkstra) where delivery address (customer address) is used as starting node and available drone depots are the finish nodes. Stores are the neighbors which must be visited. As a result we have a list of shortest distances between the path: from selected customer over any store to any depot. The shortest path is selected. This algorithm can be applied to any number of stores and depots.

* Calculating distance between two points by their latitude and longitude using trigonometric functions (https://developers.google.com/maps/solutions/store-locator/clothing-store-locator#findnearsql)

--------------------

