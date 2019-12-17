function printPage(id,id2)

{
   var html="<html>";
   html+= document.getElementById(id).innerHTML;
	
   html+="</html>";

   var printWin = window.open('','','left=10000,top=10000,width=1,height=1,toolbar=0,scrollbars=0,status=0');
   printWin.document.write(html);
   printWin.document.close();
   setTimeout(function(){ printWin.print(); printWin.close(); }, 300);
     
   setTimeout(function(){printPage2(id2);},1000)
     
    
}

function printPage2(id)

{
	   var html="<html>";
	   html+= document.getElementById(id).innerHTML;
		
	   html+="</html>";

	   var printWin = window.open('','','left=10000,top=10000,width=1,height=1,toolbar=0,scrollbars=0,status=0');
	   printWin.document.write(html);
	   printWin.document.close();
	   setTimeout(function(){ printWin.print(); printWin.close(); }, 300);
}




setInterval(function(){
  var date = new Date();
  var format = "DD-MMM-YYYY DDD";
  dateConvert(date,format)
}, 1);

function dateConvert(dateobj,format){
 var year = dateobj.getFullYear();
 var month= ("0" + (dateobj.getMonth()+1)).slice(-2);
 var date = ("0" + dateobj.getDate()).slice(-2);
 var hours = ("0" + dateobj.getHours()).slice(-2);
 var minutes = ("0" + dateobj.getMinutes()).slice(-2);
 var seconds = ("0" + dateobj.getSeconds()).slice(-2);
 var day = dateobj.getDay();
 var months = ["1","2","3","4","5","6","7","8","9","10","11","12"];
 var converted_date = "";

switch(format){
 case "DD-MMM-YYYY":
  converted_date = month + "-" + year + "-" + date;
  break;
case "DD-MMM-YYYY DDD":
  converted_date = date + "/" + months[parseInt(month)-1] + "/" + year	
  + " " + hours + ":" + minutes + ":" + seconds;
  break;
}
//return converted_date;	
// to show it I used innerHTMl in a <p> tag
document.getElementById("TimeTicket").innerHTML = converted_date;
document.getElementById("TimeTicket2").innerHTML = converted_date;
}