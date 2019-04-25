<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kirill
  Date: 21.3.19
  Time: 16.14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="documentPopUp" tabindex="-1" role="dialog" aria-labelledby="documentPopUpLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="documentPopUplLabel">New message</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label for="fileName" class="col-form-label">File Name:</label>
                    <input type="text" class="form-control" name="fileName" id="fileName">
                </div>
                <div class="form-group">
                    <label for="document" class="col-form-label">Path:</label>
                    <input type="text" class="form-control" name="document" id="document">
                </div>
                <div class="form-group">
                    <label for="comm" class="col-form-label">Comment:</label>
                    <textarea name="comm" id="comm" class="form-control"></textarea>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="addDocument('documentTable')">Add document</button>
            </div>
        </div>
    </div>
</div>
