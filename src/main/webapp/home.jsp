<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/style.css" type="text/css">
    <title>Home</title>
</head>
<body>

<h2>Flowers Bouquet App</h2>
<p>You can create your bouquet here!</p>

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
    <h2>Welcome</h2>
</div>

</body>
</html>