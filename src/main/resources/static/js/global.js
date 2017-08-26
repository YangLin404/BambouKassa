'use strict';

$(document).ready(function () {
    retrieveTable();
})


function retrieveTicket(ticketNr) {
    var url = '/restaurant/'+ticketNr;
    $("#ticket"+ticketNr).load(url);

}

function retrieveTable() {
    $.get("/restaurant/getTables", function (data){
        console.log(data);
        data.forEach(t => {
            $("#table"+t.tableNr).on('show.bs.collapse', function () {
                if (t.ticket !== null)
                    retrieveTicket(t.ticket.ticketNr);
            })
        })
    },

    )
}