	  var count = "1";
	  function addRow(in_tbl_name)
	  {
		var tbody = document.getElementById(in_tbl_name).getElementsByTagName("TBODY")[0];
		// create row
		var row = document.createElement("TR");
		// create table cell 1
		var td1 = document.createElement("TD")
		var strHtml1 = "<select id=\"inputSexo\" class=\"form-control form-control-sm\" th:field=\"*{Sexo}\"><option th:value=\"Mixto\">Mixto</option><option th:value=\"Femenino\">Femenino</option><option th:value=\"Masculino\">Masculino</option></select>";
		td1.innerHTML = strHtml1.replace(/!count!/g,count);
		// create table cell 2
		var td2 = document.createElement("TD")
		var strHtml2 = "<select id=\"inputEdad\" class=\"form-control form-control-sm\" th:field=\"*{Referencia}\"><option th:value=\"Meses\">Meses</option><optionth:value=\"Años\">Años</option></select>";
		td2.innerHTML = strHtml2.replace(/!count!/g,count);
		// create table cell 3
		var td3 = document.createElement("TD")
		var strHtml3 = "<input type=\"text\" class=\"form-control form-control-sm\" SIZE=\"1\" MAXLENGTH=\"3\" th:field=\"*{EdadInferior}\">";
		td3.innerHTML = strHtml3.replace(/!count!/g,count);
		// create table cell 4
		var td4 = document.createElement("TD")
		var strHtml4 = "<input type=\"text\" class=\"form-control form-control-sm\" SIZE=\"1\" MAXLENGTH=\"3\" th:field=\"*{EdadSuperior}\">";
		td4.innerHTML = strHtml4.replace(/!count!/g,count);
		// create table cell 5
		var td5 = document.createElement("TD")
		var strHtml5 = "<input type=\"text\" class=\"form-control form-control-sm\" SIZE=\"1\" th:field=\"*{P1}\">";
		td5.innerHTML = strHtml5.replace(/!count!/g,count);
		// create table cell 6
		var td6 = document.createElement("TD")
		var strHtml6 = "<input type=\"text\" class=\"form-control form-control-sm\" SIZE=\"1\" th:field=\"*{P2}\">";
		td6.innerHTML = strHtml6.replace(/!count!/g,count);
		// create table cell 7
		var td7 = document.createElement("TD")
		var strHtml7 = "<input type=\"text\" class=\"form-control form-control-sm\" SIZE=\"1\" th:field=\"*{P3}\">";
		td7.innerHTML = strHtml7.replace(/!count!/g,count);
		// create table cell 8
		var td8 = document.createElement("TD")
		var strHtml8 = "<input type=\"text\" class=\"form-control form-control-sm\" SIZE=\"1\" th:field=\"*{P4}\">";
		td8.innerHTML = strHtml8.replace(/!count!/g,count);
		// create table cell 9
		var td9 = document.createElement("TD")
		var strHtml9 = "<input type=\"text\" class=\"form-control form-control-sm\" SIZE=\"1\" th:field=\"*{P5}\">";
		td9.innerHTML = strHtml9.replace(/!count!/g,count);
		// create table cell 10
		var td10 = document.createElement("TD")
		var strHtml10 = "<input type=\"text\" class=\"form-control form-control-sm\" SIZE=\"1\" th:field=\"*{P6}\">";
		td10.innerHTML = strHtml10.replace(/!count!/g,count);
		// create table cell 11
		var td11 = document.createElement("TD")
		var strHtml11 = "<input type=\"text\" class=\"form-control form-control-sm\" SIZE=\"1\" th:field=\"*{P7}\">";
		td11.innerHTML = strHtml11.replace(/!count!/g,count);
		// create table cell 12
		var td12 = document.createElement("TD")
		var strHtml12 = "<input type=\"text\" class=\"form-control form-control-sm\" SIZE=\"1\" th:field=\"*{Cualitativo}\">";
		td12.innerHTML = strHtml12.replace(/!count!/g,count);
		// create table cell 13
		var td13 = document.createElement("TD")
		var strHtml13 = "<input type=\"text\" class=\"form-control form-control-sm\" SIZE=\"1\" th:field=\"*{Variantes}\">";
		td13.innerHTML = strHtml13.replace(/!count!/g,count);
		// create table cell 14
		var td14 = document.createElement("TD")
		var strHtml14 = "<a class=\"btn btn-danger btn-sm\" onClick=\"delRow()\"><imgsrc=\"https://img.icons8.com/officel/20/000000/delete-row.png\">Borrar</a>";
		td14.innerHTML = strHtml14.replace(/!count!/g,count);
		// append data to row
		row.appendChild(td1);
		row.appendChild(td2);
		row.appendChild(td3);
		row.appendChild(td4);
		row.appendChild(td5);
		row.appendChild(td6);
		row.appendChild(td7);
		row.appendChild(td8);
		row.appendChild(td9);
		row.appendChild(td10);
		row.appendChild(td11);
		row.appendChild(td12);
		row.appendChild(td13);
		row.appendChild(td14);
		// add to count variable
		count = parseInt(count) + 1;
		// append row to table
		tbody.appendChild(row);
	  }
	  function delRow()
	  {
		var current = window.event.srcElement;
		//here we will delete the line
		while ( (current = current.parentElement)  && current.tagName !="TR");
			 current.parentElement.removeChild(current);
	  }