<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book Menu</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
</head>

<script>
    var service = "http://localhost:8080/book";
    var RestGetAll = function () {
        $.ajax({
            type: 'GET',
            url: service + '/all',
            contentType: 'application/json;utf-8',
            async: false,
            success: function (result) {
                $('#response').html(JSON.stringify(result));
            },
            error: function (jqXHR, testStatus, errorThrown) {
                $('#response').html(JSON.stringify(jqXHR));
            }
        });
    };

    var service = 'http://localhost:8080/book';
    var RestGetById = function (id) {
        $.ajax({
            type: 'GET',
            url: service + "/get/" + id,
            contentType: 'application/json;utf-8',
            async: false,
            success: function (result) {
                $('#response').html(JSON.stringify(result));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $('#response').html(JSON.stringify(jqXHR));
            }
        });
    };

</script>
<body>

<table class="table">
    <tr>
        <th>Request type</th>
        <th>URL</th>
        <th>Value</th>
    </tr>
    <tr>
        <td>GET ALL</td>
        <td>
            <code>GET http://localhost:8080/book/all</code>
        </td>
        <td>
            <form>
                <button type="button" onclick="RestGetAll()">Try</button>
            </form>
        </td>
    </tr>
    <tr>
        <td>GET BY ID</td>
        <td>
            <code>GET http://localhost:8080/book/get/</code>
            <input id="getBookId" value=""/>
        </td>
        <td>
            <form>
                <button type="button" onclick="RestGetById($('#getBookId').val())">Try</button>
            </form>
        </td>
    </tr>
</table>


<div class="panel panel-default">
    <div class="page-heading">
        <strong>RESPONSE</strong>
    </div>
    <div class="panel-body" id="response"></div>
</div>
</body>
</html>
