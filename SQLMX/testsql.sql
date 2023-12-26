set catalog mallcat;
set schema mallsch;

DROP TABLE IF EXISTS commodity_tags_test;

CREATE TABLE commodity_tags_test (						
	CommoditySubTag VARCHAR(20) CHARACTER SET UCS2 NOT NULL PRIMARY KEY,
	CommodityMainTag VARCHAR(20) CHARACTER SET UCS2 NOT NULL 
);

INSERT INTO commodity_tags_test (CommoditySubTag, CommodityMainTag) 
VALUES (_UCS2'¤ôªG', _UCS2'­¹ª«');