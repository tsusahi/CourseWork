<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Authorization</h1>
    <hr>
    <form action="/login " method="post">
        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" class="form-control" id="email" name="username">
            <div id="email" class="invalid-feedback">
                Incorrect email!
            </div>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" name="password">
            <div id="password" class="invalid-feedback">
                Incorrect password!
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" style="width: 100%" class="btn btn-dark">Log In</button>
    </form>
    <hr>
    <h2>No account? <a href="/registration">Register!</a></h2>
</@t.template>
