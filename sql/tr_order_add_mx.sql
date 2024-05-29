set catalog mallcat;
set schema mallsch;

DROP TRIGGER tr_order_add;

--有新訂單要扣掉對應的庫存數量
CREATE TRIGGER tr_order_add
AFTER INSERT ON orderlists
REFERENCING NEW AS newodr
FOR EACH ROW
UPDATE commodities SET CommodityQty = (
(SELECT CommodityQty FROM commodities WHERE CommodityID = newodr.OrderCommodityID) -
newodr.OrderQty) WHERE CommodityID = newodr.OrderCommodityID;