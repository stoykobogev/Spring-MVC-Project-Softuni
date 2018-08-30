$(() => {
    let domainName = 'http://localhost:8080';
    let table = $('table');
    let tbody = table.find('tbody');

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
        let commentId = row.find('input').val();

        $.ajax({
            method: 'POST',
            url: domainName +'/comments/update',
            contentType: "application/json",
            data: JSON.stringify({
                id: commentId,
                status: status
            })
        });

        row.remove();
        if (tbody.children().length === 0) {
            table.remove();
            $('#container').append('<h2 class="font-weight-bold text-center m-5">No comments</h2>')
        }
    }
});