<%--
  Created by IntelliJ IDEA.
  User: Vikki
  Date: 02.12.2017
  Time: 14:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<script type="text/javascript">
    function showhide(id) {
        var e = document.getElementById(id);
        e.style.display = (e.style.display == 'block') ? 'none' : 'block';
    }
</script>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css" type="text/css">
<title>List</title>
<body>

<h2>Flowers Bouquet App</h2>
<p></p>

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
    <p class="error"> ${message} </p>
    Your bouquet:
    <table>
        <tr>Id: ${bouquet.id}</tr>
        <tr>Name: ${bouquet.name}</tr>
        <tr>Discount: ${bouquet.discount}</tr>
    </table>
    <p class="error"> ${messageFlowers} </p>
    <table class="clothBoard">
        <tr>
            <th>#</th>
            <th>Title</th>
            <th>Long of stem</th>
            <th>Price</th>
            <th>Freshness</th>
            <th>Delete</th>
        </tr>
        <c:forEach var="flower" items="${bouquet.flowers}" >
            <tr>
                <td>${flower.id}</td>
                <td>${flower.name}</td>
                <td>${flower.longOfStem}</td>
                <td>${flower.price}</td>
                <td>${flower.freshness}</td>
                <td width="15%">
                    <form method="post" action="">
                        <input type="hidden" name="flower_id" value="${flower.id}">
                        <input type="hidden" name="bouquet_id" value="${bouquet.id}">
                        <input type="hidden" name="command" value="RemoveFromBouquet">
                        <input type="submit" class="submit" value="Delete From Bouquet"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <p class="error"> ${messagePlants} </p>
    <table class="clothBoard">
        <tr>
            <th>#</th>
            <th>Title</th>
            <th>Long of stem</th>
            <th>Price</th>
            <th>Freshness</th>
            <th>Delete</th>
        </tr>
        <c:forEach var="plant" items="${bouquet.plants}" >
            <tr>
                <td>${plant.id}</td>
                <td>${plant.name}</td>
                <td>${plant.longOfStem}</td>
                <td>${plant.price}</td>
                <td>${plant.freshness}</td>
                <td width="15%">
                    <form method="post" action="">
                        <input type="hidden" name="plant_id" value="${plant.id}">
                        <input type="hidden" name="bouquet_id" value="${bouquet.id}">
                        <input type="hidden" name="command" value="RemoveFromBouquet">
                        <input type="submit" class="submit" value="Delete From Bouquet"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

    <a href="javascript:showhide('flowers')">
        Click to add flowers:
    </a>
    <div id="flowers" style="display:none;">

        <table class="clothBoard">
            <tr>
                <th>#</th>
                <th>Title</th>
                <th>Long of stem</th>
                <th>Price</th>
                <th>Freshness</th>
                <th>Delete</th>
            </tr>
            <c:forEach var="flower" items="${listOfFlowers}" >
                <tr>
                    <td>${flower.id}</td>
                    <td>${flower.name}</td>
                    <td>${flower.longOfStem}</td>
                    <td>${flower.price}</td>
                    <td>${flower.freshness}</td>
                    <td width="15%">
                        <form method="post" action="">
                            <input type="hidden" name="command" value="AddInBouquet">
                            <input type="hidden" name="bouquet_id" value="${bouquet.id}">
                            <input type="hidden" name="flower_id" value="${flower.id}">
                            <input type="submit" class="submit" value="Add in bouquet"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <a href="javascript:showhide('plants')">
        Click to view plants:
    </a>
    <div id="plants" style="display:none;">
        <table class="clothBoard">
            <tr>
                <th>#</th>
                <th>Title</th>
                <th>Long of stem</th>
                <th>Price</th>
                <th>Freshness</th>
                <th>Delete</th>
            </tr>
            <c:forEach var="plant" items="${listOfPlants}" >
                <tr>
                    <td>${plant.id}</td>
                    <td>${plant.name}</td>
                    <td>${plant.longOfStem}</td>
                    <td>${plant.price}</td>
                    <td>${plant.freshness}</td>
                    <td width="15%">
                        <form method="post" action="">
                            <input type="hidden" name="command" value="AddInBouquet">
                            <input type="hidden" name="bouquet_id" value="${bouquet.id}">
                            <input type="hidden" name="plant_id" value="${plant.id}">
                            <input type="submit" class="submit" value="Add in bouquet"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
