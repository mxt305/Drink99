<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title"><i>#\${ index }</i> \${ title }</h3>
    <small>作者：\${ mem } 時間:\${ time }</small>
  </div>
  <div class="panel-body">
  <p>
  {{html content}}
  </p>
  {{if isAuth}}
  <div class="text-right">
 	 <button class="btn btn-default" type="button" id="btn_del\${ msgID }">刪除</button>
  </div>
  {{/if}}
  </div>
</div>