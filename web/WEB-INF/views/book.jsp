<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="js/jquery-3.3.1.js" type="text/javascript"></script>
    <link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <script>
        var service = "http://localhost:8080/book";
        //var restGet = function (id, name, description, isdn) {
        var restGet = function () {
        // var jsonobject = {
        //     'id': id,
        //     'name': name,
        //     'description': description,
        //     'isdn': isdn
        // };
            $.ajax({
                url: service + '/all',
                type: 'GET',
                contentType: 'application/json;charset=utf-8',
                datatype: 'json',
                success: function(result) {
                    //$("#id_for").innerhtml(result);
                    var v = JSON.stringify(result[0]);
                    var v1 = JSON.parse();
                    //alert(result);
                    alert("v = " + v.id);
                  //  alert("v1 = " + v1);
                    // for (var i = 0; i < v1.length; i++) {
                    //      for (var v2 in v1) {
                    //          alert(v2);
                    //      }
                    // }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    $('#id_for').html(JSON.stringify(jqXHR));
                    alert("error");
                }
            });
        };

        var restAdd = function(text) {
            $.ajax({
                url: service + '/add',
                type: 'PUT',
                data: text,
                contentType: 'application/json;charset=utf-8',
                datatype: 'json',
                success: function(result) {
                    alert("OK");
                    $('#id_for').html(JSON.stringify(result));
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert("error");
                    $('#id_for').html(JSON.stringify(jqXHR));
                }
            });
        };

        var restGetById = function(id) {
            $.ajax({
                url: service + '/get/' + id,
                type: 'GET',
                contentType: 'application/json;charset=utf-8',
                datatype: 'json',
                success: function(result) {
                    alert("OK");
                    $('#id_for').html(JSON.stringify(result));
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert("error");
                    $('#id_for').html(JSON.stringify(jqXHR));
                }
            });
        };

        var restUpdate = function(text) {
            $.ajax({
                url: service + '/update',
                type: 'POST',
                data: text,
                contentType: 'application/json;charset=utf-8',
                datatype: 'json',
                success: function(result) {
                    alert("OK");
                    $('#id_for').html(JSON.stringify(result));
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert("error");
                    $('#id_for').html(JSON.stringify(jqXHR));
                }
            });
        };

        var restDelete = function(id) {
            $.ajax({
                url: service + '/delete' + '/?id=' + id,
                type: 'DELETE',
                contentType: 'application/json;charset=utf-8',
                datatype: 'json',
                success: function(result) {
                    alert("OK");
                    $('#id_for').html(JSON.stringify(result));
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert("error");
                    $('#id_for').html(JSON.stringify(jqXHR));
                }
            });
        };

    </script>
    <button type="button" onclick="restGet()">press any key</button>
    <div class="panel">
        <p id="id_for" ></p>
    </div>
    <div>
        <textarea id="add_book_text">text</textarea>
        <button id="add_book_button" onclick="restAdd($('#add_book_text').val())">try add</button>
    </div>
    <div>
        <textarea id="book_id">id</textarea>
        <button id="get_book_by_id_button" onclick="restGetById($('#book_id').val())">try get book by id</button>
    </div>
    <div>
        <textarea id="book_update">text_update</textarea>
        <button id="update_book_button" onclick="restUpdate($('#book_update').val())">try update book</button>
    </div>
    <div>
        <textarea id="book_delete">text_delete</textarea>
        <button id="delete_book_button" onclick="restDelete($('#book_delete').val())">try delete book</button>
    </div>
</body>
</html>
