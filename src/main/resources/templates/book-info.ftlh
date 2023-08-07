<!DOCTYPE html>
<html>
<head>
    <title>Library54</title>
</head>
<body>
<h1>Let's see!</h1>
<b><h2>${book.name}<br></h2></b>
<b><h3>${book.author}</h3></b>
<#list images as img>
    <img src="/images/${img.id}" height="60px"><br><br>
</#list>
<b>About book: </b>${book.description}<br>
<b>Price: </b>${book.price}<br>
<b>Seller: </b><a href="/user/${book.user.id}">${book.user.name}</a><br>
</body>
</html>