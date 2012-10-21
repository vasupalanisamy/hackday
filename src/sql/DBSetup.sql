
-- CREATE USERS TABLE
CREATE TABLE USERS (
PROFILE_ID INTEGER generated by default as identity (start with 1),
FIRST_NAME VARCHAR(150),
LAST_NAME VARCHAR(150),
EMAIL_ADDRESS VARCHAR(300),
PASSWORD VARCHAR(100),
PHOTO_LINK VARCHAR(500),
CONSTRAINT USERS_PK PRIMARY KEY(PROFILE_ID)
);

-- CREATE ASSOCIATE TABLE	
CREATE TABLE ASSOCIATES(
ASSOCIATE_ID INTEGER generated by default as identity (start with 1),
FIRST_NAME VARCHAR(150),
LAST_NAME VARCHAR(150),
EMAIL_ADDRESS VARCHAR(300),
PASSWORD VARCHAR(100),
STORE_ID INTEGER,
CONSTRAINT ASSOCIATE_PK PRIMARY KEY(ASSOCIATE_ID)
);

-- CREATE SKU TABLE
CREATE TABLE SKU(
SKU_ID INTEGER NOT NULL,
DESCRIPTION VARCHAR(500),
IMAGE_URL VARCHAR(500),
CONSTRAINT SKU_ID_PK PRIMARY KEY(SKU_ID)
);

-- CREATE CROSS_SELL_MAPPING TABLE
CREATE TABLE CROSS_SELL_MAPPING (
SKU_ID INTEGER NOT NULL,
CROSS_SELL_SKU INTEGER NOT NULL,
CONSTRAINT FK_SKU FOREIGN KEY(SKU_ID) REFERENCES SKU(SKU_ID),
CONSTRAINT FK_CROSS_SELL_SKU FOREIGN KEY(CROSS_SELL_SKU) REFERENCES SKU(SKU_ID)
);

-- CREATE ORDERS TABLE
CREATE TABLE ORDERS(
ORDER_ID INTEGER generated by default as identity (start with 1000000),
CREATION_DATE TIMESTAMP,
STORE_ID INTEGER,
STATUS VARCHAR(20),
PROFILE_ID INTEGER NOT NULL,
CHECK_IN_LATITUDE VARCHAR(50),
CHECK_IN_LONGITUDE VARCHAR(50),
CHECK_IN_TIME TIMESTAMP, 
PICKED_BY INTEGER,
CONSTRAINT ORDERS_PK PRIMARY KEY(ORDER_ID),
CONSTRAINT FK_PROFILE FOREIGN KEY(PROFILE_ID) REFERENCES USERS(PROFILE_ID),
CONSTRAINT FK_ASSOCIATE FOREIGN KEY(PICKED_BY) REFERENCES ASSOCIATES(ASSOCIATE_ID) 
);	

-- CREATE LINE ITEMS TABLE
CREATE TABLE ORDER_LINE_ITEMS(
ORDER_LINE_ID INTEGER generated by default as identity (start with 100),
ORDER_ID INTEGER NOT NULL,
SKU_ID INTEGER NOT NULL,
CONSTRAINT ORDER_LINE_PK PRIMARY KEY(ORDER_LINE_ID),
CONSTRAINT FK_ORDER FOREIGN KEY(ORDER_ID) REFERENCES ORDERS(ORDER_ID),
CONSTRAINT FK_ORDER_LINE_ITEMS_SKU FOREIGN KEY(SKU_ID) REFERENCES SKU(SKU_ID)
);







-- INSERT DUMMY RECORDS INTO USERS TABLE
INSERT INTO USERS (PROFILE_ID, FIRST_NAME, LAST_NAME, EMAIL_ADDRESS, PASSWORD) 
VALUES (1, 'VASU', 'PALANISAMY', 'vpalanisamy@walmart.com', 'password');

INSERT INTO USERS (PROFILE_ID, FIRST_NAME, LAST_NAME, EMAIL_ADDRESS, PASSWORD) 
VALUES (2, 'CHANDRASEKAR', 'RAMALINGAM', 'cramalingam@walmart.com', 'password');

INSERT INTO USERS (PROFILE_ID, FIRST_NAME, LAST_NAME, EMAIL_ADDRESS, PASSWORD) 
VALUES (3, 'TEST', 'USER', 'testuser@walmart.com', 'password');

-- INSERT DUMMY RECORDS INTO SKU TABLE
INSERT INTO SKU(SKU_ID,DESCRIPTION,IMAGE_URL) 
VALUES (10727522,'Nikon D90 Black 12.3MP Digital SLR','http://i.walmartimages.com/i/p/00/01/82/08/25/0001820825446_300X300.jpg');

