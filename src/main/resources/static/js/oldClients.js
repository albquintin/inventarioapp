$(".open-modal").on("click", function(){
  var row = $(this).closest('tr');
  var clientId = row.find("td:first-child");
  var clientName = row.find("td:nth-child(2)");
  var url = "/clients/old_clients/delete/" + clientId.text();
  $("#modal-content").append("¿Desea borrar de manera definitiva el cliente \'" + clientName.text() + "\'?");
  $(".delete-button").attr("href", url);
  $(".modal").addClass("is-active");
});
$(".close-modal").on("click", function(){
  $(".modal").removeClass("is-active");
  $("#modal-content").empty();
});
$(document).ready(function()  {
    $('#oldClientsTable').DataTable();
});
$('#oldClientsTable').DataTable({
    'aoColumnDefs': [{
        'bSortable': false,
        'aTargets': [-1]
    }]
});