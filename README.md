# Consultancy Scheduling System

Note: Refer to the generated Javadocs for detailed explanations for each class and function.

This is a client and appointment management application for a hypothetical consultancy firm. It stores its data on a remote MySQL server which is accessed via Java’s JDBC Driver. The application itself is built with Java 11.2 with JavaFX 11.0.2 and scene builder.

The following are some of the scenes in the application, along with their explanations:


## Login SCreen

![Login](/screen_shots/login.png?raw=true "Login")

The Login screen validates the input crendentials from the MySQL server's users table. This includes validating both the username and the password. It also then appends the login attempt, whether successful or not, to a login_activity.txt file. 

The login screen can automatically detect the local language of the machine. If it is French, then the screen, along with its error messages, are displayed in French.


## Customers Dashboard

![Customers Dashboard](/screen_shots/Customers_Dashboard.png?raw=true "Customers Dashboard")

A successful login takes the user to the customer dashboard. Here, they can add, modify, and delete customers. There is also a reports page and a dashboard for appointments. All customer data is pulled from the MySQL server, and pushed back upon modification.


## Add Customer

![Add Customer](/screen_shots/Add_Customers.png?raw=true "Add Customer")

The add customer scene auto-generated a customer ID, and allows the user to input the rest of the data (each with its own validation conditions which are checked upon an attempt to save). The selected country will determine which first level divisions become available. Upon saving and data validation, the new customer is added to the MySQL customer table,  

## Modify Customer

![Modify Customer](/screen_shots/modify_customer.png?raw=true "Modify Customer")

Text Explainer


## Appointments Dashboard

![Appointments Dashboard](/screen_shots/Appointments_Dashboard.png?raw=true "Appointments Dashboard")

Text Explainer


## Add Appointment

![Add Appointment](/screen_shots/Add_Appointment.png?raw=true "Add Appointment")

Text Explainer


## Modify Appointment

![Modify Appointment](/screen_shots/modify_appointment.png?raw=true "Modify Appointment")

Text Explainer


## Reports Page

![Reports](/screen_shots/Reports.png?raw=true "Reports")

Text Explainer
