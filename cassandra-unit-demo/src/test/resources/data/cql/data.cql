CREATE KEYSPACE IF NOT EXISTS demo_keyspace WITH replication={'class' : 'SimpleStrategy', 'replication_factor':1};
USE demo_keyspace;
CREATE TABLE IF NOT EXISTS demo_table(id varchar, login varchar, full_name varchar, country_code varchar, PRIMARY KEY(id));
INSERT INTO demo_table(id, login, full_name, country_code) values('USR0000001', 'anishsneh', 'Anish Sneh', 'IN');
INSERT INTO demo_table(id, login, full_name, country_code) values('USR0000002', 'rakeshk', 'Rakesh K', 'UK');
INSERT INTO demo_table(id, login, full_name, country_code) values('USR0000003', 'ballys', 'Bally S', 'US');
INSERT INTO demo_table(id, login, full_name, country_code) values('USR0000004', 'yogeshd', 'Yogesh D', 'US');
