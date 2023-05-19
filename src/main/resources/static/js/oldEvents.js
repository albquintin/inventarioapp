$(".open-modal").on("click", function(){
  var row = $(this).closest('tr');
  var eventId = row.find("td:first-child");
  var eventName = row.find("td:nth-child(2)");
  var url = "/events/old_events/delete/" + eventId.text();
  $("#modal-content").append("¿Desea borrar de manera definitiva el evento \'" + eventName.text() + "\'?");
  $(".delete-button").attr("href", url);
  $(".modal").addClass("is-active");
});
$(".close-modal").on("click", function(){
  $(".modal").removeClass("is-active");
  $("#modal-content").empty();
});
$(document).ready(function()  {
    $('#oldEventsTable').DataTable();
});
$('#oldEventsTable').DataTable({
    'aoColumnDefs': [{
        'bSortable': false,
        'aTargets': [-1]
    }]
});