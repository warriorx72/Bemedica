function printPage(id)

{
   var html="<html>";
   html+= document.getElementById(id).innerHTML;
	
   html+="</html>";

   var printWin = window.open('','','left=10000,top=10000,width=1,height=1,toolbar=0,scrollbars=0,status=0');
   printWin.document.write(html);
   printWin.document.close();
   setTimeout(function(){ printWin.print(); printWin.close(); }, 200);
   
   var printWin2 = window.open('','','left=10000,top=10000,width=1,height=1,toolbar=0,scrollbars=0,status=0')
   printWin2.document.write(html);
   printWin2.document.close();
   setTimeout(function(){ printWin2.print(); printWin2.close(); }, 200);
      
}