'use strict';

var items = [];
let current_title;

$(function () {
    initItems();
    current_title = $(document).attr('title');
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

function addItemToTicket(elem) {
    event.preventDefault();

    var ticketNr = $(elem).val();
    var inputElem = $("#inputTicket"+ticketNr);

    const content = findContent(inputElem, ticketNr);
    if (!inputElem.val()) {

    } else {
        var quicklink = inputElem.val();
        var postUrl = "/restaurant/addItemToTicket/"+ticketNr+"?quicklink="+quicklink;
        $.post(postUrl, function (data, status) {
            content.empty().append(data);
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

function editTicketCount(elem, addMore) {
    const dataDiv =  $(elem).parent("div");
    const ticketNr = dataDiv.data("ticketnr");
    const itemName = dataDiv.data("name");
    const quicklink = dataDiv.data("quicklink");
    const content = findContent(dataDiv, ticketNr);
    let url;
    if (addMore) {
        url = "/restaurant/" + ticketNr + "/addExistItem?name="+itemName+"&quicklink="+quicklink;
    } else {
        url = "/restaurant/" + ticketNr + "/removeExistItem?name="+itemName+"&quicklink="+quicklink;
    }
    $.post(url, function (data, status) {
        content.empty().append(data);
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

function findContent(inputElem, ticketNr) {

    if (current_title === 'resto')
        return inputElem.closest("[id^=tableContent]");
    else
        return $("#ticketContent"+ticketNr);
}

function removeItemFromTicket(elem) {
    event.preventDefault();

}

function addExtraToTicketItem(extra, ticketNr, quicklink, tableNr) {
    $('#modal'+ticketNr+'_'+quicklink).on('hidden.bs.modal', function (e) {
        $.post("/restaurant/"+ticketNr+"/"+quicklink+"/AddExtraToItem?extra="+extra, function (data, status) {

            let content;
            if (current_title === 'resto')
                content = $("#tableContent"+tableNr);
            else
                content = $("#ticketContent"+ticketNr);

            content.empty().append(data);
            $("#inputTicket" + ticketNr).focus();
            $("#btnAddItem" + ticketNr).click(function () {
                var itemQL = $("#inputTicket" + ticketNr).val();
                addItemToTicket(ticketNr,itemQL);
            })
            initTypeahead();
        })
    })
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

Date.prototype.toDateInputValue = (function() {
    var local = new Date(this);
    local.setMinutes(this.getMinutes() - this.getTimezoneOffset());
    return local.toJSON().slice(0,10);
});