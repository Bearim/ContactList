<%--
  Created by IntelliJ IDEA.
  User: fogou
  Date: 17.04.2019
  Time: 22:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="newPhotoModal" tabindex="-1" role="dialog" aria-labelledby="newPhotoModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="newPhotoModalLabel">Photo</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="input-group mb-3">
                    <div class="custom-file">
                        <input type="file" accept=".tiff,.jpeg,.bmp,.jpe,.jpg,.png,.gif" class="custom-file-input" name="newPhoto" id="newPhoto" onchange="toLable('newPhoto')">
                        <label class="custom-file-label" for="newPhoto" id="fileLable">Choose</label>
                    </div>
                </div>
                <div class="form-group">
                    <label for="newPhotoName" class="col-form-label">Description:</label>
                    <input type="text" class="form-control" name="newPhotoName" id="newPhotoName">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="addToInput()">Update Photo</button>
            </div>
        </div>
    </div>
</div>
<script>
    function toLable(id) {
        var objInput = document.getElementById(id);
        var lable = document.getElementById('fileLable');
        lable.innerHTML = objInput.files[0].name;
    }
</script>