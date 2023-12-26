set catalog mallcat;
set schema mallsch;

DROP TABLE IF EXISTS orderlists;
DROP TABLE IF EXISTS userorders;
DROP TABLE IF EXISTS carts;
DROP TABLE IF EXISTS userinfo;
DROP TABLE IF EXISTS commodities;
DROP TABLE IF EXISTS commodity_tags;

CREATE TABLE commodity_tags (						
	CommoditySubTag VARCHAR(20) NOT NULL PRIMARY KEY,
	CommodityMainTag VARCHAR(20) NOT NULL 
);

CREATE INDEX IX_main_tags ON commodity_tags(CommodityMainTag);

CREATE TABLE commodities (		--GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1)欄位會自動增加
	CommodityID BIGINT GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
	CommodityName VARCHAR(30),		
	CommodityQty INT,				
	CommodityPrice INT,					
	CommodityTag VARCHAR(20),		
	CommodityImgPath VARCHAR(200),	
	CommodityDetail VARCHAR(500),			
	CommoditySaleFlag NUMERIC(1),
	CommodityDiscount NUMERIC(1),
	CommodityDisRate NUMERIC(1),
	FOREIGN KEY (CommodityTag) REFERENCES commodity_tags(CommoditySubTag) ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE INDEX IX_commodities_tag ON commodities(CommodityTag);
CREATE INDEX IX_commodities_name ON commodities(CommodityName);
CREATE INDEX IX_commodities_sale ON commodities(CommoditySaleFlag);
CREATE INDEX IX_commodities_discount ON commodities(CommodityDiscount);

CREATE TABLE userinfo (			
	UserAccount VARCHAR(30) NOT NULL PRIMARY KEY,
	UserPassword VARCHAR(30),	
	UserName VARCHAR(20),		
	UserPhone VARCHAR(20),		
	UserEmail VARCHAR(50),			
	UserAddress VARCHAR(100),	
	UserMsg VARCHAR(200)           
);

CREATE TABLE carts (			
	CartSeq BIGINT GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,    
	CartAccount VARCHAR(30) NOT NULL,
	CartCommodityID BIGINT NOT NULL,
	CartQty INT,
	FOREIGN KEY (CartAccount) REFERENCES userinfo(UserAccount) ON UPDATE CASCADE ON DELETE RESTRICT,
	FOREIGN KEY (CartCommodityID) REFERENCES commodities(CommodityID) ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE INDEX IX_carts_account ON carts(CartAccount);

CREATE TABLE userorders (				
	OrderNo BIGINT GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,	
	OrderAccount VARCHAR(30) NOT NULL,
	OrderDate TIMESTAMP,
	OrderTotal BIGINT,
	FOREIGN KEY (OrderAccount) REFERENCES userinfo(UserAccount) ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE INDEX IX_orders_account ON userorders(OrderAccount);

CREATE TABLE orderlists (
	OrderNo BIGINT NOT NULL,
	OrderCommodityID BIGINT NOT NULL,
	OrderQty INT,
	OrderPrice BIGINT,
	OrderReturn NUMERIC(1),
	FOREIGN KEY (OrderCommodityID) REFERENCES commodities(CommodityID) ON UPDATE CASCADE ON DELETE RESTRICT,
	FOREIGN KEY (OrderNo) REFERENCES userorders(OrderNo) ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE INDEX CX_orderlist_no ON orderlists(OrderNo);

INSERT INTO commodity_tags (CommoditySubTag, CommodityMainTag) 
VALUES ('水果', '食物');

INSERT INTO commodities (CommodityName, CommodityQty, CommodityPrice, CommodityTag, CommodityImgPath, CommodityDetail)
VALUES ('蘋果', 50, 25, '水果', 'images/apple.jpg', '蘋果 Detail...');

INSERT INTO commodities (CommodityName, CommodityQty, CommodityPrice, CommodityTag, CommodityImgPath, CommodityDetail)
VALUES ('西瓜', 100, 90, '水果', 'images/watermelon.png', '西瓜 Detail...');

INSERT INTO commodities (CommodityName, CommodityQty, CommodityPrice, CommodityTag, CommodityImgPath, CommodityDetail)
VALUES ('檸檬', 100, 20, '水果', 'images/lemon.jpg', '檸檬 Detail...');

INSERT INTO commodities (CommodityName, CommodityQty, CommodityPrice, CommodityTag, CommodityImgPath, CommodityDetail)
VALUES ('葡萄', 100, 100, '水果', 'images/grape.jpg', '葡萄 Detail...');

INSERT INTO userinfo (UserAccount, UserPassword, UserName, UserPhone, UserEmail, UserAddress, UserMsg)
VALUES ('user1', 'user1', 'user1', '01234567890', 'user1@mail', 'Taipei', 'User message...');

INSERT INTO carts (CartAccount, CartCommodityID, CartQty)
VALUES ('user1', 1, 2);

INSERT INTO userorders (OrderAccount, OrderDate, OrderTotal)
VALUES ('user1', TIMESTAMP '2023-12-13:15:32:31.00', 50);

INSERT INTO orderlists (OrderNo, OrderCommodityID, OrderQty, OrderPrice, OrderReturn)
VALUES (1, 1, 2, 50, 0);