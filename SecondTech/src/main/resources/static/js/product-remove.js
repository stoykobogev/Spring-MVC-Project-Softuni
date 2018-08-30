$(() => {
    let domainName = 'http://localhost:8080';
    $(document).ajaxSend(function (event, xhr) {
        xhr.setRequestHeader("X-CSRF-TOKEN", $('#_csrf').val());
    });

    $('.reject-btn').click(function () {
        let row = $(this).parent().parent();
        let productId = row.find('input').val();
        let route = $('#route').val();

        $.ajax({
            method: 'POST',
            url: domainName + route,
            data: {
                id: productId
            }
        });

        row.remove();
    });
});