$(function (){
    $(".draggable").draggable({
        handle: '.handle',
        iframeFix:true
    });
});
function toggleChatbot() {
    $("#chatbot-container").show("blind",500);
}
function fn_hide() {
    $('#chatbot-container').hide('blind',500);
}