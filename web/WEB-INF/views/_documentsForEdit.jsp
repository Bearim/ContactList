<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<body>

<table class="simple-little-table" cellspacing='0'  id="documentTable" border="1" cellspacing="2" cellpadding="10" align="rigth">
    <tr>
        <td>Checkbox</td>
        <td>Document</td>
        <td>Comment</td>
    </tr>
    <c:forEach items="${documentList}" var="document" >
        <tr>
            <td><input type="checkbox"/> </td>
            <td>
                <a href="${pageContext.request.contextPath}/downloadAttachment?id=${document.contact_id}&fileName=${document.fileName}">${document.fileName}
                    <input type="hidden" name="documents" value="${document.fileName}">
                    <input type="hidden" name="documentPath" value="${document.filePath}">
                </a>
            </td>
            <td>${document.fileName}<input type="hidden" name="comm" value="${document.comment}"></td>
        </tr>
    </c:forEach>
</table>
<jsp:include page="_documentsPopUp.jsp"></jsp:include>
<script>
    /*** FUNCTION TO ADD ROW ***/
    function addDocument(id) {

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
        var input3 = document.createElement("input");
        input3.type = "hidden";
        input3.name = "documentPath"
        input1.type = "hidden";
        input1.name = "documents";
        var fileName = document.getElementById("fileName").value;
        var path = document.getElementById("document").value;
        input1.value = fileName;
        input3.value = path
        objCell2.innerHTML = fileName;
        objCell2.appendChild(input1);
        objCell2.appendChild(input3);

        var objCell3 = objRow.insertCell(2);

        var input2 = document.createElement("input");
        input2.type = "hidden";
        input2.name = "comms";
        var desc = document.getElementById("comm").value;
        input2.value = desc;
        objCell3.innerHTML = desc;
        objCell3.appendChild(input2);
    }

    /*** FUNCTION TO DELETE ROW ***/
    function removeDocument(id) {
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
</script>

</body>
</html>