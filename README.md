# TicketMachine
This is a small system about sale ticket use state pattern, you can understand this project well if you read the requirement.

If you want use DB to store sale log and use statistics function, you must build a database table, the column name is as below:

id (bigint, primary key, increment)   date_time (datetime)    station_name (text)   dest_name (text)    ticket_count (bigint)   price (bigint)

and then modify the config.properties file, make "db_use" as Y, "db_name" is your database's name, "table_name" is your table's name."db_username" and "db_password" also need to be set.
