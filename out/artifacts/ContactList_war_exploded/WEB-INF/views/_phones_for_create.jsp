<%--
  Created by IntelliJ IDEA.
  User: fogou
  Date: 09.04.2019
  Time: 10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Edit Remove HTML Table Row</title>
    <meta charset="windows-1252">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <style>
        .tab-2 input{display: block;margin-bottom: 10px}
        tr{transition:all .25s ease-in-out}
        tr:hover{background-color:#EEE;cursor: pointer}
    </style>

</head>

<table class="simple-little-table" cellspacing='0'  id="phoneTable" border="1" cellspacing="2" cellpadding="10" align="rigth">
    <tr>
        <td>Checkbox</td>
        <td>Phone number</td>
        <td>Description</td>
        <td>Comment</td>
    </tr>
</table>
<script>
    var rIndex,
        table = document.getElementById("table");
    /*** FUNCTION TO ADD ROW ***/
    function addSampleRow(id) {

        /*** We get the table object based on given id ***/
        var objTable = document.getElementById(id);

        /*** We insert the row by specifying the current rows length ***/
        var objRow = objTable.insertRow(objTable.rows.length);

        /*** We insert the first row cell ***/
        var objCell1 = objRow.insertCell(0);

        /*** We  insert a checkbox object ***/
        var objInputCheckBox = document.createElement("input");
        objInputCheckBox.type = "checkbox";
        objCell1.appendChild(objInputCheckBox);

        /*** We  insert the second row cell ***/
        var objCell2 = objRow.insertCell(1);

        var input1 = document.createElement("input");
        input1.type = "hidden";
        input1.name = "numbers";
        var number = document.getElementById("phone").value;
        input1.value = number;
        objCell2.innerHTML = number;
        objCell2.appendChild(input1);

        var objCell3 = objRow.insertCell(2);

        var input2 = document.createElement("input");
        input2.type = "hidden";
        input2.name = "descriptions";
        var description = document.getElementById("description").value;
        input2.value = description;
        objCell3.innerHTML = description;
        objCell3.appendChild(input2);

        var objCell4 = objRow.insertCell(3);

        var input3 = document.createElement("input");
        input3.type = "hidden";
        input3.name = "comments";
        var comment = document.getElementById("comment").value;
        input3.value = comment;
        objCell4.innerHTML = comment;
        objCell4.appendChild(input3);
    }

    /*** FUNCTION TO DELETE ROW ***/
    function removeSampleRow(id) {
        /***We get the table object based on given id ***/
        var objTable = document.getElementById(id);

        /*** Get the current row length ***/
        var iRow = objTable.rows.length;

        /*** Initial row counter ***/
        var counter = 0;

        /*** Performing a loop inside the table ***/
        if (objTable.rows.length > 1) {
            for (var i = 0; i < objTable.rows.length; i++) {

                /*** Get checkbox object ***/
                var chk = objTable.rows[i].cells[0].childNodes[0];
                if (chk.checked) {
                    /*** if checked we del ***/
                    objTable.deleteRow(i);
                    iRow--;
                    i--;
                    counter = counter + 1;
                }
            }

            /*** Alert user if there is now row is selected to be deleted ***/
            if (counter == 0) {
                alert("Please select the row that you want to delete.");
            }
        }else{
            /*** Alert user if there are no rows being added ***/
            alert("There are no rows being added");
        }
    }

    function selectedRowToInput()
    {

        for(var i = 1; i < table.rows.length; i++)
        {
            table.rows[i].onclick = function()
            {
                // get the seected row index
                rIndex = this.rowIndex;
                document.getElementById("fname").value = this.cells[0].innerHTML;
                document.getElementById("lname").value = this.cells[1].innerHTML;
                document.getElementById("age").value = this.cells[2].innerHTML;
            };
        }
    }
    selectedRowToInput();

    function editHtmlTbleSelectedRow()
    {
        var fname = document.getElementById("fname").value,
            lname = document.getElementById("lname").value,
            age = document.getElementById("age").value;
        if(!checkEmptyInput()){
            table.rows[rIndex].cells[0].innerHTML = fname;
            table.rows[rIndex].cells[1].innerHTML = lname;
            table.rows[rIndex].cells[2].innerHTML = age;
        }
    }
</script>

</body>
</html>
