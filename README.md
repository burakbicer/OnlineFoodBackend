# OnlineFoodBackend

### Veritabanı Scriptleri

```
CREATE TABLE "CUSTOMER" 
   (	
  "ID" NUMBER(19,0) NOT NULL ENABLE, 
	"NAME" VARCHAR2(255 CHAR), 
	"PASSWORD" VARCHAR2(255 CHAR), 
	"SURNAME" VARCHAR2(255 CHAR), 
	"USERNAME" VARCHAR2(255 CHAR), 
	 PRIMARY KEY ("ID")
    );
	INSERT INTO CUSTOMER (ID, name, password, surname, username) VALUES (1, 'Murat', '$2a$10$84/E1qM1KMD8FDnlC.DI5eUnr802k9zNoMC5JZuGNoTeqLpQCAZLK', 'Yılmaz', 'murat');
	INSERT INTO CUSTOMER (ID, name, password, surname, username) VALUES (2, 'Burak', '$2a$10$4j3s7Br1.Kg1ihtVfAV/y.VliPCRBAYBdkTQH7WSn3mhC0ocqHxeq', 'Bicer', 'burak');


	CREATE TABLE "EMPLOYEE" 
   (	
 	 "EMP_ID" NUMBER(19,0) NOT NULL ENABLE, 
	"NAME" VARCHAR2(255 CHAR), 
	"PASSWORD" VARCHAR2(255 CHAR), 
	"SURNAME" VARCHAR2(255 CHAR), 
	"USERNAME" VARCHAR2(255 CHAR), 
	 PRIMARY KEY ("EMP_ID")
    );
	INSERT INTO EMPLOYEE (EMP_ID, name, password, surname, USERNAME) VALUES (1, 'mesut', '$2a$10$Ojm9B05Bt3uyX1.la7d80.JOteZQ7IUUNuiGpF4UNA//bAuJaCcbC', 'can', 'mesutcan');
   
   
   CREATE TABLE "MEAL" 
   (	
  "CODE" VARCHAR2(255 CHAR) NOT NULL ENABLE, 
	"CAMPAIGN" NUMBER(1,0),
	"CREATION_DATE" TIMESTAMP (6), 
	"DETAIL" VARCHAR2(255 CHAR), 
	"NAME" VARCHAR2(255 CHAR), 
	"PHOTO" VARCHAR2(255 CHAR), 
	"PRICE" NUMBER(19,0), 
	"EMP_ID" NUMBER(19,0),
        PRIMARY KEY ("CODE")
        CONSTRAINT "MEAL_CHK1" CHECK (CAMPAIGN IN (1,0)) ENABLE,
	REFERENCES "EMPLOYEE" ("EMP_ID") ENABLE
    );
	INSERT INTO MEAL (CODE, CAMPAIGN, CREATION_DATE, detail, name, photo, price, EMP_ID) VALUES ('CRB1', 1, TO_TIMESTAMP('07/11/2019 03:00:00,000000000'), 'Mis gibi', 'Mercimek', 'https://im.haberturk.com/2019/09/13/ver1568794591/2521922_810x458.jpg', 5, 1);
	INSERT INTO MEAL (CODE, CAMPAIGN, CREATION_DATE, detail, name, photo, price, EMP_ID) VALUES ('KVR1', 1, '07/11/2019 03:00:00,000000000', 'Harika', 'Kavurma', 'https://i4.hurimg.com/i/hurriyet/75/750x422/5d4fca332269a20f5c723a58.jpg', 25, 1);
	INSERT INTO MEAL (CODE, CAMPAIGN, CREATION_DATE, detail, name, photo, price, EMP_ID) VALUES ('MNT1', 1, '08/10/2019 03:12:00,000000000', 'İçi dolu dolu', 'Mantı', 'https://lezzet.blob.core.windows.net/images-xxlarge-recipe/kayseri_mantisi-51ae051e-edd3-48f1-a54b-7f728dc93461.jpg', 12, 1);


 CREATE TABLE "ROLE" 
   (	"ROLE_ID" NUMBER(19,0) NOT NULL ENABLE, 
	"NAME" VARCHAR2(255 CHAR),
	"EXPLANATION" VARCHAR2(255 CHAR), 
	 PRIMARY KEY ("ROLE_ID")
   );
	INSERT INTO CUSTOMER_ROLE(ROLE_ID, NAME, EXPLANATION) VALUES (1, 'DIRECTOR_RESTAURANT', 'Director of Restaurant');


	CREATE TABLE "CUSTOMER_ROLE" 
   	(	
	"CUSTOMER_ID" NUMBER NOT NULL ENABLE,
 	"ROLE_ID" NUMBER(19,0) NOT NULL ENABLE, 
	"ACTIVE" NUMBER(1,0),
	"SINCE" TIMESTAMP (6) NOT NULL ENABLE, 
	 PRIMARY KEY ("ROLE_ID"),
	 REFERENCES "CUSTOMER_ROLE" ("ROLE_ID") ENABLE
	);
	INSERT INTO CUSTOMER_ROLE(CUSTOMER_ID, ROLE_ID, ACTIVE, SINCE) VALUES (1, 1, 1, '01/01/2020 03:00:00,000000000');
	

  CREATE TABLE "EMPLOYEE_ROLE" 
   (	
 	"EMP_ID" NUMBER NOT NULL ENABLE, 
	"ROLE_ID" NUMBER NOT NULL ENABLE, 
	"ACTIVE" NUMBER(1,0), 
	"SINCE" TIMESTAMP (6) NOT NULL ENABLE, 
	 CONSTRAINT "EMPLOYEE_ROLE_CHK1" CHECK (ACTIVE IN(1,0)) ENABLE, 
	 CONSTRAINT "EMPLOYEE_ROLE_FK1" FOREIGN KEY ("EMP_ID")
  	 REFERENCES "EMPLOYEE" ("EMP_ID") ENABLE, 
	 CONSTRAINT "EMPLOYEE_ROLE_FK2" FOREIGN KEY ("ROLE_ID")
	 REFERENCES "ROLE" ("ROLE_ID") ENABLE
   );
   INSERT INTO EMPLOYEE_ROLE (EMP_ID,ROLE_ID,ACTIVE,SINCE) VALUES (1, 1, 1, '07/11/2019 03:00:00,000000000');
   
```

