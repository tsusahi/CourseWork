<!DOCTYPE html>
<html lang="pa">
<head>
    <title>Library</title>
</head>
<body>
<h1>Welcome!</h1><hr>
<#if user.email??>
    <h3>Username: <i>${user.name}</i></h3>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Exist"/>
    </form>
<#else>
    <a href="/login">Login</a>
</#if>
<hr>
<h4>Books from all over the world live here!</h4>
<form action="/" method="get">
    Find by name: <input type="text" name="name"><br>
    <input type="submit" value="Find"/>
</form>
<#list books as book>
    <div>
        <p><a href="/book/${book.id}"><b>${book.author} "${book.name}"</b></a> | ${book.price} </p>
    </div>
<#else>
    <h3>Books are missing!</h3>
</#list>
<#if user.email??>
    <hr>
    <h3>Create new book</h3>
    <form action="/book/create" method="post" enctype="multipart/form-data">
        Book name: <input type="text" name="name"/><br><br>
        Author: <input type="text" name="author"/><br><br>
        Description: <input type="text" name="description"/><br><br>
        Price: <input type="text" name="price"/><br><br>
        First image: <input type="file" name="file1"/><br><br>
        Second image: <input type="file" name="file2"/><br><br>
        Third image: <input type="file" name="file3"/><br><br>
        <input type="hidden" name="_csrf" value=${_csrf.token}>
        <input type="submit" value="Add book"/>
    </form>
</#if>
</body>
</html>