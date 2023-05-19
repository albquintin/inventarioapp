  $(document).ready(function(){
    var typeId = $("#types").val();
    var subtypeId = $("#selectedSubtype").val();
    var s = '<option value=' + -1 + '>Elija el Subtipo</option>';
    $.ajax({
      type: 'GET',
      url: '/loadSubtypesByType/' + typeId,
      success: function(result){
        var result = JSON.parse(result);
        for (var i = 0; i < result.length; i++) {
            if(result[i].id==subtypeId){
                s += '<option value="' + result[i].id + '" selected>'+ result[i].name+ '</option>';
            }else{
                s += '<option value="' + result[i].id + '">'+ result[i].name+ '</option>';
            }

        }
        $('#subtypes').html(s);
      }
    });
    $("#types").change(function(){
      typeId = $("#types").val();
      s = '<option value=' + -1 + '>Elija el Subtipo</option>';
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