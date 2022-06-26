ALTER TABLE IF EXISTS ce_item
    ADD COLUMN initial_amount DECIMAL;
ALTER TABLE IF EXISTS ce_item
    ADD COLUMN initial_currency VARCHAR (3);
ALTER TABLE IF EXISTS ce_item
    ADD COLUMN buynowprice_amount DECIMAL;
ALTER TABLE IF EXISTS ce_item
    ADD COLUMN buynowprice_currency VARCHAR (3);

UPDATE ce_item
SET initial_amount=0.0;
UPDATE ce_item
SET initial_currency='EUR';
UPDATE ce_item
SET buynowprice_amount=0.0;
UPDATE ce_item
SET buynowprice_currency='USD';

ALTER TABLE IF EXISTS ce_item
ALTER
COLUMN initial_amount SET NOT NULL;
ALTER TABLE IF EXISTS ce_item
ALTER
COLUMN initial_amount SET NOT NULL;
ALTER TABLE IF EXISTS ce_item
ALTER
COLUMN buynowprice_amount SET NOT NULL;
ALTER TABLE IF EXISTS ce_item
ALTER
COLUMN buynowprice_currency SET NOT NULL;

ALTER TABLE IF EXISTS ce_item
DROP
COLUMN initialprice;

ALTER TABLE IF EXISTS ce_item
DROP
COLUMN price;