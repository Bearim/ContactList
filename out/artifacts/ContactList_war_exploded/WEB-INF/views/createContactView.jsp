<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kirill
  Date: 11.3.19
  Time: 0.16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Contact</title>
    <style>
        <%@include file='/WEB-INF/css/form_style.css' %>
        <%@include file='/WEB-INF/css/table_style.css' %>
    </style>
</head>
<body>

<jsp:include page="_header.jsp"></jsp:include>

<p style="color: red;">${errorString}</p>
<form class="contact_form" method="POST" action="${pageContext.request.contextPath}/createContact" name="contact_form" enctype="multipart/form-data">
    <img src="${pageContext.request.contextPath}/image?id=0" class="rounded float-left" alt="200x200" style="max-width:353px;max-height:353px;">
    <div class="container">
        <div class="row">
            <div class="col-md-4 order-md-2 mb-4"
                <jsp:include page="_phones_for_create.jsp"></jsp:include>
                <button type="button" class="btn btn-primary" onclick="removeDocument('phoneTable')">Remove Phones</button>
                <jsp:include page="_documentsForCreate.jsp"></jsp:include>
                <button type="button" class="btn btn-primary" onclick="removeDocument('documentTable')">Remove Documents</button>
            </div>
            <ul>
                <li>
                    <h2>Create contact</h2>
                    <span class="required_notification">* Denotes Required Field</span>
                </li>
                <li>
                    <label>Name:</label>
                    <input type="text" name="name" value="${contact.name}" placeholder="John" pattern="^[а-яА-ЯёЁa-zA-Z]+$"  required/>
                </li>
                <li>
                    <label>Surname:</label>
                        <input type="text" name="surname" value="${contact.surname}" placeholder="Cena" pattern="^[а-яА-ЯёЁa-zA-Z]+$" required/>
                </li>
                <li>
                    <label>Patrynomic:</label>
                    <input type="text" name="patrynomic" value="${contact.patrynomic}" placeholder="Addamson" pattern="^[а-яА-ЯёЁa-zA-Z]+$" />
                </li>
                <li>
                    <label>Birth Date:</label>
                    <input type="date" id="dt" onchange="mydate1();" max="2019-25-04" min="1980-04-04" hidden/>
                    <input type="text" id="ndt" name="birthDate" value="${contact.birthDate}" onclick="mydate();"  placeholder="yyyy-mm-dd"/>
                </li>
                <li>
                    <label>Photo:</label>
                    <div class="input-group mb-3">
                        <div class="custom-file" >
                            <input type="file" accept=".tiff,.jpeg,.bmp,.jpe,.jpg,.png,.gif" class="custom-file-input" name="file" id="fileInput" onchange="toLable('fileInput')">
                            <label class="custom-file-label" for="fileInput" id="fileLable">Choose</label>
                        </div>
                    </div>
                </li>
                <li>
                    <label>Gender:</label>
                    <select class="form-control" type="text" name="gender" value="${contact.gender}">
                        <option value="Male">Male</option>
                        <option value="Female">Female</option>
                    </select>
                </li>
                <li>
                    <label>Nationality:</label>
                    <input type="text" name="nationality" value="${contact.nationality}" placeholder="Belarus" pattern="^[а-яА-ЯёЁa-zA-Z]+$"/>
                </li>
                <li>
                    <label>Mariage status:</label>
                    <select class="form-control" type="text" name="mariageStatus">
                        <option value="Single">Single</option>
                        <option value="Married" >Married</option>
                    </select>
                </li>
                <li>
                    <label>Website:</label>
                    <input type="url" name="webSite" value="${contact.webSite}" placeholder="http://johndoe.com/" pattern="(http|https)://.+"/>
                    <span class="form_hint">Proper format "http://someaddress.com"</span>
                </li>
                <li>
                    <label>Email:</label>
                    <input type="email" name="eMail" value="${contact.Email}" placeholder="email"/>
                    <span class="form_hint">Proper format "name@something.com"</span>
                </li>
                <li>
                    <label>Place Of Work:</label>
                    <input type="text" name="placeOfWork" value="${contact.placeOfWork}" placeholder="iTechArt" pattern="^[а-яА-ЯёЁa-zA-Z0-9]+$"/>
                </li>
                <jsp:include page="_adressFields.jsp"></jsp:include>
                <li>
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">Add phone</button>
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#documentPopUp">Add document</button>
                </li>
                <li>
                    <button class="submit" type="submit">Create</button>
                    <a href="${pageContext.request.contextPath}/contactList">Cancel</a>
                </li>
            </ul>
        </div>
    </div>

</form>

<jsp:include page="_phonesPopUp.jsp"></jsp:include>
<jsp:include page="_footer.jsp"></jsp:include>
<script>
        function toLable(id) {
            var objInput = document.getElementById(id);
            var lable = document.getElementById('fileLable');
            lable.innerHTML = objInput.files[0].name;
        }
        function mydate()
        {
            document.getElementById("dt").hidden=false;
            document.getElementById("ndt").hidden=true;
        }
        function mydate1()
        {
            d=new Date(document.getElementById("dt").value);
            dt=d.getDate();
            mn=d.getMonth();
            mn++;
            yy=d.getFullYear();
            document.getElementById("ndt").value=yy+"-"+mn+"-"+dt;
            document.getElementById("ndt").hidden=false;
            document.getElementById("dt").hidden=true;
        }
</script>
</body>
</html>
