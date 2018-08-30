$(() => {
    let domainName = 'http://localhost:8080';
    let table = $('table');
    let tbody = table.find('tbody');
    $(document).ajaxSend(function (event, xhr) {
        xhr.setRequestHeader("X-CSRF-TOKEN", $('#_csrf').val());
    });

    $('.reject-btn').click(function () {
        let row = $(this).parent().parent();
        let id = row.find('input').val();

        $.ajax({
            method: 'POST',
            url: domainName + "/admin/users/remove",
            data: {
                id: id
            }
        });

        row.remove();
        if (tbody.children().length === 0) {
            table.remove();
            $('#container').append('<h2 class="font-weight-bold text-center m-5">No users</h2>')
        }
    });
});