'use strict';

$(document).ready(function () {
    $("#newTicket").click(function () {
        $.post("/takeway/createTicket", function (data) {
            if (data !== null) {
                var ticket = new Ticket();
                ticket.ticketNr = data.ticketNr;
                ticket.name = data.name;
                location.reload(true);
            }
        })
    })
    initModal();
})


function retrieveTicket(ticketNr, pay) {
    if (($("#takewayTicketContent"+ticketNr).hasClass("col-lg-6")) || pay) {
        $.get("/takeway/" + ticketNr, function (data, status) {
            $("#ticketContent" + ticketNr).empty().append(data);
            $("#inputTicket" + ticketNr).focus();
            $("#btnAddItem" + ticketNr).click(function () {
                var itemQL = $("#inputTicket" + ticketNr).val();
                addItemToTicket(ticketNr, itemQL);
            })
            $("#takewayTicketContent" + ticketNr).removeClass("col-lg-6");
            initTypeahead();
            $("#ticket" + ticketNr).on('hidden.bs.collapse', function () {
                $("#takewayTicketContent" + ticketNr).addClass("col-lg-6");
            });
        })
    }
    if (pay) {
        $("#ticket" + ticketNr).collapse("toggle");
    }

    updateTicketBtn(ticketNr,pay);
}

function updateTicketBtn(ticketNr, pay) {
    var ticketPrice = $("#amountToPay"+ticketNr).text();
    $("#ticketTotalOnBtn"+ticketNr).text("€ " + ticketPrice);
    if (pay) {
        $("#takewayTicketBtnContainer"+ticketNr).append("<div class='p-2 bg-success text-white'>betaald</div>");
    }
}

function payTicket(ticketNr, tableNr, payMethod) {
    $('#payModal'+ticketNr).on('hidden.bs.modal', function (e) {
        $.post("/restaurant/" + ticketNr + "/pay?payMethod="+ payMethod, function (data) {
            //todo after payment close collapse
            retrieveTicket(ticketNr, true);
        });
    });
}

function addItemToTicket(elem) {
    event.preventDefault();

    var ticketNr = $(elem).val();
    var inputElem = $("#inputTicket"+ticketNr);
    var ticketContent = $("#ticketContent"+ticketNr);
    if (!inputElem.val()) {

    } else {
        var quicklink = inputElem.val();
        var postUrl = "/restaurant/addItemToTicket/"+ticketNr+"?quicklink="+quicklink;
        $.post(postUrl, function (data, status) {
            ticketContent.empty().append(data);
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

function addExtraToTicketItem(extra, ticketNr, quicklink, tableNr) {
    //todo no extra if not maindishe
    $('#modal'+ticketNr+'_'+quicklink).on('hidden.bs.modal', function (e) {
        $.post("/restaurant/"+ticketNr+"/"+quicklink+"/AddExtraToItem?extra="+extra, function (data, status) {
            $("#ticketContent"+ticketNr).empty().append(data);
            $("#inputTicket" + ticketNr).focus();
            $("#btnAddItem" + ticketNr).click(function () {
                var itemQL = $("#inputTicket" + ticketNr).val();
                addItemToTicket(ticketNr,itemQL);
            })
            initTypeahead();
        })
    })
}

function updateTicket(ticketNr, name, ticketTime) {
    var paras = {
        name:name,
        time:ticketTime
    };
    var url = "/takeway/updateTicket/"+ticketNr+"?" + $.param(paras);
    $.post(url, function (data) {
        if (data !== null) {
            var ticket = new Ticket();
            ticket.name = data.name;
            ticket.time = data.time;
            ticket.ticketNr = data.ticketNr;
            $('#ticketNameLabel' + ticket.ticketNr).text(ticket.name);
            $('#ticketTimeLabel' + ticket.ticketNr).text(ticket.time);
        }

    })
}

function deleteTicket(elem) {
    var ticketNr = $(elem).val();

    $.post("/takeway/"+ticketNr+"/delete", function (data) {
        location.reload(true);
    })

}

function initModal() {
    $('#myModal').on('show.bs.modal', function(e) {

        //get data-id attribute of the clicked element
        var name = $(e.relatedTarget).data('name');
        var ticketTime = $(e.relatedTarget).data('time');
        var ticketNr = $(e.relatedTarget).data('ticketnr');
        //populate the textbox
        var inputName = $(e.currentTarget).find('input[name="client-name"]');
        var inputTime = $(e.currentTarget).find('input[name="ticket-time"]');
        inputName.val(name);
        inputTime.val(ticketTime);
        $('#myModal').on('hide.bs.modal',function () {
            updateTicket(ticketNr, inputName.val(), inputTime.val());
        })
    });
}

function Ticket() {

    this.ticketNr='';
    this.name='';
    this.time='';
};