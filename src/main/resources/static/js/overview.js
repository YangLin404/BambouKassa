'use strict';

function updateResultTotal() {
    $('resultTotal')
}

function searchTickets() {
    var paymothod = $('input[name=payMothodRadioBtn]:checked').val();
    var date = $('#inputDate').val();
    var url = '/overview/dayOverview/findTicketsByDate?date=' + date + '&filter=' + paymothod;
        $.get(url, function (data) {
            $('#result').empty().append(data);
            updateResultTotal();
        })
}

function initDayOverview() {
    $('#dayOverviewForm :input').change(function () {
        searchTickets();
    })
}

$(function () {
    initTabs();
    $.get("/overview/dayOverview", function (data) {
        console.log(data);
        $("#dayOverviewTab").empty().append(data).addClass('show');
        $("#inputDate").val(new Date().toDateInputValue());
        initDayOverview();
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

