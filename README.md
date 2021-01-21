# Java Consultancy Application v1.03 #

Note: Refer to the generated Javadocs for detailed explanations for each class and function.

This is a client and appointment management application for a hypothetical consultancy firm. It stores its data on a remote MySQL server which is accessed via Java’s JDBC Driver. The application itself is built with Java 11.2 with JavaFX 11.0.2 and scene builder.

## Usage ##

This appointment scheduling application that allows the user (presumably in the role of an employee at this hypothetical company) to manage customer and appointment records, as found in an online mySQL database. 

## Customers and Appointments ##

The application can manage all creating and scheduling of customers and appointments, without allowing any overlaps. 
Appropriate validation is in place if any data is entered incorrectly or left blank. 
It also displays the appointments in the tableviews as LocalDateTimes in the default system timezone of the user. 


## Office Hours ##

The office hours of this company are in EST. So, the user will only see EST office hours when scheduling an appointment. 
The application can handle if the converted hours cross over midnight and require the end time to next day in the users local time. 
They still only have to worry about selecting in the EST equivalent.

## Login Screen

![Login](/screen_shots/login.png?raw=true "Login")

The Login screen validates the input crendentials from the MySQL server's users table. This includes validating both the username and the password. It also then appends the login attempt, whether successful or not, to a login_activity.txt file. 

The login screen can automatically detect the local language of the machine. If it is French, then the screen, along with its error messages, are displayed in French.


## Customers Dashboard

![Customers Dashboard](/screen_shots/Customers_Dashboard.png?raw=true "Customers Dashboard")

A successful login takes the user to the customer dashboard. Here, they can add, modify, and delete customers. However, customers with existing appointments cannot be deleted before their associated appointments are removed. There is also a reports page and a dashboard for appointments. All customer data is pulled from the MySQL server, and pushed back upon modification.


## Add Customer

![Add Customer](/screen_shots/Add_Customers.png?raw=true "Add Customer")

The add customer scene auto-generated a customer ID, and allows the user to input the rest of the data (each with its own validation conditions which are checked upon an attempt to save). The selected country will determine which first level divisions become available. Upon saving and data validation, the new customer is added to the MySQL customer table, along with the local run-time objects class.  

## Modify Customer

![Modify Customer](/screen_shots/modify_customer.png?raw=true "Modify Customer")

The modify customer scene pulls the data of that customer, and allows fields other than the ID to be edited. Upon saving and validation, a function is called to modify the customer on the MySQL server, using prepared statements, and the local objects are then synced with the server.


## Appointments Dashboard

![Appointments Dashboard](/screen_shots/Appointments_Dashboard.png?raw=true "Appointments Dashboard")

The appointments dashboard is templated from the customer dashboard, and also pulls data from MySQL the same way. It has has the same options of adding, modifying, and deleting. There is an option to display appointments by upcoming for the next week or month. This creates a list object list for that parameter and places the result in the tableview. 


## Add Appointment

![Add Appointment](/screen_shots/Add_Appointment.png?raw=true "Add Appointment")

Text Explainer


## Modify Appointment

![Modify Appointment](/screen_shots/modify_appointment.png?raw=true "Modify Appointment")

Text Explainer


## Reports Page

![Reports](/screen_shots/Reports.png?raw=true "Reports")

The reports scene displays three different reports for total appointments by type or month, schedules by contact, and appointments by timezones.
The first report allows the user to select the category of month or type of appointment, and have a label display the total appointments by that filter.
Note that month will only show the actual months in the system for the combo box selection items.
The second report allows the user to select a contact in the system and see their schedule of appointments.
The third report allows the user to see the start and end times of the appointments in any US timezone of their choosing.

