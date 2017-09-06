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

function payTicket(ticketNr, tableNr, payMethod) {
    $('#payModal'+ticketNr).on('hidden.bs.modal', function (e) {
        console.log(payMethod);
        $.post("/restaurant/" + ticketNr + "/pay?payMethod="+ payMethod, function (data) {
            retrieveTicket(tableNr);
        });
    });
}