INSERT INTO SKU(SKU_ID,DESCRIPTION,IMAGE_URL) 
VALUES (21281829,'Dell Silver 17.3" Inspiron i17R-2105SLV Laptop PC with Intel Core i5-3210M Processor and Windows 7', 'http://i.walmartimages.com/i/p/00/88/41/16/08/0088411608287_300X300.jpg');

INSERT INTO SKU(SKU_ID,DESCRIPTION,IMAGE_URL)
VALUES (20573258,'The new iPad Black or White (16GB, 32GB or 64GB)','http://i.walmartimages.com/i/p/11/13/00/62/53/1113006253072_300X300.jpg');

INSERT INTO SKU(SKU_ID,DESCRIPTION,IMAGE_URL) 
VALUES (9914706, 'Nikon AF-S Nikkor 24-70mm f/2.8G ED Wide Angle Lens','http://i.walmartimages.com/i/p/00/01/82/08/02/0001820802164_300X300.jpg');

INSERT INTO SKU(SKU_ID,DESCRIPTION,IMAGE_URL) 
VALUES (21104949, 'Nikon AF-S NIKKOR 50mm f/1.8G Fixed Focal Length Lens', 'http://i.walmartimages.com/i/p/00/01/82/08/02/0001820802199_300X300.jpg');

INSERT INTO SKU(SKU_ID,DESCRIPTION,IMAGE_URL) 
VALUES (17057341, 'Dell SWITCH by Design Studio Lids Lotus Pink, Inspiron N7110', 'http://i.walmartimages.com/i/p/00/88/41/16/06/0088411606389_300X300.jpg');

INSERT INTO SKU(SKU_ID,DESCRIPTION,IMAGE_URL) 
VALUES (19239236, 'FileMate Imagine Series Smart Cover Case for iPad2 and The new iPad', 'http://i.walmartimages.com/i/p/00/80/09/53/17/0080095317820_300X300.jpg');

-- INSERT DUMMY RECORDS INTO ORDERS TABLE
INSERT INTO ORDERS(CREATION_DATE, STORE_ID, STATUS, PROFILE_ID) 
VALUES (TO_TIMESTAMP('10-19-2012','MM-DD-YYYY'), 2000, 'Open', 1);

INSERT INTO ORDERS(CREATION_DATE, STORE_ID, STATUS, PROFILE_ID) 
VALUES (TO_TIMESTAMP('10-19-2012','MM-DD-YYYY'), 2000, 'Open', 2);

INSERT INTO ORDERS(CREATION_DATE, STORE_ID, STATUS, PROFILE_ID) 
VALUES (TO_TIMESTAMP('10-19-2012','MM-DD-YYYY'), 2000, 'Open', 3);

-- INSERT DUMMY RECORDS INTO ORDER LINE ITEMS TABLE
INSERT INTO ORDER_LINE_ITEMS(ORDER_ID,SKU_ID) 
VALUES (1000000, '10727522');

INSERT INTO ORDER_LINE_ITEMS(ORDER_ID,SKU_ID)  
VALUES (1000001, '21281829');

INSERT INTO ORDER_LINE_ITEMS(ORDER_ID,SKU_ID) 
VALUES (1000002, '20573258');

-- INSERT INTO CROSS_SELL_MAPPING
INSERT INTO CROSS_SELL_MAPPING(SKU_ID,CROSS_SELL_SKU)
VALUES (10727522, 9914706);

INSERT INTO CROSS_SELL_MAPPING(SKU_ID,CROSS_SELL_SKU)
VALUES (10727522, 21104949);

INSERT INTO CROSS_SELL_MAPPING(SKU_ID,CROSS_SELL_SKU)
VALUES (21281829, 17057341);

INSERT INTO CROSS_SELL_MAPPING(SKU_ID,CROSS_SELL_SKU)
VALUES (20573258, 19239236);

-- INSERT DUMMY RECORDS INTO ASSOCIATES TABLE
INSERT INTO ASSOCIATES (ASSOCIATE_ID, FIRST_NAME, LAST_NAME, EMAIL_ADDRESS, PASSWORD, STORE_ID) 
VALUES (1, 'Shan', 'Sowndararajan', 'ssowndararajan@walmart.com', 'password', '2000');

INSERT INTO ASSOCIATES (ASSOCIATE_ID, FIRST_NAME, LAST_NAME, EMAIL_ADDRESS, PASSWORD, STORE_ID) 
VALUES (2, 'Dharani', 'Pannam', 'dpannam@walmart.com', 'password', '2000');

INSERT INTO ASSOCIATES (ASSOCIATE_ID, FIRST_NAME, LAST_NAME, EMAIL_ADDRESS, PASSWORD, STORE_ID) 
VALUES (3, 'TEST', 'USER', 'testuser@walmart.com', 'password', '2000');

COMMIT;
	