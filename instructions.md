# MASMOVIL Challenge

## Requirements

You must build a API for a phone app with next requeriments

1. Create a endpoint to retrieve the phone catalog, and pricing.
+ It returns a collection of phones, and their prices.
+ Each phone should contain a reference to its image, the name, the description, and
its price.

2. OPTIONAL: Create and endpoints to check and create and order.
+ Receives and order that contains the customer information name, surname, and
email, and the list of phones that the customer wants to buy.
+ Calculate the total prices of the order.
+ log the final order to the console.

### Mandatory in your code
+ it should have tests.

### Response this questions:
+ How would you improve the system?
+ How would you avoid your order api to be overflow?

###Bonus Points
+ Dockerizing the app
+ The second endpoint use the first endpoint to validate the order.
+ Using a microservice approach.
