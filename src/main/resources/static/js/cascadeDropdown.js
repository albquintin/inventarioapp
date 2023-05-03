  $(document).ready(function(){
    $("#types").change(function(){
      var typeId = $("#types").val();
      var s = '<option value=' + -1 + '>Elija el Subtipo</option>';
      $.ajax({
        type: 'GET',
        url: '/loadSubtypesByType/' + typeId,
        success: function(result){
          var result = JSON.parse(result);
          for (var i = 0; i < result.length; i++) {
        	  s += '<option value="' + result[i].id + '">'+ result[i].name+ '</option>';
          }
          $('#subtypes').html(s);
        }
      });
    });
  });