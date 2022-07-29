# santander-tech-test
Completed this test as the first stage of interviewing for Santander.

## Overview
The objective of this code is to receive a CSV message which contains information relating to a FX price. This code reads in the message and creates a Price object which has commission applied to it and the it is stored in the PriceRepository. There is an endpoint to display all the prices (/prices) and an endpoint to display the the latest price for a specific FX instrument (e.g. /prices/eur-jpy).

## Assumptions
The following assumption was made:
- The initial message format would remain constant for all messages.

## Design
The design was to read in CSV messages according to the given format. The Price object converts the format of the instrument from EUR/JPY to EUR-JPY to avoid an error when posting to en end point. The message can either be a single line or multiple lines and is currently read in via a CSV file using a method called onMessage. This is implemented from an interface PriceMessageInterface. Before the Price object is added, the commission to the BID and ASK prices is taken into account by calling the addCommission function.

When adding a price into the PriceRepository, the AddPrice method calls the checkPriceIsLatest method which ensures that a new price is only added to the repository if it is newer than the last instrument added instrument in the repository. When retrieving the latest price from the repository, the array is looped through from the back and the instruments are compared to the given instrument in the endpoint. 

## Testing Approach
The code was tested in two ways. The first was to test that the Price object was converting the timestamp correctly and adding the commission correctly. 

The second test was for the Spring Application which I created. There were three tests which ensured the following: 
- The repository loads.
- The latest price is returned when the respective end point is called.
- The latest prices is returned after a new message is received.
