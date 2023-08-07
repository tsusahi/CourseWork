<!DOCTYPE html>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<h1>Let's see!</h1>
<h4>${user.name}'s books</h4>
<#list books as book>
    <div>
        <p><a href="/book/${book.id}"><b>${book.author} "${book.name}"</b></a> | ${book.price} </p>
    </div>
    <#else>
    <h3>Books not found :(</h3>
</#list>
<#if errorMessage??>
    <h2 style="color: red">${errorMessage}</h2>
</#if>
</body>
</html>