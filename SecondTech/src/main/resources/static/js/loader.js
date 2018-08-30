let domainName = 'http://127.0.0.1:8080';

function load(callback) {
    let container = $('#container');
    let footer = $($('.footer')[0]);
    container.append('<img src="/images/gear.png" width="150" height="150" class="loader">');
    callback().then(context => {
        container.append(context[0]);
        footer.remove();

        $(() => {
            $('.loader').remove();
            $('body').append(footer);
        });
    });
}

let loader = {
    load
};