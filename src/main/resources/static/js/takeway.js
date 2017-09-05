'use strict';

$(document).ready(function () {

})


function retrieveTicket(ticketNr) {
    $.get("/takeway/"+ticketNr, function (data, status) {
        $("#ticketContent" + ticketNr).empty().append(data);
        $("#inputTicket" + ticketNr).focus();
        $("#btnAddItem" + ticketNr).click(function () {
            var itemQL = $("#inputTicket" + ticketNr).val();
            addItemToTicket(ticketNr,itemQL);
        })
        $("#takewayTicketContent"+ticketNr).removeClass("col-lg-6");
        $(".tableBtn").click(function () {
            $('.collapse').collapse('hide');
        })
        initTypeahead();
    })
}