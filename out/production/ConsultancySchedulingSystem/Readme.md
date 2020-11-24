# Java Consultancy Application v1.03 #

## Usage ##

This is an appointment scheduling application that allows the user (presumably in the role of an employee at this hypothetical company) to manage customer and appointment records, as found in an online mySQL database. 

## Customers and Appointments ##

The application can manage all creating and scheduling of customers and appointments, without allowing any overlaps. 
Appropriate validation is in place if any data is entered incorrectly or left blank. 
It also displays the appointments in the tableviews as LocalDateTimes in the default system timezone of the user. 


## Office Hours ##

The office hours of this company are in EST. So, the user will only see EST office hours when scheduling an appointment. 
The application can handle if the converted hours cross over midnight and require the end time to next day in the users local time. 
They still only have to worry about selecting in the EST equivalent.

## Reports ##

The reports scene displays three different reports for total appointments by type or month, schedules by contact, and appointments by timezones.
The first report allows the user to select the category of month or type of appointment, and have a label display the total appointments by that filter.
Note that month will only show the actual months in the system for the combo box selection items.
The second report allows the user to select a contact in the system and see their schedule of appointments.
The third report allows the user to see the start and end times of the appointments in any US timezone of their choosing.