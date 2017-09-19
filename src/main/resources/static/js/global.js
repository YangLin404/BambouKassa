'use strict';

var items = [];

$(function () {
    initItems();
})

function createTicket(tableNr) {
    $.post("/restaurant/createTicket?tableNr="+tableNr, function (data, status) {
        $("#tableContent"+tableNr).empty().append(data);
        $("#createNewTicketBtn"+tableNr).hide();
        var ticketNr = $("#hiddenTicketNr").val();
        $("#inputTicket" + ticketNr).focus();
        initTypeahead();
    })
}

function payTicket(ticketNr, tableNr, payMethod) {
    $('#payModal'+ticketNr).on('hidden.bs.modal', function (e) {
        $.post("/restaurant/" + ticketNr + "/pay?payMethod="+ payMethod, function (data) {
            retrieveTicket(tableNr);
        });
    });
}

function payAmountChanged(ticketNr) {
    var amountToPay = $("#amountToPay" + ticketNr).text();
    var payAmount = $("#payAmount" + ticketNr).val();
    var payback = (Number(payAmount) - Number(amountToPay)).toFixed(2);
    $("#payBackAmount" + ticketNr).text('€ ' + payback);
}



function doesItemNeedExtra(quicklink) {
    for (var item in items) {
        if (items[item].quicklink == quicklink) {
            if (items[item].itemType == "MainDishe") {
                return true;
            } else
                break;
        }
    }
    return false;
}

function Item() {

    this.quicklink='';
    this.name='';
    this.itemType='';
};

function initItems() {
    $.get("/restaurant/getItems", function (data) {
        for (var i in data) {
            items[i] = new Item();
            items[i].quicklink = data[i].quicklink;
            items[i].name = data[i].name;
            items[i].itemType = data[i].itemType;
        }
    })
}

function initTypeahead() {
    $('.js-typeahead').typeahead({
        order: "desc",
        display: ["quicklink", "name"],
        source: items,
        minLength: 1,
        maxItem: 8,
        highlight: true,
        template: '<span>' +
        '<span class="name">{{quicklink}} | </span>' +
        '<span class="quicklink">{{name}}</span>' +
        '</span>',
        templateValue: "{{quicklink}},{{name}}",
        cancelButton: true
    });
}

Date.prototype.toDateInputValue = (function() {
    var local = new Date(this);
    local.setMinutes(this.getMinutes() - this.getTimezoneOffset());
    return local.toJSON().slice(0,10);
});