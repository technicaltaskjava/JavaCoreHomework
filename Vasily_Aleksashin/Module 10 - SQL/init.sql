//The SQL script for the initialization of the database
//Change filepath
//JDBC URL:
//jdbc:h2:mem:cookies;INIT=RUNSCRIPT FROM '~/JavaCoreHomework/Vasily_Aleksashin/Module 10 - SQL/init.sql'

RUNSCRIPT FROM '~/JavaCoreHomework/Vasily_Aleksashin/Module 10 - SQL/drop_data.sql';
RUNSCRIPT FROM '~/JavaCoreHomework/Vasily_Aleksashin/Module 10 - SQL/create_data.sql';
RUNSCRIPT FROM '~/JavaCoreHomework/Vasily_Aleksashin/Module 10 - SQL/filling_data.sql';
RUNSCRIPT FROM '~/JavaCoreHomework/Vasily_Aleksashin/Module 10 - SQL/edit_data.sql'