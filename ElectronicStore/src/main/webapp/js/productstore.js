$(document).ready(function () {
    // Ensure all tabs (ul elements) are visible by default
    $('#menu ul').show();

});

$(document).ready(function() {
    $("#grid tr:even").addClass('classEven');
});

$(document).ready(function () {
    $("span.tooltip_message").hover(function () {
        $(this).append('<div class="message"><p>Search by Keyword in:<ul><li>Product Name</li><li>Category<li>Brand</li></ul></p></div>');
    },function () {
        $("div.message").remove();
    });
});
