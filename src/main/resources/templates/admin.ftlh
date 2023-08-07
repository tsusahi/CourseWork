<!DOCTYPE html>
<html lang="pa">
<head>
    <title>Library54</title>
</head>
<body>
<h1>Administrator's panel</h1><hr>
<table border="1">
    <tr>
        <th>Email</th>
        <th>Phone number</th>
        <th>Activity</th>
        <th>Roles</th>
        <th>Ban</th>
        <th>Editing rights</th>
        <th>Details</th>
    </tr>
    <#list users as user>
        <tr>
            <th>${user.email}</th>
            <th>${user.phoneNumber}</th>
            <th><#if user.active>true<#else>false</#if></th>
            <th><#list user.roles as role>${role}</#list></th>
            <th>
                <form action="/admin/user/ban/${user.id}" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <input type="submit" value="Ban">
                </form>
            </th>
            <th><a href="/admin/user/edit/${user.id}">Editing rights</a></th>
            <th><a href="/user/${user.id}">Details</a></th>
        </tr>
    <#else>
        <h3>Users not found</h3>
    </#list>
</table>
</body>
</html>