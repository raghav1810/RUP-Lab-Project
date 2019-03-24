import mysql.connector
import pandas as pd
import random

mydb = mysql.connector.connect(
  host="localhost",
  user="testuser",
  passwd="password",
  database="javastuff"
)

print(mydb)

places = {1:'Manipal',
			2:'Mumbai',
			3:'Hyderabad',
			4:'Delhi',
			5:'Kolkata',
			6:'Bangalore',
			7:'Jaipur',
			8:'Shimla',
			9:'Darjeeling',
			10:'Panaji'}

govtOrg = {1:'Electricity',
			2:'Food',
			3:'Chemical',
			4:'Foreign',
			5:'Culture',
			6:'Water',
			7:'Agriculture',
			8:'Coal',
			9:'Forest',
			10:'Tax'}



mycursor = mydb.cursor()

# mycursor.execute("SELECT * FROM Adhaar")

# myresult = mycursor.fetchall()

# for x in myresult:
  # print(x)


names = pd.read_csv("names.csv")
# for i in range(len(names)):
# 	d = random.randint(1, 30)
# 	m = random.randint(1, 12)
# 	y = random.randint(1940, 2010)
# 	date= str(d)+"/"+str(m)+"/"+str(y)
# 	print(names.iloc[i, 0], random.randint(10000, 99999), places[random.randint(1, 10)],date, 'none')
# 	sql = "INSERT INTO Adhaar VALUES (%s, %s, %s, %s, %s)"
# 	val = (names.iloc[i, 0], random.randint(10000, 99999), places[random.randint(1, 10)], date, "none")
# 	mycursor.execute(sql, val)

# mydb.commit()

# names = pd.read_csv("employeenames.csv")
for i in range(0, len(names), 3):
	d = random.randint(1, 30)
	m = random.randint(1, 12)
	y = random.randint(1940, 2010)
	date= str(d)+"/"+str(m)+"/"+str(y)
	print(names.iloc[i, 0], random.randint(10000, 99999), "password", govtOrg[random.randint(1, 10)], 1)
	sql = "INSERT INTO employee VALUES (%s, %s, %s, %s, %s)"
	val = (names.iloc[i, 0], random.randint(10000, 99999), "password", govtOrg[random.randint(1, 10)], 1)
	mycursor.execute(sql, val)
	
for i in range(1, len(names), 3):
	d = random.randint(1, 30)
	m = random.randint(1, 12)
	y = random.randint(1940, 2010)
	date= str(d)+"/"+str(m)+"/"+str(y)
	print(names.iloc[i, 0], random.randint(10000, 99999), "password", "Service IT", 2)
	sql = "INSERT INTO employee VALUES (%s, %s, %s, %s, %s)"
	val = (names.iloc[i, 0], random.randint(10000, 99999), "password", "Service IT", 2)
	mycursor.execute(sql, val)

mydb.commit()