# catalogue

## Getting Started

1. Installation Docker & Docker Compose: [Getting started officiel](https://docs.docker.com/engine/getstarted/)
2. Cloner ce repo
3. Construire l'app et lancer le serveur local: `docker-compose up`
4. Aller regarder sur localhost:8083
5. Installer Postman : [Postman](https://www.getpostman.com/downloads/)


## Access to H2 database

1. Go to url http://localhost:8181/
2. Set JDBC URL: jdbc:h2:tcp://h2:1521/mem:test-db
3. Connect


## API postman 

### login 

Method: POST
Url : http://localhost:8083/login
Payload : {"username": "user","password": "passer"} or {"username": "admin","password": "1234"}

get JWT token on Authorization headers response

- Paste this token in all requests to get resources in Authorization headers
Format: Bearer token

Example : Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNTczMDU1NjE4LCJyb2xlcyI6W3siYXV0aG9yaXR5IjoiVVNFUiJ9XX0.7e9eZadRsVsBFVqyLmPBWVw4EMjJBK-LyXsS25TN47E

### Product Cataloge

- Method: GET
- Url: http://localhost:8083/api/products

### Product Detail

- Method: GET
- Url: http://localhost:8083/api/products/id

### Add or Update Product to Bag

- Method: POST
- Url: http://localhost:8083/api/shopping-carts
- payload example : {
  "productOrders": [{
   "product": {
   	 "id": 1,
     "name": "Produit 1"
    },
   "quantity": 3
  }]
}

- Response Example: {
    "id": 1,
    "code": null,
    "status": "CREATED",
    "username": "user",
    "shoppingProducts": [
        {
            "quantity": 2,
            "productId": 1,
            "totalPrice": 26000.0,
            "productName": "Produit 1"
        }
    ],
    "numberOfProducts": 1,
    "totalOrderPrice": 26000.0
}

### Show bag containt

- Method: GET
- URL: http://localhost:8083/api/shopping-carts/bag

- Response Example: {
    "id": 1,
    "code": null,
    "status": "CREATED",
    "username": "user",
    "shoppingProducts": [
        {
            "quantity": 4,
            "productId": 2,
            "totalPrice": 12000.0,
            "productName": "Produit 2"
        },
        {
            "quantity": 3,
            "productId": 1,
            "totalPrice": 39000.0,
            "productName": "Produit 1"
        }
    ],
    "numberOfProducts": 2,
    "totalOrderPrice": 51000.0
}


### Valided shopping cart (bag containt)

- Method: GET
- URL: http://localhost:8083/api/shopping-carts/validate-bag

- Response Example : {
    "id": 1,
    "code": null,
    "status": "PAID",
    "username": "user",
    "shoppingProducts": [
        {
            "quantity": 2,
            "productId": 1,
            "totalPrice": 26000.0,
            "productName": "Produit 1"
        }
    ],
    "numberOfProducts": 1,
    "totalOrderPrice": 26000.0
}

### Improvement in progress

- Handle order ( link order and shopping-cart) transform validate shopping card to order
- Add Jwt config in swagger for api documentation swagger link : http://localhost:8083/swagger-ui.html
- Have better test coverage
- Add front app for this api

