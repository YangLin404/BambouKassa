'use strict';

var items = [];

$(document).ready(function () {
    initTable();
    initItems();
})

function addExtraToTicketItem(extra, ticketNr, quicklink, tableNr) {
    $('#modal'+ticketNr+'_'+quicklink).on('hidden.bs.modal', function (e) {
        $.post("/restaurant/"+ticketNr+"/"+quicklink+"/AddExtraToItem?extra="+extra, function (data, status) {
            $("#tableContent"+tableNr).empty().append(data);
            $("#inputTicket" + ticketNr).focus();
            $("#btnAddItem" + ticketNr).click(function () {
                var itemQL = $("#inputTicket" + ticketNr).val();
                addItemToTicket(ticketNr,itemQL);
            })
            initTypeahead();
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

function addItemToTicket(elem) {
    event.preventDefault();

    var ticketNr = $(elem).val();
    var inputElem = $("#inputTicket"+ticketNr);
    var tableContent = inputElem.closest("[id^=tableContent]");
    if (!inputElem.val()) {

    } else {
        var quicklink = inputElem.val();
        var postUrl = "/restaurant/addItemToTicket/"+ticketNr+"?quicklink="+quicklink;
        $.post(postUrl, function (data, status) {
            tableContent.empty().append(data);
            $("#inputTicket" + ticketNr).focus();
            $("#btnAddItem" + ticketNr).click(function () {
                var itemQL = $("#inputTicket" + ticketNr).val();
                addItemToTicket(ticketNr,itemQL);
            });
            if (doesItemNeedExtra(quicklink)) {
                $("#btnExtra"+ticketNr+'_'+quicklink).trigger("click");
            }
            initTypeahead();
        })
    }
}

function createTicket(tableNr) {
    $.post("/restaurant/createTicket?tableNr="+tableNr, function (data, status) {
        $("#tableContent"+tableNr).empty().append(data);
        $("#createNewTicketBtn"+tableNr).hide();
        var ticketNr = $("#hiddenTicketNr").val();
        $("#inputTicket" + ticketNr).focus();
        initTypeahead();
    })
}

function initTable() {
    $.get("/restaurant/getTables", function (data){
        console.log(data);
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
            console.log(items[i]);
        }
    })
}

function initTypeahead() {
    $('.js-typeahead').typeahead({
        order: "asc",
        display: ["quicklink", "name"],
        source: items,
        minLength: 1,
        maxItem: 8,
        highlight: true,
        template: '<span>' +
        '<span class="name">{{quicklink}} | </span>' +
        '<span class="quicklink">{{name}}</span>' +
        '</span>',
        templateValue: "{{quicklink}}",
        cancelButton: false
    });
}