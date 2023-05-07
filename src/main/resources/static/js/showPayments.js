$(document).ready(function(){
      $("#secondPaymentContainer").hide();
      $("#thirdPaymentContainer").hide();
      $("#totalPayments").change(function(){
        var totalPayments = $("#totalPayments").val();
        if(totalPayments==2){
          $("#secondPaymentContainer").show();
          $("#thirdPaymentContainer").hide();
        } else if(totalPayments==3){
          $("#secondPaymentContainer").show();
          $("#thirdPaymentContainer").show();
        }else{
          $("#secondPaymentContainer").hide();
          $("#thirdPaymentContainer").hide();
        }
      });
    });