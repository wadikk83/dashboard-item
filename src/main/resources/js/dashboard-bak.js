/*
AJS.$(document).ready(function () {
    let xhttp = new XMLHttpRequest();

    xhttp.open("GET", AJS.params.baseURL + "/rest/rest-resource/1.0/plugin-rest/all",
        true, "admin", "admin");
    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send();

    xhttp.onload = function () {
        //alert("Status -> " + xhttp.status + " Responce -> " + $.parseJSON(xhttp.response));
        let resp = $.parseJSON(xhttp.response);
        buildTable(resp);
    };
    xhttp.onerror = function () {
        alert("Запрос не удался");
    };
});

function buildTable(data) {
    $("#myTable").empty();
    //$("#myTable").remove();

    let table = document.getElementById('myTable');
    for (var i = 0; i < data.length; i++) {
        var row = `<tr>
    <td>${i + 1}</td>
    <td>${data[i].project}</td>
    <td>${data[i].fields}</td>
					  </tr>`
        table.innerHTML += row
    }
}

function sendPostRequest(params) {
    let xhttp = new XMLHttpRequest();
    console.log(JSON.stringify(params));

    xhttp.open("POST", AJS.params.baseURL + "/rest/rest-resource/1.0/plugin-rest/get",
        true, "admin", "admin");
    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.onreadystatechange = function () {//Call a function when the state changes.
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            //alert(xhttp.responseText);
            buildTable($.parseJSON(xhttp.response))
            //$("#myTable").empty();
        }
    }
    xhttp.send(JSON.stringify(params));
}

function ckChange(event) {
    // Determine if it was a checkbox that got clicked
    if (event.type = "checkbox") {
        // Get the count of all the checked checkboxes
        let checkedCount = document.querySelectorAll("input[type='checkbox']:checked").length;

        let boxes = document.querySelectorAll("input[type='checkbox']:checked");
        const params = [];
        boxes.forEach(element => {
            params[params.length] = element.id;
            console.log(element.id);
        });

        console.log("params -> " + params);
        sendPostRequest(params);
    }
}
*/