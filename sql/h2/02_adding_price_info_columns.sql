ALTER TABLE PUBLIC.PRODUCT_IN_ESHOP ADD PRODUCT_NAME_IN_ESHOP varchar(255);
ALTER TABLE PUBLIC.PRODUCT_IN_ESHOP ADD PRODUCT_ACTION varchar(255);
ALTER TABLE PUBLIC.PRODUCT_IN_ESHOP ADD ACTION_VALID_TO timestamp;
ALTER TABLE PUBLIC.PRODUCT_IN_ESHOP ADD PRICE_FOR_PACKAGE decimal(19,2);
ALTER TABLE PUBLIC.PRODUCT_IN_ESHOP ADD PRICE_FOR_ONE_ITEM_IN_PAC decimal(19,2);
ALTER TABLE PUBLIC.PRODUCT_IN_ESHOP ADD PRICE_FOR_UNIT decimal(19,2);
ALTER TABLE PUBLIC.PRODUCT_IN_ESHOP ADD LAST_UPDATED_PRICE timestamp;