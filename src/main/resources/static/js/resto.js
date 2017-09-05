$(document).ready(function () {
    initTable();
})

function initTable() {
    $.get("/restaurant/getTables", function (data){
        data.forEach(t => {
            $("#table"+t.tableNr).on('show.bs.collapse', function () {
                retrieveTicket(t.tableNr);
                $("#restaurantTable"+t.tableNr).removeClass("col-lg-6");
            }).on('hide.bs.collapse', function () {
                $("#restaurantTable"+t.tableNr).addClass("col-lg-6");
            });
            $(".tableBtn").click(function () {
                $('.collapse').collapse('hide');
            })
        })
    })
}

function retrieveTicket(tableNr) {
    $.get("/restaurant/"+tableNr, function (data, status) {
        if (status == "success") {
            $("#tableContent" + tableNr).empty().append(data);
            var ticketNr = $("#hiddenTicketNr").val();
            $("#inputTicket" + ticketNr).focus();
            $("#btnAddItem" + ticketNr).click(function () {
                var itemQL = $("#inputTicket" + ticketNr).val();
                addItemToTicket(ticketNr,itemQL);
            })
            $("#createNewTicketBtn"+tableNr).click(function () {
                createTicket(tableNr);
            })
            if (!$("#createNewTicketBtn"+tableNr).length)
                initTypeahead();
        }
    })
}