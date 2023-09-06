<#import "blocks/template.ftlh" as t>
<@t.template userByPrincipal>
    <h1 style="text-align: center">User info</h1>
    <hr>
    <div style="text-align: center">
        <img src="/static/images/avatar.png"
             style="border:2px solid black; border-radius: 20px" height="120">
        <br>
        <br>
        <h2>${user.name}</h2>
        <br>
        Phone number: <b>${user.phoneNumber}</b>
    </div>
    <br>
    <hr>
    <h1 style="text-align: center">User books ${user.name}</h1>
    <br>
    <div class="card-columns">
        <#list books as book>
            <a href="/book/${book.id}" class="book-link">
                <div class="card text-white bg-dark my-3 book">

                    <img src="/images/${book.previewImageId}"
                         class="card-img-top">
                    <div class="card-body">
                        <h5 class="card-title">${book.name}</h5>
                        <p class="card-text">${book.price} ₽</p>
                    </div>
                    <div class="card-footer">
                        ${book.user.name}
                    </div>
                </div>
            </a>
        <#else>
            <h1 style="text-align: center">Not found</h1>
        </#list>
    </div>
</@t.template>
