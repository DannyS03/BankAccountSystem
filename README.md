# Bank Account System
Project for OOAD (Object Oriented Design & Analysis) course using JavaFX Scene Builder.

A Simple Bank Account application consisting of two types of Users - Manager and Customer. Each user has specified behaviours (refer to UML diagrams). 

- Both users have to login. A Customer can only login if they have been created by the manager with a specified amount; a Manager can add or delete customers.
- A Customer can deposit funds, withdraw funds, or buy an online item.
- A Customer can check their balance.
  - Depending on the balance, customers are valued at a level of wealth.
  - Depending on their level, this determines if they are charged a fee when buying an item.

- There are generated txt files are in place for the manager's login, which is [username, password]: [admin, admin].
  - When a manager creates a new customer, their information is stored in a new file as a list.
  - Likewise, items for purchase are pre-determined in the items list. 
