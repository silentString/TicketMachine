# TicketMachine
This is a small system about sale ticket use state pattern, you can understand this project well if you read the requirement.

If you want use DB to store sale log and use statistics function, you must build a database table, the column name is as below:\<br>
id (bigint, primary key, increment)\<br>   
date_time (datetime)\<br>    
station_name (text)\<br>   
dest_name (text)\<br>    
ticket_count (bigint)\<br>   
price (bigint)\<br>
and then modify the config.properties file, make "db_use" as Y, "db_name" is your database's name, "table_name" is your table's name."db_username" and "db_password" also need to be set.
