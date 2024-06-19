$(document).ready(function () {
    $(".toggle-header").click(function () {
        $(this).next(".toggle-body").slideToggle();
    });
});