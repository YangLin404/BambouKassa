'use strict';

function searchTickets(elem) {
    var date = $("#inputDate").val();
    var bcc = $("#checkBcc").val();
    var cash = $("#checkCash").val();

}

function initDayOverview() {
    $('#dayOverviewForm').change(function () {
        searchTickets($(this));
    })
}

$(document).ready(function () {
    initTabs();
    initDayOverview();

    $.get("/overview/dayOverview", function (data) {
        console.log(data);
        $("#dayOverviewTab").empty().append(data).addClass('show');
        $("#inputDate").val(new Date().toDateInputValue());

    });
});

function initTabs() {
    $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        var containerId = e.target.href.split("#")[1]
        var container = $("#" + containerId);
        var urlAttr = containerId.substring(0,containerId.length-3);
        $.get("/overview/" + urlAttr, function (data) {
            container.empty().append(data);
        });
    })
}

function retrieveTicket(ticketNr) {
    if (($("#resultTicketContent"+ticketNr).hasClass("col-lg-6"))) {
        $.get("/takeway/" + ticketNr, function (data) {
            $("#ticketContent" + ticketNr).empty().append(data);
            $("#resultTicketContent" + ticketNr).removeClass("col-lg-6");
            $("#ticket" + ticketNr).on('hidden.bs.collapse', function () {
                $("#resultTicketContent" + ticketNr).addClass("col-lg-6");
            });
        })
    }
}

