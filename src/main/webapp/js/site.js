/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
window.onload = function () {
    $('#editFormDiv').attr("style", "display: ${tableVisible}");
    $('#title').attr("style", "display: ${tableVisible}");
    $('#newFormDiv').hide();
};
function clicked(id) {
    $('#' + id).submit();
}
function newEntry() {
    $('#editFormDiv').hide();
    $('#newFormDiv').show();
    $('#title').show().html('Add new record <hr>');
}

$(".dropdown-menu li a").click(function(){

      $("#selectedState").html($(this).text() + ' <span class="caret"></span>');
      $("#filterState").val($(this).text());

   });
