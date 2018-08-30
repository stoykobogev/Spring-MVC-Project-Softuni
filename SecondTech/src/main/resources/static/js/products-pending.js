$(() => {
    let domainName = 'http://localhost:8080';
    let table = $('#table');
    $(document).ajaxSend(function (event, xhr) {
        xhr.setRequestHeader("X-CSRF-TOKEN", $('#_csrf').val());
    });

    $('.approve-btn').click(function () {
        sendRequest(this, 'APPROVED')
    });

    $('.reject-btn').click(function () {
        sendRequest(this, 'REJECTED')
    });

    function sendRequest(button, status) {
        let row = $(button).parent().parent();
        let productId = row.find('input').val();
        let route = $('#route').val();

        $.ajax({
            method: 'POST',
            url: domainName + route,
            contentType: "application/json",
            data: JSON.stringify({
                id: productId,
                status: status
            })
        });

        row.parent().remove();
        if (table.children().length === 0) {
            table.remove();
            $('#container').append('<h2 class="font-weight-bold text-center m-5">No products</h2>')
        }
    }
});