# BankAccountSystem
Using JavaFX Scene Builder

Project for OOAD course (Object Oriented and Design)

Simple Bank Account Application with two types of users - Manager and Customer
Each with specified behaviours (Refer to UML Diagrams) 

Manager can add and delete customers.
User can deposit funds, withdraw funds, or buy an online item. Customers can of course check their balance.
Depending on the balance, customers are at a certain level. 
Depending on their level, this also determines if they are charged a fee when buying an item.

Both users have to login. A Customer can only login if they have been created by the manager with a
specified amount. 

Generated files are in place for the manager's login, which is [username, password]: [admin, admin].
When a manager creates a new customer, their information is stored in a new file as a list.
Likewise, items for purchase are pre determined in the items list. 
