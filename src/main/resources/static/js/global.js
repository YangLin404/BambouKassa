'use strict';

$(document).ready(function () {
    initTable();
})


function retrieveTicket(tableNr) {
    $.get("/restaurant/"+tableNr, function (data, status) {
        console.log(data);
        console.log(status);
        if (status == "success") {
            $("#tableContent" + tableNr).empty().append(data);
            $("#inputTicket" + tableNr).focus();
            $("#createNewTicketBtn"+tableNr).click(function () {
                createTicket(tableNr);
            })
        }

    })
}

function createTicket(tableNr) {
    $.post("/restaurant/createTicket?tableNr="+tableNr, function (data, status) {
        console.log(data);
        $("#tableContent"+tableNr).empty().append(data);
        $("#createNewTicketBtn"+tableNr).hide();
        $("#inputTicket" + tableNr).focus();
    })


}

function initTable() {
    $.get("/restaurant/getTables", function (data){
        console.log(data);
        data.forEach(t => {
            $("#table"+t.tableNr).on('show.bs.collapse', function () {
                retrieveTicket(t.tableNr);

                $("#restaurantTable"+t.tableNr).removeClass("col-lg-6");
            })

            $("#table"+t.tableNr).on('hide.bs.collapse', function () {
                $("#restaurantTable"+t.tableNr).addClass("col-lg-6");
            })
        })
    })
}

