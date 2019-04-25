<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kirill
  Date: 21.3.19
  Time: 16.14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Phone</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label for="phone" class="col-form-label">Number:</label>
                    <input type="text" class="form-control" name="number" onchange="validatePhone()" id="phone">
                </div>
                <div class="form-group">
                    <label for="description" class="col-form-label">Description:</label>
                    <select class="form-control" type="text" name="description" id="description">
                        <option>Mobile</option>
                        <option>Home</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="comment" class="col-form-label">Comment:</label>
                    <textarea name="comment" id="comment" class="form-control"></textarea>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal" >Close</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" id="addButton" onclick="addSampleRow('phoneTable')" disabled>Add phone</button>
            </div>
        </div>
    </div>
</div>
<script>
    function validatePhone() {
        var pattern = /^(\+375|80)(29|25|44|33)(\d{3})(\d{2})(\d{2})$/;
        var inputValue = document.getElementById('phone').value;
        var button = document.getElementById('addButton');
        var valid = pattern.test(inputValue);
        if (valid) button.disabled = false;
        else {
            button.disabled = true;
            alert("Wrong phone");
        }
    }
</script>
