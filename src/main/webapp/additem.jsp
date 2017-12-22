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
<script type="text/javascript">
    function showhide(id) {
        var e = document.getElementById(id);
        e.style.display = (e.style.display == 'block') ? 'none' : 'block';
    }
</script>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/style.css" type="text/css">
    <title>Add Item</title>
</head>
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
    <p class=${type}> ${message} </p>
    <a href="javascript:showhide('flowers')">
        Click to add flower:
    </a>
    <div id="flowers" style="display:none;">
        <p>Please, input the parameters:</p>
        <form method="post" action="">
            <table>
                <tr>
                    <td>Name:</td>
                    <td><input type="text" name="flower_name"></td>
                </tr>
                <tr>
                    <td>Long of stem:</td>
                    <td><input type="text" name="flower_longOfStem"></td>
                </tr>
                <tr>
                    <td>Price:</td>
                    <td><input type="text" name="flower_price"></td>
                </tr>
                <tr>
                    <td>Freshness:</td>
                    <td>
                        <select name="flower_freshness">
                            <c:forEach var="freshness" items="${freshnessValues}">
                                <option value=${freshness}>${freshness}</option>
                            </c:forEach>
                        </select>
                        <input type="hidden" value=${freshness} name="flower_freshness">
                    </td>
                </tr>
                <tr>
                    <td>Color:</td>
                    <td><input type="text" name="flower_color"></td>
                </tr>
                <tr>
                    <td>Count of flowers:</td>
                    <td><input type="text" name="countOfFlowers"></td>
                </tr>
                <tr>
                    <input name="command" value="AddFlower" type="hidden"/>
                    <td><input type="submit" class="submit" name="addButton" value="Add flower"></td>
                </tr>
            </table>
        </form>
    </div>

    <a href="javascript:showhide('plant')">
        Click to add plant:
    </a>
    <div id="plant" style="display:none;">
        <p>Please, input the parameters:</p>
        <form method="post" action="">
            <table>
                <tr>
                    <td>Name:</td>
                    <td><input type="text" name="plant_name"></td>
                </tr>
                <tr>
                    <td>Long of stem:</td>
                    <td><input type="text" name="plant_longOfStem"></td>
                </tr>
                <tr>
                    <td>Price:</td>
                    <td><input type="text" name="plant_price"></td>
                </tr>
                <tr>
                    <td>Freshness:</td>
                    <td>
                        <select name="plant_freshness">
                            <c:forEach var="freshness" items="${freshnessValues}">
                                <option value=${freshness}>${freshness}</option>
                            </c:forEach>
                        </select>
                        <input type="hidden" value=${freshness} name="plant_freshness">
                    </td>
                </tr>
                <tr>
                    <td>Count of Branches:</td>
                    <td><input type="text" name="countOfBranches"></td>
                </tr>
                <tr>
                    <input name="command" value="AddPlant" type="hidden"/>
                    <td><input type="submit" class="submit" name="addButton" value="Add plant"></td>
                </tr>
            </table>
        </form>
    </div>
</div>

</body>
</html>