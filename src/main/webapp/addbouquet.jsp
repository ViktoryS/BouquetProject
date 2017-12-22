<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Vikki
  Date: 10.12.2017
  Time: 23:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/style.css" type="text/css">
    <title>Home</title>
</head>
<body>

<h2>Flowers Bouquet App</h2>
<p>In this example, we create an "active" class with a green background color and a white text. The class is added to the "Home" link.</p>

<div class="menuBar">
    <form method="post" action="">
        <input name="command" value="ListItems" type="hidden"/>
        <button type="submit">List Of Items</button>
    </form>
    <form method="post" action="">
        <input name="command" value="AddFlower" type="hidden"/>
        <button type="submit">Add item</button>
    </form>
    <form method="post" action="">
        <input name="command" value="ListBouquets" type="hidden"/>
        <button type="submit">List Of Bouquets</button>
    </form>
    <form method="post" action="">
        <input name="command" value="AddBouquet" type="hidden"/>
        <button type="submit">Add bouquet</button>
    </form>
</div>

<div style="margin-left:25%;padding:1px 16px;height:1000px;">
    <p class=${type}> ${message} </p>
    <div>
        <p>Please, input the parameters:</p>
        <form method="post" action="">
            <table>
                <tr>
                    <td>Name:</td>
                    <td><input type="text" name="name"></td>
                </tr>
                <tr>
                    <td>Discount:</td>
                    <td><input type="text" name="discount"></td>
                </tr>
                <tr>
                    <input name="command" value="AddBouquet" type="hidden"/>
                    <td><input type="submit" class="submit" value="Add bouquet"></td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>