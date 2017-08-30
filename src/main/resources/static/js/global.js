'use strict';

var items = [];

$(document).ready(function () {
    initTable();
    initItems();
    console.log(items);
})


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
        }
    })
}

function addItemToTicket(elem) {
    event.preventDefault();
    var ticketNr = $(elem).val();
    var inputElem = $("#inputTicket"+ticketNr);
    var tableContent = inputElem.closest("[id^=tableContent]");
    if (!inputElem.val()) {

    } else {
        var quickLink = inputElem.val();
        var postUrl = "/restaurant/addItemToTicket/"+ticketNr+"?itemQL="+quickLink;
        $.post(postUrl, function (data, status) {
            tableContent.empty().append(data);
            $("#inputTicket" + ticketNr).focus();
            $("#btnAddItem" + ticketNr).click(function () {
                var itemQL = $("#inputTicket" + ticketNr).val();
                addItemToTicket(ticketNr,itemQL);
            })

        })
    }

}

function createTicket(tableNr) {
    $.post("/restaurant/createTicket?tableNr="+tableNr, function (data, status) {
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
            });

            $("#table"+t.tableNr).on('hide.bs.collapse', function () {
                $("#restaurantTable"+t.tableNr).addClass("col-lg-6");
            });
        })
    })
}

function initItems() {
    $.get("/restaurant/getItems", function (data) {
        for (var i in data) {
            items[i] = new Item();
            items[i].quickLink = data[i].quickLink;
            items[i].name = data[i].name;


        }
    })
}

function Item() {

    this.quickLink='';
    this.name='';
}

