CREATE TABLE IF NOT EXISTS ORDERS(

    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,

    fullName VARCHAR(50) NOT NULL,
    phoneNumber VARCHAR(50) NOT NULL,
    mail VARCHAR(50) NOT NULL,

    productName VARCHAR(50) NOT NULL,

    comment VARCHAR(200) NOT NULL,

    orderDate DATE NOT NULL,
    orderId  VARCHAR(50) NOT NULL,

    orderStatus VARCHAR(50) NOT NULL

);