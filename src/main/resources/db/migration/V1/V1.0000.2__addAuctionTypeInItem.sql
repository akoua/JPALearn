ALTER TABLE IF EXISTS ce_item
    ADD COLUMN auctionType VARCHAR (30);

UPDATE ce_item
SET auctionType='HIGHEST_BID';

ALTER TABLE IF EXISTS ce_item
ALTER
COLUMN auctionType SET NOT NULL;
