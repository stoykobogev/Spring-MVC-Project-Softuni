$(() => {
    let domainName = 'http://localhost:8080';
    let productId = $('#productId').val();
    let productType = $('meta[name=product]').attr('content');
    $(document).ajaxSend(function (event, xhr) {
        xhr.setRequestHeader("X-CSRF-TOKEN", $('#_csrf').val());
    });

   $('#comment-button').click(function (e) {

       let content = $('#comment').val();

       $.ajax({
           method: 'POST',
           url: domainName +'/comments/add',
           contentType: "application/json",
           data: JSON.stringify({
               productId: productId,
               content: content,
               productType: productType
           })
        });

       $('#comment-div').remove();
   });

    $('#order-btn-modal').click(function () {
        $.ajax({
            method: 'POST',
            url: domainName +'/orders/add',
            contentType: "application/json",
            data: JSON.stringify({
                productId: productId,
                productType: productType
            })
        });

        $('#order-btn').prop("disabled", true);
    })
});