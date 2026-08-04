ALTER TABLE review
ALTER COLUMN review_rating TYPE DOUBLE PRECISION USING review_rating::double precision;