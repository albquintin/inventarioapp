$(document).ready(function()  {
    $('#oldItemsTable').DataTable();
});
$('#oldItemsTable').DataTable({
    'aoColumnDefs': [{
        'bSortable': false,
        'aTargets': [-1]
    }]
});
$(".open-modal").on("click", function(){
  var row = $(this).closest('tr');
  var inventoryItemId = row.find("td:first-child");
  var inventoryItemName = row.find("td:nth-child(2)");
  var url = "/items/old_inventory_items/delete/" + inventoryItemId.text();
  $("#modal-content").append("¿Desea borrar de manera definitiva el objeto \'" + inventoryItemName.text() + "\'?");
  $(".delete-button").attr("href", url);
  $(".modal").addClass("is-active");
});
$(".close-modal").on("click", function(){
  $(".modal").removeClass("is-active");
  $("#modal-content").empty();
});