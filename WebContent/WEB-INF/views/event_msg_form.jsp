<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<form class="form-horizontal">
	<div class="form-group">
		<label for="f_title" class="col-sm-2 control-label">標題</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" name="title" id="f_title" maxlength="80" />
		</div>
	</div>
	<div class="form-group">
		<label for="f_content" class="col-sm-2 control-label">內容</label>
		<div class="col-sm-10">
			<textarea class="form-control" rows="3" name="content" id="f_content" required></textarea>
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<button type="button" class="btn btn-default" id="btn_submit">送出</button>
			<button type="button" class="btn btn-default" id="btn_msgcln">清除</button>
		</div>
	</div>
</form>
