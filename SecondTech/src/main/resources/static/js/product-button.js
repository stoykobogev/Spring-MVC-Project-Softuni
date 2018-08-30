$(() => {
   $('.product').click(function () {
       $(this).find('.product-form').submit();
   })
});