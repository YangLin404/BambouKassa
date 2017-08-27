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
            var ticketNr = $("#hiddenTicketNr").val();
            $("#inputTicket" + ticketNr).focus();
            $("btnAddItem" + ticketNr).click(function () {
                var itemQL = $("#inputTicket" + ticketNr).val();
                addItemToTicket(ticketNr,itemQL);
            })
            $("#createNewTicketBtn"+tableNr).click(function () {
                createTicket(tableNr);
            })
        }
    })
}

function addItemToTicket(ticketNr, itemQL) {

}

function createTicket(tableNr) {
    $.post("/restaurant/createTicket?tableNr="+tableNr, function (data, status) {
        console.log(data);
        $("#tableContent"+tableNr).empty().append(data);
        $("#createNewTicketBtn"+tableNr).hide();
        var ticketNr = $("#hiddenTicketNr").val();
        $("#inputTicket" + ticketNr).focus();
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

