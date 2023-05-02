$(document).ready(function(){
    $("#types").change(function(){
      var typeId = $("#types").val();
      var s = '<option value=' + -1 + '>SELECT</option>';
      $.ajax({
        type: 'GET',
        url: '/loadSubtypesByType/' + typeId,
        success: function(result){
          var result = JSON.parse(result);
          for (var i = 0; i < result.length; i++) {
        	  s += '<option value="' + result[i][0] + '">'+ result[i][1]+ '</option>';
          }
          $('#subtypes').html(s);
        }
      });
    });
  });