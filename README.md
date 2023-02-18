# ProductsStore
 E-Commerce app which allows users to view products, see product details and add products to a cart.

This application uses spring boot, mongodb cloud.
If you would like to check the database data go to cloud.mongodb.com, enter login/password ysednev/testPassword1 
click database, browse collections
![img_5.png](img_5.png)


To start the application follow these steps:

1) Install maven 3, java 11
2) Open productsStore folder and run this command in console: mvnw spring-boot:run
3) Use postman to call the GET endpoint to see all products: http://localhost:8080/products
4) Use postman to call the GET endpoint to see product details by id: http://localhost:8080/products/1

Here is the sample json response which will be returned:

![img.png](img.png)

5) Use postman to call the POST endpoint to add products to user cart: http://localhost:8080/products/1/cart

and send request body as such:
{
"userId": "1",
"quantity": 3
}

This request will add 3 items of product 1 to a user cart with user id = 1;

Example:

![img_4.png](img_4.png)

To check the jacoco coverage report run "mvn clean package", after build is complete go to target\site\jacoco\
As you can see business logic located in our ProductsDelegate is mostly covered
(except for some methods which are generated by lombok)

![img_2.png](img_2.png)

**Assumptions**

Each user has a unique cartId, if a user does not have a cart it will be created on a first "add to cart" call
Min value for quantity is 1
For simplicity users can only add items to a cart

**Ways to improve:**

One way to improve the application is to return specific messages when user input is invalid. 
For example now in add to cart request if you try to add a negative quantity you will get a generic error message
