
$(document).ready(function () {
    $("#update_infor").on('click', 'input', function () {
        $(this).next().remove("div[class*='ui-message']");
    });
    $("#dangky").on('click', 'input', function () {
        $(this).next().remove("div[class*='ui-message']");
    });

    $("body").on('click', 'input', function () {
        $(this).next().remove("div[class*='ui-message']");
    });

});


